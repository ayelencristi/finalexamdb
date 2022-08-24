package com.petersen.examenfinal.domain.services.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class SaleDetailDTO {

    @NotNull
    @Min(1)
    private Integer quantity;

    @NotNull
    private BigDecimal total;

    @NotNull
    private ProductDTO product;
}
