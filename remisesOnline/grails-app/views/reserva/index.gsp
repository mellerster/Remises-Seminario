
<%@ page import="remisesonline.Reserva" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'reserva.label', default: 'Reserva')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-reserva" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-reserva" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<th><g:message code="reserva.remise.label" default="Remise" /></th>
					
						<g:sortableColumn property="fechaReserva" title="${message(code: 'reserva.fechaReserva.label', default: 'Fecha Reserva')}" />
					
						<g:sortableColumn property="estado" title="${message(code: 'reserva.estado.label', default: 'Estado')}" />
					
						<th><g:message code="reserva.agencia.label" default="Agencia" /></th>
					
						<g:sortableColumn property="creado" title="${message(code: 'reserva.creado.label', default: 'Creado')}" />
					
						<th><g:message code="reserva.destinos.label" default="Destinos" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${reservaInstanceList}" status="i" var="reservaInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${reservaInstance.id}">${fieldValue(bean: reservaInstance, field: "remise")}</g:link></td>
					
						<td><g:formatDate date="${reservaInstance.fechaReserva}" /></td>
					
						<td>${fieldValue(bean: reservaInstance, field: "estado")}</td>
					
						<td>${fieldValue(bean: reservaInstance, field: "agencia")}</td>
					
						<td><g:formatDate date="${reservaInstance.creado}" /></td>
					
						<td>${fieldValue(bean: reservaInstance, field: "destinos")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${reservaInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
