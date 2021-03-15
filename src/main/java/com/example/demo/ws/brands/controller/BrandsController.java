/*
 * Created by Salvador Torres Velasco
 * 2021
 */

package com.example.demo.ws.brands.controller;

import com.example.demo.model.brands.services.BrandService;
import com.example.demo.ws.brands.dto.BrandDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping(path = "/brands")
public class BrandsController {

    @Autowired
    BrandService brandService;

    @GetMapping
    public ResponseEntity<Collection<BrandDTO>> findAll() {
        final Collection<BrandDTO> brands = this.brandService.findAll();

        return brands.isEmpty() ? new ResponseEntity<>(HttpStatus.NO_CONTENT) :
                new ResponseEntity<>(brands, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BrandDTO> getBrandById(@PathVariable("id") final Integer id) {
        final Optional<BrandDTO> brand = Optional.ofNullable(this.brandService.findById(id));
        return brand.map(brandDTO -> new ResponseEntity<>(brandDTO, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<BrandDTO> getBrandByName(@PathVariable("name") final String name) {
        final Optional<BrandDTO> brand = Optional.ofNullable(this.brandService.findByName(name));
        return brand.map(brandDTO -> new ResponseEntity<>(brandDTO, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<BrandDTO> createBrand(@RequestBody final BrandDTO brand) {
        final Optional<BrandDTO> brandCreated = Optional.ofNullable(this.brandService.save(brand));
        return brandCreated.map(brandDTO -> new ResponseEntity<>(brandDTO, HttpStatus.CREATED))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BrandDTO> updateBrand(@PathVariable("id") final Integer id,
                                                @RequestBody final BrandDTO brand) {
        final Optional<BrandDTO> brandUpdated = Optional.ofNullable(this.brandService.update(id, brand));
        return brandUpdated.map(brandDTO -> new ResponseEntity<>(brandDTO, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteBrand(@PathVariable("id") final Integer id) {
        try {
            final Optional<BrandDTO> brandDeleted = Optional.ofNullable(this.brandService.delete(id));
            return brandDeleted.isPresent() ? new ResponseEntity<>(HttpStatus.NO_CONTENT) :
                    new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
