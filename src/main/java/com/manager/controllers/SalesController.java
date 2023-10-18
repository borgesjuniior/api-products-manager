package com.manager.controllers;

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

import com.manager.dtos.SaleRequestDTO;
import com.manager.dtos.SalesResponseDTO;
import com.manager.entities.Product;
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
  public ResponseEntity<SalesResponseDTO> create(@RequestBody SaleRequestDTO sale, UriComponentsBuilder uriBuider) {
    Product product = productRepository.getReferenceById(sale.productId());
    Sale createdSale = saleRepository.save(new Sale(sale, product));

    var uri = uriBuider.path("/sales/{id}").buildAndExpand(createdSale.getId()).toUri();

    return ResponseEntity.created(uri).body(new SalesResponseDTO(createdSale));
  }

  @GetMapping
  public Page<SalesResponseDTO> list(Pageable pageable) {
    return saleRepository.findAll(pageable).map(sale -> new SalesResponseDTO(sale));
  }

}
