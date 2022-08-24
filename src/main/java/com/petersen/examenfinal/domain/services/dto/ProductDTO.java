package com.petersen.examenfinal.domain.services.dto;

import com.petersen.examenfinal.domain.enums.Category;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
public class ProductDTO {

    @NotNull
    private String code;

    @NotNull
    @Size(min = 4)
    private String name;

    @NotNull
    private BigDecimal amount;

    @NotNull
    private Category category;

}
