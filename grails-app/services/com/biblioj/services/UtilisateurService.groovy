package com.biblioj.services

import javax.servlet.http.HttpSession
import org.codehaus.groovy.grails.web.metaclass.GetSessionDynamicProperty;
import org.codehaus.groovy.grails.web.servlet.mvc.GrailsHttpSession
import org.codehaus.groovy.grails.web.servlet.mvc.GrailsWebRequest
import org.codehaus.groovy.grails.web.util.WebUtils
import org.springframework.web.context.request.RequestContextHolder

import pjbiblioj.Utilisateur

class UtilisateurService {

	/**
	 * Permet de ce connecter au site
	 * @param pseudo
	 * @param motDePasse
	 * @return
	 */
    def connecter(String identifiant,String motDePasse, HttpSession mySession){
			
	
		def utilisateurs = Utilisateur.findAll([max: 1]){
			eq("identifiant",identifiant)
			eq("motDePasse",motDePasse)
		}
		
		println utilisateurs.nom
		
		if (!utilisateurs.isEmpty()){
			def utilisateurSelectionner
			utilisateurs.each {
				utilisateurSelectionner = it
			}
			println utilisateurSelectionner.getNom()
			
			//def utilisateurSession = [name: utilisateurSelectionner.getNom()]
			
			GrailsWebRequest webUtils = WebUtils.retrieveGrailsWebRequest()
			/*def request = webUtils.getCurrentRequest()
			def response = webUtils.getCurrentResponse()*/
			//def mySession = webUtils.getSession()
			mySession.user = utilisateurSelectionner.getNom()
			/*mySession.getServletContext().add

			mySession["user"] = utilisateurSelectionner.getNom()*/
			//session["client"] = utilisateurSelectionner
			//def fooAttr = session["foo"]
		}
		
		return utilisateurs
	}
}
