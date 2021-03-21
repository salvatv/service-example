/*
 * Created by Salvador Torres Velasco
 * 2021
 */

package com.example.demo.model.prices.repositories;

import com.example.demo.ws.prices.dto.PriceFilterDTO;

import java.util.Collection;

/**
 * The interface Price custom repository.
 */
public interface PriceCustomRepository {

    /**
     * Find by filter collection.
     *
     * @param filter the filter
     * @return the collection
     */
    Collection<Prices> findByFilter(PriceFilterDTO filter);

}
