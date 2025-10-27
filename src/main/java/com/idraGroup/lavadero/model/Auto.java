package com.idraGroup.lavadero.model;

import java.util.Objects;

public class Auto {

    private Integer id;
    private String patente;
    private String tipo;

    // Constructor vacío
    public Auto() {
    }

    // Constructor completo
    public Auto(Integer id, String patente, String tipo) {
        this.id = id;
        this.patente = patente;
        this.tipo = tipo;
    }

    // --- Getters y Setters ---
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    //METODOS
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Auto auto = (Auto) o;
        return id == auto.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Auto{"
                + "id=" + id
                + ", patente='" + patente + '\''
                + ", tipo='" + tipo + '\''
                + '}';
    }
}


