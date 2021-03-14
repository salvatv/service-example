package com.example.demo.ws.prices.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PriceBO {

    private Integer productId;

    private Integer brandId;

    private Date startDate;

    private Date endDate;

    private Double finalPrice;

    private Integer priority;

}
