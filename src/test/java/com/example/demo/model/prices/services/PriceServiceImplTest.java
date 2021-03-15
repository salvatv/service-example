package com.example.demo.model.prices.services;

import com.example.demo.model.prices.repositories.Price;
import com.example.demo.model.prices.repositories.PriceCustomRepository;
import com.example.demo.ws.prices.dto.PriceBO;
import com.example.demo.ws.prices.dto.PriceFilterDTO;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.stream.Collectors;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PriceServiceImplTest {

    @InjectMocks
    private PriceServiceImpl priceService;

    @Mock
    private PriceCustomRepository priceCustomRepository;

    @Captor
    private ArgumentCaptor<PriceFilterDTO> priceFilterCaptor;

    @Test
    @DisplayName("Test findBy Success")
    public void findByTest() {
        // arrange
        final Price mockedPrice = getMockedPrice();
        final Collection<Price> prices = new HashSet<>(Collections.singleton(mockedPrice));
        when(this.priceCustomRepository.findByFilter(priceFilterCaptor.capture())).thenReturn(prices);

        // act
        Collection<PriceBO> actual = this.priceService.findBy(priceFilterCaptor.capture());

        // assert
        Assertions.assertEquals(actual, getBoFromPrices(prices));
    }

    private Price getMockedPrice() {
        PodamFactory podamFactory = new PodamFactoryImpl();
        podamFactory.manufacturePojo(Price.class);
        return podamFactory.manufacturePojo(Price.class);
    }

    private Collection<PriceBO> getBoFromPrices(Collection<Price> prices) {
        return prices.stream().map(p -> PriceBO.builder()
                .productId(p.getProduct().getId())
                .brandId(p.getBrand().getId())
                .startDate(p.getStartDate())
                .endDate(p.getEndDate())
                .finalPrice(p.getCost())
                .priority(p.getPriority())
                .build()).collect(Collectors.toSet());

    }

}