
<%@ page import="remisesonline.ServicioDeRemiseria"%>
<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="main">
<g:set var="entityName"
	value="${message(code: 'servicioDeRemiseria.label', default: 'ServicioDeRemiseria')}" />
<title><g:message code="default.list.label" args="[entityName]" /></title>
</head>
<body>
	<a href="#list-servicioDeRemiseria" class="skip" tabindex="-1"><g:message
			code="default.link.skip.label" default="Skip to content&hellip;" /></a>
	<div class="nav" role="navigation">
		<ul>
			<li><g:link class="list" action="listServicios">Ver Servicios</g:link></li>
			<li><g:link class="list" action="misAdhesiones">Mis Adhesiones</g:link></li>
		</ul>

	</div>
	<div id="list-servicioDeRemiseria" class="content scaffold-list"
		role="main">
		<h1>
			Mis Adhesiones
		</h1>
		<g:if test="${flash.message}">
			<div class="message" role="status">
				${flash.message}
			</div>
		</g:if>
		<table>
			<thead>
				<tr>

					<g:sortableColumn property="nombre"
						title="${message(code: 'servicioDeRemiseria.nombre.label', default: 'Nombre')}" />

					<g:sortableColumn property="descripcion"
						title="${message(code: 'servicioDeRemiseria.descripcion.label', default: 'Descripcion')}" />

					<th>Agencia</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<g:each in="${servicioDeRemiseriaInstanceList}" status="i"
					var="servicioDeRemiseriaInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

						<td><g:link action="show"
								id="${servicioDeRemiseriaInstance.id}">
								${fieldValue(bean: servicioDeRemiseriaInstance, field: "nombre")}
							</g:link></td>

						<td>
							${fieldValue(bean: servicioDeRemiseriaInstance, field: "descripcion")}
						</td>

						<td>
							${fieldValue(bean: servicioDeRemiseriaInstance, field: "agencia")}
						</td>
						
						<td>
							<g:if test="${servicioDeRemiseriaInstance.adheridos.id.contains(session.pasajero.id) }">
								<g:link action="desadherirse" id="${servicioDeRemiseriaInstance.id }">Desadherirse</g:link>
							</g:if>
						</td>

					</tr>
				</g:each>
			</tbody>
		</table>
		<div class="pagination">
			<g:paginate total="${servicioDeRemiseriaInstanceCount ?: 0}" />
		</div>
	</div>
</body>
</html>
