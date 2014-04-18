package pjbiblioj

import java.util.Set;

class Livre {

    String titre
	int nombreExemplaires, nombreExemplairesDisponibles
	TypeDocument typeDoc
	Set auteurs = new HashSet()
	
	static belongsTo = Reservation
	static hasMany = [auteurs:Auteur]
	
    static constraints = {
		titre nullable: false
		nombreExemplaires nullable: false
		nombreExemplairesDisponibles nullable: false
		typeDoc nullable: true
		auteurs nullable: false
    }
	
	
	
	def static rechercherLivreTypeDoc(TypeDocument typeDoc) {
		Livre.findAllByTypeDoc(typeDoc, [max: 5]).each {
			println it.titre + " " + it.auteurs.nom + " " + it.typeDoc.intitule + " " + it.nombreExemplairesDisponibles
		}
	}
	
	
	def static rechercherLivreTitre(String titre) {
		Livre.findAllByTitre(titre, [max: 5]).each {
			println it.titre + " " + it.auteurs.nom + " " + it.typeDoc.intitule + " " + it.nombreExemplairesDisponibles
		}
	}
	
	
	def static rechercherLivreAuteur(String nom) {
		Auteur auteur = Auteur.findByNom(nom)
		
		int tailleListe = auteur.livres.size()
		if (tailleListe > 5) tailleListe = 5
		
		for (int i=0; i<tailleListe; i++) {
			Livre livre = auteur.livres.toList().get(i)
			println livre.titre + " " + livre.auteurs.nom + " " + livre.typeDoc.intitule + " " + livre.nombreExemplairesDisponibles
		}
		
		/*Livre.findAll("from Livre as l, Auteur as a where a.nom=:auteur and a in l.auteurs", [auteur: nom], [max: 5]).each {
			println it.titre + " " + it.auteurs.nom + " " + it.typeDoc.intitule + " " + it.nombreExemplairesDisponibles
		}*/
		
	}
}
