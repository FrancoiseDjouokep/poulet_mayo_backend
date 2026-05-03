package com.pouletmayo.backend.repository;


import com.pouletmayo.backend.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, Long> {
}
