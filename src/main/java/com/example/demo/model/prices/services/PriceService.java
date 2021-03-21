/*
 * Created by Salvador Torres Velasco
 * 2021
 */

package com.example.demo.model.prices.services;

import com.example.demo.ws.prices.dto.PriceBO;
import com.example.demo.ws.prices.dto.PriceFilterDTO;

import java.util.Collection;

/**
 * The interface Price service.
 */
public interface PriceService {

    /**
     * Find by collection.
     *
     * @param filter the price filter
     * @return the prices collection
     */
    Collection<PriceBO> findByFilter(PriceFilterDTO filter);
}
