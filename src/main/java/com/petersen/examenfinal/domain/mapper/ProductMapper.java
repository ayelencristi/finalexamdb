package com.petersen.examenfinal.domain.mapper;

import com.petersen.examenfinal.domain.entities.Product;
import com.petersen.examenfinal.domain.services.dto.ProductDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductDTO mapToDTO(Product product);

    Product mapToEntity(ProductDTO productDTO);
}
