package com.petersen.examenfinal.domain.mapper;

import com.petersen.examenfinal.domain.entities.Seller;
import com.petersen.examenfinal.domain.services.dto.SellerDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SellerMapper {

    SellerDTO mapToDTO(Seller seller);

    Seller mapToEntity(SellerDTO sellerDTO);
}
