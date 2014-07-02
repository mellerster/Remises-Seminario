
<%@ page import="remisesonline.Promocion" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'promocion.label', default: 'Promocion')}" />
		<title>Mis promociones</title>
	</head>
	<body>
		<a href="#list-promocion" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-promocion" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<th><g:message code="promocion.agencia.label" default="Agencia" /></th>
					
						<g:sortableColumn property="descripcion" title="${message(code: 'promocion.descripcion.label', default: 'Descripcion')}" />
					
						<g:sortableColumn property="fechaDesde" title="${message(code: 'promocion.fechaDesde.label', default: 'Fecha Desde')}" />
					
						<g:sortableColumn property="fechaHasta" title="${message(code: 'promocion.fechaHasta.label', default: 'Fecha Hasta')}" />
						<th>Editar</th>
						<th>Eliminar</th>
					</tr>
			</thead>
				<tbody>
				<g:each in="${promocionInstanceList}" status="i" var="promocionInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${promocionInstance.id}">${fieldValue(bean: promocionInstance, field: "agencia")}</g:link></td>
					
						<td>${fieldValue(bean: promocionInstance, field: "descripcion")}</td>
					
						<td><g:formatDate date="${promocionInstance.fechaDesde}" /></td>
					
						<td><g:formatDate date="${promocionInstance.fechaHasta}" /></td>
						<td><g:link action="edit" controller="promocion" id="${promocionInstance.id}"><input type="button" value="Si" /></g:link></td>
						<td><g:link action="eliminar" controller="promocion" id="${promocionInstance.id}"><input type="button" value="Si" /></g:link></td>
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${promocionInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
