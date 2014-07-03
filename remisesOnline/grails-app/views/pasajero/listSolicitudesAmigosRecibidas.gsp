
<%@ page import="remisesonline.SolicitudAmistad" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'solicitudAmistad.label', default: 'SolicitudAmistad')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-solicitudAmistad" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><g:link class="pasajero" controller="solicitudAmistad" action="create"><g:message code="Agregar amigo" args="[entityName]" /></g:link></li>
				<li><g:link class="pasajero" controller="pasajero" action="listSolicitudesAmigosEnviadas"><g:message code="Solicitudes Enviadas" args="[entityName]" /></g:link></li>
				<li><g:link class="pasajero" controller="pasajero" action="listSolicitudesAmigosRecibidas"><g:message code="Solicitudes Recibidas" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-solicitudAmistad" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="estado" title="${message(code: 'solicitudAmistad.estado.label', default: 'Estado')}" />
					
						<g:sortableColumn property="fechaCreada" title="${message(code: 'solicitudAmistad.fechaCreada.label', default: 'Fecha Creada')}" />
					
						<th><g:message code="solicitudAmistad.pasajero.label" default="Pasajero" /></th>
						
						<th><g:message code="solicitudAmistad.pasajero.label" default="Opcion" /></th>
					</tr>
				</thead>
				<tbody>
				<g:each in="${solicitudesRecibidas}" status="i" var="solicitud">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${solicitud.id}">${fieldValue(bean: solicitud, field: "estado")}</g:link></td>
					
						<td><g:formatDate date="${solicitud.fechaCreada}"  format="dd/MM/yyyy HH:mm" /></td>
					
						<td>${fieldValue(bean: solicitud, field: "pasajero")}</td>
						
						<td><g:link action="aprobarSolicitud" controller="solicitudAmistad" id="${solicitud.id}"><input type="button" value="Aceptar" /></g:link>
						     <g:link action="denegarSolicitud" controller="solicitudAmistad" id="${solicitud.id}"><input type="button" value="Denegar" /></g:link>
						</td>

					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${solicitudAmistadInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>