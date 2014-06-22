<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="main">
<g:set var="entityName"
	value="${message(code: 'comodidad.label', default: 'Comodidad')}" />
<title><g:message code="default.create.label"
		args="[entityName]" /></title>
</head>
<body>
	<a href="#create-comodidad" class="skip" tabindex="-1"><g:message
			code="default.link.skip.label" default="Skip to content&hellip;" /></a>
	<div id="create-comodidad" class="content scaffold-create" role="main">
		<h1>
			<g:message code="default.create.label" args="[entityName]" />
		</h1>
		<g:if test="${flash.message}">
			<div class="message" role="status">
				${flash.message}
			</div>
		</g:if>
		<g:hasErrors bean="${comodidadInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${comodidadInstance}" var="error">
					<li
						<g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message
							error="${error}" /></li>
				</g:eachError>
			</ul>
		</g:hasErrors>
		<g:form url="[resource:comodidadInstance, action:'save']">
			<fieldset class="form">
			<%@ page import="remisesonline.Comodidad"%>
				<div
					class="fieldcontain ${hasErrors(bean: comodidadInstance, field: 'descripcion', 'error')} required">
					<label for="descripcion"> <g:message
							code="comodidad.descripcion.label" default="Descripcion" /> <span
						class="required-indicator">*</span>
					</label>
					<g:textField name="descripcion" required=""
						value="${comodidadInstance?.descripcion}" />

				</div>
			</fieldset>
			<fieldset class="buttons">
				<g:submitButton name="create" class="save"
					value="${message(code: 'default.button.create.label', default: 'Create')}" />
			</fieldset>
		</g:form>
	</div>
</body>
</html>
