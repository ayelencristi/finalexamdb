package com.petersen.examenfinal.controller;

import com.petersen.examenfinal.domain.entities.Seller;
import com.petersen.examenfinal.domain.services.SellerService;
import com.petersen.examenfinal.domain.services.dto.ProductDTO;
import com.petersen.examenfinal.domain.services.dto.SellerDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/seller")
public class SellerController {

    private final SellerService sellerService;

    public SellerController(SellerService sellerService) {
        this.sellerService = sellerService;
    }


    @GetMapping()
    public ResponseEntity<Iterable<SellerDTO>> findAll(){
        Iterable<SellerDTO> result = this.sellerService.findAll();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<SellerDTO> create(@RequestBody SellerDTO sellerDTO){
        SellerDTO sellerToCreate = this.sellerService.create(sellerDTO);
        return new ResponseEntity<>(sellerToCreate, HttpStatus.CREATED);
    }
}
