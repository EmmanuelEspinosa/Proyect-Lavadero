package com.idraGroup.lavadero.dao.mem;

import com.idraGroup.lavadero.model.Auto;
import com.idraGroup.lavadero.model.Cliente;
import com.idraGroup.lavadero.model.Reserva;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public final class DataStore {
    private DataStore() {}

    // Listas “persistentes” en memoria
    public static final List<Cliente> CLIENTES = new ArrayList<>();
    public static final List<Auto> AUTOS = new ArrayList<>();
    public static final List<Reserva> RESERVAS = new ArrayList<>();

    // Autoincrementales simples
    public static final AtomicInteger SEQ_CLIENTE = new AtomicInteger(1);
    public static final AtomicInteger SEQ_AUTO = new AtomicInteger(1);
    public static final AtomicInteger SEQ_RESERVA = new AtomicInteger(1);
}
