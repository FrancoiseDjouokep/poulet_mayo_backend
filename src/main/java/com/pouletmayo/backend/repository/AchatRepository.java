package com.pouletmayo.backend.repository;

import com.pouletmayo.backend.model.Achat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AchatRepository
        extends JpaRepository<Achat, Long> {

    List<Achat> findByDateAchat(LocalDate date);

}
