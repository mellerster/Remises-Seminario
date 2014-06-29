<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<meta name="layout" content="main" />
<title>Quiero ir junto</title>
</head>
<body>
		<div class="nav" role="navigation">
			<ul>
				<li><g:link class="pasajero" controller="solicitudAmistad" action="create"><g:message code="Agregar amigo" args="[entityName]" /></g:link></li>
				<li><g:link class="pasajero" controller="pasajero" action="listSolicitudesEnviadas"><g:message code="Solicitudes Enviadas" args="[entityName]" /></g:link></li>
				<li><g:link class="pasajero" controller="pasajero" action="listSolicituderRecibidas"><g:message code="Solicitudes Recibidas" args="[entityName]" /></g:link></li>
				<li><g:link class="pasajero" controller="pasajero" action="quieroIrJunto"><g:message code="Quiero ir junto" args="[entityName]" /></g:link></li>
				
			</ul>
		</div>
	<div class="body">
		<g:if test="${flash.message}">
			<ul class="errors" role="alert">
				<li ><g:message error="${flash.message}"/></li>
			</ul>
		</g:if>
		<g:form action="listarReservasDeAmigo">
			<fieldset class="form">
				<g:select name="pasajero" from="${amigos}" value="pasajero?.id"
					optionKey="id" class="many-to-one"
					noSelection="['null':'Seleccione el amigo']" />
 			</fieldset>
 
			<fieldset class="buttons">
				<g:submitButton name="irJunto" value="Listar reservas"
					action="listarReservasDeAmigo" />
			</fieldset>
		</g:form>
	</div>
</body>
</html>
