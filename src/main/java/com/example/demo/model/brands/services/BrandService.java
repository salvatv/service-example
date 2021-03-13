package com.example.demo.model.brands.services;

import com.example.demo.ws.brands.dto.BrandDTO;

import java.util.Collection;

/**
 * The interface Brand service.
 */
public interface BrandService {

    /**
     * Find all collection.
     *
     * @return the collection
     */
    Collection<BrandDTO> findAll();

    /**
     * Find by id brand dto.
     *
     * @param id the id
     * @return the brand dto
     */
    BrandDTO findById(Integer id);

    /**
     * Find by name brand dto.
     *
     * @param name the name
     * @return the brand dto
     */
    BrandDTO findByName(String name);

    /**
     * Save brand dto.
     *
     * @param brand the brand
     * @return the brand dto
     */
    BrandDTO save(BrandDTO brand);

    /**
     * Update brand dto.
     *
     * @param id    the id
     * @param brand the brand
     * @return the brand dto
     */
    BrandDTO update(Integer id, BrandDTO brand);

    /**
     * Delete brand dto.
     *
     * @param id the id
     * @return the brand dto
     */
    BrandDTO delete(Integer id);

}
