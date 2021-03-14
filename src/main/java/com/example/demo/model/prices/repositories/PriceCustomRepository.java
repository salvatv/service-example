package com.example.demo.model.prices.repositories;

import com.example.demo.ws.prices.dto.PriceFilterDTO;

import java.util.Collection;

public interface PriceCustomRepository {

    Collection<Price> findByFilter(PriceFilterDTO filter);

}
