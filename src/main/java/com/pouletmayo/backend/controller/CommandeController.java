package com.pouletmayo.backend.controller;

import com.pouletmayo.backend.dto.CreateCommandeRequest;
import com.pouletmayo.backend.model.Commande;
import com.pouletmayo.backend.service.CommandeService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/commandes")
@Tag(name = "Commandes")
@SecurityRequirement(name = "bearerAuth")
public class CommandeController {

    private final CommandeService commandeService;

    public CommandeController(CommandeService commandeService) {
        this.commandeService = commandeService;
    }

    @PostMapping
    public Commande creer(
            @RequestBody CreateCommandeRequest request
    ) {
        return commandeService.creer(request);
    }

    @GetMapping
    public List<Commande> lireToutes() {
        return commandeService.lireToutes();
    }

    @GetMapping("/date/{date}")
    public List<Commande> lireParDate(
            @PathVariable LocalDate date
    ) {
        return commandeService.lireParDate(date);
    }

    @DeleteMapping("/{id}")
    public void supprimer(@PathVariable Long id) {
        commandeService.supprimer(id);
    }
}
