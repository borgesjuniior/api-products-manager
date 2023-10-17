package com.manager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.manager.entities.Product;
import java.util.UUID;

public interface IProductRespository extends JpaRepository<Product, UUID> {

}
