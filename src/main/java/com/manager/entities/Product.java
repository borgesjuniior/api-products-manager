package com.manager.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.manager.dtos.ProductRequestDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;
  private String name;
  private String description;
  private Double price;
  private Integer quantity;

  @CreationTimestamp
  private LocalDateTime created_at;

  @UpdateTimestamp
  private LocalDateTime updated_at;

  public Product(ProductRequestDTO product) {
    this.id = product.id();
    this.name = product.name();
    this.description = product.description();
    this.price = product.price();
    this.quantity = product.quantity();
    this.created_at = LocalDateTime.now();
    this.updated_at = LocalDateTime.now();
  }

  public void update(ProductRequestDTO product) {
    this.name = product.name();
    this.description = product.description();
    this.price = product.price();
    this.updated_at = LocalDateTime.now();
  }
}
