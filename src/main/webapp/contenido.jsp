<%@page import="org.hibernate.internal.build.AllowSysOut"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" import="java.util.List" 
    import="proyectoFinal.entities.User, proyectoFinal.entities.User ,proyectoFinal.utils.*"%>
<!DOCTYPE html>
<html lang="es-ES">

<head>
	<meta charset="utf-8">
	<meta name="viewport">
	<title>cabecera</title>
<!-- 	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous"> -->
	<link href="contenido.css" rel="stylesheet" type="text/css">	
</head>

<body> 


<% 

User usuario = (User)request.getAttribute("Usuario");
String tipo = usuario.getRol();
boolean notAdmin = !tipo.toLowerCase().contains("admin");
//System.out.println("notAdmin= "+notAdmin);
%>

<section class="site">
  <nav>
    <a href="LoginServlet?accion=accesoadmin" <%if (notAdmin) {%> style="pointer-events: none" <%}%>>Gestion de usuario</a>
    <a href="https://www.rincondelvago.com/">Trabajo</a>
    <a href="https://www.filmaffinity.com/es/main.html">Ocio</a>
    <a href="LoginServlet?accion=logout">LogOut</a>
  </nav>
  <blockquote>
     <img src="imagen2.jpg" height="500px" align="left" >
     <ul>
	<li>Lo que no te mata, te hace más fuerte</li>
	<li>Aquello que se hace por amor está siempre más allá del bien y del mal</li>
	<li>No hay hechos, solo interpretaciones</li>
	<li>La esperanza es el peor de los males, pues prolonga el tormento del hombre</li>
	<li>El individuo ha luchado siempre para no ser absorbido por la tribu. Si lo intentas, a menudo estarás solo, y a veces asustado. Pero ningún precio es demasiado alto por el privilegio de ser uno mismo</li>
	<li>Se tú mismo; todos los demás ya están ocupados</li>
	<li>La moral es la mejor de todas las limas de los caracteres vulgares</li>
	<li>Dios ha muerto</li>
	<li>Sin música la vida sería un error</li>
	<li>Al final todo es voluntad de poder, y nada más que voluntad de poder</li>
	<li>El hecho de vivir es estar enfermo de futuro</li>
     </ul>

― friedrich nietzsche
  </blockquote>
</section>

<!--     <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script> -->
</body>

</html>