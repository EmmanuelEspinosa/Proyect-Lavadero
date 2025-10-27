package com.idraGroup.lavadero.dao.jdbc;

import com.idraGroup.lavadero.dao.ReservaDao;
import com.idraGroup.lavadero.model.Reserva;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ReservaDAOJDBC implements ReservaDao {

    
    private static final String CREATE_SQL = "INSERT INTO reservas (id_cliente, id_auto, turno, tipo_lavado, precio) VALUES (?, ?, ?, ?, ?)";
    private static final String FIND_BY_ID_SQL = "SELECT id, id_cliente, id_auto, turno, tipo_lavado, precio FROM reservas WHERE id = ?";
    private static final String FIND_ALL_SQL = "SELECT id, id_cliente, id_auto, turno, tipo_lavado, precio FROM reservas ORDER BY turno DESC";
    private static final String UPDATE_SQL = "UPDATE reservas SET id_cliente = ?, id_auto = ?, turno = ?, tipo_lavado = ?, precio = ? WHERE id = ?";
    private static final String DELETE_SQL = "DELETE FROM reservas WHERE id = ?";
    private static final String FIND_BY_TURNO_SQL = "SELECT id, id_cliente, id_auto, turno, tipo_lavado, precio FROM reservas WHERE turno = ?";

    private Reserva mapResultSetToReserva(ResultSet rs) throws SQLException {
        Reserva reserva = new Reserva();
        reserva.setId(rs.getInt("id"));
        reserva.setClienteId(rs.getInt("id_cliente"));
        reserva.setAutoId(rs.getInt("id_auto"));
        
        Timestamp turnoTimestamp = rs.getTimestamp("turno");
        reserva.setTurno(turnoTimestamp.toLocalDateTime()); 
        
        reserva.setTipoLavado(rs.getString("tipo_lavado"));
        reserva.setPrecio(rs.getDouble("precio"));
        return reserva;
    }


    @Override
    public Reserva create(Reserva reserva) {
        try (Connection conn = dbConnection.conectar();
             PreparedStatement pstmt = conn.prepareStatement(CREATE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            

            pstmt.setInt(1, reserva.getClienteId());
            pstmt.setInt(2, reserva.getAutoId());
            
            pstmt.setTimestamp(3, Timestamp.valueOf(reserva.getTurno())); 
            
            pstmt.setString(4, reserva.getTipoLavado());
            pstmt.setDouble(5, reserva.getPrecio());
            
            int affectedRows = pstmt.executeUpdate();
            
            if (affectedRows == 0) {
                throw new SQLException("La creación de la reserva falló, no se insertaron filas.");
            }
            
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    reserva.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("La creación de la reserva falló, no se obtuvo ID generado.");
                }
            }
            return reserva;
        } catch (SQLException e) {
            throw new RuntimeException("Error al crear la reserva. Revise IDs o formato de datos. Detalle: " + e.getMessage(), e);
        }
    }

    @Override
    public Optional<Reserva> findById(Integer id) {
        try (Connection conn = dbConnection.conectar();
             PreparedStatement pstmt = conn.prepareStatement(FIND_BY_ID_SQL)) {
            
            pstmt.setInt(1, id);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapResultSetToReserva(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar reserva por ID: " + e.getMessage(), e);
        }
        return Optional.empty();
    }
    

    @Override
    public List<Reserva> findAll() {
        List<Reserva> reservas = new ArrayList<>();
        try (Connection conn = dbConnection.conectar();
             PreparedStatement pstmt = conn.prepareStatement(FIND_ALL_SQL);
             ResultSet rs = pstmt.executeQuery()) {
            
            while (rs.next()) {
                reservas.add(mapResultSetToReserva(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar todas las reservas: " + e.getMessage(), e);
        }
        return reservas;
    }

    @Override
    public Reserva update(Reserva reserva) {
        try (Connection conn = dbConnection.conectar();
             PreparedStatement pstmt = conn.prepareStatement(UPDATE_SQL)) {
            
            pstmt.setInt(1, reserva.getClienteId());
            pstmt.setInt(2, reserva.getAutoId());
            pstmt.setTimestamp(3, Timestamp.valueOf(reserva.getTurno())); 
            pstmt.setString(4, reserva.getTipoLavado());
            pstmt.setDouble(5, reserva.getPrecio());
            pstmt.setInt(6, reserva.getId());
            
            pstmt.executeUpdate();
            return reserva;
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar reserva: " + e.getMessage(), e);
        }
    }
    

    @Override
    public boolean deleteById(Integer id) {
        try (Connection conn = dbConnection.conectar();
             PreparedStatement pstmt = conn.prepareStatement(DELETE_SQL)) {
            
            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar reserva por ID: " + e.getMessage(), e);
        }
    }
    
    @Override
    public Optional<Reserva> findByTurno(LocalDateTime turno) {
        try (Connection conn = dbConnection.conectar();
             PreparedStatement pstmt = conn.prepareStatement(FIND_BY_TURNO_SQL)) {
            pstmt.setTimestamp(1, Timestamp.valueOf(turno)); 

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapResultSetToReserva(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar reserva por turno: " + e.getMessage(), e);
        }
        return Optional.empty();
    }
}


