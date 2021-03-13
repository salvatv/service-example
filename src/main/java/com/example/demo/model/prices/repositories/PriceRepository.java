package com.example.demo.model.prices.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Repository
public interface PriceRepository extends JpaRepository<Price, Integer> {

    @Query("select p from Price p where (:date is null or p.startDate >= :date and p.endDate <= :date) " +
            " and (:brandIds is null or p.brand.id in :brandIds) and (:productIds is null or p.product.id in :productIds)")
    List<Price> findAllByFilter(@Param("date") Date date, @Param("brandIds") Collection<Integer> brandIds,
                                @Param("productIds") Collection<Integer> productIds);

}
