package com.idraGroup.lavadero.model;

import java.time.LocalDateTime;
import java.util.Objects;

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
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getClienteId() {
        return clienteId; 
    }

    public void setClienteId(Integer id) {
        this.clienteId = id; 
    }

    public Integer getAutoId() {
        return autoId; 
    }

    public void setAutoId(Integer id) {
        this.autoId = id; 
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Auto getAuto() {
        return auto;
    }

    public void setAuto(Auto auto) {
        this.auto = auto;
    }

    public LocalDateTime getTurno() {
        return turno;
    }

    public void setTurno(LocalDateTime turno) {
        this.turno = turno;
    }

    public String getTipoLavado() {
        return tipoLavado;
    }

    public void setTipoLavado(String tipoLavado) {
        this.tipoLavado = tipoLavado;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
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
        Reserva reserva = (Reserva) o;
        return id == reserva.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

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
