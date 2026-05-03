package com.pouletmayo.backend.repository;

import com.pouletmayo.backend.model.Commande;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface CommandeRepository extends JpaRepository<Commande, Long> {

    List<Commande> findByDateCommande(LocalDate date);

}
