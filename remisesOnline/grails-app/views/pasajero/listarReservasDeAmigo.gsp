<%@ page import="remisesonline.Reserva" %>
<html>
	<head>
		<meta name="layout" content="main" />
		<title>Resultado reservas por pasajero</title>
	</head>
	<body>
		<div class="body">
			<g:if test="${flash.message}">
			<ul class="errors" role="alert">
				<li ><g:message error="${flash.message}"/></li>
			</ul>
			</g:if>
			<h1>Resultados</h1>
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
								<g:link action="unirseAReserva" controller="reserva" id="${reserva.id}"><input type="button" value="Si" /></g:link>
								
							</td>
						</tr>
					</g:each>
					</tbody>
			</table>
		</div>
	</body>
</html>
