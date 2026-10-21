/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.avanzadaii;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author 16
 */

public class ConexionDB {

    private static final String URL = "jdbc:sqlserver://localhost:1433;"
            + "databaseName=AvanzadaII;"
            + "encrypt=true;"
            + "trustServerCertificate=true;";
    private static final String USER = "sa";
    private static final String PASSWORD = "yahir101";

    private static Connection conexion = null;

    private ConexionDB() {}

    public static Connection getConexion() {
        try {
            if (conexion == null || conexion.isClosed()) {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                conexion = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("-> Conexión exitosa a SQL Server");
            }
        } catch (ClassNotFoundException e) {
            System.err.println("Error: No se encontró el driver mssql-jdbc: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Error al conectar con SQL Server: " + e.getMessage());
        }
        return conexion;
    }

    public static void cerrarConexion() {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
                System.out.println("-> Conexión cerrada");
            }
        } catch (SQLException e) {
            System.err.println("Error al cerrar la conexión: " + e.getMessage());
        }
    }
}