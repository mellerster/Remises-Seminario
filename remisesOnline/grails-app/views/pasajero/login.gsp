<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<meta name="layout" content="main" />
<title>Ingreso de Pasajero</title>
</head>
<body>
	<div class="body">
		<g:form action="entrar">
			<fieldset class="form">
				<g:select name="pasajero" from="${pasajeros}" value="pasajero?.id"
					optionKey="id" class="many-to-one"
					noSelection="['null':'Seleccione sus datos...']" />
			</fieldset>
			<fieldset class="buttons">
				<g:submitButton name="ingresar" class="ingresar" value="Ingresar"
					action="entrar" />
				<g:link class="nuevoPasajero" action="crear">
								Soy un nuevo pasajero
							</g:link>
			</fieldset>
		</g:form>
	</div>
</body>
</html>