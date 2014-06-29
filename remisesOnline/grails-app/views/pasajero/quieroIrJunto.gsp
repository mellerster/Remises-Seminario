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
				aca van a ir botones 
			</ul>
		</div>
<h1>Nueva Solicitud</h1>
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
					<g:submitButton name="irJunto" value="Listar reservas"
					action="listarReservasDeAmigo" />
 			</fieldset>

		</g:form>
	</div>
</body>
</html>