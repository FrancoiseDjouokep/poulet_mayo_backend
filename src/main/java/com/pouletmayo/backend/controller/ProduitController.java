package com.pouletmayo.backend.controller;


import com.pouletmayo.backend.dto.ProduitRequest;
import com.pouletmayo.backend.model.Produit;
import com.pouletmayo.backend.service.ProduitService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produits")
@Tag(name = "Produits")
@SecurityRequirement(name = "bearerAuth")
public class ProduitController {

    private final ProduitService produitService;

    public ProduitController(ProduitService produitService) {
        this.produitService = produitService;
    }

    @PostMapping
    public Produit creer(@RequestBody ProduitRequest request) {
        return produitService.creer(request);
    }

    @GetMapping
    public List<Produit> lister() {
        return produitService.lister();
    }

    @PutMapping("/{id}")
    public Produit modifier(
            @PathVariable Long id,
            @RequestBody ProduitRequest request
    ) {
        return produitService.modifier(id, request);
    }

    @DeleteMapping("/{id}")
    public void supprimer(@PathVariable Long id) {
        produitService.supprimer(id);
    }
}
