package com.manager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.manager.entities.Sale;

import java.util.UUID;

public interface ISaleRepository extends JpaRepository<Sale, UUID> {

}
