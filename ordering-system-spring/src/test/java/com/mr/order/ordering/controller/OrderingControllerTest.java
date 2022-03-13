package com.mr.order.ordering.controller;

import com.mr.orderingsystemapp.ordering.controller.OrderingController;
import com.mr.orderingsystemapp.ordering.entity.Ordering;
import com.mr.orderingsystemapp.ordering.service.OrderingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class OrderingControllerTest {

    private MockMvc mockMvc;

    @Mock
    private OrderingService orderingService;

    @Autowired
    private ConversionService conversionService;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new OrderingController(conversionService, orderingService))
                .build();
    }

    @Test
    void shouldReturnOrderingById() throws Exception {

        Ordering expectedOrdering = new Ordering(
                1L,
                "Petr",
                false
        );

        given(orderingService.findById(1L)).willReturn(expectedOrdering);

        mockMvc.perform(get("/api/ordering/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.username").value("Petr"))
                .andExpect(jsonPath("$.done").value(false));
    }
}
