/*
 * Created by Salvador Torres Velasco
 * 2021
 */

package com.example.demo.ws.prices.controller;

import com.example.demo.model.prices.services.PriceService;
import com.example.demo.ws.prices.dto.PriceBO;
import com.example.demo.ws.prices.dto.PriceFilterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping(path = "/prices")
public class PricesController {

    @Autowired
    PriceService priceService;

    @GetMapping
    public ResponseEntity<Collection<PriceBO>> getPricesByFilter(@Valid final PriceFilterDTO filter) {
        final Collection<PriceBO> prices = this.priceService.findByFilter(filter);

        return prices.isEmpty() ? new ResponseEntity<>(HttpStatus.NO_CONTENT) :
                new ResponseEntity<>(prices, HttpStatus.OK);
    }

}
