<%@page import="org.hibernate.internal.build.AllowSysOut"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.List, java.text.SimpleDateFormat" 
    import="proyectoFinal.entities.User, proyectoFinal.entities.Rol ,proyectoFinal.utils.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
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
	function borrar () {
		var x = document.forms.myForm;
		alert("se ha pulsado borrar");
	}
</script>


</head>
<body>

<%Propiedades pro=new Propiedades();%>

<table align="center" cellpadding = "10">
		<caption><%=pro.leerProper("titulo1")%></caption>
		<tr>
			<th></th>
			<th></th>
			<th><a href="#">Usuario</a></th>
			<th><a href="#">Nombre</a></th>
			<th><a href="#">Apellidos</a></th>
			<th><a href="#">DNI</a></th>
			<th><a href="#">Genero</a></th>
			<th><a href="#">E-mail</a></th>
			<th><a href="#">Telefono</a></th>
			<th><a href="#">Nacimiento</a></th>
			<th><a href="#">Tipo</a></th>
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
				<%String claveString = new String(u.getClave());%>
				&clave=<%=claveString%>
				&id=<%=u.getIdUsuario()%>">
				<img border="0" alt="modificar" src="modify.png" width="16" height="16">
				</a></td>  
				
				<td>
				<a href="LoginServlet?accion=borrar&id=<%=u.getIdUsuario()%>">
				<img border="0" alt="borrar" src="delete.png" width="16" height="16">
				</a>
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
	
	<p><a href='userdata.jsp?usuario=""&nombre=""&apellidos=""&dni=""&genero=""
				&mail=""&telefono=0&nacimiento="01-01-2000"&rol=""&clave=""&id=""'>
				Crear nuevo usuario</a></p>
</body>
</html>