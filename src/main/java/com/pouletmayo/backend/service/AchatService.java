package com.pouletmayo.backend.service;

import com.pouletmayo.backend.dto.CreateAchatRequest;
import com.pouletmayo.backend.model.Achat;
import com.pouletmayo.backend.model.Produit;
import com.pouletmayo.backend.repository.AchatRepository;
import com.pouletmayo.backend.repository.ProduitRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AchatService {

    private final AchatRepository achatRepository;
    private final ProduitRepository produitRepository;

    public AchatService(
            AchatRepository achatRepository,
            ProduitRepository produitRepository
    ) {
        this.achatRepository = achatRepository;
        this.produitRepository = produitRepository;
    }

    public Achat creer(CreateAchatRequest request) {

        Produit produit =
                produitRepository.findByNom(request.getNomProduit())
                        .orElseThrow(() ->
                                new RuntimeException("Produit introuvable"));

        Achat achat = new Achat();

        achat.setProduit(produit);
        achat.setQuantite(request.getQuantite());
        achat.setDateAchat(LocalDate.now());

        achat.calculerPrixTotal();

        return achatRepository.save(achat);
    }

    public List<Achat> lireTous() {
        return achatRepository.findAll();
    }

    public Integer coutTotalParJour(LocalDate date) {

        List<Achat> achats =
                achatRepository.findByDateAchat(date);

        return achats.stream()
                .map(Achat::getPrixTotal)
                .reduce(0, Integer::sum);
    }

    public void supprimer(Long id) {

        Achat achat =
                achatRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException("Achat introuvable"));

        achatRepository.delete(achat);
    }
}