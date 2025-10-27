package com.idraGroup.lavadero.dao.jdbc;

import com.idraGroup.lavadero.dao.ClienteDao; 
import com.idraGroup.lavadero.model.Cliente;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
/**
 * Class <code>ClienteDAOJDBC</code> del proyecto Lavadero.
 * <p>
 * Archivo: <code>com/idraGroup/lavadero/dao/jdbc/ClienteDaoJdbc.java</code>
 * Paquete: <code>com.idraGroup.lavadero.dao.jdbc</code>
 * </p>
 */

public class ClienteDAOJDBC implements ClienteDao {
    
    

    private static final String CREATE_SQL = "INSERT INTO clientes (dni, nombre, telefono) VALUES (?, ?, ?)";
    private static final String FIND_BY_ID_SQL = "SELECT id, dni, nombre, telefono FROM clientes WHERE id = ?";
    private static final String FIND_ALL_SQL = "SELECT id, dni, nombre, telefono FROM clientes";
    private static final String UPDATE_SQL = "UPDATE clientes SET dni = ?, nombre = ?, telefono = ? WHERE id = ?";
    private static final String DELETE_SQL = "DELETE FROM clientes WHERE id = ?";
    private static final String FIND_BY_DNI_SQL = "SELECT id, dni, nombre, telefono FROM clientes WHERE dni = ?";
    



    private Cliente mapResultSetToCliente(ResultSet rs) throws SQLException {
        Cliente cliente = new Cliente();
        cliente.setId(rs.getInt("id"));
        cliente.setDni(rs.getString("dni"));
        cliente.setNombre(rs.getString("nombre"));
        cliente.setTelefono(rs.getString("telefono"));
        return cliente;
    }
/**
 * create.
 * @param cliente Cliente.

 * @return Cliente.
 */
    
    @Override
    public Cliente create(Cliente cliente) {
        try (Connection conn = dbConnection.conectar();
             PreparedStatement pstmt = conn.prepareStatement(CREATE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            
            pstmt.setString(1, cliente.getDni().trim());
            pstmt.setString(2, cliente.getNombre().trim());
            pstmt.setString(3, cliente.getTelefono().trim());
            
            int affectedRows = pstmt.executeUpdate();
            
            if (affectedRows == 0) {
                throw new SQLException("La creación del cliente falló, no se insertaron filas.");
            }
            
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    cliente.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("La creación del cliente falló, no se obtuvo ID generado.");
                }
            }
            return cliente;
        } catch (SQLException e) {
            throw new RuntimeException("Error al crear cliente: " + e.getMessage(), e);
        }
    }
/**
 * findById.
 * @param id Integer.

 * @return Optional<Cliente>.
 */

    @Override
    public Optional<Cliente> findById(Integer id) {
        try (Connection conn = dbConnection.conectar();
             PreparedStatement pstmt = conn.prepareStatement(FIND_BY_ID_SQL)) {
            
            pstmt.setInt(1, id);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapResultSetToCliente(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar cliente por ID: " + e.getMessage(), e);
        }
        return Optional.empty();
    }
/**
 * findAll.

 * @return List<Cliente>.
 */
    

    @Override
    public List<Cliente> findAll() {
        List<Cliente> clientes = new ArrayList<>();
        try (Connection conn = dbConnection.conectar();
             PreparedStatement pstmt = conn.prepareStatement(FIND_ALL_SQL);
             ResultSet rs = pstmt.executeQuery()) {
            
            while (rs.next()) {
                clientes.add(mapResultSetToCliente(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar todos los clientes: " + e.getMessage(), e);
        }
        return clientes;
    }
/**
 * update.
 * @param cliente Cliente.

 * @return Cliente.
 */
    

    @Override
    public Cliente update(Cliente cliente) {
        try (Connection conn = dbConnection.conectar();
             PreparedStatement pstmt = conn.prepareStatement(UPDATE_SQL)) {
            
            pstmt.setString(1, cliente.getDni().trim());
            pstmt.setString(2, cliente.getNombre().trim());
            pstmt.setString(3, cliente.getTelefono().trim());
            pstmt.setInt(4, cliente.getId());
            
            pstmt.executeUpdate();
            return cliente;
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar cliente: " + e.getMessage(), e);
        }
    }
/**
 * deleteById.
 * @param id Integer.

 * @return boolean.
 */

    @Override
    public boolean deleteById(Integer id) {
        try (Connection conn = dbConnection.conectar();
             PreparedStatement pstmt = conn.prepareStatement(DELETE_SQL)) {
            
            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar cliente por ID: " + e.getMessage(), e);
        }
    }
/**
 * findByDni.
 * @param dni String.

 * @return Optional<Cliente>.
 */


    @Override
    public Optional<Cliente> findByDni(String dni) {
        try (Connection conn = dbConnection.conectar();
             PreparedStatement pstmt = conn.prepareStatement(FIND_BY_DNI_SQL)) {
            
            pstmt.setString(1, dni.trim()); 

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapResultSetToCliente(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar cliente por DNI: " + e.getMessage(), e);
        }
        return Optional.empty();
    }
}