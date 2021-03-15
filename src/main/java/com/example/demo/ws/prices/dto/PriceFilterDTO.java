/*
 * Created by Salvador Torres Velasco
 * 2021
 */

package com.example.demo.ws.prices.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PriceFilterDTO {

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "PST")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    private Collection<Integer> productIds = new ArrayList<>();

    private Collection<Integer> brandIds = new ArrayList<>();

}
