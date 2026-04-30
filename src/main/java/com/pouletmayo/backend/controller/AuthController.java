package com.pouletmayo.backend.controller;

import com.pouletmayo.backend.dto.*;
import com.pouletmayo.backend.model.Utilisateur;
import com.pouletmayo.backend.service.JwtService;

import com.pouletmayo.backend.service.UtilisateurService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Authentification")
public class AuthController {

    private final UtilisateurService utilisateurService;
    private final JwtService jwtService;

    public AuthController(UtilisateurService utilisateurService,
                          JwtService jwtService) {
        this.utilisateurService = utilisateurService;
        this.jwtService = jwtService;
    }

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request) {

        Utilisateur utilisateur = new Utilisateur();

        utilisateur.setNom(request.getNom());
        utilisateur.setEmail(request.getEmail());
        utilisateur.setPassword(request.getMotDePasse());

        utilisateurService.inscription(utilisateur);

        return "Compte créé avec succès";
    }

    @PostMapping("/activation")
    public String activation(
            @RequestBody ActivationRequest request
    ) {

        utilisateurService.activation(request);

        return "Compte activé avec succès";
    }
    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {

        Utilisateur utilisateur = utilisateurService.login(request);

        Map<String, String> jwt = jwtService.generate(utilisateur.getEmail());

        return new AuthResponse(
                "Connexion réussie",
                jwt.get("bearer")
        );
    }
}