/*
 * Created by Salvador Torres Velasco
 * 2021
 */

package com.example.demo.model.prices.services;

import com.example.demo.model.prices.repositories.PriceCustomRepository;
import com.example.demo.model.prices.repositories.Prices;
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

import java.time.ZoneId;
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
        final Prices mockedPrice = getMockedPrice();
        final Collection<Prices> prices = new HashSet<>(Collections.singleton(mockedPrice));
        when(this.priceCustomRepository.findByFilter(priceFilterCaptor.capture())).thenReturn(prices);

        // act
        Collection<PriceBO> actual = this.priceService.findByFilter(priceFilterCaptor.capture());

        // assert
        Assertions.assertEquals(actual, getBoFromPrices(prices));
    }

    private Prices getMockedPrice() {
        PodamFactory podamFactory = new PodamFactoryImpl();
        podamFactory.manufacturePojo(Prices.class);
        return podamFactory.manufacturePojo(Prices.class);
    }

    private Collection<PriceBO> getBoFromPrices(Collection<Prices> prices) {
        return prices.stream().map(p -> PriceBO.builder()
                .productId(p.getProductId())
                .brandId(p.getBrandId())
                .startDate(p.getStartDate().toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDateTime())
                .endDate(p.getEndDate().toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDateTime())
                .finalPrice(p.getPrice())
                .priority(p.getPriority())
                .build())
                .collect(Collectors.toSet());

    }

}