package com.manager.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.manager.dtos.SaleRequest;
import com.manager.dtos.SalesResponse;
import com.manager.entities.Sale;
import com.manager.repositories.IProductRepository;
import com.manager.repositories.ISaleRepository;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/sales")
public class SalesController {

  @Autowired
  private ISaleRepository saleRepository;

  @Autowired
  private IProductRepository productRepository;

  @PostMapping
  @Transactional
  public ResponseEntity<Sale> create(@RequestBody SaleRequest sale, UriComponentsBuilder uriBuider) {
    UUID productId = UUID.fromString(sale.productId());
    var product = productRepository.findById(productId);

    if (!product.isPresent()) {
      return ResponseEntity.notFound().build();
    }

    Sale newSale = new Sale();

    newSale.setProduct(product.get());
    newSale.setQuantity(sale.quantity());
    newSale.setTotal(sale.quantity() * product.get().getPrice());

    var createdSale = saleRepository.save(newSale);

    var uri = uriBuider.path("/sales/{id}").buildAndExpand(createdSale.getId()).toUri();

    return ResponseEntity.created(uri).body(createdSale);
  }

  @GetMapping
  public Page<SalesResponse> list(Pageable pageable) {
    return saleRepository.findAll(pageable).map(sale -> new SalesResponse(sale));
  }

}
