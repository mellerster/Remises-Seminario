
<%@ page import="remisesonline.Remise" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'remise.label', default: 'Remise')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-remise" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div id="show-remise" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list remise">
			
				<g:if test="${remiseInstance?.patente}">
				<li class="fieldcontain">
					<span id="patente-label" class="property-label"><g:message code="remise.patente.label" default="Patente" /></span>
					
						<span class="property-value" aria-labelledby="patente-label"><g:fieldValue bean="${remiseInstance}" field="patente"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${remiseInstance?.comodidades}">
				<li class="fieldcontain">
					<span id="comodidades-label" class="property-label"><g:message code="remise.comodidades.label" default="Comodidades" /></span>
					
						<g:each in="${remiseInstance.comodidades}" var="c">
						<span class="property-value" aria-labelledby="comodidades-label"><g:link controller="comodidad" action="show" id="${c.id}">${c?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${remiseInstance?.chofer}">
				<li class="fieldcontain">
					<span id="chofer-label" class="property-label"><g:message code="remise.chofer.label" default="Chofer" /></span>
					
						<span class="property-value" aria-labelledby="chofer-label"><g:link controller="chofer" action="show" id="${remiseInstance?.chofer?.id}">${remiseInstance?.chofer?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${remiseInstance?.foto}">
				<li class="fieldcontain">
					<span id="foto-label" class="property-label"><g:message code="remise.foto.label" default="Foto" /></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:remiseInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${remiseInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
