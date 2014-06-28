
<%@ page import="remisesonline.Pasajero" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'pasajero.label', default: 'Pasajero')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-pasajero" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div id="show-pasajero" class="content scaffold-show" role="main">
			<h1>Mis Datos</h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${pasajeroInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${pasajeroInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
			<ol class="property-list pasajero">
			
				<g:if test="${pasajeroInstance?.nombre}">
				<li class="fieldcontain">
					<span id="nombre-label" class="property-label"><g:message code="pasajero.nombre.label" default="Nombre" /></span>
					
						<span class="property-value" aria-labelledby="nombre-label"><g:fieldValue bean="${pasajeroInstance}" field="nombre"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${pasajeroInstance?.email}">
				<li class="fieldcontain">
					<span id="email-label" class="property-label"><g:message code="pasajero.email.label" default="Email" /></span>
					
						<span class="property-value" aria-labelledby="email-label"><g:fieldValue bean="${pasajeroInstance}" field="email"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${pasajeroInstance?.telefono}">
				<li class="fieldcontain">
					<span id="telefono-label" class="property-label"><g:message code="pasajero.telefono.label" default="Telefono" /></span>
					
						<span class="property-value" aria-labelledby="telefono-label"><g:fieldValue bean="${pasajeroInstance}" field="telefono"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${pasajeroInstance?.amigos}">
				<li class="fieldcontain">
					<span id="amigos-label" class="property-label"><g:message code="pasajero.amigos.label" default="Amigos" /></span>
					
						<g:each in="${pasajeroInstance.amigos}" var="a">
						<span class="property-value" aria-labelledby="amigos-label"><g:link controller="pasajero" action="show" id="${a.id}">${a?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${pasajeroInstance?.fechaNacimiento}">
				<li class="fieldcontain">
					<span id="fechaNacimiento-label" class="property-label"><g:message code="pasajero.fechaNacimiento.label" default="Fecha Nacimiento" /></span>
					
					<span class="property-value" aria-labelledby="fechaNacimiento-label"><g:formatDate date="${pasajeroInstance?.fechaNacimiento}" format="dd/MM/yyyy" /></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:pasajeroInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${pasajeroInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
