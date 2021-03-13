package com.example.demo.ws.prices.dto;

import lombok.Data;

import java.util.Date;

@Data
public class PriceBO {

    private Integer productId;

    private Integer brandId;

    private Date inicialDate;

    private Date finalDate;

    private Double finalPrice;

}
