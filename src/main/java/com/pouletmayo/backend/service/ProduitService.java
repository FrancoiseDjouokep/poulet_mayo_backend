package com.pouletmayo.backend.service;


import com.pouletmayo.backend.dto.ProduitRequest;
import com.pouletmayo.backend.model.Produit;
import com.pouletmayo.backend.repository.ProduitRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProduitService {

    private final ProduitRepository produitRepository;

    public ProduitService(ProduitRepository produitRepository) {
        this.produitRepository = produitRepository;
    }

    public Produit creer(ProduitRequest request) {

        Produit produit = new Produit();

        produit.setNom(request.getNom());
        produit.setPrixUnitaire(request.getPrixUnitaire());

        return produitRepository.save(produit);
    }

    public List<Produit> lister() {
        return produitRepository.findAll();
    }

    public Produit modifier(Long id, ProduitRequest request) {

        Produit produit = produitRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Produit introuvable")
                );

        produit.setNom(request.getNom());
        produit.setPrixUnitaire(request.getPrixUnitaire());

        return produitRepository.save(produit);
    }

    public void supprimer(Long id) {

        Produit produit = produitRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Produit introuvable")
                );

        produitRepository.delete(produit);
    }
}
