<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<meta name="layout" content="main" />
<title>Ingreso de Agencia</title>
</head>
<body>
	<div class="body">
		<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
		</g:if>
		<g:form action="entrar">
			<fieldset class="form">
				<g:select name="agencia" from="${agencias}" class="many-to-one"
					noSelection="['null':'Seleccione su agencia...']" />
			</fieldset>
			<fieldset class="buttons">
				<g:submitButton name="ingresar" class="ingresar" value="Ingresar" />
				<g:link action="create" class="nuevaAgencia">Soy una agencia nueva</g:link>
			</fieldset>

		</g:form>
	</div>
</body>
</html>