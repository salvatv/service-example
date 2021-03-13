package com.example.demo.ws.prices.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
public class PriceBO {

    private Integer productId;

    private Integer brandId;

    private Date startDate;

    private Date endDate;

    private Double finalPrice;

}
