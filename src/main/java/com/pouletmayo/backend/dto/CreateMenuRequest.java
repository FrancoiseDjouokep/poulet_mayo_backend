package com.pouletmayo.backend.dto;
public class CreateMenuRequest {

    private String nom;
    private Integer prix;

    public CreateMenuRequest() {
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Integer getPrix() {
        return prix;
    }

    public void setPrix(Integer prix) {
        this.prix = prix;
    }
}
