<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<meta name="layout" content="main" />
<title>Agregar un amigo</title>
</head>
<body>
	<div class="body">
		<g:form action="enviarSolicitud">
			<fieldset class="form">
				<g:select name="pasajeroSeleccionado" from="${pasajeros}"
					noSelection="['null':'Pasajeros...']" />
			</fieldset>
			<fieldset class="buttons">
				<g:submitButton name="enviar" value="Enviar solicitud de amistad" />
			</fieldset>
		</g:form>
	</div>
</body>
</html>
