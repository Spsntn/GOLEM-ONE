package com.golemone.repository;


import com.golemone.model.CC;
import com.golemone.model.Scontrini;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ScontriniRepository extends JpaRepository<Scontrini, UUID> {


    Scontrini findByOrdini_Id(CC id);
}
