package com.manager.dtos;

import java.util.UUID;

public record SaleRequestDTO(UUID productId, Integer quantity) {

}
