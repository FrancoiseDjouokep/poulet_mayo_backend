package com.pouletmayo.backend.service;

import com.pouletmayo.backend.dto.CreateCommandeRequest;
import com.pouletmayo.backend.model.Commande;
import com.pouletmayo.backend.model.Menu;
import com.pouletmayo.backend.model.Supplement;
import com.pouletmayo.backend.repository.CommandeRepository;
import com.pouletmayo.backend.repository.MenuRepository;
import com.pouletmayo.backend.repository.SupplementRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommandeService {

    private final CommandeRepository commandeRepository;
    private final MenuRepository menuRepository;
    private final SupplementRepository supplementRepository;

    public CommandeService(
            CommandeRepository commandeRepository,
            MenuRepository menuRepository,
            SupplementRepository supplementRepository
    ) {
        this.commandeRepository = commandeRepository;
        this.menuRepository = menuRepository;
        this.supplementRepository = supplementRepository;
    }

    public Commande creer(CreateCommandeRequest request) {

        Menu menu = menuRepository.findById(request.getMenuId())
                .orElseThrow(() -> new RuntimeException("Menu introuvable"));

        List<Supplement> supplements =
                supplementRepository.findAllById(request.getSupplementsIds());

        Commande commande = new Commande();

        commande.setNomClient(request.getNomClient());
        commande.setMenu(menu);
        commande.setQuantite(request.getQuantite());
        commande.setPayee(request.getPayee());
        commande.setSupplements(supplements);
        commande.setDateCommande(LocalDateTime.now());
        commande.calculerPrixTotal();

        return commandeRepository.save(commande);
    }

    public List<Commande> lireToutes() {
        return commandeRepository.findAll();
    }

    public List<Commande> lireParDate(LocalDate date) {
        return commandeRepository.findByDateCommande(date);
    }

    public void supprimer(Long id) {
        commandeRepository.deleteById(id);
    }
}