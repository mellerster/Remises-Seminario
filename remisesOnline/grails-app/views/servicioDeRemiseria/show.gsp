
<%@ page import="remisesonline.ServicioDeRemiseria"%>
<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="main">
<g:set var="entityName"
	value="${message(code: 'servicioDeRemiseria.label', default: 'ServicioDeRemiseria')}" />
<title><g:message code="default.show.label" args="[entityName]" /></title>
</head>
<body>
	<a href="#show-servicioDeRemiseria" class="skip" tabindex="-1"><g:message
			code="default.link.skip.label" default="Skip to content&hellip;" /></a>
	<g:if test="${session.pasajero }">
			<div class="nav" role="navigation">
				<ul>
					<li><g:link class="list" action="listServicios">Ver Servicios</g:link></li>
					<li><g:link class="list" action="misAdhesiones">Mis Adhesiones</g:link></li>
				</ul>
			</div>
		</g:if>
	<div id="show-servicioDeRemiseria" class="content scaffold-show"
		role="main">
		<h1>
			<g:message code="default.show.label" args="[entityName]" />
		</h1>
		<g:if test="${flash.message}">
			<div class="message" role="status">
				${flash.message}
			</div>
		</g:if>
		<ol class="property-list servicioDeRemiseria">

			<g:if test="${servicioDeRemiseriaInstance?.nombre}">
				<li class="fieldcontain"><span id="nombre-label"
					class="property-label"><g:message
							code="servicioDeRemiseria.nombre.label" default="Nombre" /></span> <span
					class="property-value" aria-labelledby="nombre-label"><g:fieldValue
							bean="${servicioDeRemiseriaInstance}" field="nombre" /></span></li>
			</g:if>

			<g:if test="${servicioDeRemiseriaInstance?.descripcion}">
				<li class="fieldcontain"><span id="descripcion-label"
					class="property-label"><g:message
							code="servicioDeRemiseria.descripcion.label"
							default="Descripcion" /></span> <span class="property-value"
					aria-labelledby="descripcion-label"><g:fieldValue
							bean="${servicioDeRemiseriaInstance}" field="descripcion" /></span></li>
			</g:if>
			<g:if test="${session.agencia }">
				<g:if test="${servicioDeRemiseriaInstance?.adheridos}">
					<li class="fieldcontain"><span id="adheridos-label"
						class="property-label"><g:message
								code="servicioDeRemiseria.adheridos.label" default="Adheridos" /></span>

						<g:each in="${servicioDeRemiseriaInstance.adheridos}" var="a">
							<span class="property-value" aria-labelledby="adheridos-label"><g:link
									controller="pasajero" action="show" id="${a.id}">
									${a?.encodeAsHTML()}
								</g:link></span>
						</g:each></li>
				</g:if>
			</g:if>
			<g:if test="${session.pasajero}">
				<li class="fieldcontain"><span id="agencia-label"
					class="property-label">Agencia</span> <span class="property-value"
					aria-labelledby="descripcion-label"><g:fieldValue
							bean="${servicioDeRemiseriaInstance}" field="agencia" /></span></li>
			</g:if>

		</ol>
		<g:if test="${session.agencia }">
			<g:form url="[resource:servicioDeRemiseriaInstance, action:'delete']"
				method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit"
						resource="${servicioDeRemiseriaInstance}">
						<g:message code="default.button.edit.label" default="Edit" />
					</g:link>
				</fieldset>
			</g:form>
		</g:if>
		<g:if test="${session.pasajero}">
			<g:if
				test="${!servicioDeRemiseriaInstance.adheridos.id.contains(session.pasajero.id) }">
				<g:form
					url="[resource:servicioDeRemiseriaInstance, action:'adherirse']">
					<fieldset class="buttons">
						<g:submitButton name="Adherirse" />
					</fieldset>
				</g:form>
			</g:if>
			<g:else>
				<g:form
					url="[resource:servicioDeRemiseriaInstance, action:'desadherirse']">
					<fieldset class="buttons">
						<g:submitButton name="Desadherirse" />
					</fieldset>
				</g:form>
			</g:else>
		</g:if>
	</div>
</body>
</html>
