package com.example.demo.model.prices.services;

import com.example.demo.model.prices.mappers.PriceMapper;
import com.example.demo.model.prices.repositories.PriceRepository;
import com.example.demo.ws.prices.dto.PriceBO;
import com.example.demo.ws.prices.dto.PriceDTO;
import com.example.demo.ws.prices.dto.PriceFilterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class PriceServiceImpl implements PriceService {

    @Autowired
    PriceRepository priceRepository;

    @Autowired
    PriceMapper priceMapper;

    @Override
    public Collection<PriceDTO> findAll() {
        return this.priceMapper.asDTOs(this.priceRepository.findAll());
    }

    @Override
    public PriceDTO findById(final Integer id) {
        return priceMapper.asDTO(priceRepository.findById(id).orElse(null));
    }

    @Override
    public PriceDTO save(final PriceDTO price) {
        try {
            return this.priceMapper.asDTO(this.priceRepository.save(this.priceMapper.asEntity(price)));
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public PriceDTO update(final Integer id, final PriceDTO price) {
        if (this.findById(id) == null) {
            return null;
        }
        return this.priceMapper.asDTO(this.priceRepository.save(this.priceMapper.asEntity(price)));
    }

    @Override
    public PriceDTO delete(final Integer id) {
        final PriceDTO price = this.findById(id);
        if (price == null) {
            return null;
        }
        this.priceRepository.deleteById(id);
        return price;
    }

    @Override
    public Collection<PriceBO> findBy(final PriceFilterDTO filter) {
        this.priceRepository.findAllByFilter(filter.getDate(), filter.getBrandIds(), filter.getProductIds());

        return new ArrayList<>();
    }
}
