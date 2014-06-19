
<%@ page import="remisesonline.Chofer" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'chofer.label', default: 'Chofer')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-chofer" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-chofer" class="content scaffold-list" role="main">
			<h1>Mis Choferes</h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="dni" title="${message(code: 'chofer.dni.label', default: 'Dni')}" />
					
						<g:sortableColumn property="nombre" title="${message(code: 'chofer.nombre.label', default: 'Nombre')}" />
					
						<g:sortableColumn property="telefono" title="${message(code: 'chofer.telefono.label', default: 'Telefono')}" />
					
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${choferInstanceList}" status="i" var="choferInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${choferInstance.id}">${fieldValue(bean: choferInstance, field: "dni")}</g:link></td>
					
						<td>${fieldValue(bean: choferInstance, field: "nombre")}</td>
					
						<td>${fieldValue(bean: choferInstance, field: "telefono")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${choferInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
