<%@ page import="pjbiblioj.Utilisateur" %>



<div class="fieldcontain ${hasErrors(bean: utilisateurInstance, field: 'nom', 'error')} required">
	<label for="nom">
		<g:message code="utilisateur.nom.label" default="Nom" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nom" pattern="${utilisateurInstance.constraints.nom.matches}" required="" value="${utilisateurInstance?.nom}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: utilisateurInstance, field: 'identifiant', 'error')} required">
	<label for="identifiant">
		<g:message code="utilisateur.identifiant.label" default="Identifiant" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="identifiant" pattern="${utilisateurInstance.constraints.identifiant.matches}" required="" value="${utilisateurInstance?.identifiant}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: utilisateurInstance, field: 'motDePasse', 'error')} required">
	<label for="motDePasse">
		<g:message code="utilisateur.motDePasse.label" default="Mot De Passe" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="motDePasse" pattern="${utilisateurInstance.constraints.motDePasse.matches}" required="" value="${utilisateurInstance?.motDePasse}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: utilisateurInstance, field: 'panier', 'error')} required">
	<label for="panier">
		<g:message code="utilisateur.panier.label" default="Panier" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="panier" name="panier.id" from="${pjbiblioj.Panier.list()}" optionKey="id" required="" value="${utilisateurInstance?.panier?.id}" class="many-to-one"/>
</div>

