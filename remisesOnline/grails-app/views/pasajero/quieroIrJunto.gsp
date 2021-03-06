
<%@ page import="remisesonline.SolicitudAmistad" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'solicitudAmistad.label', default: 'SolicitudAmistad')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<g:if test="${flash.message}">
			<ul class="errors" role="alert">
				<li ><g:message error="${flash.message}"/></li>
			</ul>
		</g:if>
		<a href="#list-solicitudAmistad" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><g:link class="pasajero" controller="pasajero" action="showReservasAmigos"><g:message code="Solicitar ir junto a un amigo" args="[entityName]" /></g:link></li>
				<li><g:link class="solicitudesEnviadas" controller="pasajero" action="listSolicitudesQuieroIrJuntoEnviadas"><g:message code="Solicitudes Enviadas" args="[entityName]" /></g:link></li>
				<li><g:link class="solicitudesRecibidas" controller="pasajero" action="listSolicitudesQuieroIrJuntoRecibidas"><g:message code="Solicitudes Recibidas" args="[entityName]" /></g:link></li>
			</ul>
		</div>
	</body>
</html>