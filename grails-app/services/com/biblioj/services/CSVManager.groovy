package com.biblioj.services


import pjbiblioj.Auteur;
import pjbiblioj.Livre;

class CSVManager {
	private File fichierCSV;
	
	
	CSVManager(){
		
		//fichierCSV = grailsApplication.mainContext.getResource("csv/Les 1000 titres les plus recherches en 2012.csv").file
		
		fichierCSV = new File("resources/Les 1000 titres les plus recherches en 2012.csv")
		
		//fichierCSV = grailsApplication.mainContext.getResource("csv/Les 1000 titres les plus recherches en 2012.csv").file
	}
	
	
	/**
	 * Permet de convertir le contenue de la case auteur en un objet Auteur
	 * @param caseAuteur
	 * @return
	 */
	Auteur convertCaseCSVenAuteur(String caseAuteur){	
		
		def auteur = new Auteur()

		if (caseAuteur.contains(",")){
			
			caseAuteur.splitEachLine(','){ valeurs ->
				
				auteur.setNom(valeurs[0])
				auteur.setPrenom(valeurs[1])
				return auteur
			}
		}
		
		return auteur
		
	}
	
	/**
	 * Permet d'obtenir les livre contenue dans le csv
	 * @return
	 */
	List<Livre> getLivres(){
		
		def listeDeFichier = new ArrayList<Livre>();
		
		if (fichierCSV.exists()) {
			
			fichierCSV.splitEachLine(';') { row ->	
				
				Livre livre = new Livre();
				livre.setTitre(row[3])
				
				
				Auteur auteurDuLivre =  convertCaseCSVenAuteur(row[4])
				println auteurDuLivre.getNom() + "" + auteurDuLivre.getPrenom()
				livre.getAuteurs().add(auteurDuLivre);
				
				listeDeFichier.add(livre);
			 }
			
			listeDeFichier.remove(0)
		}
		
		return listeDeFichier

	}
}