
<%@ page import="pjbiblioj.Utilisateur" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'utilisateur.label', default: 'Utilisateur')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-utilisateur" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-utilisateur" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="nom" title="${message(code: 'utilisateur.nom.label', default: 'Nom')}" />
					
						<g:sortableColumn property="identifiant" title="${message(code: 'utilisateur.identifiant.label', default: 'Identifiant')}" />
					
						<g:sortableColumn property="motDePasse" title="${message(code: 'utilisateur.motDePasse.label', default: 'Mot De Passe')}" />
					
						<th><g:message code="utilisateur.panier.label" default="Panier" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${utilisateurInstanceList}" status="i" var="utilisateurInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${utilisateurInstance.id}">${fieldValue(bean: utilisateurInstance, field: "nom")}</g:link></td>
					
						<td>${fieldValue(bean: utilisateurInstance, field: "identifiant")}</td>
					
						<td>${fieldValue(bean: utilisateurInstance, field: "motDePasse")}</td>
					
						<td>${fieldValue(bean: utilisateurInstance, field: "panier")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${utilisateurInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
