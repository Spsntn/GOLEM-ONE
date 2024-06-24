package com.golemone.repository;


import com.golemone.model.Prodotti;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProdottiRepository extends JpaRepository<Prodotti, UUID> {



}
