package com.pouletmayo.backend.repository;

import com.pouletmayo.backend.model.Produit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProduitRepository extends JpaRepository<Produit, Long> {
}
