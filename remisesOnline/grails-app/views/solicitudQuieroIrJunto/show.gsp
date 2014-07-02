
<%@ page import="remisesonline.SolicitudQuieroIrJunto" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'solicitudQuieroIrJunto.label', default: 'SolicitudQuieroIrJunto')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-solicitudQuieroIrJunto" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-solicitudQuieroIrJunto" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list solicitudQuieroIrJunto">
			
				<g:if test="${solicitudQuieroIrJuntoInstance?.estado}">
				<li class="fieldcontain">
					<span id="estado-label" class="property-label"><g:message code="solicitudQuieroIrJunto.estado.label" default="Estado" /></span>
					
						<span class="property-value" aria-labelledby="estado-label"><g:fieldValue bean="${solicitudQuieroIrJuntoInstance}" field="estado"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudQuieroIrJuntoInstance?.fechaCreada}">
				<li class="fieldcontain">
					<span id="fechaCreada-label" class="property-label"><g:message code="solicitudQuieroIrJunto.fechaCreada.label" default="Fecha Creada" /></span>
					
						<span class="property-value" aria-labelledby="fechaCreada-label"><g:formatDate date="${solicitudQuieroIrJuntoInstance?.fechaCreada}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudQuieroIrJuntoInstance?.pasajero}">
				<li class="fieldcontain">
					<span id="pasajero-label" class="property-label"><g:message code="solicitudQuieroIrJunto.pasajero.label" default="Pasajero" /></span>
					
						<span class="property-value" aria-labelledby="pasajero-label"><g:link controller="pasajero" action="show" id="${solicitudQuieroIrJuntoInstance?.pasajero?.id}">${solicitudQuieroIrJuntoInstance?.pasajero?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudQuieroIrJuntoInstance?.reservaSolicitada}">
				<li class="fieldcontain">
					<span id="reservaSolicitada-label" class="property-label"><g:message code="solicitudQuieroIrJunto.reservaSolicitada.label" default="Reserva Solicitada" /></span>
					
						<span class="property-value" aria-labelledby="reservaSolicitada-label"><g:link controller="reserva" action="show" id="${solicitudQuieroIrJuntoInstance?.reservaSolicitada?.id}">${solicitudQuieroIrJuntoInstance?.reservaSolicitada?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudQuieroIrJuntoInstance?.solicitado}">
				<li class="fieldcontain">
					<span id="solicitado-label" class="property-label"><g:message code="solicitudQuieroIrJunto.solicitado.label" default="Solicitado" /></span>
					
						<span class="property-value" aria-labelledby="solicitado-label"><g:link controller="pasajero" action="show" id="${solicitudQuieroIrJuntoInstance?.solicitado?.id}">${solicitudQuieroIrJuntoInstance?.solicitado?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:solicitudQuieroIrJuntoInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${solicitudQuieroIrJuntoInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
