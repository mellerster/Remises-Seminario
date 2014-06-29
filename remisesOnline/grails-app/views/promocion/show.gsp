
<%@ page import="remisesonline.Promocion" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'promocion.label', default: 'Promocion')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-promocion" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-promocion" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list promocion">
			
				<g:if test="${promocionInstance?.agencia}">
				<li class="fieldcontain">
					<span id="agencia-label" class="property-label"><g:message code="promocion.agencia.label" default="Agencia" /></span>
					
						<span class="property-value" aria-labelledby="agencia-label"><g:link controller="agencia" action="show" id="${promocionInstance?.agencia?.id}">${promocionInstance?.agencia?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${promocionInstance?.descripcion}">
				<li class="fieldcontain">
					<span id="descripcion-label" class="property-label"><g:message code="promocion.descripcion.label" default="Descripcion" /></span>
					
						<span class="property-value" aria-labelledby="descripcion-label"><g:fieldValue bean="${promocionInstance}" field="descripcion"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${promocionInstance?.fechaDesde}">
				<li class="fieldcontain">
					<span id="fechaDesde-label" class="property-label"><g:message code="promocion.fechaDesde.label" default="Fecha Desde" /></span>
					
						<span class="property-value" aria-labelledby="fechaDesde-label"><g:formatDate date="${promocionInstance?.fechaDesde}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${promocionInstance?.fechaHasta}">
				<li class="fieldcontain">
					<span id="fechaHasta-label" class="property-label"><g:message code="promocion.fechaHasta.label" default="Fecha Hasta" /></span>
					
						<span class="property-value" aria-labelledby="fechaHasta-label"><g:formatDate date="${promocionInstance?.fechaHasta}" /></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:promocionInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${promocionInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
