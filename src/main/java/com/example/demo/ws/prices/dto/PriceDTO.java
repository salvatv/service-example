package com.example.demo.ws.prices.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PriceDTO {

    private Integer id;

    private Integer brandId;

    private Date startDate;

    private Date endDate;

    private Integer productId;

    private Integer priority;

    private Double cost;

    private String curr;

}
