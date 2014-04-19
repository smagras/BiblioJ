package com.biblioj.services

import pjbiblioj.Auteur
import pjbiblioj.Livre
import pjbiblioj.TypeDocument;

/**
 * Permet d'avoir les services concernants les livres
 */
class LivreService {

	/**
	 * Permet de retourner les livres en fonction du type de document
	 * @param typeDoc
	 * @return livres
	 */
	def rechercherLivreTypeDoc(TypeDocument typeDoc) {
		def livresEnFonctionDuTypeDeDoc = new ArrayList<Livre>()
		
		def listeTypeDocEquivalant = TypeDocument.findAll {
			eq("intitule", typeDoc.getIntitule())
		}

		if (!listeTypeDocEquivalant.isEmpty()) {
			def typeDansLaBDEquivalant = listeTypeDocEquivalant.get(0)
	
			Livre.findAllByTypeDoc(typeDansLaBDEquivalant, [max: 5]).each {
				livresEnFonctionDuTypeDeDoc.add(it)
			}
		}
		
		return livresEnFonctionDuTypeDeDoc
	}
	
	
	/**
	 * Permet de retourner les livres en fonction du titre
	 * @param titre
	 * @return livres
	 */
	def rechercherLivreTitre(String titre) {
		def livresEnFonctionDuTitre = new ArrayList<Livre>()
		titre = "%" + titre + "%"

		Livre.findAllByTitreIlike(titre, [max: 5]).each {
			livresEnFonctionDuTitre.add(it)
		}
		
		return livresEnFonctionDuTitre
	}
	
	
	/**
	 * Permet de retourner les livres en fonction des auteurs
	 * @param nom
	 * @return
	 */
	def rechercherLivreAuteur(String nom) {
		
		def livresEnFonctionAuteur = new ArrayList<Livre>()
		Auteur auteur = Auteur.findByNom(nom)
		
		int tailleListe = auteur.livres.size()
		if (tailleListe > 5) tailleListe = 5
		
		for (int i=0; i<tailleListe; i++) {
			Livre livre = auteur.livres.toList().get(i)
			livresEnFonctionAuteur.add(livre)
		}
		
		return livresEnFonctionAuteur
		
	}
}
