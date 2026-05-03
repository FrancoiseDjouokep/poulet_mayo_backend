package com.pouletmayo.backend.controller;

import com.pouletmayo.backend.dto.CreateMenuRequest;
import com.pouletmayo.backend.model.Menu;
import com.pouletmayo.backend.service.MenuService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menus")
@Tag(name = "Menus")
@SecurityRequirement(name = "bearerAuth")
public class MenuController {

    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @PostMapping("/create")
    public Menu creer(@RequestBody CreateMenuRequest request) {
        return menuService.creer(request);
    }

    @GetMapping("/read")
    public List<Menu> lireTous() {
        return menuService.lireTous();
    }

    @PutMapping("/{id}")
    public Menu modifier(
            @PathVariable Long id,
            @RequestBody Menu menu
    ) {
        return menuService.modifier(id, menu);
    }

    @DeleteMapping("/{id}")
    public void supprimer(@PathVariable Long id) {
        menuService.supprimer(id);
    }
}