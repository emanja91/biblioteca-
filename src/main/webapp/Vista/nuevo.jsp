<%-- 
    Document   : nuevo
    Created on : 15/08/2025, 6:41:34 a. m.
    Author     : manja
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Biblioteca | Nuevo Libro</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-LN+7fdVzj6u52u30Kp6M/trliBMCMKTyK833zpbD+pXdCLuTusPj697FH4R/5mcr" crossorigin="anonymous">
  </head>
  <body style="background:#FFDEAD; ">
      <div class="container" >
          <div class="card mt-4">
            
              <div class="card-body">
                    <h2 class="mt-3">${libro.id==0 ? "Nuevo":"Editar"} Libro</h2>
              <hr/>
                  <form action="LibroControlador" method="post">
                      <div class="mb-3">
                          <label>Titulo</label>
                          <input type="text" id="titulo" name="titulo" value="${libro.titulo}"  class="form-control" placeholder="Digite el titulo del libro" required="">
                      </div>
                      <div class="mb-3">
                          <label>Autor</label>
                          <input type="text" id="autor" name="autor" value="${libro.autor}"  class="form-control" placeholder="Digite el nombre del autor" required="">
                      </div>
                      <div class="mb-3">
                          <label>Precio</label>
                          <input type="text"  id="precio" name="precio" value="${libro.precio}" required="" class="form-control" placeholder="Digite el precio del libro">
                      </div>
                      <div class="mb-3">
                          <input type="hidden" name="id" value="${libro != null ? libro.id : 0}">
                          <input type="hidden" name ="accion" value="guardar">
                          <button class="btn btn-primary btn-block">
                              <i class="fa-solid fa-floppy-disk"></i> Guardar
                          </button>
                          <a href="LibroControlador?accion=listar" class="btn btn-danger">
                              <i class="fa-solid fa-arrow-left"></i> Volver atras
                          </a>
                      </div>
                  </form>
              </div>
          </div>
      </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/js/bootstrap.bundle.min.js" integrity="sha384-ndDqU0Gzau9qJ1lfW4pNLlhNTkCfHzAVBReH9diLvGRem5+R9g2FzA8ZGN954O5Q" crossorigin="anonymous"></script>
  </body>
</html>