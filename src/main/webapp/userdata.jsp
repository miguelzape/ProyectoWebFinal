<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.List"%>
<!DOCTYPE html>
<html lang="es-ES">
<head>
	<meta charset="utf-8">
	<link rel="stylesheet" href="userdata.css">
	<script type="text/javascript">
		
		function validarForm() {
 		 
 		 let p1 = document.forms["formulario1"]["password"].value
 		 let p2 = document.forms["formulario1"]["password2"].value
 		 
	     if (p1 == p2){
			  return true
		  }
		  else{
			  alert("La constraseña no coincide con su verificacion")
	    	  return false
		  }
		} 
		
	</script>
	<title>Datos de usuario</title>
</head>
<body>
<%
	   String p_user = request.getParameter("usuario");
	   String p_nomb = request.getParameter("nombre");
	   String p_apel = request.getParameter("apellidos");
	   String p_dni = request.getParameter("dni");
	   String p_gene = request.getParameter("genero");
	   String p_mail = request.getParameter("mail");
	   long p_tele =  Long.parseLong(request.getParameter("telefono"));
	String p_naci = request.getParameter("nacimiento");
	   String p_clav = request.getParameter("clave");
	String p_rol = request.getParameter("rol");
	String p_id = request.getParameter("id");
	
	
	
%>

    <div class="bg-amarillo">
    <h3>Crear/Modificar usuario</h3>

	<form name="formulario1" action="UserDataServlet" onsubmit="return validarForm()" method="post">
	<table align="center" cellpadding = "10">
	
		
<!--		Letra: <input type="text" id="letra" required="true" name="letra" size="3" maxlength="1" autofocus>-->
<!--		<input type="button" value="Probar" onclick="probar()">-->
<!--		<br><br>-->
<!--		<button type="reset" onclick="reiniciar()">Reiniciar</button>-->
		
	
		<tr>
			<td>Nombre</td> 
			<td><input type="text" name="nombre" maxlength="30" size="30" 
			tabindex="1" value="<%=p_nomb%>" autofocus></td>
		</tr>
		<tr>
			<td>Apellidos</td> 
			<td><input type="text" name="apellidos" maxlength="30" size="30" 
			tabindex="2" value="<%=p_apel%>"></td>
		</tr>
		<tr>
			<td>Dni</td> 
			<td><input type="text" name="dni" maxlength="9" size="10" 
			tabindex="3" value="<%=p_dni%>"></td>
		</tr>
		<tr>
			<td>Genero</td> 
			<td>
				<!-- input type="text" id="genero" maxlength="1" size="2"-->
				Hombre <input type="radio" name="genero" value="V" tabindex="4" 
				<%if (p_gene.equals("V")){%>checked<%}%>/>
				Mujer <input type="radio" name="genero" value="M" tabindex="4"
				<%if (p_gene.equals("M")){%>checked<%}%>/>
			</td>
		</tr>
		<tr>
			<td>Usuario</td>
			<td>*<input type="text" name="idusuario" required="true" 
			     maxlength="15" minlength="4" tabindex="5" value="<%=p_user%>"></td>
		</tr>
		<tr>
			<td>Contraseña</td>
			<td>*<input type="password" name="password" required="true"
			     maxlength="15" minlength="4" tabindex="6" value="<%=p_clav%>"></td>
		</tr>
		<tr>
			<td>Contraseña</td> 
			<td>*<input type="password" name="password2" required="true"
			     maxlength="15" minlength="4" tabindex="7" value="<%=p_clav%>"/> verificación </td>
		</tr>
		<tr>
			<td>E-mail</td>
			<td><input type="email" name="mail"  maxlength="30" size="30" 
			tabindex="8" value="<%=p_mail%>"></td>
		</tr>
		<tr>
			<td>Telefono</td>
			<td><input type="tel" name="telefono" maxlength="9" size="11" 
			tabindex="9" value="<%=p_tele%>"></td>
		</tr>
		<tr>
			<td>Fecha nacimiento</td> 
			<td><input type="date" name="fecha" maxlength="9" size="10" 
			tabindex="10" value="<%=p_mail%>"></td>
		</tr>
		<tr>
			<td></td>
			<td><input type="submit" value="Aceptar" tabindex="11"></td>
		</tr>	
	
</table>
</form>
</div>
    
</body>
</html>