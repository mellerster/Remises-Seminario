
<%@ page import="remisesonline.SolicitudAcompaniamiento" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'solicitudAcompaniamiento.label', default: 'SolicitudAcompaniamiento')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-solicitudAcompaniamiento" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-solicitudAcompaniamiento" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list solicitudAcompaniamiento">
			
				<g:if test="${solicitudAcompaniamientoInstance?.estado}">
				<li class="fieldcontain">
					<span id="estado-label" class="property-label"><g:message code="solicitudAcompaniamiento.estado.label" default="Estado" /></span>
					
						<span class="property-value" aria-labelledby="estado-label"><g:fieldValue bean="${solicitudAcompaniamientoInstance}" field="estado"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudAcompaniamientoInstance?.fechaCreada}">
				<li class="fieldcontain">
					<span id="fechaCreada-label" class="property-label"><g:message code="solicitudAcompaniamiento.fechaCreada.label" default="Fecha Creada" /></span>
					
						<span class="property-value" aria-labelledby="fechaCreada-label"><g:formatDate date="${solicitudAcompaniamientoInstance?.fechaCreada}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudAcompaniamientoInstance?.pasajero}">
				<li class="fieldcontain">
					<span id="pasajero-label" class="property-label"><g:message code="solicitudAcompaniamiento.pasajero.label" default="Pasajero" /></span>
					
						<span class="property-value" aria-labelledby="pasajero-label"><g:link controller="pasajero" action="show" id="${solicitudAcompaniamientoInstance?.pasajero?.id}">${solicitudAcompaniamientoInstance?.pasajero?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudAcompaniamientoInstance?.reservaSolicitada}">
				<li class="fieldcontain">
					<span id="reservaSolicitada-label" class="property-label"><g:message code="solicitudAcompaniamiento.reservaSolicitada.label" default="Reserva Solicitada" /></span>
					
						<span class="property-value" aria-labelledby="reservaSolicitada-label"><g:link controller="reserva" action="show" id="${solicitudAcompaniamientoInstance?.reservaSolicitada?.id}">${solicitudAcompaniamientoInstance?.reservaSolicitada?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudAcompaniamientoInstance?.solicitado}">
				<li class="fieldcontain">
					<span id="solicitado-label" class="property-label"><g:message code="solicitudAcompaniamiento.solicitado.label" default="Solicitado" /></span>
					
						<span class="property-value" aria-labelledby="solicitado-label"><g:link controller="pasajero" action="show" id="${solicitudAcompaniamientoInstance?.solicitado?.id}">${solicitudAcompaniamientoInstance?.solicitado?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:solicitudAcompaniamientoInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${solicitudAcompaniamientoInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
