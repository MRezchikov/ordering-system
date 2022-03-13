package com.mr.order.orderingitems.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mr.orderingsystemapp.orderingitems.controller.OrderingItemsController;
import com.mr.orderingsystemapp.orderingitems.entity.OrderingItems;
import com.mr.orderingsystemapp.orderingitems.service.OrderingItemsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class OrderingItemsControllerTest {

    private MockMvc mockMvc;

    @Mock
    private OrderingItemsService orderingItemsService;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new OrderingItemsController(orderingItemsService))
                .build();
    }

    @Test
    void shouldReturnOrderingItemsById() throws Exception {

        OrderingItems expectedItem = new OrderingItems(
                1L,
                1L,
                "ball",
                10,
                20.0
        );

        Gson gson = new GsonBuilder().create();

        List<OrderingItems> expectedList = List.of(expectedItem);
        given(orderingItemsService.findOrderingItemsByOrderingId(1L)).willReturn(expectedList);

        mockMvc.perform(get("/api/item/orderingitem/{orderingId}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(gson.toJson(expectedList)));
    }
}