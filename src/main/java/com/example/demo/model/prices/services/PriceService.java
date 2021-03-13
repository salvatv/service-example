package com.example.demo.model.prices.services;

import com.example.demo.ws.prices.dto.PriceBO;
import com.example.demo.ws.prices.dto.PriceDTO;
import com.example.demo.ws.prices.dto.PriceFilterDTO;

import java.util.Collection;

/**
 * The interface Price service.
 */
public interface PriceService {
    /**
     * Find all collection.
     *
     * @return the collection
     */
    Collection<PriceDTO> findAll();

    /**
     * Find by id price dto.
     *
     * @param id the id
     * @return the price dto
     */
    PriceDTO findById(Integer id);

    /**
     * Save price dto.
     *
     * @param price the price
     * @return the price dto
     */
    PriceDTO save(PriceDTO price);

    /**
     * Update price dto.
     *
     * @param id    the id
     * @param price the price
     * @return the price dto
     */
    PriceDTO update(Integer id, PriceDTO price);

    /**
     * Delete price dto.
     *
     * @param id the id
     * @return the price dto
     */
    PriceDTO delete(Integer id);

    /**
     * Find by collection.
     *
     * @param filter the filter
     * @return the collection
     */
    Collection<PriceBO> findBy(PriceFilterDTO filter);
}
