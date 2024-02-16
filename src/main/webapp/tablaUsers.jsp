<%@page import="org.hibernate.internal.build.AllowSysOut"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" import="java.util.List, java.text.SimpleDateFormat" 
    import="proyectoFinal.entities.User, proyectoFinal.entities.Rol ,proyectoFinal.utils.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>

<title>Listado de usuarios</title>

<style type="text/css">
	table, td, th { border:1px solid black; }
	#table { border-spacing:10px; }
	#table { border-collapse:collapse; }
	#td { padding:10px; }
	#td, th { padding:5px; }
	table { width:90%; }
	thead { color:navy; } 
	tbody { color:black; } 
	tfoot { color:maroon; }
	fieldset{
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
 			window.open(enlace);
		}    
	}
	
</script>




</head>
<body>
<%Propiedades pro=new Propiedades();
  long idd;  %>
<div class="container">
	<div class="row">
		<div class="col-12 text-center">
			<h3><%=pro.leerProper("titulo1")%></h3>
		</div>
	</div>

<div class="row">				
	<div class="col">
<table class="table table-striped table-primary" align="center" cellpadding = "10">
		
		<tr>
			<th></th>
			<th></th>
			<th><a href="LoginServlet?accion=ordenar&orden=usuario">Usuario</a></th>
			<th><a href="LoginServlet?accion=ordenar&orden=nombre">Nombre</a></th>
			<th><a href="LoginServlet?accion=ordenar&orden=apellidos">Apellidos</a></th>
			<th><a href="LoginServlet?accion=ordenar&orden=dni">DNI</a></th>
			<th><a href="LoginServlet?accion=ordenar&orden=sexo">Genero</a></th>
			<th><a href="LoginServlet?accion=ordenar&orden=email">E-mail</a></th>
			<th><a href="LoginServlet?accion=ordenar&orden=telefono">Telefono</a></th>
			<th><a href="LoginServlet?accion=ordenar&orden=nacimiento">Nacimiento</a></th>
			<th><a href="LoginServlet?accion=ordenar&orden=rol">Tipo</a></th>
		</tr>
		
<% 

List<User> usuarios = (List<User>)request.getAttribute("listaUsuarios");
//usuarios.get(1).getApellidos();
int indice=0;
for (User u: usuarios) {
	SimpleDateFormat df = new SimpleDateFormat("dd-MMMM-yyyy");
    String fechaStr1 = df.format(u.getNacimiento());
%>

		<tr>
				<td><a href="userdata.jsp?usuario=<%=u.getUsuario()%>
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
				<%idd=u.getIdUsuario();%>
				<img border="0" alt="modificar" src="modify.png" width="16" height="16">
				</a></td>  
				
				<td>
				<img role="button"  alt="borrar" src="delete.png" width="16" height="16" onclick="borradoSeguro(<%=idd%>)">
				</td>  

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
			<%indice++;}%>
		
	</table>
	</div>
	
	<p><a href='userdata.jsp?usuario=""&nombre=""&apellidos=""&dni=""&genero=""
				&mail=""&nacimiento=01-01-2000&rol=""&clave=""&id=""'>
				Crear nuevo usuario</a></p>
		</div>		
	</div>			
</div>
</body>
</html>