<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.List, proyectoFinal.utils.Utils, java.util.Date"%>
<!DOCTYPE html>
<html lang="es-ES">
<head>
	<meta charset="utf-8">
	<link rel="stylesheet" href="userdata.css">
	<script type="text/javascript">
		
		function validarForm() {
 		 let p1 = document.forms["formulario1"]["password"].value
 		 let p2 = document.forms["formulario1"]["password2"].value
 		 let r = document.forms["formulario1"]["tipo"].value
 		
	     if ((p1 == p2) && (r != "0")){
			  return true
		  }
		  else{
			  if (r!="0"){
				  alert("La constraseña no coincide con su verificacion")
			  }
			  else
			  {
				  alert("Debe seleccionar un rol")
			  }
	    	  return false
		  }
		} 
		
	</script>
	<title>Datos de usuario</title>
</head>
<body>
<%
	   
	   String p_user = request.getParameter("usuario");
	   boolean tipo = (p_user != null && p_user.length() > 3); // true= modificar false=crear
	   p_user = request.getParameter("usuario") != null ? request.getParameter("usuario"):"";
	   String p_nomb = request.getParameter("nombre") != null ? request.getParameter("nombre"):"";
	   String p_apel = request.getParameter("apellidos") != null ? request.getParameter("apellidos"):"";
	   String p_dni = request.getParameter("dni") != null ? request.getParameter("dni"):"";
	   String p_gene = request.getParameter("genero")!= null ? request.getParameter("genero"):"";
	   String p_mail = request.getParameter("mail") != null ? request.getParameter("mail"):"";
	   String s_tele = request.getParameter("telefono");
	   long p_tele = (s_tele != null) ? Long.parseLong(s_tele.trim()) : 0;
	   String p_naci = request.getParameter("nacimiento");
	   //Date d_naci = Utils.stringToDate(p_naci);
	   String p_naci2 = (p_naci != null) ?  Utils.dateEspToEng(p_naci) : p_naci;
	
	   String p_clav = request.getParameter("clave") != null?request.getParameter("clave"):"";
	   String p_rol = request.getParameter("rol") != null?request.getParameter("rol"):"";
	   String p_id = request.getParameter("id");

%>

    <div class="bg-amarillo">
    
    <%if (tipo){ %>
    	<h3>Modificar usuario</h3>
    <%}else{ %>
    	<h3>Crear usuario</h3>
    <%}%>

	<form name="formulario1" action="LoginServlet?accion=nuevo" onsubmit="return validarForm()" method="post">
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
			tabindex="10" value="<%=p_naci2%>"></td>
		</tr>
		<tr>
			<td>Rol</td> 
			<td><select name="tipo" id="tipo" tabindex="11">
				<option value="0">Elija una opcion</option>
  				<option value="user" <% 
  					if (p_rol.equalsIgnoreCase("user")){
  						%> selected="selected" <%
  					}
  				%>>Usuario</option>
  				<option value="admin" <% 
  					if (p_rol.equalsIgnoreCase("admin")){
  						%> selected="selected" <%
  					}
  				%>>Administrador</option>
 		</select></td>
		</tr>
		
		
		
		<tr>
			<td></td>
			<td><input type="submit" value="Aceptar" tabindex="12"></td>
		</tr>	
	
</table>
</form>
</div>
    
</body>
</html>