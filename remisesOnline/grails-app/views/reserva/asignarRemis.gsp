
<%@ page import="remisesonline.Reserva"%>
<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="main">
<g:set var="entityName"
	value="${message(code: 'reserva.label', default: 'Reserva')}" />
<title><g:message code="default.show.label" args="[entityName]" /></title>
</head>
<body>
	<a href="#show-reserva" class="skip" tabindex="-1"><g:message
			code="default.link.skip.label" default="Skip to content&hellip;" /></a>

	<div id="show-reserva" class="content scaffold-show" role="main">
		<h1>
			<g:message code="default.show.label" args="[entityName]" />
		</h1>
		<g:if test="${flash.message}">
			<div class="message" role="status">
				${flash.message}
			</div>
		</g:if>

		<g:hasErrors bean="${reservaInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${reservaInstance}" var="error">
					<li
						<g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message
							error="${error}" /></li>
				</g:eachError>
			</ul>
		</g:hasErrors>
		<g:form url="[resource:reservaInstance, action:'update']"	method="PUT">

		<ol class="property-list reserva">
			<g:hiddenField name="version" value="${reservaInstance?.version}" />
			<g:if test="${reservaInstance?.remise}">
				<li class="fieldcontain"><span id="remise-label"
					class="property-label"><g:message
							code="reserva.remise.label" default="Remise" /></span> <span
					class="property-value" aria-labelledby="remise-label"><g:link
							controller="remise" action="show"
							id="${reservaInstance?.remise?.id}">
							${reservaInstance?.remise?.encodeAsHTML()}
						</g:link></span></li>
			</g:if>

			<g:if test="${reservaInstance?.fechaReserva}">
				<li class="fieldcontain"><span id="fechaReserva-label"
					class="property-label"><g:message
							code="reserva.fechaReserva.label" default="Fecha Reserva" /></span> <span
					class="property-value" aria-labelledby="fechaReserva-label">
						<g:formatDate date="${reservaInstance?.fechaReserva}"
							type="datetime" format="dd/MM/yyyy HH:mm" />
				</span></li>
			</g:if>

			<g:if test="${reservaInstance?.estado}">
				<li class="fieldcontain"><span id="estado-label"
					class="property-label"><g:message
							code="reserva.estado.label" default="Estado" /></span> <span
					class="property-value" aria-labelledby="estado-label"> <g:fieldValue
							bean="${reservaInstance}" field="estado" />
				</span></li>
			</g:if>
			<div class="fieldcontain ${hasErrors(bean: reservaInstance, field: 'remise', 'error')} ">
				<label for="remise">
					<g:message code="reserva.remise.label" default="Remise" />
				</label>
				<g:select id="remise" name="remise.id" from="${reservaInstance.agencia.remises}" optionKey="id" value="${reservaInstance?.remise?.id}" class="many-to-one" noSelection="['null': '']"/>
			</div>
			<g:if test="${reservaInstance?.creado}">
				<li class="fieldcontain"><span id="creado-label"
					class="property-label"><g:message
							code="reserva.creado.label" default="Creado" /></span> <span
					class="property-value" aria-labelledby="creado-label"> <g:formatDate
							date="${reservaInstance?.creado}" type="datetime"
							format="dd/MM/yyyy HH:mm" />
				</span></li>
			</g:if>

			<g:if test="${reservaInstance?.paradas}">
				<li class="fieldcontain">
					<h4>Paradas</h4>
					<table>
						<thead>
							<tr>
								<th>Calle</th>
								<th>Número</th>
								<th>Localidad</th>
								<th>Descripción</th>
							</tr>
						</thead>
						<tbody>
							<g:each in="${reservaInstance?.paradas}" status="i" var="parada">
								<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
									<td>
										${fieldValue(bean: parada, field: "calle")}
									</td>
									<td>
										${fieldValue(bean: parada, field: "numero")}
									</td>
									<td>
										${fieldValue(bean: parada, field: "localidad")}
									</td>
									<td>
										${fieldValue(bean: parada, field: "descripcion")}
									</td>
								</tr>
							</g:each>
						</tbody>
					</table>
				</li>
			</g:if>


		</ol>
		
		<g:if test="${session.agencia}">

				<fieldset class="buttons">
						
						<g:submitButton name="asignarRemis" class="update" value="Asignar" />

				</fieldset>
		</g:if>

		</g:form>
	</div>
</body>
</html>
