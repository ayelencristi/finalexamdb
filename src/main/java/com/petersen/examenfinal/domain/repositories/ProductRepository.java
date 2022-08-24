package com.petersen.examenfinal.domain.repositories;

import com.petersen.examenfinal.domain.entities.Product;
import com.petersen.examenfinal.domain.enums.Category;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {

    Product findByCode(String code);

    List<Product> findByCategory(Category category);

    List<Product> findByNameContainingIgnoreCase(String name);
}
