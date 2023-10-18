package com.manager.dtos;

import java.util.UUID;

public record ProductRequestDTO(UUID id, String name, String description, Double price, Integer quantity) {
}
