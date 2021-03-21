/*
 * Created by Salvador Torres Velasco
 * 2021
 */

package com.example.demo.model.prices.services;

import com.example.demo.model.prices.repositories.PriceCustomRepository;
import com.example.demo.model.prices.repositories.Prices;
import com.example.demo.ws.prices.dto.PriceBO;
import com.example.demo.ws.prices.dto.PriceFilterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

@Service
public class PriceServiceImpl implements PriceService {

    @Autowired
    PriceCustomRepository priceCustomRepository;

    @Override
    public Collection<PriceBO> findByFilter(final PriceFilterDTO filter) {
        final Set<PriceBO> result = new HashSet<>();
        final Collection<Prices> prices = this.priceCustomRepository.findByFilter(filter);

        prices.stream()
                .max(Comparator.comparing(Prices::getPriority))
                .ifPresent(priceMaxPriority -> result.add(getPriceBO(priceMaxPriority)));

        return result;
    }

    private PriceBO getPriceBO(Prices priceMaxPriority) {
        return PriceBO.builder()
                .productId(priceMaxPriority.getProductId())
                .brandId(priceMaxPriority.getBrandId())
                .startDate(priceMaxPriority.getStartDate().toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDateTime())
                .endDate(priceMaxPriority.getEndDate().toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDateTime())
                .finalPrice(priceMaxPriority.getPrice())
                .priority(priceMaxPriority.getPriority())
                .build();
    }

}
