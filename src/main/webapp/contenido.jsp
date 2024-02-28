<%@page import="org.hibernate.internal.build.AllowSysOut"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" import="java.util.List" 
    import="proyectoFinal.entities.User, proyectoFinal.entities.User ,proyectoFinal.utils.*"%>
<!DOCTYPE html>
<html lang="es-ES">

<head>
	<meta charset="utf-8">
	<meta name="viewport">
	<title>contenido</title>
	<link href="contenido.css" rel="stylesheet" type="text/css">
	
	<link rel="stylesheet" href="css/bootstrap.min.css">
	<script src="js/bootstrap.bundle.min.js"></script>	
	
	<script type="text/javascript">
		function aviso() {
			alert("Solo los administradores pueden gestionar usuarios");
		} 
	</script>
</head>

<body> 
<jsp:include page="cabecera.html"></jsp:include>


<% 

User usuario = (User)request.getAttribute("Usuario");
String tipo = usuario.getRol();
boolean notAdmin = !tipo.toLowerCase().contains("admin");

%>

<section class="site">
  <nav>

	 <%if (notAdmin) {
 	 	  %><a onclick="aviso()" href="#"><strike>Gestion de usuarios</strike></a><%}
	  else{ 
		  %><a href="LoginServlet?accion=accesoadmin">Gestion de usuarios</a><%
	  }%>
    <a href="https://www.rincondelvago.com/">Trabajo</a>
    <a href="https://www.filmaffinity.com/es/main.html">Ocio</a>
    <a href="LoginServlet?accion=logout">LogOut</a>
  </nav>
  <blockquote>
     <img src="imagen2.jpg" height="450px" align="left" >
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
<jsp:include page="piepagina.html"></jsp:include>

</body>

</html>