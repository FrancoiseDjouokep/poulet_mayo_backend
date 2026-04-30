package com.pouletmayo.backend.repository;

import com.pouletmayo.backend.model.Validation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ValidationRepository extends JpaRepository<Validation, Long> {

    Optional<Validation> findByCode(String code);
}