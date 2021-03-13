package com.example.demo.ws.prices.dto;

import lombok.Data;

import java.util.Date;

@Data
public class PriceDTO {

    private Integer id;

    private Integer brandId;

    private Date startDate;

    private Date endDate;

    private Integer productId;

    private Integer priority;

    private Integer cost;

    private String curr;

}
