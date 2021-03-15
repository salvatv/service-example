/*
 * Created by Salvador Torres Velasco
 * 2021
 */

package com.example.demo.model.products.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
}
