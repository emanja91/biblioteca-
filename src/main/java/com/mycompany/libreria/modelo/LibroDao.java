/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.libreria.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author manja
 */
public class LibroDao {
    // declaración de variables
    private Connection conn = null;
    private PreparedStatement stmt = null;
    private ResultSet rs = null;
    
    // se crea un nuevo metodo que permita listar todos los libros registrados en la tabla libro
    public ArrayList<Libro> ListarTodosLibros(){
        ArrayList<Libro> lb = new ArrayList<>();
        try{
            conn = ConexionDao.ObtenerConexion();
            String sql = "SELECT * FROM libro";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            //usamos un ciclo
            while(rs.next()){
                Libro libro = new Libro();
                libro.setId(rs.getInt("id_libro"));
                libro.setTitulo(rs.getString("titulo"));
                libro.setAutor(rs.getString("autor"));
                libro.setPrecio(rs.getFloat("precio"));
                lb.add(libro);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(conn !=null){
                    conn.close();
                }
                if(rs !=null){
                    rs.close();
                }
                if(stmt !=null){
                    stmt.close();
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return lb;
    }
    
    // creamos un nuevo metodo este metodo va retornar valor
    public int RegistrarNuevoLibro(Libro lib){
        int result = 0;
        try{
             conn = ConexionDao.ObtenerConexion();
             String sql="INSERT INTO libro(titulo,autor,precio) values(?,?,?);";
             stmt = conn.prepareStatement(sql);
             stmt.setString(1, lib.getTitulo());
             stmt.setString(2,lib.getAutor());
             stmt.setFloat(3, lib.getPrecio());
             result =stmt.executeUpdate(); // si se inserto correctamente nos retorna un valor mayor a 0 
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(conn !=null){
                    conn.close();
                }
                
                if(stmt !=null){
                    stmt.close();
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return result;
    }
    
    //Metodo para Editar los datos del libro
     public int EditarNuevoLibro(Libro lib){
        int result = 0;
        try{
             conn = ConexionDao.ObtenerConexion();
             String sql="UPDATE libro SET titulo =?,autor=?,precio=? WHERE id_libro=? ;";
             stmt = conn.prepareStatement(sql);
             stmt.setString(1, lib.getTitulo());
             stmt.setString(2,lib.getAutor());
             stmt.setFloat(3, lib.getPrecio());
             stmt.setInt(4, lib.getId());
             result =stmt.executeUpdate(); // si se inserto correctamente nos retorna un valor mayor a 0 
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(conn !=null){
                    conn.close();
                }
                
                if(stmt !=null){
                    stmt.close();
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return result;
    }
    // se crea un nuevo metodo para eliminar un libro
      public int EliminarLibro(int id){
        int result = 0;
        try{
             conn = ConexionDao.ObtenerConexion();
             String sql="DELETE  FROM libro WHERE id_libro=?";
             stmt = conn.prepareStatement(sql);
             stmt.setInt(1, id);
             result =stmt.executeUpdate();  
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(conn !=null){
                    conn.close();
                }
                
                if(stmt !=null){
                    stmt.close();
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return result;
    }
   // vamos a crear un metodo que nos ayude a buscar por Id
     public Libro BuscarPorIdLibro(int id){
         // lo inicializamos en vacio
        Libro lib = null;
        try{
            conn = ConexionDao.ObtenerConexion();
            String sql = "SELECT * FROM libro WHERE id_libro=?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            //usamos un ciclo
            if(rs.next()){
                lib = new Libro();
                lib.setId(rs.getInt("id_libro"));
                lib.setTitulo(rs.getString("titulo"));
                lib.setAutor(rs.getString("autor"));
                lib.setPrecio(rs.getFloat("precio"));
                
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(conn !=null){
                    conn.close();
                }
                if(rs !=null){
                    rs.close();
                }
                if(stmt !=null){
                    stmt.close();
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return lib;
    }
}
