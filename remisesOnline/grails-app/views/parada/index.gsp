
<%@ page import="remisesonline.Parada" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'parada.label', default: 'Parada')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-parada" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-parada" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="calle" title="${message(code: 'parada.calle.label', default: 'Calle')}" />
					
						<g:sortableColumn property="numero" title="${message(code: 'parada.numero.label', default: 'Numero')}" />
					
						<g:sortableColumn property="descripcion" title="${message(code: 'parada.descripcion.label', default: 'Descripcion')}" />
					
						<g:sortableColumn property="localidad" title="${message(code: 'parada.localidad.label', default: 'Localidad')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${paradaInstanceList}" status="i" var="paradaInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${paradaInstance.id}">${fieldValue(bean: paradaInstance, field: "calle")}</g:link></td>
					
						<td>${fieldValue(bean: paradaInstance, field: "numero")}</td>
					
						<td>${fieldValue(bean: paradaInstance, field: "descripcion")}</td>
					
						<td>${fieldValue(bean: paradaInstance, field: "localidad")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${paradaInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
