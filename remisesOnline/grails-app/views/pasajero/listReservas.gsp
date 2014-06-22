<%@ page import="remisesonline.Reserva" %>
<html>
  <head>
    <meta name="layout" content="main" />
    <title>Resultado reservas por pasajero</title>
  </head>
  <body>
    <div class="body">
      <h1>Resultados</h1>
      <table>
        <thead>
					<tr>
						<th>Nro</th>
            <g:sortableColumn property="fechaReserva" title="${message(code: 'reserva.fechaReserva.label', default: 'Fecha Reserva')}" />
						<g:sortableColumn property="estado" title="${message(code: 'reserva.estado.label', default: 'Estado')}" />
						<th><g:message code="reserva.agencia.label" default="Agencia" /></th>
            <th><g:message code="reserva.remise.label" default="Remise" /></th>
            <g:sortableColumn property="creado" title="${message(code: 'reserva.creado.label', default: 'Creado')}" />
					</tr>
          </thead>
          <tbody>
          <g:each in="${reservas}" status="i" var="reserva">
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
              <td><g:link action="show" controller="reserva" id="${reserva.id}">${fieldValue(bean: reserva, field: "id")}</g:link></td>
              <td><g:formatDate date="${reserva.fechaReserva}" format="dd/MM/yyyy HH:mm" /></td>
              <td>${fieldValue(bean: reserva, field: "estado")}</td>
              <td>${fieldValue(bean: reserva, field: "agencia")}</td>
              <td><g:link action="show" controller="remise" id="${reserva.remise?.id}">${fieldValue(bean: reserva, field: "remise")}</g:link></td>
              <td><g:formatDate date="${reserva.creado}" format="dd/MM/yyyy HH:mm" /></td>
            </tr>
          </g:each>
          </tbody>
			</table>
    </div>
  </body>
</html>
