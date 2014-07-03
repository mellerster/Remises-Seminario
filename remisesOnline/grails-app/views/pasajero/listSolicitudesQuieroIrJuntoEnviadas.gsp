
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
				<li><g:link class="pasajero" controller="pasajero" action="showReservasAmigos"><g:message code="Solicitar ir junto a un amigo" args="[entityName]" /></g:link></li>
				<li><g:link class="solicitudesEnviadas" controller="pasajero" action="listSolicitudesQuieroIrJuntoEnviadas"><g:message code="Solicitudes Enviadas" args="[entityName]" /></g:link></li>
				<li><g:link class="solicitudesRecibidas" controller="pasajero" action="listSolicitudesQuieroIrJuntoRecibidas"><g:message code="Solicitudes Recibidas" args="[entityName]" /></g:link></li>
			</ul>
		</div>
				<div id="list-solicitudQuieroIrJunto" class="content scaffold-list" role="main">
			<h1>Solicitudes enviadas</h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="estado" title="${message(code: 'solicitudQuieroIrJunto.estado.label', default: 'Estado')}" />
					
						<g:sortableColumn property="fechaCreada" title="${message(code: 'solicitudQuieroIrJunto.fechaCreada.label', default: 'Fecha Creada')}" />
					
						<th><g:message code="solicitudQuieroIrJunto.reservaSolicitada.label" default="Reserva Solicitada" /></th>
					
						<th><g:message code="solicitudQuieroIrJunto.solicitado.label" default="Solicitado" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${solicitudesEnviadas}" status="i" var="solicitudQuieroIrJuntoInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${solicitudQuieroIrJuntoInstance.id}">${fieldValue(bean: solicitudQuieroIrJuntoInstance, field: "estado")}</g:link></td>
					
						<td><g:formatDate date="${solicitudQuieroIrJuntoInstance.fechaCreada}"  format="dd/MM/yyyy HH:mm" /></td>
					
				
						<td>${fieldValue(bean: solicitudQuieroIrJuntoInstance, field: "reservaSolicitada")}</td>
					
						<td>${fieldValue(bean: solicitudQuieroIrJuntoInstance, field: "solicitado")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${solicitudQuieroIrJuntoInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>