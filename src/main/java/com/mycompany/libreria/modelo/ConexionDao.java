/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.libreria.modelo;


import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author manja
 */
public class ConexionDao {
    // Declaramos variables para la conexion con la base de datos;
    public static final String url="jdbc:mysql://localhost:3306/libreria";
    public static final String username ="root";
    public static final String pass="Sena2025..";
    
    // creamos una función para conectarnos a la base de datos
    
    public static Connection ObtenerConexion(){
          Connection conn = null;
            try {
                  Class.forName("com.mysql.cj.jdbc.Driver");
                  conn = DriverManager.getConnection(url,username,pass);
              } catch (Exception e) {
                  System.out.println("Error: " + e.getMessage());
              }

              return conn;
    }
}
