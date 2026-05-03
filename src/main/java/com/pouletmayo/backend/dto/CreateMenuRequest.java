package com.pouletmayo.backend.dto;
public class CreateMenuRequest {

    private String nomMenu;
    private Integer prix;

    public CreateMenuRequest() {
    }

    public String getNomMenu() {
        return nomMenu;
    }

    public void setNomMenu(String nomMenu) {
        this.nomMenu = nomMenu;
    }

    public Integer getPrix() {
        return prix;
    }

    public void setPrix(Integer prix) {
        this.prix = prix;
    }
}
