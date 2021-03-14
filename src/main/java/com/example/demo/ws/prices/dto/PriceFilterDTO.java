package com.example.demo.ws.prices.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PriceFilterDTO {

    private String date;

    private Collection<Integer> productIds = new ArrayList<>();

    private Collection<Integer> brandIds = new ArrayList<>();

}
