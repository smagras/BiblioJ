package com.biblioj.services

import pjbiblioj.Auteur
import pjbiblioj.Livre
import pjbiblioj.TypeDocument;

/**
 * Permet d'avoir les services concernants les livres
 */
class LivreService {
	
	def rechercherLivres(TypeDocument typeDoc, String titre, String auteur) {
		def livresTries = new ArrayList<Livre>()
		
		def listeEnFonctionDesDocuments = rechercherLivreTypeDoc(typeDoc)
		def listeEnFonctionDuTitre = rechercherLivreTitre(titre)
		def listeEnFonctionDesAuteurs = rechercherLivreAuteur(auteur)
		
		def listeDeListeValide = new ArrayList<List>()
		if (!typeDoc.isEmpty()) listeDeListeValide.add(listeEnFonctionDesDocuments)
		if (titre != "") listeDeListeValide.add(listeEnFonctionDuTitre)
		if (auteur != "") listeDeListeValide.add(listeEnFonctionDesAuteurs)
		
		
		if (listeDeListeValide.size() == 1) {
			listeDeListeValide.get(0)
		}
		
		else if (!listeDeListeValide.isEmpty()) {
			
			def premiereListe = listeDeListeValide.get(0)
			listeDeListeValide.remove(0)
			
			for (Livre livre : premiereListe) {
				boolean estCommun = true
				
				for (ArrayList<Livre> livreAutre : listeDeListeValide) {
					if (!livreAutre.contains(livre))
						estCommun = false
				}
				
				if (estCommun)
					livresTries.add(livre)
			}
			
			livresTries
		}
	}
	

	/**
	 * Permet de retourner les livres en fonction du type de document
	 * @param typeDoc
	 * @return livres
	 */
	def rechercherLivreTypeDoc(TypeDocument typeDoc) {
		def livresEnFonctionDuTypeDeDoc = new ArrayList<Livre>()
		
		if (typeDoc != null) {
		
			def listeTypeDocEquivalant = TypeDocument.findAll {
				ilike("intitule", typeDoc.getIntitule())
			}
	
			if (!listeTypeDocEquivalant.isEmpty()) {
				def typeDansLaBDEquivalant = listeTypeDocEquivalant.get(0)
		
				Livre.findAllByTypeDoc(typeDansLaBDEquivalant).each {
					livresEnFonctionDuTypeDeDoc.add(it)
				}
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
		
		if (titre != "") {
			
			Livre.findAllByTitreIlike("%"+titre+"%").each {
				livresEnFonctionDuTitre.add(it)
			}
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
		
		if (nom != "") {
			Auteur auteur = Auteur.findByNomIlike("%"+nom+"%")
			
			if (auteur) {
				println auteur.getNom() + " " + auteur.getPrenom()
				
				auteur.livres.each {
					livresEnFonctionAuteur.add(it)
				}
			}
		}
		
		return livresEnFonctionAuteur
	}
}
