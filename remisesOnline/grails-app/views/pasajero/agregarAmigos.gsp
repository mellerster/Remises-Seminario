<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'pasajero.label', default: 'Pasajero')}" />
		<title>Agregar amigo</title>
	</head>
	<body>
		<g:select name="pasajero.nombre" from="${pasajeros}" value="${nombre}" noSelection="['':'Pasajeros']"/>  
	</body>
</html>	











        
 