package com.petersen.examenfinal.domain.repositories;

import com.petersen.examenfinal.domain.entities.Seller;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerRepository extends PagingAndSortingRepository<Seller, Long> {

    Seller findByCode(String code);

    Seller findByName(String name);
}
