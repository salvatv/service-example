/*
 * Created by Salvador Torres Velasco
 * 2021
 */

package com.example.demo.model.prices.services;

import com.example.demo.model.prices.repositories.Price;
import com.example.demo.model.prices.repositories.PriceCustomRepository;
import com.example.demo.ws.prices.dto.PriceBO;
import com.example.demo.ws.prices.dto.PriceDTO;
import com.example.demo.ws.prices.dto.PriceFilterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static java.util.stream.Collectors.groupingBy;

@Service
public class PriceServiceImpl implements PriceService {

    @Autowired
    PriceCustomRepository priceCustomRepository;

    @Override
    public Collection<PriceBO> findBy(final PriceFilterDTO filter) {
        final Set<PriceBO> result = new HashSet<>();
        final Collection<Price> prices = this.priceCustomRepository.findByFilter(filter);

        Map<PriceDTO, List<Price>> pricesByPriority = prices.stream()
                .collect(groupingBy(price -> PriceDTO.builder()
                        .brandId(price.getBrand().getId())
                        .productId(price.getProduct().getId())
                        .startDate(price.getStartDate())
                        .endDate(price.getEndDate())
                        .cost(price.getCost())
                        .curr(price.getCurr())
                        .build()));

        pricesByPriority.forEach((priceDTO, pricesPriority) ->
                pricesPriority.stream()
                        .max(Comparator.comparing(Price::getPriority)).ifPresent(priceMaxPriority -> result.add(PriceBO.builder()
                        .productId(priceMaxPriority.getProduct().getId())
                        .brandId(priceMaxPriority.getBrand().getId())
                        .startDate(priceMaxPriority.getStartDate())
                        .endDate(priceMaxPriority.getEndDate())
                        .finalPrice(priceMaxPriority.getCost())
                        .priority(priceMaxPriority.getPriority())
                        .build()))
        );

        return result;
    }


}
