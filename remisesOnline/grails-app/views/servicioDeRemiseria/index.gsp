
<%@ page import="remisesonline.ServicioDeRemiseria" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'servicioDeRemiseria.label', default: 'ServicioDeRemiseria')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-servicioDeRemiseria" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-servicioDeRemiseria" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="nombre" title="${message(code: 'servicioDeRemiseria.nombre.label', default: 'Nombre')}" />
					
						<g:sortableColumn property="descripcion" title="${message(code: 'servicioDeRemiseria.descripcion.label', default: 'Descripcion')}" />
					
						<th>Adheridos</th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${servicioDeRemiseriaInstanceList}" status="i" var="servicioDeRemiseriaInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${servicioDeRemiseriaInstance.id}">${fieldValue(bean: servicioDeRemiseriaInstance, field: "nombre")}</g:link></td>
					
						<td>${fieldValue(bean: servicioDeRemiseriaInstance, field: "descripcion")}</td>
					
						<td>${fieldValue(bean: servicioDeRemiseriaInstance, field: "cantidadAdheridos")}</td>
					
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
