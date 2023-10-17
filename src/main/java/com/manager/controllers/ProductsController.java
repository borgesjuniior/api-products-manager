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

import com.manager.entities.Product;
import com.manager.repositories.IProductRespository;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/products")
public class ProductsController {

  @Autowired
  private IProductRespository productRespository;

  @PostMapping
  public ResponseEntity<Product> create(@RequestBody Product product, UriComponentsBuilder uriBuilder) {
    var createdProduct = productRespository.save(product);
    var uri = uriBuilder.path("/products/{id}").buildAndExpand(createdProduct.getId()).toUri();

    return ResponseEntity.created(uri).body(product);
  }

  @GetMapping
  public Page<Product> list(Pageable pageable) {
    return productRespository.findAll(pageable);
  }

  @PutMapping("/{id}")
  @Transactional
  public ResponseEntity<Product> update(@PathVariable UUID id, @RequestBody Product product) {
    var productFound = productRespository.findById(id).get();
    productFound.update(product);
    return ResponseEntity.ok(productFound);
  }

  @DeleteMapping("/{id}")
  @Transactional
  public ResponseEntity<Product> update(@PathVariable UUID id) {
    productRespository.deleteById(id);
    return ResponseEntity.noContent().build();
  }

}
