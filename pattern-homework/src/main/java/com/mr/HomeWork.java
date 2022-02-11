package com.mr;

import com.mr.handler.ComplexProcessor;
import com.mr.listener.ListenerPrinterConsole;
import com.mr.listener.homework.HistoryListener;
import com.mr.model.Message;
import com.mr.model.ObjectForMessage;
import com.mr.processor.ProcessorEvenSecondException;
import com.mr.processor.ProcessorFieldReplacer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class HomeWork {

    private static final Logger LOGGER = LoggerFactory.getLogger(HomeWork.class);

    /*
     Реализовать to do:
       1. Добавить поля field11 - field13 (для field13 используйте класс ObjectForMessage)
       2. Сделать процессор, который поменяет местами значения field11 и field12
       3. Сделать процессор, который будет выбрасывать исключение в четную секунду (сделайте тест с гарантированным результатом)
             Секунда должна определяьться во время выполнения.
             Тест - важная часть задания
             Обязательно посмотрите пример к паттерну Мементо!
       4. Сделать Listener для ведения истории (подумайте, как сделать, чтобы сообщения не портились)
          Уже есть заготовка - класс HistoryListener, надо сделать его реализацию
          Для него уже есть тест, убедитесь, что тест проходит
     */

    public static void main(String[] args) {
        /*
           по аналогии с Demo.class
           из элеменов "to do" создать new ComplexProcessor и обработать сообщение
         */

        var processors = List.of(
                new ProcessorFieldReplacer(),
                new ProcessorEvenSecondException(LocalDateTime::now)
        );

        var complexProcessor = new ComplexProcessor(processors, ex ->
                LOGGER.info("An exception has occurred ", ex)
        );
        var listenerPrinter = new ListenerPrinterConsole();

        var historyListener = new HistoryListener();
        complexProcessor.addListener(historyListener);
        complexProcessor.addListener(listenerPrinter);

        List<String> strings = new ArrayList<>();
        strings.add("string1");
        strings.add("string2");
        strings.add("string3");

        ObjectForMessage objectForMessage = new ObjectForMessage();
        objectForMessage.setData(strings);

        var message = new Message.Builder(1L)
                .field1("field1")
                .field2("field2")
                .field3("field3")
                .field6("field6")
                .field10("field10")
                .field11("field11")
                .field12("field12")
                .field13(objectForMessage)
                .build();

        var result = complexProcessor.handle(message);
        LOGGER.info("result: {} ", result);

        List<String> newStrings = new ArrayList<>();
        objectForMessage.setData(newStrings);

        message.toBuilder()
                .field13(objectForMessage)
                .build();

        result = complexProcessor.handle(message);

        LOGGER.info("result2: {} ", result);

        complexProcessor.removeListener(listenerPrinter);
    }
}
