<%-- 
    Document   : listar
    Created on : 14/08/2025, 6:25:41 p. m.
    Author     : manja
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>listar libros</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-LN+7fdVzj6u52u30Kp6M/trliBMCMKTyK833zpbD+pXdCLuTusPj697FH4R/5mcr" crossorigin="anonymous">
    <link href="${pageContext.request.contextPath}/Public/css/fontawesome.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/Public/css/solid.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/Public/css/regular.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/Public/css/brands.min.css" rel="stylesheet">
  </head>
  <body style="background:#FFDEAD; ">
      <div class="container">
          <div class="card mt-4">
              <div class="card-body">
                  <h2><center>Biblioteca Nacional de Colombia | Gestión de Libros</center></h2>
                  <hr/>
                  <a href="LibroControlador?accion=nuevo" class="btn btn-success btn-sm">
                      <i class="fa-solid fa-plus"></i> Nuevo Libro</a>
                  <jsp:include page="../componentes/Mensajes.jsp" />
                  <table class ="table  table-striped table-hover">
                      <thead>
                          <tr>
                              <th>ID</th>
                              <th>Titulo</th>
                              <th>Autor</th>
                              <th>Precio</th>
                              <th>Acciones</th>
                          </tr>
                      </thead>
                      <tbody>
                          <c:forEach items="${libros}" var="item">
                              <tr>
                                  <td>${item.id}</td>
                                  <td>${item.titulo}</td>
                                  <td>${item.autor}</td>
                                  <td>${item.precio}</td>
                                  <td>
                                      <a href="LibroControlador?accion=editar&id=${item.id}" class="btn btn btn-warning btn-sm">
                                         <i class="fa-solid fa-pencil"></i> 
                                      </a>
                                          <a href="LibroControlador?accion=eliminar&id=${item.id}" class="btn btn-danger btn-sm">
                                          <i class="fa-solid fa-trash"></i>
                                      </a>
                                  </td>
                                 
                              </tr>
                          </c:forEach>
                          <c:if test="${libros.size()==0}">
                              <tr>
                                  <td colspan="5"> No hay Registros</td>
                              </tr>
                          </c:if>
                      </tbody>
                  </table>
              </div>
          </div>
      </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/js/bootstrap.bundle.min.js" integrity="sha384-ndDqU0Gzau9qJ1lfW4pNLlhNTkCfHzAVBReH9diLvGRem5+R9g2FzA8ZGN954O5Q" crossorigin="anonymous"></script>
    <script src="${pageContext.request.contextPath}/Public/js/fontawesome.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/Public/js/solid.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/Public/js/brands.min.js" type="text/javascript"></script>
  </body>
</html>
