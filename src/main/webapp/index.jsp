<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.List, proyectoFinal.utils.*"%>

<!DOCTYPE html>
<html lang="es-ES">
<head>
<meta charset="utf-8">
<title>Login</title>
<link rel="stylesheet" href="index.css">
<script type="text/javascript">
	function comprobar () {
		var x = document.forms.myForm;
		alert(x.action);
	}
</script>

</head>
<body>
<%String msg = request.getParameter("msg") != null ? request.getParameter("msg"):""; %>

	<div class="bg-naranja">
	<div class="title-wrapper">
		<h2>¡Bienvenido!</h2>
		<h4>Inicie sesion</h4>
	</div>
    <!--h3>Introduzca sus credenciales</h3-->
<table table align="center" cellpadding = "10">
	<form action="http://localhost:8081/proyectoWebFinal/LoginServlet?accion=login" method="post">
			<tr class="input-box">
				<td><input type="text" class="input-box2" name="userCaja" required="true"
				maxlength="15" minlength="4" placeholder="usuario" tabindex="1" autofocus/></td> 
			</tr>
			<tr class="input-box">
				<td><input type="password" class="input-box2" name="passwordCaja"
				required="true" maxlength="15" minlength="4" placeholder="clave" tabindex="2"/></td>	
			</trclass="input-box">
			<tr>
			<td><p style="color: white;"><%=msg%></p></td>
		</tr>
		<tr>
			<td><input type="submit" value="Aceptar" class="button-74" tabindex="3"></td>	
		</tr>
		
<!--<input type="text" id="usuario" value="" placeholder="username" />-->
<!--<input type="password" id="clave" value="" placeholder="password" /><span class="show-pass icon-eye" title="show characters"></span>-->
		
		
	</form>
</table>
    
    </div>
</body>
</html>

