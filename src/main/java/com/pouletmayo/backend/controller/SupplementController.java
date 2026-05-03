package com.pouletmayo.backend.controller;

import com.pouletmayo.backend.model.Supplement;
import com.pouletmayo.backend.service.SupplementService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/supplements")
@Tag(name = "Suppléments")
public class SupplementController {

    private final SupplementService supplementService;

    public SupplementController(SupplementService supplementService) {
        this.supplementService = supplementService;
    }

    @PostMapping("/create")
    public Supplement creer(@RequestBody Supplement supplement) {
        return supplementService.creer(supplement);
    }

    @GetMapping("/read")
    public List<Supplement> lireTous() {
        return supplementService.lireTous();
    }

    @DeleteMapping("/{id}")
    public void supprimer(@PathVariable Long id) {
        supplementService.supprimer(id);
    }
}
