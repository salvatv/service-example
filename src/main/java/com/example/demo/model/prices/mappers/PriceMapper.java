package com.example.demo.model.prices.mappers;

import com.example.demo.model.prices.repositories.Price;
import com.example.demo.ws.prices.dto.PriceDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface PriceMapper {

    Collection<PriceDTO> asDTOs(List<Price> collection);

    @Mapping(source = "brand.id", target = "brandId")
    @Mapping(source = "product.id", target = "productId")
    PriceDTO asDTO(Price brand);

    @Mapping(source = "brandId", target = "brand.id")
    @Mapping(source = "productId", target = "product.id")
    Price asEntity(PriceDTO brand);

}
