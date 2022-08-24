package com.petersen.examenfinal.domain.services;

import com.petersen.examenfinal.domain.entities.Product;
import com.petersen.examenfinal.domain.enums.Category;
import com.petersen.examenfinal.domain.mapper.ProductMapper;
import com.petersen.examenfinal.domain.repositories.ProductRepository;
import com.petersen.examenfinal.domain.services.dto.ProductDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ProductService implements IProductService {

    private final ProductMapper productMapper;

    private final ProductRepository productRepository;

    public ProductService(ProductMapper productMapper, ProductRepository productRepository) {
        this.productMapper = productMapper;
        this.productRepository = productRepository;
    }

    @Override
    public ProductDTO create(ProductDTO productDTO) {
        if(productDTO == null){
            throw new IllegalArgumentException("El producto no puede ser nulo");
        }
        if(productDTO.getCode() == null){
            throw new IllegalArgumentException("El campo código no puede ser nulo");
        }
        Product product = this.productRepository.findByCode(productDTO.getCode());
        if(product != null){
            throw new IllegalArgumentException("El producto ya existe");
        }
        Product newProduct = this.productMapper.mapToEntity(productDTO);
        this.productRepository.save(newProduct);
        return productDTO;

    }

    @Override
    public ProductDTO findByCode(String code) {
        if(code == null || code == ""){
            throw new IllegalArgumentException("El campo codigo no puede ser nulo");
        }
        Product product = this.productRepository.findByCode(code);
        return product == null ? null : this.productMapper.mapToDTO(product);
    }

    @Override
    public Iterable<ProductDTO> findByName(String name) {
        if(name == null || name == ""){
            throw new IllegalArgumentException("El campo nombre no puede ser nulo");
        }
        List<Product> products = this.productRepository.findByNameContainingIgnoreCase(name);
        return products == null ? null : products
                .stream()
                .map(product -> productMapper.mapToDTO(product))
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<ProductDTO> findAll() {
        Iterable<Product> result = this.productRepository.findAll();
        return StreamSupport.stream(result.spliterator(), false)
                .map(product -> productMapper.mapToDTO(product))
                .collect(Collectors.toList());
    }

    @Override
    public ProductDTO update(ProductDTO productDTO) {
        if(productDTO == null){
            throw new IllegalArgumentException("El producto no puede ser nulo o vacio");
        }
        if(productDTO.getCode() == null){
            throw new IllegalArgumentException("El campo código no puede ser nulo");
        }
        Product product = this.productRepository.findByCode(productDTO.getCode());
        if(product == null){
            throw new IllegalArgumentException("El producto no existe");
        }
        product.setAmount(productDTO.getAmount());
        product.setName(productDTO.getName());
        product.setCategory(productDTO.getCategory());
        this.productRepository.save(product);
        return productDTO;
    }

    public List<ProductDTO> findByCategory(Category category){
        if(category == null){
            throw new IllegalArgumentException("La categoría no puede ser nula");
        }
        List<Product> products = this.productRepository.findByCategory(category);
        return products == null ? null : products
                .stream()
                .map(product -> productMapper.mapToDTO(product))
                .collect(Collectors.toList());
    }

}
