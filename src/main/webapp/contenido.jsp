<%@page import="org.hibernate.internal.build.AllowSysOut"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" import="java.util.List" 
    import="proyectoFinal.entities.User, proyectoFinal.entities.User ,proyectoFinal.utils.*"%>
<!DOCTYPE html>
<html lang="es-ES">

<head>


<!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport">

	<title>contenido</title>
	<link href="contenido.css" rel="stylesheet" type="text/css">
	
	<link rel="stylesheet" href="css/bootstrap.min.css">
	<script src="js/bootstrap.bundle.min.js"></script>	
	<script type="text/javascript">
				function confirmarLogOut() {
				var mensaje = confirm("¿Quiere cerrar la sesión?");
	
				if (mensaje) {
						var enlace="LoginServlet?accion=logout";
						window.location.href = enlace;
				}   
				}
		</script>
	
</head>


<body> 
<jsp:include page="cabecera.html"></jsp:include>
<div class="container">


<% 

User u = (User)request.getAttribute("Usuario");
String tipo = u.getRol();
boolean notAdmin = !tipo.toLowerCase().contains("admin");
Propiedades pro = new Propiedades();

%>

<div class="row">
<section class="site">
  
  <nav>
  	<a href="https://weyland-yutani.proffer.dev/"><img src="wey-yu-transparent.png" width="80"></a>
	 <%if (notAdmin) {
 	 	  %><a href="userdata.jsp?usuario=<%=u.getUsuario()%>
				&retorno=contenido&nombre=<%=u.getNombre()%>
				&apellidos=<%=u.getApellidos()%>
				&dni=<%=u.getDni()%>
				&genero=<%=u.getSexo()%>
				&mail=<%=u.getEmail()%>
				&telefono=<%=u.getTelefono()%>
	    		&nacimiento=<%=Utils.dateToString(u.getNacimiento())%>   
				&rol=<%=u.getRol()%>
				
				&clave=<%=u.getClave()%>
				&id=<%=u.getIdUsuario()%>">Gestion de usuario</a><%}
	  else{ 
	 %><a href="LoginServlet?accion=ordenar">Gestion de usuarios</a><%}%>
       <a href="<%=pro.leerProper("Contenido_Enlace_1")%>"><%=pro.leerProper("Contenido_Texto_Enlace_1")%></a>
       <a href="<%=pro.leerProper("Contenido_Enlace_2")%>"><%=pro.leerProper("Contenido_Texto_Enlace_2")%></a>
       <a href="javascript:confirmarLogOut()"><%=pro.leerProper("Contenido_Texto_Enlace_LogOut")%></a>
  </nav>
<!--   <blockquote> -->
<div class="col-12 col-md-6 col-lg-3">
     <img src="imagen2.jpg" height="450px" align="left" >
</div>


<div class="col-12 col-md-6 col-lg-9">
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
</div>
<!--   </blockquote> -->
</section>
</div>
</div>

<jsp:include page="piepagina.html"></jsp:include>
</body>

</html>