/*
 * Created by Salvador Torres Velasco
 * 2021
 */

package com.example.demo.ws.prices.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PriceBO {

    private Integer productId;

    private Integer brandId;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private Double finalPrice;

    private Integer priority;

}
