package com.petersen.examenfinal.controller;

import com.petersen.examenfinal.domain.entities.Product;
import com.petersen.examenfinal.domain.services.ProductService;
import com.petersen.examenfinal.domain.services.dto.ProductDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
    public ResponseEntity<Iterable<ProductDTO>> findAll(){
       Iterable<ProductDTO> result = this.productService.findAll();
       return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProductDTO> create(@RequestBody ProductDTO productDTO){
        ProductDTO productToCreate = this.productService.create(productDTO);
        return new ResponseEntity<>(productToCreate, HttpStatus.CREATED);
    }

    @PatchMapping
    public ResponseEntity<ProductDTO> update(@RequestBody ProductDTO productDTO){
        ProductDTO productToCreate = this.productService.update(productDTO);
        return new ResponseEntity<>(productToCreate, HttpStatus.OK);
    }


}
