package com.petersen.examenfinal.controller;

import com.petersen.examenfinal.domain.services.SaleService;
import com.petersen.examenfinal.domain.services.dto.ProductDTO;
import com.petersen.examenfinal.domain.services.dto.SaleDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sale")
@CrossOrigin("*")
public class SaleController {

    private final SaleService saleService;

    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @GetMapping()
    public ResponseEntity<Iterable<SaleDTO>> findAll(){
        Iterable<SaleDTO> result = this.saleService.findAll();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<SaleDTO> create(@RequestBody SaleDTO saleDTO){
        SaleDTO saleToCreate = this.saleService.create(saleDTO);
        return new ResponseEntity<>(saleToCreate, HttpStatus.CREATED);
    }
}
