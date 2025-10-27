package com.idraGroup.lavadero.model;

import java.util.Objects;
/**
 * Class <code>Auto</code> del proyecto Lavadero.
 * <p>
 * Archivo: <code>com/idraGroup/lavadero/model/Auto.java</code>
 * Paquete: <code>com.idraGroup.lavadero.model</code>
 * </p>
 */

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
/**
 * getId.

 * @return Integer.
 */
    public Integer getId() {
        return id;
    }
/**
 * setId.
 * @param id Integer.

 */

    public void setId(Integer id) {
        this.id = id;
    }
/**
 * getPatente.

 * @return String.
 */

    public String getPatente() {
        return patente;
    }
/**
 * setPatente.
 * @param patente String.

 */

    public void setPatente(String patente) {
        this.patente = patente;
    }
/**
 * getTipo.

 * @return String.
 */

    public String getTipo() {
        return tipo;
    }
/**
 * setTipo.
 * @param tipo String.

 */

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    //METODOS
/**
 * equals.
 * @param o Object.

 * @return boolean.
 */
    
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
/**
 * hashCode.

 * @return int.
 */

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
/**
 * toString.

 * @return String.
 */

    @Override
    public String toString() {
        return "Auto{"
                + "id=" + id
                + ", patente='" + patente + '\''
                + ", tipo='" + tipo + '\''
                + '}';
    }
}


