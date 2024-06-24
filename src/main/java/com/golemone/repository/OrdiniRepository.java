package com.golemone.repository;

import com.golemone.model.CC;
import com.golemone.model.Ordini;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrdiniRepository extends JpaRepository<Ordini, CC> {
}
