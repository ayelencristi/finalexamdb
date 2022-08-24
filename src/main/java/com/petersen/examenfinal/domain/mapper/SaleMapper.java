package com.petersen.examenfinal.domain.mapper;

import com.petersen.examenfinal.domain.entities.Sale;
import com.petersen.examenfinal.domain.services.dto.SaleDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Optional;

@Mapper(componentModel = "spring")
public interface SaleMapper {

    SaleDTO mapToDTO(Sale sale);

    @Mapping(target = "seller", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "saleDetails", ignore = true)
    @Mapping(target = "commission", ignore = true)
    Sale mapToEntity(SaleDTO saleDTO);

}
