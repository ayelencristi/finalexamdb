package com.petersen.examenfinal.domain.services.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class SaleDTO {

    private Long id;

    @NotNull
    private Date date;

    @NotNull
    private SellerDTO seller;

    @NotNull
    private List<SaleDetailDTO> saleDetails;

    private BigDecimal total;
}
