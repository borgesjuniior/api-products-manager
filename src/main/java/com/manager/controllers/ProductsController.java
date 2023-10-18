package com.manager.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.manager.dtos.ProductRequestDTO;
import com.manager.dtos.ProductResponseDTO;
import com.manager.entities.Product;
import com.manager.repositories.IProductRepository;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/products")
public class ProductsController {

  @Autowired
  private IProductRepository productRespository;

  @PostMapping
  @Transactional
  public ResponseEntity<ProductResponseDTO> create(
      @RequestBody ProductRequestDTO product,
      UriComponentsBuilder uriBuilder) {
    Product createdProduct = productRespository.save(new Product(product));
    var uri = uriBuilder.path("/products/{id}").buildAndExpand(createdProduct).toUri();

    return ResponseEntity.created(uri).body(new ProductResponseDTO(createdProduct));
  }

  @GetMapping
  public Page<ProductResponseDTO> list(Pageable pageable) {
    return productRespository.findAll(pageable).map(product -> new ProductResponseDTO(product));
  }

  @PutMapping
  @Transactional
  public ResponseEntity<ProductResponseDTO> update(@RequestBody ProductRequestDTO product) {
    var productFound = productRespository.getReferenceById(product.id());
    productFound.update(product);
    return ResponseEntity.ok(new ProductResponseDTO(productFound));
  }

  @DeleteMapping("/{id}")
  @Transactional
  public ResponseEntity<Product> update(@PathVariable UUID id) {
    productRespository.deleteById(id);
    return ResponseEntity.noContent().build();
  }

}
