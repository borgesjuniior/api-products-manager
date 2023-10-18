package com.manager.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

import com.manager.entities.Product;

public record ProductResponseDTO(UUID id, String name, String description, Double price, Integer quantity,
    LocalDateTime created_at, LocalDateTime updated_at) {

  public ProductResponseDTO(Product product) {
    this(product.getId(), product.getName(), product.getDescription(), product.getPrice(), product.getQuantity(),
        product.getCreated_at(), product.getUpdated_at());
  }
}
