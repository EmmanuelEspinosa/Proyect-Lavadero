package com.idraGroup.lavadero.dao.jdbc;
import com.idraGroup.lavadero.dao.AutoDao;
import com.idraGroup.lavadero.model.Auto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AutoDAOJDBC implements AutoDao {

    private static final String INSERT_SQL = "INSERT INTO AUTOS (patente, tipo) VALUES (?, ?)";
    private static final String FIND_BY_ID_SQL = "SELECT id, patente, tipo FROM AUTOS WHERE id = ?";
    private static final String FIND_ALL_SQL = "SELECT id, patente, tipo FROM AUTOS";
    private static final String UPDATE_SQL = "UPDATE AUTOS SET patente = ?, tipo = ? WHERE id = ?";
    private static final String DELETE_SQL = "DELETE FROM AUTOS WHERE id = ?";
    private static final String FIND_BY_PATENTE_SQL = "SELECT id, patente, tipo FROM autos WHERE patente = ?";

    // --- Auxiliar de Mapeo ---
    private Auto mapResultSetToAuto(ResultSet rs) throws SQLException {
        Auto auto = new Auto();
        auto.setId(rs.getInt("id"));
        auto.setPatente(rs.getString("patente"));
        auto.setTipo(rs.getString("tipo"));
        return auto;
    }

    @Override
    public Auto create(Auto a) {
        try (Connection conn = dbConnection.conectar();
             PreparedStatement pstmt = conn.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, a.getPatente().trim().toUpperCase()); // Guarda la patente en mayúsculas
            pstmt.setString(2, a.getTipo());

            pstmt.executeUpdate();

            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    a.setId(rs.getInt(1));
                }
            }
            return a;
        } catch (SQLException e) {
            // Manejo de error de DNI duplicado, conexión, etc.
            throw new RuntimeException("Error al guardar el auto en la base de datos.", e);
        }
    }

    @Override
    public Optional<Auto> findById(Integer id) {
        try (Connection conn = dbConnection.conectar();
             PreparedStatement pstmt = conn.prepareStatement(FIND_BY_ID_SQL)) {

            pstmt.setInt(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapResultSetToAuto(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar auto por ID.", e);
        }
        return Optional.empty();
    }

    @Override
    public List<Auto> findAll() {
        List<Auto> autos = new ArrayList<>();
        try (Connection conn = dbConnection.conectar();
             PreparedStatement pstmt = conn.prepareStatement(FIND_ALL_SQL);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                autos.add(mapResultSetToAuto(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar todos los autos.", e);
        }
        return autos;
    }

    @Override
    public Auto update(Auto a) {
        if (a.getId() <= 0) {
            throw new IllegalArgumentException("El auto debe tener un ID para ser actualizado.");
        }
        
        try (Connection conn = dbConnection.conectar();
             PreparedStatement pstmt = conn.prepareStatement(UPDATE_SQL)) {

            pstmt.setString(1, a.getPatente().trim().toUpperCase());
            pstmt.setString(2, a.getTipo());
            pstmt.setInt(3, a.getId()); 

            pstmt.executeUpdate();
            
            return a;
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar el auto en la base de datos.", e);
        }
    }

    @Override
    public boolean deleteById(Integer id) {
        try (Connection conn = dbConnection.conectar();
             PreparedStatement pstmt = conn.prepareStatement(DELETE_SQL)) {

            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar el auto de la base de datos.", e);
        }
    }
    
    @Override
    public Optional<Auto> findByPatente(String patente){
    try (Connection conn = dbConnection.conectar();
         PreparedStatement pstmt = conn.prepareStatement(FIND_BY_PATENTE_SQL)) {
        pstmt.setString(1, patente.trim().toUpperCase()); 

        try (ResultSet rs = pstmt.executeQuery()) {  
            if (rs.next()) {

                Auto autoEncontrado = mapResultSetToAuto(rs);
                return Optional.of(autoEncontrado);
            }
        }
    } catch (SQLException e) {
        throw new RuntimeException("Error al buscar auto por patente en la base de datos.", e);
    }
    return Optional.empty();
    
    }
}
