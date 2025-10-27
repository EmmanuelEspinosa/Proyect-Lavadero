package com.idraGroup.lavadero.controller;

import com.idraGroup.lavadero.dao.ReservaDao;
import com.idraGroup.lavadero.model.Reserva;
import com.idraGroup.lavadero.model.Cliente;
import com.idraGroup.lavadero.model.Auto;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;

public class ReservaController {

    private final ReservaDao reservaDao;
    private final ClienteController clienteController;
    private final AutoController autoController;
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public ReservaController(ReservaDao reservaDao, ClienteController clienteController, AutoController autoController) {
        this.reservaDao = reservaDao;
        this.clienteController = clienteController;
        this.autoController = autoController;
    }

    public Reserva crearReserva(String dni, String patente, String turnoStr, String tipoLavado) {

        LocalDateTime turno;
        try {
            turno = LocalDateTime.parse(turnoStr, DATE_TIME_FORMATTER);
            if (turno.isBefore(LocalDateTime.now())) {
                throw new IllegalArgumentException("No se pueden crear reservas para fechas y horas pasadas.");
            }
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("El formato de fecha/hora es incorrecto. Use: YYYY-MM-DD HH:MM:SS");
        }

        if (reservaDao.findByTurno(turno).isPresent()) {
            throw new IllegalArgumentException("Ya existe una reserva programada para el turno exacto: " + turnoStr);
        }

        Optional<Cliente> clienteOpt = clienteController.buscarPorDni(dni);
        if (clienteOpt.isEmpty()) {
            throw new IllegalArgumentException("Error: No se encontró un cliente con el DNI proporcionado: " + dni);
        }

        Optional<Auto> autoOpt = autoController.buscarPorPatente(patente);
        if (autoOpt.isEmpty()) {
            throw new IllegalArgumentException("Error: No se encontró un auto con la patente proporcionada: " + patente);
        }

        Reserva nuevaReserva = new Reserva();
        nuevaReserva.setClienteId(clienteOpt.get().getId());
        nuevaReserva.setAutoId(autoOpt.get().getId());
        nuevaReserva.setTurno(turno);
        nuevaReserva.setTipoLavado(tipoLavado);
        nuevaReserva.setPrecio(calcularPrecio(tipoLavado));

        return reservaDao.create(nuevaReserva);
    }

    public List<Reserva> listarReservas() {
        List<Reserva> reservasConIds = reservaDao.findAll();
        for (Reserva reserva : reservasConIds) {

            autoController.buscarPorId(reserva.getAutoId()).ifPresent(auto -> {
                reserva.setAuto(auto);
            });

            clienteController.buscarPorId(reserva.getClienteId()).ifPresent(cliente -> {
                reserva.setCliente(cliente);
            });
        }

        return reservasConIds;
    }

    public Optional<Reserva> buscarPorId(int id) {
        return reservaDao.findById(id);
    }

    public Reserva actualizarReserva(Integer idReserva, String dni, String patente, String turnoStr, String tipoLavado) {
        LocalDateTime nuevoTurno;
        try {
            nuevoTurno = LocalDateTime.parse(turnoStr, DATE_TIME_FORMATTER);
            
            if (nuevoTurno.isBefore(LocalDateTime.now())) {
                throw new IllegalArgumentException("No se pueden actualizar reservas a fechas y horas pasadas.");
            }
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("El formato de fecha/hora es incorrecto. Use: YYYY-MM-DD HH:MM:SS");
        }

        
        Optional<Reserva> reservaDuplicadaOpt = reservaDao.findByTurno(nuevoTurno);
        if (reservaDuplicadaOpt.isPresent() && !reservaDuplicadaOpt.get().getId().equals(idReserva)) {
            throw new IllegalArgumentException("Ya existe otra reserva programada para el turno exacto: " + turnoStr);
        }

       
        Optional<Cliente> clienteOpt = clienteController.buscarPorDni(dni);
        if (clienteOpt.isEmpty()) {
            throw new IllegalArgumentException("Error: No se encontró un cliente con el DNI proporcionado: " + dni);
        }

        Optional<Auto> autoOpt = autoController.buscarPorPatente(patente);
        if (autoOpt.isEmpty()) {
            throw new IllegalArgumentException("Error: No se encontró un auto con la patente proporcionada: " + patente);
        }

        
        Optional<Reserva> reservaOriginalOpt = reservaDao.findById(idReserva);
        if (reservaOriginalOpt.isEmpty()) {
            throw new IllegalArgumentException("Error de actualización: La reserva original (ID: " + idReserva + ") no fue encontrada.");
        }

        Reserva reservaActualizar = reservaOriginalOpt.get();

     
        reservaActualizar.setId(idReserva); 
        reservaActualizar.setClienteId(clienteOpt.get().getId());
        reservaActualizar.setAutoId(autoOpt.get().getId());
        reservaActualizar.setTurno(nuevoTurno);
        reservaActualizar.setTipoLavado(tipoLavado);
        reservaActualizar.setPrecio(calcularPrecio(tipoLavado));

       
        return reservaDao.update(reservaActualizar);
    }

    public boolean eliminarReserva(Integer id) {
        return reservaDao.deleteById(id);
    }

    public double calcularPrecio(String tipoLavado) {
        return switch (tipoLavado.toUpperCase()) {
            case "STANDARD" ->
                1000.00;
            case "PREMIUM" ->
                2500.00;
            case "DELUXE" ->
                4000.00;
            default ->
                0.00;
        };
    }
}
