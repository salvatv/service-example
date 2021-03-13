package com.example.demo.model.prices.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Date;

@Repository
public interface PriceRepository extends JpaRepository<Price, Integer> {

    @Query("select p from Price p where p.startDate >= :date and p.endDate <= :date  and p.brand.id in :brandIds and p.product.id in :productIds")
    Collection<Price> findAllByFilter(@Param("date") Date date, @Param("brandIds") Collection<Integer> brandIds,
                                      @Param("productIds") Collection<Integer> productIds);

}
