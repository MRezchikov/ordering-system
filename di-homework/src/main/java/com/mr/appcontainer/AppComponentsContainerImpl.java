package com.mr.appcontainer;

import com.mr.appcontainer.api.AppComponent;
import com.mr.appcontainer.api.AppComponentsContainer;
import com.mr.appcontainer.api.AppComponentsContainerConfig;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class AppComponentsContainerImpl implements AppComponentsContainer {

    private final List<Object> appComponents = new ArrayList<>();
    private final Map<String, Object> appComponentsByName = new HashMap<>();

    public AppComponentsContainerImpl(String packageName) {
        List<Class<?>> allClassesByPackageName = findAllClassesByPackageName(packageName);
        processConfigsFromList(allClassesByPackageName);
    }

    public AppComponentsContainerImpl(Class<?> initialConfigClass, Class<?>... initialConfigs) {
        processConfig(initialConfigClass);
        processConfigs(initialConfigs);
    }

    private void processConfig(Class<?> configClass) {
        checkConfigClass(configClass);
        prepareComponents(configClass);
    }

    private void processConfigs(Class<?>[] initialConfigs) {
        for (Class<?> initialConfig : initialConfigs) {
            processConfig(initialConfig);
        }
    }

    private void processConfigsFromList(List<Class<?>> allClassesByPackageName) {
        for (Class<?> initialConfig : allClassesByPackageName) {
            processConfig(initialConfig);
        }
    }

    @Override
    public <C> C getAppComponent(Class<C> componentClass) {

        for (Object object : appComponents) {
            if (componentClass.isAssignableFrom(object.getClass())) {
                return (C) object;
            }
        }

        throw new AppComponentsContainerException("Error occurred while getting app component by componentClass");
    }

    @Override
    public <C> C getAppComponent(String componentName) {

        Object object = appComponentsByName.get(componentName);
        if (Objects.nonNull(object)) {
            return (C) object;
        }
        throw new AppComponentsContainerException("Error occurred while getting app component by componentName");
    }

    private void checkConfigClass(Class<?> configClass) {
        if (!configClass.isAnnotationPresent(AppComponentsContainerConfig.class)) {
            throw new IllegalArgumentException(String.format("Given class is not config %s", configClass.getName()));
        }
    }

    private void prepareComponents(Class<?> configClass) {

        Method[] methods = configClass.getMethods();

        List<Method> sortedMethods = Arrays.stream(methods)
                .filter(method -> method.getAnnotation(AppComponent.class) != null)
                .sorted(Comparator.comparingInt(method -> {
                    AppComponent annotation = method.getAnnotation(AppComponent.class);
                    return annotation.order();
                }))
                .collect(Collectors.toList());

        addComponentsToStorages(configClass, sortedMethods);
    }

    private void addComponentsToStorages(Class<?> configClass, List<Method> sortedMethods) {
        try {
            Object config = configClass.getConstructor().newInstance();
            for (Method method : sortedMethods) {
                Object component = getPreparedComponent(config, method);
                appComponents.add(component);
                appComponentsByName.put(method.getAnnotation(AppComponent.class).name(), component);
            }
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new AppComponentsContainerException(e.getMessage(), e);
        }
    }

    private Object getPreparedComponent(Object config, Method method) throws IllegalAccessException, InvocationTargetException {

        Class<?>[] parameterTypes = method.getParameterTypes();
        Object[] methodArgs = new Object[parameterTypes.length];

        for (int j = 0; j < parameterTypes.length; j++) {
            methodArgs[j] = getAppComponent(parameterTypes[j]);
        }

        return method.invoke(config, methodArgs);
    }

    private List<Class<?>> findAllClassesByPackageName(String packageName) {
        Reflections reflections = new Reflections(packageName);
        Set<Class<?>> configClasses = reflections.getTypesAnnotatedWith(AppComponentsContainerConfig.class, true);
        return new ArrayList<>(configClasses);
    }
}
