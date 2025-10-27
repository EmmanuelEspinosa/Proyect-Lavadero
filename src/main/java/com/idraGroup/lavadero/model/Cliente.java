package com.idraGroup.lavadero.model;

import java.util.Objects;
/**
 * Class <code>Cliente</code> del proyecto Lavadero.
 * <p>
 * Archivo: <code>com/idraGroup/lavadero/model/Cliente.java</code>
 * Paquete: <code>com.idraGroup.lavadero.model</code>
 * </p>
 */

public class Cliente {

    private Integer id;
    private String nombre;
    private String dni;
    private String telefono;

    // Constructor vacío
    public Cliente() {
    }

    // Constructor completo
    public Cliente(Integer id, String nombre, String dni, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.dni = dni;
        this.telefono = telefono;
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
 * getNombre.

 * @return String.
 */

    public String getNombre() {
        return nombre;
    }
/**
 * setNombre.
 * @param nombre String.

 */

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
/**
 * getDni.

 * @return String.
 */

    public String getDni() {
        return dni;
    }
/**
 * setDni.
 * @param dni String.

 */

    public void setDni(String dni) {
        this.dni = dni;
    }
/**
 * getTelefono.

 * @return String.
 */

    public String getTelefono() {
        return telefono;
    }
/**
 * setTelefono.
 * @param telefono String.

 */

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    //METODOS
/**
 * toString.

 * @return String.
 */
    
    @Override
    public String toString() {
        return "Cliente{"
                + "id=" + id
                + ", nombre='" + nombre + '\''
                + ", dni='" + dni + '\''
                + ", telefono='" + telefono + '\''
                + '}';
    }
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
        Cliente cliente = (Cliente) o;
        return id == cliente.id;
    }
/**
 * hashCode.

 * @return int.
 */

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
