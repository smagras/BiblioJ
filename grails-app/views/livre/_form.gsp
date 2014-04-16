<%@ page import="pjbiblioj.Livre" %>



<div class="fieldcontain ${hasErrors(bean: livreInstance, field: 'titre', 'error')} ">
	<label for="titre">
		<g:message code="livre.titre.label" default="Titre" />
		
	</label>
	<g:textField name="titre" value="${livreInstance?.titre}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: livreInstance, field: 'nombreExemplaires', 'error')} required">
	<label for="nombreExemplaires">
		<g:message code="livre.nombreExemplaires.label" default="Nombre Exemplaires" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="nombreExemplaires" type="number" value="${livreInstance.nombreExemplaires}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: livreInstance, field: 'nombreExemplairesDisponibles', 'error')} required">
	<label for="nombreExemplairesDisponibles">
		<g:message code="livre.nombreExemplairesDisponibles.label" default="Nombre Exemplaires Disponibles" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="nombreExemplairesDisponibles" type="number" value="${livreInstance.nombreExemplairesDisponibles}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: livreInstance, field: 'typeDoc', 'error')} ">
	<label for="typeDoc">
		<g:message code="livre.typeDoc.label" default="Type Doc" />
		
	</label>
	<g:select id="typeDoc" name="typeDoc.id" from="${pjbiblioj.TypeDocument.list()}" optionKey="id" value="${livreInstance?.typeDoc?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: livreInstance, field: 'auteurs', 'error')} required">
	<label for="auteurs">
		<g:message code="livre.auteurs.label" default="Auteurs" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="auteurs" from="${pjbiblioj.Auteur.list()}" multiple="multiple" optionKey="id" size="5" required="" value="${livreInstance?.auteurs*.id}" class="many-to-many"/>
</div>

