<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'pasajero.label', default: 'Pasajero')}" />
		<title>Ver amigos</title>
	</head>
   <body>
        <ul>
        <g:each in="${amigos}" var="amigo">
           <li>${amigo.nombre} </li>
        </g:each>
        </ul>
   </body>
</html>