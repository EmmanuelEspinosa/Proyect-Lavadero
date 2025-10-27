package com.idraGroup.lavadero.model;

import java.time.LocalDateTime;
import java.util.Objects;
/**
 * Class <code>Reserva</code> del proyecto Lavadero.
 * <p>
 * Archivo: <code>com/idraGroup/lavadero/model/Reserva.java</code>
 * Paquete: <code>com.idraGroup.lavadero.model</code>
 * </p>
 */

public class Reserva {

    private Integer id;
    private Integer clienteId;
    private Integer autoId;
    private Cliente cliente;
    private Auto auto;
    private LocalDateTime turno;
    private String tipoLavado;
    private double precio;

    // Constructor vacío
    public Reserva() {
    }

    // Constructor completo
    public Reserva(Integer id, Cliente cliente, Auto auto, LocalDateTime turno, String tipoLavado, double precio) {
        this.id = id;
        this.cliente = cliente;
        this.auto = auto;
        this.turno = turno;
        this.tipoLavado = tipoLavado;
        this.precio = precio;
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
 * getClienteId.

 * @return Integer.
 */

    public Integer getClienteId() {
        return clienteId; 
    }
/**
 * setClienteId.
 * @param id Integer.

 */

    public void setClienteId(Integer id) {
        this.clienteId = id; 
    }
/**
 * getAutoId.

 * @return Integer.
 */

    public Integer getAutoId() {
        return autoId; 
    }
/**
 * setAutoId.
 * @param id Integer.

 */

    public void setAutoId(Integer id) {
        this.autoId = id; 
    }
/**
 * getCliente.

 * @return Cliente.
 */

    public Cliente getCliente() {
        return cliente;
    }
/**
 * setCliente.
 * @param cliente Cliente.

 */

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
/**
 * getAuto.

 * @return Auto.
 */

    public Auto getAuto() {
        return auto;
    }
/**
 * setAuto.
 * @param auto Auto.

 */

    public void setAuto(Auto auto) {
        this.auto = auto;
    }
/**
 * getTurno.

 * @return LocalDateTime.
 */

    public LocalDateTime getTurno() {
        return turno;
    }
/**
 * setTurno.
 * @param turno LocalDateTime.

 */

    public void setTurno(LocalDateTime turno) {
        this.turno = turno;
    }
/**
 * getTipoLavado.

 * @return String.
 */

    public String getTipoLavado() {
        return tipoLavado;
    }
/**
 * setTipoLavado.
 * @param tipoLavado String.

 */

    public void setTipoLavado(String tipoLavado) {
        this.tipoLavado = tipoLavado;
    }
/**
 * getPrecio.

 * @return double.
 */

    public double getPrecio() {
        return precio;
    }
/**
 * setPrecio.
 * @param precio double.

 */

    public void setPrecio(double precio) {
        this.precio = precio;
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
        Reserva reserva = (Reserva) o;
        return id == reserva.id;
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
        return "Reserva{"
                + "id=" + id
                + ", clienteId=" + (cliente != null ? cliente.getId() : "null")
                + ", autoPatente=" + (auto != null ? auto.getPatente() : "null")
                + ", turno=" + turno
                + ", tipoLavado='" + tipoLavado + '\''
                + ", precio=" + precio
                + '}';
    }
}
