package com.pouletmayo.backend.controller;

import com.pouletmayo.backend.dto.CreateAchatRequest;
import com.pouletmayo.backend.model.Achat;
import com.pouletmayo.backend.service.AchatService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/achats")
@Tag(name = "Achats")
@SecurityRequirement(name = "bearerAuth")
public class AchatController {

    private final AchatService achatService;

    public AchatController(AchatService achatService) {
        this.achatService = achatService;
    }

    @PostMapping
    public Achat creer(
            @RequestBody CreateAchatRequest request
    ) {
        return achatService.creer(request);
    }

    @GetMapping
    public List<Achat> lireTous() {
        return achatService.lireTous();
    }

    @GetMapping("/cout-total")
    public Integer coutTotal(
            @RequestParam LocalDate date
    ) {
        return achatService.coutTotalParJour(date);
    }

    @DeleteMapping("/{id}")
    public void supprimer(@PathVariable Long id) {
        achatService.supprimer(id);
    }
}