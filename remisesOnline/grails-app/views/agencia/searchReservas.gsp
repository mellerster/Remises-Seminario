<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<meta name="layout" content="main" />
<title>Buscar reservas por estado</title>
</head>
<body>
	<div class="body">
		<g:form action="listReservas">
			<fieldset class="form">
				<g:select name="estadoSeleccionado" from="${estadosReservas}" class="many-to-one"
					noSelection="['null':'Seleccione estado...']" />
			</fieldset>
			<fieldset class="buttons">
				<g:submitButton name="listar" value="Listar" />
			</fieldset>

		</g:form>
	</div>
</body>
</html>