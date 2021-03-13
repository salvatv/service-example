package com.example.demo.model.brands.services;

import com.example.demo.model.brands.mappers.BrandMapper;
import com.example.demo.model.brands.repositories.BrandRepository;
import com.example.demo.ws.brands.dto.BrandDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    BrandRepository brandRepository;

    @Autowired
    BrandMapper brandMapper;

    @Override
    public Collection<BrandDTO> findAll() {
        return this.brandMapper.asDTOs(this.brandRepository.findAll());
    }

    @Override
    public BrandDTO findById(final Integer id) {
        return brandMapper.asDTO(brandRepository.findById(id).orElse(null));
    }

    @Override
    public BrandDTO findByName(final String name) {
        return brandMapper.asDTO(brandRepository.findByName(name).orElse(null));
    }

    @Override
    public BrandDTO save(final BrandDTO brand) {
        try {
            return this.brandMapper.asDTO(this.brandRepository.save(this.brandMapper.asEntity(brand)));
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public BrandDTO update(final Integer id, final BrandDTO brand) {
        if (this.findById(id) == null) {
            return null;
        }
        return this.brandMapper.asDTO(this.brandRepository.save(this.brandMapper.asEntity(brand)));
    }

    @Override
    public BrandDTO delete(final Integer id) {
        final BrandDTO brand = this.findById(id);
        if (brand == null) {
            return null;
        }
        this.brandRepository.deleteById(id);
        return brand;
    }
}
