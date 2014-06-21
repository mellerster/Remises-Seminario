<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'pasajero.label', default: 'Pasajero')}" />
		<title>Mis amigos</title>
	</head>
	<body>
		<ul>
        <g:each in="${pasajeros}" var="pasajero">
           <li>${pasajero.nombre} </li>
        </g:each>
        </ul>
	</body>
</html>	











        
 