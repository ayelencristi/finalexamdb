package com.petersen.examenfinal.domain.services;

import com.petersen.examenfinal.domain.services.dto.SellerDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ISellerService {

    SellerDTO create(SellerDTO sellerDTO);

    Iterable<SellerDTO> findAll();

    SellerDTO findByCode(String code);

    SellerDTO findByName(String name);

}
