package com.pouletmayo.backend.service;

import com.pouletmayo.backend.dto.CreateMenuRequest;
import com.pouletmayo.backend.model.Menu;
import com.pouletmayo.backend.model.Produit;
import com.pouletmayo.backend.repository.MenuRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {

    private final MenuRepository menuRepository;

    public MenuService(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    public Menu creer(CreateMenuRequest request) {
        Menu menu = new Menu();

        menu.setNom(request.getNom());
        menu.setPrix(request.getPrix());

        return menuRepository.save(menu);
    }

    public List<Menu> lireTous() {
        return menuRepository.findAll();
    }

    public Menu lireParId(Long id) {
        return menuRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Menu introuvable"));
    }

    public Menu modifier(Long id, Menu menu) {

        Menu ancien = lireParId(id);

        ancien.setNom(menu.getNom());
        ancien.setPrix(menu.getPrix());

        return menuRepository.save(ancien);
    }

    public void supprimer(Long id) {
        menuRepository.deleteById(id);
    }
}
