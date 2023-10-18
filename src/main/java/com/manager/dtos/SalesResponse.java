package com.manager.dtos;

import java.util.UUID;

import com.manager.entities.Sale;

public record SalesResponse(UUID id, UUID productId, String name, Integer quantity, Double total) {
  public SalesResponse(Sale sale) {
    this(sale.getId(), sale.getProduct().getId(), sale.getProduct().getName(), sale.getQuantity(),
        sale.getQuantity() * sale.getProduct().getPrice());
  }
}
