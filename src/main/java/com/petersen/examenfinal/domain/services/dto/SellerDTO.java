package com.petersen.examenfinal.domain.services.dto;

import lombok.Data;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class SellerDTO {

    @NotNull
    private String code;

    @NotNull
    private String name;

    @NotNull
    @Digits(integer = 10, fraction = 2)
    private BigDecimal salary;

    private BigDecimal commission;
}
