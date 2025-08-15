/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.controlador;

import com.mycompany.libreria.modelo.Libro;
import com.mycompany.libreria.modelo.LibroDao;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author manja
 */
public class LibroControlador extends HttpServlet {

    private final LibroDao libroDao = new LibroDao();
    private final String ListarLibros = "/Vista/listar.jsp";
    private final String NuevosLibros = "/Vista/nuevo.jsp";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // usar un switch para definir el acciones a realizar
        String action = request.getParameter("accion");
        switch (action) {
            case "listar":
                ListarTodosLibros(request, response);
                break;
            case "nuevo":
                Nuevo(request, response);
                break;
            case "guardar":
                Guardar(request, response);
                break;
            case "editar":
                Editar(request, response);
                break;
            case "eliminar":
                 Eliminar(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Acción no válida: " + action);
        }
    }

    protected void ListarTodosLibros(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("libros", libroDao.ListarTodosLibros());
        request.getRequestDispatcher(ListarLibros).forward(request, response);
    }

    // crear un metodo el cual nos permitira crear un nuevo libro
    private void Nuevo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("libro", new Libro());
        request.getRequestDispatcher(NuevosLibros).forward(request, response);
    }

    private void Guardar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Libro lib = new Libro();
        lib.setId(Integer.parseInt(request.getParameter("id")));
        lib.setTitulo(request.getParameter("titulo"));
        lib.setAutor(request.getParameter("autor"));
        lib.setPrecio(Float.parseFloat(request.getParameter("precio")));
        // validamos por id si actualizar o va realizar la operación insercción de nuevo libro en la tabla de la bases de datos librerias
        //int result =libroDao.RegistrarNuevoLibro(lib);
        int result;
        // realizamos una pequeña validación
        if(lib.getId()==0){
            result=libroDao.RegistrarNuevoLibro(lib);
        }else{
            // si el id es diferente de 0 quiere decir que lo voy a editar
            result = libroDao.EditarNuevoLibro(lib);
        }
        if(result >0){
            request.getSession().setAttribute("success", "Se han guardado los datos satisfactoriamente :)");
            response.sendRedirect("LibroControlador?accion=listar");
        }else{
            request.setAttribute("libro", lib);
            request.getSession().setAttribute("error", "No se pudo guardar datos");
            request.getRequestDispatcher(NuevosLibros).forward(request,response);
        }
    }
     private void Editar(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
         int id = Integer.parseInt(request.getParameter("id"));
         Libro lib = libroDao.BuscarPorIdLibro(id);
         // creamos una pequeña validación
         if(lib !=null){
             request.setAttribute("libro", lib);
             request.getRequestDispatcher(NuevosLibros).forward(request, response);
         }else{
             request.getSession().setAttribute("error", "No se el libro con ID"+id);
             response.sendRedirect("LibroControlador?accion=listar");
         }
    }
     
    // metodo para eliminar los libros de la tabla libro
     private void Eliminar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     int id = Integer.parseInt(request.getParameter("id"));
     int result = libroDao.EliminarLibro(id);
     // validamos a traves de una condicional
     if(result > 0){
         request.getSession().setAttribute("success", "El libro ha sido " + " "+ id + "eliminado!");
     }else{
         request.getSession().setAttribute("error", "No se pudo eliminar el libro");
     }
     response.sendRedirect("LibroControlador?accion=listar");
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    

   

}
