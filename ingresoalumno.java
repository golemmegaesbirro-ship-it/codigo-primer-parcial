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
public class ingresoalumno {

    /**
     * @param args the command line arguments
     */
public static void main(String[] args) throws SQLException {
        System.out.println("===Ejecutando operaciones en tabla Alumnos ===");
        //obtener la conexion
        Connection cn = ConexionDB.getConexion();
        if(cn == null){
            System.out.println("No se puede establecer la conexión");
            return;
        }
        //Declarar los objetos de SQL para nuestra base
        PreparedStatement psInsertar = null;
        PreparedStatement psSeleccionar = null;
        ResultSet rs = null;
        try{
            System.out.println("\n 1. Intentando insertar nuevo Alumno...");
            String psInsert = "INSERT INTO Alumnos(nombre, correo) VALUES(?,?)";
            psInsertar = cn.prepareStatement(psInsert);
            
            //asignaremos valores a nuestros paremetros
            psInsertar.setString(1, "Messi Ronaldo");
            psInsertar.setString(2, "messi.ronaldo@uth.hn");
            // ejecutar la inserción
            int filasAfectadas = psInsertar.executeUpdate();
            if(filasAfectadas > 0){
                System.out.println("!Alumno insertado con exito!");
            }
        }catch(SQLException e){
            System.err.println("Ocurrio un error con la base de datos" + e.getMessage());
        }finally{
            //cerrar la conexion de manera obligatoria
            if(rs != null){
                rs.close();
            }
            if(psInsertar != null)psInsertar.close();
            ConexionDB.cerrarConexion();
        }
    }
}