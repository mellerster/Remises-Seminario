<%@ page import="remisesonline.Reserva" %>
<html>
	<head>
		<meta name="layout" content="main" />
		<title>Resultado reservas por pasajero</title>
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
		<div class="body">
			<g:if test="${flash.message}">
			<ul class="errors" role="alert">
				<li ><g:message error="${flash.message}"/></li>
			</ul>
			</g:if>
			<h1>Resultados</h1>
			<g:hasErrors bean="${pasajeroSesion}">
			<ul class="errors" role="alert">
				<g:eachError bean="${pasajeroSesion}" var="error">
					<li
						<g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message
							error="${error}" /></li>
				</g:eachError>
			</ul>
		</g:hasErrors>

			<table>
				<thead>
					<tr>
						<g:sortableColumn property="fechaReserva" title="${message(code: 'reserva.fechaReserva.label', default: 'Fecha Reserva')}" />
						<th><g:message code="reserva.agencia.label" default="Agencia" /></th>
						<th><g:message code="reserva.remise.label" default="Remise" /></th>
						<th>Unirse?</th>
					</tr>
					</thead>
					<tbody>
					<g:each in="${reservas}" status="i" var="reserva">
						<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
							<td><g:formatDate date="${reserva.fechaReserva}" format="dd/MM/yyyy HH:mm" /></td>
							<td>${fieldValue(bean: reserva, field: "agencia")}</td>
							<td><g:link action="show" controller="remise" id="${reserva.remise?.id}">${fieldValue(bean: reserva, field: "remise")}</g:link></td>
							<td>
								<g:link action="solicitarUnirse" controller="reserva" id="${reserva.id}" params="[pasajero: pasajero]"><input type="button" value="Si" /></g:link>
							</td>
						</tr>
					</g:each>
					</tbody>
			</table>
		</div>
	</body>
</html>
