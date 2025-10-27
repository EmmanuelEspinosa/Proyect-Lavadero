package com.idraGroup.lavadero.controller;

import com.idraGroup.lavadero.dao.ClienteDao;
import com.idraGroup.lavadero.model.Cliente;
import java.util.List;
import java.util.Optional;

public class ClienteController {

    private final ClienteDao clienteDao;

    public ClienteController(ClienteDao clienteDao) {
        this.clienteDao = clienteDao;
    }

    public Cliente crearCliente(Cliente cliente) {
        if (cliente.getNombre() == null || cliente.getNombre().isBlank()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
        if (cliente.getDni() == null || cliente.getDni().isBlank()) {
            throw new IllegalArgumentException("El DNI no puede estar vacío.");
        }
        if (cliente.getTelefono() == null || cliente.getTelefono().isBlank()) {
            throw new IllegalArgumentException("El telefono no puede estar vacío.");
        }
        return clienteDao.create(cliente);
    }

    public List<Cliente> listarTodos() {
        return clienteDao.findAll();
    }

    public Cliente actualizarCliente(Cliente cliente) {
        return clienteDao.update(cliente);
    }

    public boolean eliminarCliente(Integer id) {
        return clienteDao.deleteById(id);
    }

    public Optional<Cliente> buscarPorDni(String dni) {
        return clienteDao.findByDni(dni);
    }
    
       public Optional<Cliente> buscarPorId(Integer id) {
        return clienteDao.findById(id);
    }
}
