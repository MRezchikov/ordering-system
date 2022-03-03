package com.mr.orderingsystemapp.orderingitems.dto;

import lombok.Data;

@Data
public class OrderingItemCountDto {

    private Long id;
    private Long orderingId;
    private Integer count;
}
