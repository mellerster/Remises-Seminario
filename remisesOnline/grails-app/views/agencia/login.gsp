<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<meta name="layout" content="main" />
<title>Ingreso de Agencia</title>
</head>
<body>
	<div class="body">
		<table>
			<tr>
				<td><g:form action="entrar">
						<g:select name="agencia" from="${agencias}"
							noSelection="['null':'Seleccione su agencia...']" />
						<br />
						<g:submitButton name="ingresar" value="Ingresar"/>
					</g:form></td>
			</tr>
			<tr>
				<td><g:link action="create">Soy una agencia nueva</g:link></td>
			</tr>

		</table>
	</div>
</body>
</html>