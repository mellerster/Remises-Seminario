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
        <g:link class="reservas" controller="agencia" action="searchReservas">
				Mis Reservas
        </g:link>
      </li>
      <li>
        <g:link class="list" controller="agencia" action="show" id="${session.agencia.id }">
       		Mis Datos
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
