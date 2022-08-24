package com.petersen.examenfinal.domain.mapper;

import com.petersen.examenfinal.domain.entities.SaleDetail;
import com.petersen.examenfinal.domain.services.dto.SaleDetailDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface SaleDetailMapper {

    SaleDetailDTO mapToDTO(SaleDetail saleDetail);

    @Mapping(target = "product", ignore = true)
    SaleDetail mapToEntity(SaleDetailDTO saleDetailDTO);
}
