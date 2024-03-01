<%@page import="org.hibernate.internal.build.AllowSysOut"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"
	import="java.util.List, java.text.SimpleDateFormat"
	import="proyectoFinal.entities.User, proyectoFinal.entities.Rol ,proyectoFinal.utils.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<link rel="stylesheet" href="css/bootstrap.min.css">
<script src="js/bootstrap.bundle.min.js"></script>

<title>Listado de usuarios</title>

<style type="text/css">
table, td, th {border: 1px solid black;}
#table {border-spacing: 10px;}
#table {border-collapse: collapse;}
#td {padding: 10px;}
#td, th {padding: 5px;}
table {width: 90%;}
thead {color: navy;}
tbody {color: black;
}

tfoot {
	color: maroon;
}

fieldset {
	background-color: cadetblue;
	width: 20%;
	border-radius: 3%;
}
</style>

<script type="text/javascript">

	function borradoSeguro(number) {
		//var x = document.forms.myForm;
		var mensaje = confirm("¿Seguro que quiere borrar este usuario?");
	
 		if (mensaje) {
 			var enlace="LoginServlet?accion=borrar&id="+number;
 			window.location.href = enlace;
		}    
	}
	
	function ordenar(campo, sentido, anterior) {
		if (campo==anterior){
			var enlace="LoginServlet?accion=ordenar&orden="+campo+sentido;
	    }
		else
			{
			var enlace="LoginServlet?accion=ordenar&orden="+campo+" ASC";
			}
		window.location.href = enlace;
	}
	
	function filtrar() {
		
 		var campo = document.getElementById('filtrocampo').value;
 		alert("llamado a filtar campo= "+campo);
 		var valor = document.getElementById('filtrovalor').value;
 		alert("llamado a filtar campo= "+campo+" valor= "+valor);
		
// 		window.location.href = "LoginServlet?accion=filtrar&campo="+campo+"&valor="+valor;
		
// 		alert("llamado a filtar");
	}
	
</script>




</head>

<body>
<jsp:include page="cabecera.html"></jsp:include>
	
	<%
	Propiedades pro = new Propiedades();
	String sentido = request.getParameter("sentido") != null ? request.getParameter("sentido") : " ASC";
	String anterior = request.getParameter("anterior") != null ? request.getParameter("anterior") : "";
	%>
	<div class="container">
		
		<div class="row mt-2">
			<div class="col-12 text-center">
				<h3><%=pro.leerProper("TablaUsers_Cabeza")%></h3>
			</div>
		</div>
		<div class="row mt-3">
			<div class="col-1"></div>
		
			<div class="col-3 justify-content-center">
				Valor<input type="text" name="filtrovalor" maxlength="30" size="30" value="">
			</div>
			<div class="col-4 justify-content-center">
			Filtro<select name="filtrocampo" id="filtrocampo">
					<option value="nombre">por nombre</option>
	  				<option value="genero">por genero</option>
	  				<option value="rol">por rol</option>
	 		</select>
	 		</div>
	 		
	 		<div class="col-3 justify-content-center">
	 			 <button type="button" onclick="filtrar()">Buscar</button> 
	 		</div>
	 		
	 		<div class="col-1"></div>
		</div>

		<div class="row mt-3">
			<div class="col">
				<table class="table table-striped table-primary" align="center"
					cellpadding="10">

					<tr>
						<th></th>
						<th></th>
						<!--  th><a href="LoginServlet?accion=ordenar&orden=usuario">Usuario</a></th-->
						<th><a href="javascript:ordenar('usuario','<%=sentido%>','<%=anterior%>')">Usuario</a></th>
						<th><a href="javascript:ordenar('nombre','<%=sentido%>','<%=anterior%>')">Nombre</a></th>
						<th><a href="javascript:ordenar('apellidos','<%=sentido%>','<%=anterior%>')">Apellidos</a></th>
						<th><a href="javascript:ordenar('dni','<%=sentido%>','<%=anterior%>')">DNI</a></th>
						<th><a href="javascript:ordenar('sexo','<%=sentido%>','<%=anterior%>')">Genero</a></th>
						<th><a href="javascript:ordenar('email','<%=sentido%>','<%=anterior%>')">E-mail</a></th>
						<th><a href="javascript:ordenar('telefono','<%=sentido%>','<%=anterior%>')">Telefono</a></th>
						<th><a href="javascript:ordenar('nacimiento','<%=sentido%>','<%=anterior%>')">Nacimiento</a></th>
						<th><a href="javascript:ordenar('rol','<%=sentido%>','<%=anterior%>')">Tipo</a></th>
					</tr>

					<%
					List<User> usuarios = (List<User>) request.getAttribute("listaUsuarios");
					//usuarios.get(1).getApellidos();
					int indice = 0;
					for (User u : usuarios) {
						SimpleDateFormat df = new SimpleDateFormat("dd-MMMM-yyyy");
						String fechaStr1 = df.format(u.getNacimiento());
					%>

					<tr>
						<td><a
							href="userdata.jsp?usuario=<%=u.getUsuario()%>
				&nombre=<%=u.getNombre()%>
				&apellidos=<%=u.getApellidos()%>
				&dni=<%=u.getDni()%>
				&genero=<%=u.getSexo()%>
				&mail=<%=u.getEmail()%>
				&telefono=<%=u.getTelefono()%>
	    		&nacimiento=<%=Utils.dateToString(u.getNacimiento())%>   
				&rol=<%=u.getRol()%>
				
				&clave=<%=u.getClave()%>
				&id=<%=u.getIdUsuario()%>">
					<%long idU = u.getIdUsuario();%> 
					<img border="0" alt="modificar" src="modify.png" width="16" height="16">
						</a></td>

						<td><img role="button" alt="borrar" src="delete.png"
							width="16" height="16" onclick="borradoSeguro(<%=idU%>)"></td>

						<td><%=u.getUsuario()%></td>
						<td><%=u.getNombre()%></td>
						<td><%=u.getApellidos()%></td>
						<td><%=u.getDni()%></td>
						<td><%=u.getSexo()%></td>
						<td><%=u.getEmail()%></td>
						<td><%=u.getTelefono()%></td>
						<td><%=fechaStr1%></td>
						<td><%=u.getRol()%></td>
					</tr>
					<%
					indice++;
					}
					%>

				</table>

				<nav>
					<a
						href='userdata.jsp?usuario=""&nombre=""&apellidos=""&dni=""&genero=""
					&mail=""&nacimiento=01-01-2000&rol=""&clave=""&id=""'>
						Crear nuevo usuario</a> <a href='LoginServlet?accion=contenido'>Volver
						a contenido</a>
				</nav>

			</div>
		</div>
	</div>
	<jsp:include page="piepagina.html"></jsp:include>
</body>
</html>