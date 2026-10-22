/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.avanzadaii;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
/**
 *
 * @author 16
 */
public class CRUD {

public static void main(String[] args) throws SQLException {
        System.out.println("=== Ejecutando operaciones CRUD en tabla Alumnos ===");
        
        // Obtener la conexion
        Connection cn = ConexionDB.getConexion();
        if(cn == null){
            System.out.println("No se puede establecer la conexión");
            return;
        }
        
        // Declarar los objetos de SQL para nuestra base
        PreparedStatement psInsertar = null;
        PreparedStatement psActualizar = null;
        PreparedStatement psEliminar = null;
        PreparedStatement psSeleccionar = null;
        ResultSet rs = null;
        
        try{
            // --- 1. INSERTAR (CREATE) ---
            System.out.println("\n1. Intentando insertar nuevo Alumno...");
            String psInsert = "INSERT INTO Alumnos(nombre, correo) VALUES(?,?)";
            psInsertar = cn.prepareStatement(psInsert);
            
            psInsertar.setString(1, "Neymar Pele");
            psInsertar.setString(2, "neymar.pele@uth.hn");
            
            int filasInsertadas = psInsertar.executeUpdate();
            if(filasInsertadas > 0){
                System.out.println("¡Alumno insertado con éxito!");
            }

            // --- 2. ACTUALIZAR (UPDATE) ---
               System.out.println("\n2. Intentando actualizar nombre y correo de un Alumno...");
            
            int idParaActualizar = 15; 
            // Modificamos la consulta para actualizar "nombre" y "correo"
            String sqlUpdate = "UPDATE Alumnos SET nombre = ?, correo = ? WHERE id = ?";
            psActualizar = cn.prepareStatement(sqlUpdate);
            
            // Pasamos los 3 valores en el orden en que aparecen los signos de interrogación (?)
            psActualizar.setString(1, "Lamine Yamal");             // 1er ?: nombre
            psActualizar.setString(2, "Lamine.Yaml@uth.hn");      // 2do ?: correo
            psActualizar.setInt(3, idParaActualizar);              // 3er ?: id
            
            int filasActualizadas = psActualizar.executeUpdate();
            if(filasActualizadas > 0){
                System.out.println("¡Alumno con ID " + idParaActualizar + " actualizado con éxito!");
            } else {
                System.out.println("No se encontró ningún alumno con el ID " + idParaActualizar);
            }

            // --- 3. ELIMINAR (DELETE) ---
            System.out.println("\n3. Intentando eliminar un Alumno...");
            // NOTA: Cambia el '2' por un ID que quieras borrar.
            int idParaEliminar = 17; 
            String sqlDelete = "DELETE FROM Alumnos WHERE id = ?";
            psEliminar = cn.prepareStatement(sqlDelete);
            
            psEliminar.setInt(1, idParaEliminar);
            
            int filasEliminadas = psEliminar.executeUpdate();
            if(filasEliminadas > 0){
                System.out.println("¡Alumno con ID " + idParaEliminar + " eliminado con éxito!");
            } else {
                System.out.println("No se encontró ningún alumno con el ID " + idParaEliminar);
            }

            // --- 4. CONSULTAR (READ) ---
            System.out.println("\n4. Consulta final de lista de Alumnos en la Base de Datos");
            String sqlSelect = "SELECT id, nombre, correo FROM Alumnos";
            psSeleccionar = cn.prepareStatement(sqlSelect);
            rs = psSeleccionar.executeQuery();
            
            System.out.println("\n=======================================================");
            System.out.println("  ID   | Nombre               | Correo                 ");
            System.out.println("=======================================================");
            while(rs.next()){
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String correo = rs.getString("correo");
                System.out.printf("%-6d | %-20s | %s\n", id, nombre, correo);
            }
            System.out.println("=======================================================");
            
        } catch(SQLException e) {
            System.err.println("Ocurrió un error con la base de datos: " + e.getMessage());
        } finally {
            // Cerrar la conexión de manera obligatoria y todos los PreparedStatement
            if(rs != null) rs.close();
            if(psInsertar != null) psInsertar.close();
            if(psActualizar != null) psActualizar.close();
            if(psEliminar != null) psEliminar.close();
            if(psSeleccionar != null) psSeleccionar.close();
            ConexionDB.cerrarConexion();
        }
    }
}