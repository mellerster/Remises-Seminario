<!--<g:if test="${session.agencia }">-->

<!--</g:if>-->
<div class="nav" role="navigation">
	<ul>
		<li><g:link class="remises" controller="remise" action="index">
				Mis Remises
			</g:link></li>
		<li><g:link class="choferes" controller="chofer" action="index">
				Mis Choferes
			</g:link></li>
		<li><g:link class="list" action="index">
				Reservas
			</g:link></li>
		<li><a class="logout"  href="${createLink(uri: '/')}">
				Salir
			</a></li>
	</ul>
</div>

