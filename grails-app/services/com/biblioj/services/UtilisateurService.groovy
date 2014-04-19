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
		
		
		if (!utilisateurs.isEmpty()){
			def utilisateurSelectionner
			utilisateurs.each {
				utilisateurSelectionner = it
			}
			mySession.user = utilisateurSelectionner.getNom()
			return true
		}
		
		return false
		
	}
	
	def deconnecter(HttpSession mySession){
		
	}
}
