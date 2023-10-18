package com.manager.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

import com.manager.entities.Sale;

public record SalesResponse(UUID id, String name, Integer quantity, Double total, LocalDateTime createdDate) {
  public SalesResponse(Sale sale) {
    this(sale.getId(), sale.getProduct().getName(), sale.getQuantity(), sale.getTotal(), sale.getCreated_at());
  }
}
