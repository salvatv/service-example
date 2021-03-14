package com.example.demo.ws.prices.controller;

import com.example.demo.model.prices.services.PriceService;
import com.example.demo.ws.prices.dto.PriceBO;
import com.example.demo.ws.prices.dto.PriceDTO;
import com.example.demo.ws.prices.dto.PriceFilterDTO;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping(path = "/prices")
public class PricesController {

    @Autowired
    PriceService priceService;

    @GetMapping("/{id}")
    public ResponseEntity<PriceDTO> getPriceById(@PathVariable("id") final Integer id) {
        final Optional<PriceDTO> price = Optional.ofNullable(this.priceService.findById(id));
        return price.map(priceDTO -> new ResponseEntity<>(priceDTO, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<PriceDTO> createPrice(@RequestBody final PriceDTO price) {
        final Optional<PriceDTO> priceCreated = Optional.ofNullable(this.priceService.save(price));
        return priceCreated.map(priceDto -> new ResponseEntity<>(priceDto, HttpStatus.CREATED))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PriceDTO> updatePrice(@PathVariable("id") final Integer id,
                                                @RequestBody final PriceDTO price) {
        final Optional<PriceDTO> priceUpdated = Optional.ofNullable(this.priceService.update(id, price));
        return priceUpdated.map(priceDTO -> new ResponseEntity<>(priceDTO, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deletePrice(@PathVariable("id") final Integer id) {
        try {
            final Optional<PriceDTO> priceDeleted = Optional.ofNullable(this.priceService.delete(id));
            return priceDeleted.isPresent() ? new ResponseEntity<>(HttpStatus.NO_CONTENT) :
                    new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/filter")
    public ResponseEntity<Collection<PriceBO>> getPricesByFilter(@NotNull final PriceFilterDTO filter) {
      //  final PriceFilterDTO filter = PriceFilterDTO.builder().date(null).brandIds(brandIds).productIds(productIds).build();
        final Collection<PriceBO> prices = this.priceService.findBy(filter);

        return prices.isEmpty() ? new ResponseEntity<>(HttpStatus.NO_CONTENT) :
                new ResponseEntity<>(prices, HttpStatus.OK);
    }


}
