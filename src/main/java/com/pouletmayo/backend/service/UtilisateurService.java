package com.pouletmayo.backend.service;

import com.pouletmayo.backend.dto.ActivationRequest;
import com.pouletmayo.backend.dto.LoginRequest;
import com.pouletmayo.backend.model.Role;
import com.pouletmayo.backend.model.Utilisateur;
import com.pouletmayo.backend.model.Validation;
import com.pouletmayo.backend.repository.UtilisateurRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Map;
import java.util.Optional;

@Service
public class UtilisateurService implements UserDetailsService {
    private UtilisateurRepository utilisateurRepository;
    private BCryptPasswordEncoder passwordEncoder;
    private ValidationService validationService;

    public UtilisateurService(UtilisateurRepository utilisateurRepository, BCryptPasswordEncoder passwordEncoder, ValidationService validationService) {
        this.utilisateurRepository = utilisateurRepository;
        this.passwordEncoder = passwordEncoder;
        this.validationService = validationService;
    }

    public void inscription(Utilisateur utilisateur){
        if(!utilisateur.getEmail().contains("@")){
            throw new RuntimeException("Votre email n'est pas valide");
        }
        if(!utilisateur.getEmail().contains(".")){
            throw new RuntimeException("Votre email n'est pas valide");
        }
        Optional<Utilisateur> utilisateurOptional= this.utilisateurRepository.findByEmail(utilisateur.getEmail());
        if(utilisateurOptional.isPresent()){
            throw new RuntimeException("Votre email est deja utiliser");
        }
        String mdpCrypte =  this.passwordEncoder.encode(utilisateur.getPassword());
        Role roleUtilisateur = new Role();
        roleUtilisateur.setLibelle(String.valueOf(TypeDeRole.UTILISATEUR));
        utilisateur.setRole(roleUtilisateur);
        utilisateur.setPassword(mdpCrypte);

        utilisateur= this.utilisateurRepository.save(utilisateur);
        this.validationService.enregistrer(utilisateur);
    }

    public void activation(ActivationRequest request) {
        Validation validation = this.validationService.lireEnFonctionDuCode(request.getCode());
        if(Instant.now().isAfter(validation.getExpire())){
            throw new RuntimeException("Votre delai est expirer");
        }
        Utilisateur utilisateurActiver= this.utilisateurRepository.findById(validation.getUtilisateur().getId()).orElseThrow(() -> new RuntimeException("Utilisateur inconnu"));
        utilisateurActiver.setActif(true);
        this.utilisateurRepository.save(utilisateurActiver);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.utilisateurRepository
                .findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Aucun utilisateur ne correspond a cette identifiant"));

    }

    public Utilisateur login(LoginRequest request) {

        Utilisateur utilisateur = this.utilisateurRepository
                .findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new RuntimeException("Email ou mot de passe incorrect"));

        if (!utilisateur.isActif()) {
            throw new RuntimeException("Votre compte n'est pas activé");
        }

        boolean passwordMatches = passwordEncoder.matches(
                request.getMotDePasse(),
                utilisateur.getPassword()
        );

        if (!passwordMatches) {
            throw new RuntimeException("Email ou mot de passe incorrect");
        }

        return utilisateur;
    }
}