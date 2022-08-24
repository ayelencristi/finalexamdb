package com.petersen.examenfinal.domain.services;

import com.petersen.examenfinal.domain.services.dto.SaleDTO;
import com.petersen.examenfinal.domain.services.dto.SellerDTO;

public interface ISaleService {

    SaleDTO create(SaleDTO saleDTO);

    Iterable<SaleDTO> findAll();

    SaleDTO findById(Long id);

    SaleDTO findBySeller(SellerDTO sellerDTO);

}
