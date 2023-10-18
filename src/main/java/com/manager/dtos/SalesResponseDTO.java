package com.manager.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

import com.manager.entities.Sale;

public record SalesResponseDTO(UUID id, String name, Integer quantity, Double total, LocalDateTime created_at) {
  public SalesResponseDTO(Sale sale) {
    this(sale.getId(), sale.getProduct().getName(), sale.getQuantity(), sale.getTotal(), sale.getCreated_at());
  }
}
