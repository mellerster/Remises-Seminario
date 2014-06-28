<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<meta name="layout" content="main" />
<title>Quiero ir junto</title>
</head>
<body>
	<div class="body">
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