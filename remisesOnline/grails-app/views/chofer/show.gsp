
<%@ page import="remisesonline.Chofer" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'chofer.label', default: 'Chofer')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-chofer" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div id="show-chofer" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list chofer">
			
				<g:if test="${choferInstance?.dni}">
				<li class="fieldcontain">
					<span id="dni-label" class="property-label"><g:message code="chofer.dni.label" default="Dni" /></span>
					
						<span class="property-value" aria-labelledby="dni-label"><g:fieldValue bean="${choferInstance}" field="dni"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${choferInstance?.nombre}">
				<li class="fieldcontain">
					<span id="nombre-label" class="property-label"><g:message code="chofer.nombre.label" default="Nombre" /></span>
					
						<span class="property-value" aria-labelledby="nombre-label"><g:fieldValue bean="${choferInstance}" field="nombre"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${choferInstance?.telefono}">
				<li class="fieldcontain">
					<span id="telefono-label" class="property-label"><g:message code="chofer.telefono.label" default="Telefono" /></span>
					
						<span class="property-value" aria-labelledby="telefono-label"><g:fieldValue bean="${choferInstance}" field="telefono"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${choferInstance?.direccion}">
				<li class="fieldcontain">
					<span id="direccion-label" class="property-label"><g:message code="chofer.direccion.label" default="Direccion" /></span>
					
						<span class="property-value" aria-labelledby="direccion-label"><g:fieldValue bean="${choferInstance}" field="direccion"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${choferInstance?.licencia}">
				<li class="fieldcontain">
					<span id="licencia-label" class="property-label"><g:message code="chofer.licencia.label" default="Licencia" /></span>
					
						<span class="property-value" aria-labelledby="licencia-label"><g:fieldValue bean="${choferInstance}" field="licencia"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:choferInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${choferInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
