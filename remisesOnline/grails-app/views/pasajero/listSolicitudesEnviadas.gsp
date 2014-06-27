
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
					
						<th><g:message code="solicitudAmistad.solicitado.label" default="Solicitado" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${solicitudesEnviadas}" status="i" var="solicitud">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${solicitud.id}">${fieldValue(bean: solicitud, field: "estado")}</g:link></td>
					
						<td><g:formatDate date="${solicitud.fechaCreada}" /></td>
					
						<td>${fieldValue(bean: solicitud, field: "pasajero")}</td>
					
						<td>${fieldValue(bean: solicitud, field: "solicitado")}</td>
					
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