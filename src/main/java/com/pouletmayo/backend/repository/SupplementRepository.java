package com.pouletmayo.backend.repository;

import com.pouletmayo.backend.model.Supplement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplementRepository extends JpaRepository<Supplement, Long> {
}