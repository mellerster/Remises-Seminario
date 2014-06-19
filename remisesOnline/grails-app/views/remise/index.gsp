
<%@ page import="remisesonline.Remise" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'remise.label', default: 'Remise')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-remise" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-remise" class="content scaffold-list" role="main">
			<h1>Mis Remises</h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="patente" title="${message(code: 'remise.patente.label', default: 'Patente')}" />
					
						<th><g:message code="remise.chofer.label" default="Chofer" /></th>
					
						<g:sortableColumn property="foto" title="${message(code: 'remise.foto.label', default: 'Foto')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${remiseInstanceList}" status="i" var="remiseInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${remiseInstance.id}">${fieldValue(bean: remiseInstance, field: "patente")}</g:link></td>
					
						<td>${fieldValue(bean: remiseInstance, field: "chofer")}</td>
					
						<td>${fieldValue(bean: remiseInstance, field: "foto")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${remiseInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
