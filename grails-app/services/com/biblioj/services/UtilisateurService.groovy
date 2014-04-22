package com.biblioj.services

import javax.servlet.http.HttpSession
import org.codehaus.groovy.grails.web.metaclass.GetSessionDynamicProperty;
import org.codehaus.groovy.grails.web.servlet.mvc.GrailsHttpSession
import org.codehaus.groovy.grails.web.servlet.mvc.GrailsWebRequest
import org.codehaus.groovy.grails.web.util.WebUtils
import org.springframework.web.context.request.RequestContextHolder

import pjbiblioj.Livre
import pjbiblioj.Panier
import pjbiblioj.Reservation
import pjbiblioj.Utilisateur



/**
 * Services pour les utilisateur et leur actions quand a la base de donnée
 */
class UtilisateurService {
	
	def viderPanier(HttpSession mySession){
		Utilisateur utilisateur = getUtilisateurConnecter(mySession)
		
		if (utilisateur){
			Panier panier = utilisateur.getPanier()
			
			panier.vider()
			return true
		}
		
		return false
	}
	
	
	def ajouterPanier(HttpSession mySession, Livre livre) {
		Utilisateur utilisateur = getUtilisateurConnecter(mySession)
		
		if (utilisateur) {
			Panier panier = utilisateur.getPanier()
			panier.addLivre(livre)
			return true
		}
		
		return false
	}
	
	
	def inscrire(String pidentifiant,String pmotDePasse,String pnom){
		
		def utilisateurs = Utilisateur.findAll([max: 1]){
			eq("identifiant",pidentifiant)
			eq("motDePasse",pmotDePasse)
		}
		
		if (utilisateurs.isEmpty()){
			
			Panier p = new Panier(nom:pnom)
			p.save(flush: true)
			
			Utilisateur nouvelUtilisateur = new Utilisateur(nom:pnom,identifiant:pidentifiant,motDePasse:pmotDePasse,panier:p)
			nouvelUtilisateur.save(flush: true)
			
			return true
			
		}else
		{
			return false
		}
		
		
	}
	/**
	 * Permet de ce connecter au site
	 * @param pseudo
	 * @param mySession session en cour
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
			mySession.user = utilisateurSelectionner
			mySession.livres = utilisateurSelectionner.getPanier().getLivres()
			return true
		}
		
		return false
		
	}
	
	
	/**
	 * Permet d'ajouter une reservation a l'utilisateur corrant
	 * @param mySession
	 * @param reservation
	 * @return
	 */
	def ajouterReservation(HttpSession mySession,Reservation reservation){
		Utilisateur utilisateur = getUtilisateurConnecter(mySession)
		
		utilisateur.getReservations().add(reservation)
		utilisateur.save(flush: true)
	}
	
	
	/**
	 * Permet d'obtenir les reservation de l'utilisateur courant
	 * @param mySession
	 * @return
	 */
	def getReservations(HttpSession mySession){
		def reservations = new ArrayList<Reservation>()
		
		Utilisateur utilisateur = getUtilisateurConnecter(mySession)
		
		if (utilisateur){
			def res = utilisateur.getReservations()

			res.each{
				reservations.add(it)
			}
			
		}
		
		return reservations
		
	}
	
	
	
	/**
	 * permet de decconecter la session
	 * @param mySession session en cour
	 * @return
	 */
	def deconnecter(HttpSession mySession){

		mySession.user = null
	}
	
	
	/**
	 * Permet d'obtenir l'utilisateur connecter
	 * @param mySession
	 * @return
	 */
	def getUtilisateurConnecter(HttpSession mySession){
		Utilisateur utilisateurSession = mySession["user"]
		Utilisateur utilisateur = null
		if (utilisateurSession) {
			utilisateur = Utilisateur.findByIdentifiant(utilisateurSession.getIdentifiant())
		}
		return utilisateur
	}
}
