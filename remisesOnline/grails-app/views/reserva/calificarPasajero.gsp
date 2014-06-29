<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="main">
<title>Calificar Pasajero</title>
</head>
<body>
	<a href="#calificar-pasajero" class="skip" tabindex="-1"><g:message
			code="default.link.skip.label" default="Skip to content&hellip;" /></a>
	<div id="calificar-remise" class="content scaffold-create" role="main">
		<h1>Calificar Remise</h1>
		<g:if test="${flash.message}">
			<div class="message" role="status">
				${flash.message}
			</div>
		</g:if>
		<g:hasErrors bean="${remiseInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${remiseInstance}" var="error">
					<li
						<g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message
							error="${error}" /></li>
				</g:eachError>
			</ul>
		</g:hasErrors>
		<g:form action='guardarCalificacionPasajero'
			id="${reservaInstance?.id }" enctype="multipart/form-data">
			<ol class="property-list pasajero">

				<g:if test="${reservaInstance?.pasajero}">
					<li class="fieldcontain"><span id="nombre-label"
						class="property-label">Pasajero</span> <span
						class="property-value" aria-labelledby="nombre-label"><g:fieldValue
								bean="${reservaInstance.pasajero}" field="nombre" /></span></li>
				</g:if>

				<g:if test="${reservaInstance?.pasajero?.calificacion}">
					<li class="fieldcontain"><span id="patente-label"
						class="property-label">Calificación</span> <span
						class="property-value" aria-labelledby="calificacion-label">
							<div id="calificacion"></div>
					</span></li>
					<g:javascript>
					$(function() {
							$.fn.raty.defaults.path = '${resource(dir:'images') }';
						    $('#calificacion').raty({
	  								readOnly   : true
	  								,score: ${reservaInstance?.pasajero?.calificacion}
									,hints: ['Pesimo', 'Malo', 'Bueno', 'Muy Bueno', 'Excelente']
	  							});
						    })
					</g:javascript>
					
				</g:if>
				<li class="fieldcontain"><span id="puntaje-label"
					class="property-label">Puntaje</span> <span class="property-value"
					aria-labelledby="puntaje-label">
						<div id="selectpuntaje"></div>

				</span> <g:javascript>
					$.fn.raty.defaults.path = '${resource(dir:'images') }';
					$('#selectpuntaje').raty({
						hints: ['Pesimo', 'Malo', 'Bueno', 'Muy Bueno', 'Excelente'],
					  click: function(score, evt) {
					  	$('#puntaje').val(score);
					  }
					});
				</g:javascript></li>
			</ol>
			<fieldset class="form">
				<g:hiddenField name="puntaje" />
			</fieldset>
			<fieldset class="buttons">
				<g:submitButton name="puntuar" class="save" value="Puntuar" />
			</fieldset>
		</g:form>
	</div>
</body>
</html>