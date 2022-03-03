package com.mr.orderingsystemapp.ordering.converter;

import com.mr.orderingsystemapp.ordering.entity.Ordering;
import com.mr.orderingsystemapp.ordering.dto.OrderingDto;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderingDtoConverter implements Converter<OrderingDto, Ordering> {

    private final ModelMapper modelMapper;

    @Override
    public Ordering convert(OrderingDto orderingDto) {
        return modelMapper.map(orderingDto, Ordering.class);
    }
}
