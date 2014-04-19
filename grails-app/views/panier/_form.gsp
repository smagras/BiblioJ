<%@ page import="pjbiblioj.Panier" %>



<div class="fieldcontain ${hasErrors(bean: panierInstance, field: 'nom', 'error')} required">
	<label for="nom">
		<g:message code="panier.nom.label" default="Nom" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nom" required="" value="${panierInstance?.nom}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: panierInstance, field: 'livres', 'error')} ">
	<label for="livres">
		<g:message code="panier.livres.label" default="Livres" />
		
	</label>
	<g:select name="livres" from="${pjbiblioj.Livre.list()}" multiple="multiple" optionKey="id" size="5" value="${panierInstance?.livres*.id}" class="many-to-many"/>
</div>

