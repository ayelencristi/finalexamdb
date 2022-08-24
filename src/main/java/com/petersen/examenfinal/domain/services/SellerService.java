package com.petersen.examenfinal.domain.services;

import com.petersen.examenfinal.domain.entities.Seller;
import com.petersen.examenfinal.domain.mapper.SellerMapper;
import com.petersen.examenfinal.domain.repositories.SaleRepository;
import com.petersen.examenfinal.domain.repositories.SellerRepository;
import com.petersen.examenfinal.domain.services.dto.SellerDTO;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class SellerService implements ISellerService{

    private final SellerMapper sellerMapper;
    private final SellerRepository sellerRepository;

    private final SaleRepository saleRepository;

    public SellerService(SellerMapper sellerMapper, SellerRepository sellerRepository, SaleRepository saleRepository) {
        this.sellerMapper = sellerMapper;

        this.sellerRepository = sellerRepository;
        this.saleRepository = saleRepository;
    }

    @Override
    public SellerDTO create(SellerDTO sellerDTO) {
        if(sellerDTO == null){
            throw new IllegalArgumentException("El vendedor no puede ser nulo o vacio");
        }
        if(sellerDTO.getCode() == null){
            throw new IllegalArgumentException("El campo código no puede ser nulo");
        }
        Seller seller = this.sellerRepository.findByCode(sellerDTO.getCode());
        if(seller != null){
            throw new IllegalArgumentException("El vendedor ya existe");
        }
        Seller newSeller = this.sellerMapper.mapToEntity(sellerDTO);
        this.sellerRepository.save(newSeller);
        return sellerDTO;
    }

    @Override
    public Iterable<SellerDTO> findAll() {

        Iterable<Seller> result = this.sellerRepository.findAll();
        return StreamSupport.stream(result.spliterator(), false)
                .map(seller -> {
                    SellerDTO sellerDTO = this.sellerMapper.mapToDTO(seller);
                    sellerDTO.setCommission(this.saleRepository.getCommissionBySeller(seller));
                    return sellerDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public SellerDTO findByCode(String code) {
        if(code == null || code == ""){
            throw new IllegalArgumentException("El campo codigo no puede ser nulo");
        }
        Seller seller = this.sellerRepository.findByCode(code);
        if (seller == null) return null;
        SellerDTO sellerDTO = this.sellerMapper.mapToDTO(seller);
        sellerDTO.setCommission(this.saleRepository.getCommissionBySeller(seller));
        return sellerDTO;
    }

    @Override
    public SellerDTO findByName(String name) {
        if(name == null || name == ""){
            throw new IllegalArgumentException("El campo nombre no puede ser nulo");
        }
       Seller seller = this.sellerRepository.findByName(name);
        if (seller == null) return null;
        SellerDTO sellerDTO = this.sellerMapper.mapToDTO(seller);
        sellerDTO.setCommission(this.saleRepository.getCommissionBySeller(seller));
        return sellerDTO;
    }



}
