package com.biblioj.services

import pjbiblioj.Auteur
import pjbiblioj.Livre
import pjbiblioj.TypeDocument;

/**
 * Permet d'avoir les services concernants les livres
 */
class LivreService {
	
	def rechercherLivres(TypeDocument typeDoc,String titre,String auteur){
		
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
		

		
		
		if (!listeDeListeValide.isEmpty()){
			
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
			
			
			
			
			
		/*	premiereListe.each { livreP ->
				println "sdsfsdf"
				boolean youCanAdd = true
				listeDeListeValide.each { livreAutre ->
					if (!livreAutre.contains(livreP) ) youCanAdd = false
				}
				
				if (youCanAdd){
					livresTrier.add(livreP)
				}
			}
*/
		}
		
		return livresTrier
	}
	
	/**
	 * Permet d'obtenir tout les type
	 * @return
	 */
	def getToutLesTypeDeDocuments(){
		return TypeDocument.findAll ()
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
		
				Livre.findAllByTypeDoc(typeDansLaBDEquivalant, [max: 5]).each {
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
		
		if (titre != ""){
			titre = "%" + titre + "%"
	
			Livre.findAllByTitreIlike(titre, [max: 5]).each {
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
		
		if (nom != ""){
		
			Auteur auteur = Auteur.findByNom(nom)
			
			/*Auteur auteur = Auteur.withCriteria {
		
				eq 'prenom', nom
				
			}*/
			println auteur
			
			int tailleListe = auteur.livres.size()
			if (tailleListe > 5) tailleListe = 5
			
			for (int i=0; i<tailleListe; i++) {
				Livre livre = auteur.livres.toList().get(i)
				livresEnFonctionAuteur.add(livre)
			}
		}
		
		return livresEnFonctionAuteur
		
	}
}
