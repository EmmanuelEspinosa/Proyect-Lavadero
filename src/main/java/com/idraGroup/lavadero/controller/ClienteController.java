package com.idraGroup.lavadero.controller;

import com.idraGroup.lavadero.dao.ClienteDao;
import com.idraGroup.lavadero.model.Cliente;
import java.util.List;
import java.util.Optional;
/**
 * Class <code>ClienteController</code> del proyecto Lavadero.
 * <p>
 * Archivo: <code>com/idraGroup/lavadero/controller/ClienteController.java</code>
 * Paquete: <code>com.idraGroup.lavadero.controller</code>
 * </p>
 */

public class ClienteController {

    private final ClienteDao clienteDao;

    public ClienteController(ClienteDao clienteDao) {
        this.clienteDao = clienteDao;
    }
/**
 * crearCliente.
 * @param cliente Cliente.

 * @return Cliente.
 */

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
/**
 * listarTodos.

 * @return List<Cliente>.
 */

    public List<Cliente> listarTodos() {
        return clienteDao.findAll();
    }
/**
 * actualizarCliente.
 * @param cliente Cliente.

 * @return Cliente.
 */

    public Cliente actualizarCliente(Cliente cliente) {
        return clienteDao.update(cliente);
    }
/**
 * eliminarCliente.
 * @param id Integer.

 * @return boolean.
 */

    public boolean eliminarCliente(Integer id) {
        return clienteDao.deleteById(id);
    }
/**
 * buscarPorDni.
 * @param dni String.

 * @return Optional<Cliente>.
 */

    public Optional<Cliente> buscarPorDni(String dni) {
        return clienteDao.findByDni(dni);
    }
/**
 * buscarPorId.
 * @param id Integer.

 * @return Optional<Cliente>.
 */
    
       public Optional<Cliente> buscarPorId(Integer id) {
        return clienteDao.findById(id);
    }
}
