<g:if test="${session.pasajero }">
  <div class="nav" role="navigation">
    <ul>
      <li>
        <g:link class="remises" controller="pasajero" action="listReservas">Mis Viajes</g:link>
      </li>
      <li>
        <g:link class="reservas" controller="reserva" action="create">Hacer una Reserva</g:link>
      </li>
      <li>
        <g:link class="pasajero" controller="pasajero" action="show" id="${session.pasajero.id }">Mis Datos</g:link>
      </li>
	   <li>
        <g:link class="pasajero" controller="pasajero" action="amigos">Mis Amigos</g:link>
      </li>
      <li>
        <g:link class="logout" controller="pasajero" action="logout">Salir</g:link>
      </li>
    </ul>
  </div>
</g:if>
