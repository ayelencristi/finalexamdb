package com.petersen.examenfinal.domain.repositories;

import com.petersen.examenfinal.domain.entities.Sale;
import com.petersen.examenfinal.domain.entities.Seller;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface SaleRepository extends PagingAndSortingRepository<Sale, Long> {

    Sale findBySeller(Seller seller);

    @Query("SELECT SUM(s.commission) FROM Sale s WHERE s.seller = :seller") //trae comisiones directamente por db
    BigDecimal getCommissionBySeller(@Param("seller") Seller seller);
}
