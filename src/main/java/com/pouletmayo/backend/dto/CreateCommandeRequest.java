package com.pouletmayo.backend.dto;

import java.util.List;

public class CreateCommandeRequest {

    private Long menuId;

    private Integer quantite;

    private Boolean payee;

    private List<Long> supplementsIds;

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public Integer getQuantite() {
        return quantite;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }

    public Boolean getPayee() {
        return payee;
    }

    public void setPayee(Boolean payee) {
        this.payee = payee;
    }

    public List<Long> getSupplementsIds() {
        return supplementsIds;
    }

    public void setSupplementsIds(List<Long> supplementsIds) {
        this.supplementsIds = supplementsIds;
    }
}