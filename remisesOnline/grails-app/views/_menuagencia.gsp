<g:if test="${session.agencia }">
  <div class="nav" role="navigation">
    <ul>
      <li>
        <g:link class="remises" controller="remise" action="index">
				Mis Remises
        </g:link>
      </li>
      <li>
        <g:link class="choferes" controller="chofer" action="index">
				Mis Choferes
        </g:link>
      </li>
      <li>
        <g:link class="reservas" controller="reserva" action="index">
				Reservas
        </g:link>
      </li>
      <li>
        <g:link class="reservas" controller="agencia" action="searchReservas">
				Ver reservas
        </g:link>
      </li>
      <li>
        <g:link controller="agencia" action="show" id="${session.agencia.id }">
        Agencia: ${session.agencia}
        </g:link>
      </li>
      <li>
        <g:link class="logout" controller="agencia" action="logout">
				Salir
        </g:link>
      </li>
    </ul>
  </div>
</g:if>
