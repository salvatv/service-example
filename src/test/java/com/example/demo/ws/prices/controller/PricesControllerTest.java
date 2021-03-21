/*
 * Created by Salvador Torres Velasco
 * 2021
 */

package com.example.demo.ws.prices.controller;

import com.example.demo.model.prices.services.PriceService;
import com.example.demo.ws.prices.dto.PriceBO;
import com.example.demo.ws.prices.dto.PriceFilterDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
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
        this.mockMvc.perform(get("/prices")).andDo(print()).andExpect(status().isBadRequest());
    }

    @Test
    public void getBrandsNoContentTest() throws Exception {
        // arrange
        when(priceService.findByFilter(priceFilterCaptor.capture())).thenReturn(new ArrayList<>());

        // act & assert
        this.mockMvc.perform(get("/prices?date=2020-06-14 10:00:00&productId=9&brandId=1")).andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("Test 1")
    public void getPricesTest() throws Exception {
        testCase("src/test/resources/json_prices_1.json", "/prices?date=2020-06-14 10:00:00&productId=1&brandId=1");
    }

    private void testCase(String file, String url) throws Exception {
        // arrange
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH-mm-ss"));
        Set<PriceBO> prices = Arrays.stream(objectMapper.readValue(new File(file), PriceBO[].class))
                .collect(Collectors.toSet());

        when(priceService.findByFilter(priceFilterCaptor.capture())).thenReturn(prices);

        // act & assert
        this.mockMvc.perform(get(url)).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(prices)));
    }

    @Test
    @DisplayName("Test 2")
    public void getPricesSecondTest() throws Exception {
        // arrange
        testCase("src/test/resources/json_prices_2.json", "/prices?date=2020-06-14 16:00:00&productId=1&brandId=1");
    }

    @Test
    @DisplayName("Test 3")
    public void getPricesThirdTest() throws Exception {
        // arrange
        testCase("src/test/resources/json_prices_3.json", "/prices?date=2020-06-14 21:00:00&productId=1&brandId=1");
    }

    @Test
    @DisplayName("Test 4")
    public void getPricesFourthTest() throws Exception {
        // arrange
        testCase("src/test/resources/json_prices_5.json", "/prices?date=2020-06-15 10:00:00&productId=1&brandId=1");
    }

    @Test
    @DisplayName("Test 5")
    public void getPricesFifthTest() throws Exception {
        // arrange
        testCase("src/test/resources/json_prices_5.json", "/prices?date=2020-06-16 21:00:00&productId=1&brandId=1");
    }


}