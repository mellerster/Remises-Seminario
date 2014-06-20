
<%@ page import="remisesonline.Itinerario" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'itinerario.label', default: 'Itinerario')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-itinerario" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-itinerario" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list itinerario">
			
				<g:if test="${itinerarioInstance?.descripcion}">
				<li class="fieldcontain">
					<span id="descripcion-label" class="property-label"><g:message code="itinerario.descripcion.label" default="Descripcion" /></span>
					
						<span class="property-value" aria-labelledby="descripcion-label"><g:fieldValue bean="${itinerarioInstance}" field="descripcion"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${itinerarioInstance?.paradas}">
				<li class="fieldcontain">
					<span id="paradas-label" class="property-label"><g:message code="itinerario.paradas.label" default="Paradas" /></span>
					
						<g:each in="${itinerarioInstance.paradas}" var="p">
						<span class="property-value" aria-labelledby="paradas-label"><g:link controller="parada" action="show" id="${p.id}">${p?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${itinerarioInstance?.pasajeros}">
				<li class="fieldcontain">
					<span id="pasajeros-label" class="property-label"><g:message code="itinerario.pasajeros.label" default="Pasajeros" /></span>
					
						<g:each in="${itinerarioInstance.pasajeros}" var="p">
						<span class="property-value" aria-labelledby="pasajeros-label"><g:link controller="pasajero" action="show" id="${p.id}">${p?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${itinerarioInstance?.reserva}">
				<li class="fieldcontain">
					<span id="reserva-label" class="property-label"><g:message code="itinerario.reserva.label" default="Reserva" /></span>
					
						<g:each in="${itinerarioInstance.reserva}" var="r">
						<span class="property-value" aria-labelledby="reserva-label"><g:link controller="reserva" action="show" id="${r.id}">${r?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:itinerarioInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${itinerarioInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
