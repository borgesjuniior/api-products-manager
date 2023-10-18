package com.manager.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.manager.dtos.SaleRequestDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "sales")
@Data
@NoArgsConstructor
public class Sale {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "product_id", nullable = false)
  private Product product;
  private Integer quantity;
  private Double total;

  @CreationTimestamp
  private LocalDateTime created_at;

  @UpdateTimestamp
  private LocalDateTime updated_at;

  public Sale(SaleRequestDTO sale, Product product) {
    this.product = product;
    this.quantity = sale.quantity();
    this.total = sale.quantity() * product.getPrice();
    this.created_at = LocalDateTime.now();
    this.updated_at = LocalDateTime.now();
  }
}
