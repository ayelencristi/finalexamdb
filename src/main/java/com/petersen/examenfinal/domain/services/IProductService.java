package com.petersen.examenfinal.domain.services;

import com.petersen.examenfinal.domain.entities.Product;
import com.petersen.examenfinal.domain.services.dto.ProductDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IProductService {

    ProductDTO create(ProductDTO productDTO);

    ProductDTO findByCode(String code);

    Iterable<ProductDTO> findByName(String name);

    Iterable<ProductDTO> findAll();

    ProductDTO update(ProductDTO productDTO);
}
