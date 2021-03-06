
<%@ page import="remisesonline.Pasajero" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'pasajero.label', default: 'Pasajero')}" />
		<title>Mis amigos</title>
	</head>
	<body>
		<a href="#list-pasajero" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><g:link class="agregarAmigo" controller="solicitudAmistad" action="create"><g:message code="Agregar amigo" args="[entityName]" /></g:link></li>
				<li><g:link class="solicitudesEnviadas" controller="pasajero" action="listSolicitudesAmigosEnviadas"><g:message code="Solicitudes Enviadas" args="[entityName]" /></g:link></li>
				<li><g:link class="solicitudesRecibidas" controller="pasajero" action="listSolicitudesAmigosRecibidas"><g:message code="Solicitudes Recibidas" args="[entityName]" /></g:link></li>
				
			</ul>
		</div>
		<div id="list-pasajero" class="content scaffold-list" role="main">
			<h1>Mis amigos</h1>
			<g:if test="${flash.message}">
				<ul class="errors" role="alert">
					<li ><g:message error="${flash.message}"/></li>
				</ul>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="nombre" title="${message(code: 'pasajero.nombre.label', default: 'Nombre')}" />
					
						<g:sortableColumn property="email" title="${message(code: 'pasajero.email.label', default: 'Email')}" />
					
						<g:sortableColumn property="telefono" title="${message(code: 'pasajero.telefono.label', default: 'Telefono')}" />
					
						<g:sortableColumn property="fechaNacimiento" title="${message(code: 'pasajero.fechaNacimiento.label', default: 'Fecha Nacimiento')}" />
						<th><g:message code="Pasajero.pasajero.label" default="Eliminar Amigo" /></th>

					</tr>
				</thead>
				<tbody>
				<g:each in="${amigos}" status="i" var="pasajeroInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${pasajeroInstance.id}">${fieldValue(bean: pasajeroInstance, field: "nombre")}</g:link></td>
					
						<td>${fieldValue(bean: pasajeroInstance, field: "email")}</td>
					
						<td>${fieldValue(bean: pasajeroInstance, field: "telefono")}</td>
					
						<td><g:formatDate date="${pasajeroInstance.fechaNacimiento}"  format="dd/MM/yyyy" /></td>
						
						<td><g:link action="eliminarAmigo" controller="pasajero" id="${pasajeroInstance.id}"><input type="button" value="Si" /></g:link></td>
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${pasajeroInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
