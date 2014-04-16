package com.matu.services

import pjbiblioj.Auteur;
import pjbiblioj.Livre;

class CSVManager {
	def adresseFichier;
	
	CSVManager(){
		adresseFichier = "ressources/Les 1000 titres les plus recherches en 2012.csv"
	}
	
	List<Livre> getLivres(){
		
		def listeDeFichier = new ArrayList<Livre>();
		
		new File(adresseFichier).eachCsvLine { tokens ->
			Auteur auteurDuLivre = new Auteur(tokens[4]);
			Livre livre = new Livre(tokens[3]);
			livre.getAuteurs().add(auteurDuLivre);
			listeDeFichier.add(livre);
		}
		
		return listeDeFichier
		
	}
}