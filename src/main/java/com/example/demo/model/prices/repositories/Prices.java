/*
 * Created by Salvador Torres Velasco
 * 2021
 */

package com.example.demo.model.prices.repositories;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Prices {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="PRICE_LIST")
    private Integer id;

    @Column(nullable = false)
    private Integer brandId;

    @Column(nullable = false)
    private Date startDate;

    @Column(nullable = false)
    private Date endDate;

    @Column(nullable = false)
    private Integer productId;

    @Column(columnDefinition = "INT DEFAULT 0")
    private Integer priority;


    @Column(columnDefinition = "DECIMAL(2,2) DEFAULT 0")
    private Double price;

    @Column(columnDefinition = "VARCHAR(250) DEFAULT NULL")
    private String curr;

}
