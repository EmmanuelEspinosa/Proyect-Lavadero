
package com.idraGroup.lavadero.dao.jdbc;
import java.sql.*;
public class dbConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/reservas_lavadero";
    private static final String USER = "root";
    private static final String PASSWORD = "72517";
    
    public static Connection conectar(){
        Connection con= null;
        try{
            con=DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexion Exitosa");
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return con;
    }
}

