package com.example.demo.ws.prices.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PriceFilterDTO {

    @NotBlank
    private String date;

    private Collection<Integer> productIds = new ArrayList<>();

    private Collection<Integer> brandIds = new ArrayList<>();

}
