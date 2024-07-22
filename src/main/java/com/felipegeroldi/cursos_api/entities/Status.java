package com.felipegeroldi.cursos_api.entities;

public enum Status {
    ATIVO ("Ativo"),
    INATIVO ("Inativo");

    public String label;

    private Status(String label) {
        this.label = label;
    }
}
