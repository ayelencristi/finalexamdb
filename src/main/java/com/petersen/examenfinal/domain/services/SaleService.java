package com.petersen.examenfinal.domain.services;

import com.petersen.examenfinal.domain.entities.Product;
import com.petersen.examenfinal.domain.entities.Sale;
import com.petersen.examenfinal.domain.entities.SaleDetail;
import com.petersen.examenfinal.domain.entities.Seller;
import com.petersen.examenfinal.domain.mapper.SaleMapper;
import com.petersen.examenfinal.domain.repositories.ProductRepository;
import com.petersen.examenfinal.domain.repositories.SaleRepository;
import com.petersen.examenfinal.domain.repositories.SellerRepository;
import com.petersen.examenfinal.domain.services.dto.SaleDTO;
import com.petersen.examenfinal.domain.services.dto.SaleDetailDTO;
import com.petersen.examenfinal.domain.services.dto.SellerDTO;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class SaleService implements ISaleService {

    private final SaleRepository saleRepository;
    private final SellerRepository sellerRepository;
    private final ProductRepository productRepository;

    private final SaleMapper saleMapper;

    public SaleService(SaleRepository saleRepository, SellerRepository sellerRepository, ProductRepository productRepository, SaleMapper saleMapper) {
        this.saleRepository = saleRepository;
        this.sellerRepository = sellerRepository;
        this.productRepository = productRepository;
        this.saleMapper = saleMapper;
    }

    @Override
    public SaleDTO create(SaleDTO saleDTO) {
        if(saleDTO == null){
            throw new IllegalArgumentException("La venta no puede ser nula");
        }
        if(saleDTO.getSeller() == null || saleDTO.getSeller().getCode() == null){
            throw new IllegalArgumentException("Se debe cargar un vendedor");
        }
        Seller seller = this.sellerRepository.findByCode(saleDTO.getSeller().getCode());
        if (seller == null) {
            throw new IllegalArgumentException("El vendedor no existe");
        }
        Sale newSale = this.saleMapper.mapToEntity(saleDTO);
        newSale.setSeller(seller);
        newSale.setDate(new Date());
        for(SaleDetailDTO saleDetailDTO:saleDTO.getSaleDetails()){
            Product product = this.productRepository.findByCode(saleDetailDTO.getProduct().getCode());
            SaleDetail saleDetail = new SaleDetail();
            saleDetail.setProduct(product);
            saleDetail.setQuantity(saleDetailDTO.getQuantity());
            saleDetail.setTotal(saleDetailDTO.getProduct().getAmount().multiply(BigDecimal.valueOf(saleDetailDTO.getQuantity())));
            newSale.addDetail(saleDetail);
        }
        newSale.setCommission(calculateCommission(saleDTO));
        this.saleRepository.save(newSale);
        return saleDTO;
    }

    //Calculo de comision
    private BigDecimal calculateCommission(SaleDTO saleDTO) {
        Integer countProducts = saleDTO.getSaleDetails().stream().mapToInt(dto -> dto.getQuantity()).sum();
        Double total = saleDTO.getSaleDetails().stream().mapToDouble(dto -> dto.getTotal().doubleValue()).sum();
        if(countProducts >= 3){
            return new BigDecimal(0.10).multiply(BigDecimal.valueOf(total));
        } else {
            return new BigDecimal(0.5).multiply(BigDecimal.valueOf(total));
        }
    }

    @Override
    public Iterable<SaleDTO> findAll() {
        Iterable<Sale> result = this.saleRepository.findAll();
        return StreamSupport.stream(result.spliterator(), false)
                .map(sale -> {
                   SaleDTO saleDTO = saleMapper.mapToDTO((sale));
                    Double total = saleDTO.getSaleDetails().stream().mapToDouble(dto -> dto.getTotal().doubleValue()).sum();
                    saleDTO.setTotal(BigDecimal.valueOf(total));
                    return saleDTO;
                })
                .collect(Collectors.toList());
    }


    @Override
    public SaleDTO findById(Long id) {
        if(id == null){
            throw new IllegalArgumentException("El campo id no puede ser nulo");
        }
        Optional<Sale> sale = this.saleRepository.findById(id);
        if (sale.isPresent()) {
            SaleDTO saleDTO = saleMapper.mapToDTO((sale.get()));
            Double total = saleDTO.getSaleDetails().stream().mapToDouble(dto -> dto.getTotal().doubleValue()).sum();
            saleDTO.setTotal(BigDecimal.valueOf(total));
            return saleDTO;
        }
        return null;
    }

    @Override
    public SaleDTO findBySeller(SellerDTO sellerDTO) {
        return null;
    }

}

