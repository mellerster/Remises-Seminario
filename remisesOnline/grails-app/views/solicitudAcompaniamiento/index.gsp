
<%@ page import="remisesonline.SolicitudAcompaniamiento" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'solicitudAcompaniamiento.label', default: 'SolicitudAcompaniamiento')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-solicitudAcompaniamiento" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-solicitudAcompaniamiento" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="estado" title="${message(code: 'solicitudAcompaniamiento.estado.label', default: 'Estado')}" />
					
						<g:sortableColumn property="fechaCreada" title="${message(code: 'solicitudAcompaniamiento.fechaCreada.label', default: 'Fecha Creada')}" />
					
						<th><g:message code="solicitudAcompaniamiento.pasajero.label" default="Pasajero" /></th>
					
						<th><g:message code="solicitudAcompaniamiento.reservaSolicitada.label" default="Reserva Solicitada" /></th>
					
						<th><g:message code="solicitudAcompaniamiento.solicitado.label" default="Solicitado" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${solicitudAcompaniamientoInstanceList}" status="i" var="solicitudAcompaniamientoInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${solicitudAcompaniamientoInstance.id}">${fieldValue(bean: solicitudAcompaniamientoInstance, field: "estado")}</g:link></td>
					
						<td><g:formatDate date="${solicitudAcompaniamientoInstance.fechaCreada}" /></td>
					
						<td>${fieldValue(bean: solicitudAcompaniamientoInstance, field: "pasajero")}</td>
					
						<td>${fieldValue(bean: solicitudAcompaniamientoInstance, field: "reservaSolicitada")}</td>
					
						<td>${fieldValue(bean: solicitudAcompaniamientoInstance, field: "solicitado")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${solicitudAcompaniamientoInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
