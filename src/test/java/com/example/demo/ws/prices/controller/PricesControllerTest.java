/*
 * Created by Salvador Torres Velasco
 * 2021
 */

package com.example.demo.ws.prices.controller;

import com.example.demo.model.prices.services.PriceService;
import com.example.demo.ws.prices.dto.PriceBO;
import com.example.demo.ws.prices.dto.PriceFilterDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PricesControllerTest {

    private MockMvc mockMvc;

    @MockBean
    private PriceService priceService;

    @Autowired
    private WebApplicationContext context;

    @Captor
    private ArgumentCaptor<PriceFilterDTO> priceFilterCaptor;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void getBrandsBadRequestTest() throws Exception {
        // act & assert
        this.mockMvc.perform(get("/prices/filter")).andDo(print()).andExpect(status().isBadRequest());
    }

    @Test
    public void getBrandsNoContentTest() throws Exception {
        // arrange
        when(priceService.findBy(priceFilterCaptor.capture())).thenReturn(new ArrayList<>());

        // act & assert
        this.mockMvc.perform(get("/prices/filter?date=2020-06-15 01-00-00&productIds=&brandIds")).andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void getBrandsTest() throws Exception {
        // arrange
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH-mm-ss"));
        Set<PriceBO> prices = Arrays.stream(objectMapper.readValue(new File("src/test/resources/json_prices.json"), PriceBO[].class))
                .collect(Collectors.toSet());

        when(priceService.findBy(priceFilterCaptor.capture())).thenReturn(prices);

        // act & assert
        this.mockMvc.perform(get("/prices/filter?date=2020-06-15 01:00:00&productIds=&brandIds")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(prices)));
    }


}