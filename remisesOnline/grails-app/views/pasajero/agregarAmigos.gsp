<html>
   <head>
   </head>
   <body>
        <ul>
        <g:each in="${pasajeros}" var="pasajero">
           <li>${pasajero.nombre} </li>
        </g:each>
        </ul>
   </body>
</html>