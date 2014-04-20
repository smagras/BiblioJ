package com.biblioj.services

import pjbiblioj.Auteur
import pjbiblioj.Livre
import pjbiblioj.TypeDocument;

/**
 * Permet d'avoir les services concernants les livres
 */
class LivreService {
	
	/*def rechercherLivres(TypeDocument typeDoc, String titre, String auteur) {
		
		def livresTrier = new ArrayList<Livre>()
		
		println "qsdddddddd"
		def listeEnFonctionDesDocuments = rechercherLivreTypeDoc(typeDoc)
		def listeEnFonctionDesTitre = rechercherLivreTitre(titre)
		def listeEnFonctionDesAuteur = rechercherLivreAuteur(auteur)
		
		println "okkkkkkkkkk"
		
		def listeDeListeValide = new ArrayList<List>()
		if (typeDoc != null) listeDeListeValide.add(listeEnFonctionDesDocuments)
		if (titre != "") listeDeListeValide.add(listeEnFonctionDesTitre)
		if (auteur != "") listeDeListeValide.add(listeEnFonctionDesAuteur)
		
		
		if (!listeDeListeValide.isEmpty()) {
			
			def premiereListe = listeDeListeValide.get(0)
			listeDeListeValide.remove(0)
			println "rrrrrrrrr " + premiereListe.size()
			
			for (Livre livreP: premiereListe){
				boolean youCanAdd = true
				print livreP.getTitre() + " = "
				
				for (ArrayList<Livre> livreAutre: listeDeListeValide){
					if (!livreAutre.contains(livreP) ) youCanAdd = false
					
					print livreAutre.titre
				}
				
				if (youCanAdd){
					livresTrier.add(livreP)
				}
			}
		}
		
		return livresTrier
	}*/
	
	def rechercherLivres(TypeDocument typeDoc, String titre, String auteur) {
		def livresTries = new ArrayList<Livre>()
		
		def listeEnFonctionDesDocuments = rechercherLivreTypeDoc(typeDoc)
		def listeEnFonctionDuTitre = rechercherLivreTitre(titre)
		def listeEnFonctionDesAuteurs = rechercherLivreAuteur(auteur)
		
		def listeDeListeValide = new ArrayList<List>()
		if (typeDoc != null) listeDeListeValide.add(listeEnFonctionDesDocuments)
		if (titre != "") listeDeListeValide.add(listeEnFonctionDuTitre)
		if (auteur != "") listeDeListeValide.add(listeEnFonctionDesAuteurs)
		
		if (!listeDeListeValide.isEmpty()) {
			
			def premiereListe = listeDeListeValide.get(0)
			listeDeListeValide.remove(0)
			
			for (Livre livre : premiereListe) {
				//boolean estCommun = true
				
				for (ArrayList<Livre> livreAutre : listeDeListeValide) {
					if (livreAutre == livre)
						livresTries.add(livre)
						//estCommun = false
					
					//print livreAutre.titre
				}
				
				/*if (estCommun) {
					livresTrier.add(livreP)
				}*/
			}
		}
	}
	

	/**
	 * Permet de retourner les livres en fonction du type de document
	 * @param typeDoc
	 * @return livres
	 */
	def rechercherLivreTypeDoc(TypeDocument typeDoc) {
		def livresEnFonctionDuTypeDeDoc = new ArrayList<Livre>()
		
		if (typeDoc != null){
		
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
