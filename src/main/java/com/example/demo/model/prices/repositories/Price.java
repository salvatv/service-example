package com.example.demo.model.prices.repositories;

import com.example.demo.model.brands.repositories.Brand;
import com.example.demo.model.products.repositories.Product;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Price {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BRAND_ID")
    private Brand brand;

    @Column(nullable = false)
    private Date startDate;

    @Column(nullable = false)
    private Date endDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

    @Column(columnDefinition = "INT DEFAULT 0")
    private Integer priority;

    @Column(columnDefinition = "DECIMAL(2,2) DEFAULT 0")
    private Integer cost;

    @Column(columnDefinition = "VARCHAR(250) DEFAULT NULL")
    private String curr;

}
