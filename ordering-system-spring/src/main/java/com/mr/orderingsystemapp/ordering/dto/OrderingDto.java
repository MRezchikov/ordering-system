package com.mr.orderingsystemapp.ordering.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderingDto {

    private String username;
    private Boolean done;
    private LocalDateTime updatedAt;
}
