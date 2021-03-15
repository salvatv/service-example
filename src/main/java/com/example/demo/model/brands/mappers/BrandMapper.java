/*
 * Created by Salvador Torres Velasco
 * 2021
 */

package com.example.demo.model.brands.mappers;

import com.example.demo.model.brands.repositories.Brand;
import com.example.demo.ws.brands.dto.BrandDTO;
import org.mapstruct.Mapper;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface BrandMapper {

    Collection<BrandDTO> asDTOs(List<Brand> collection);

    BrandDTO asDTO(Brand brand);

    Brand asEntity(BrandDTO brand);

}
