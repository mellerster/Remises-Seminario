<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<meta name="layout" content="main" />
<title>Quiero ir junto</title>
</head>
<body>
		<a href="#list-solicitudAmistad" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><g:link class="pasajero" controller="pasajero" action="showReservasAmigos"><g:message code="Solicitar ir junto a un amigo" args="[entityName]" /></g:link></li>
				<li><g:link class="solicitudesEnviadas" controller="pasajero" action="listSolicitudesQuieroIrJuntoEnviadas"><g:message code="Solicitudes Enviadas" args="[entityName]" /></g:link></li>
				<li><g:link class="solicitudesRecibidas" controller="pasajero" action="listSolicitudesQuieroIrJuntoRecibidas"><g:message code="Solicitudes Recibidas" args="[entityName]" /></g:link></li>
			</ul>
		</div>
	<div class="body">
		<g:if test="${flash.message}">
			<ul class="errors" role="alert">
				<li ><g:message error="${flash.message}"/></li>
			</ul>
		</g:if>
		<g:form action="listReservasDeAmigo">
			<fieldset class="form">
				<g:select name="pasajero" from="${amigos}" value="pasajero?.id"
					optionKey="id" class="many-to-one"
					noSelection="['null':'Seleccione el amigo']" />
					<g:submitButton name="irJunto" value="Listar reservas"
					action="listReservasDeAmigo" />
 			</fieldset>

		</g:form>
	</div>
</body>
</html>