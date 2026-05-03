package com.pouletmayo.backend.service;

import com.pouletmayo.backend.model.Supplement;
import com.pouletmayo.backend.repository.SupplementRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplementService {

    private final SupplementRepository supplementRepository;

    public SupplementService(SupplementRepository supplementRepository) {
        this.supplementRepository = supplementRepository;
    }

    public Supplement creer(Supplement supplement) {
        return supplementRepository.save(supplement);
    }

    public List<Supplement> lireTous() {
        return supplementRepository.findAll();
    }

    public Supplement lireParId(Long id) {
        return supplementRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Supplément introuvable"));
    }

    public void supprimer(Long id) {
        supplementRepository.deleteById(id);
    }
}