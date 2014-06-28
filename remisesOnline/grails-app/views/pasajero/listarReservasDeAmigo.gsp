<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<meta name="layout" content="main" />
<title>Reservas de amigos</title>
</head>
<body>
	<div class="body">
		<g:form action="unirseAReserva">
			<fieldset class="form">
				<g:select name="reserva" from="${reservas}" value="reserva?.id"
					optionKey="id" class="many-to-one"
					noSelection="['null':'Seleccione reserva']" />
 			</fieldset>
 
			<fieldset class="buttons">
				<g:submitButton name="reservas" value="Unirse"
					action="unirseAReserva" />
			</fieldset>
		</g:form>
	</div>
</body>
</html>