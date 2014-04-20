package com.biblioj.utils


import pjbiblioj.Auteur;
import pjbiblioj.Livre;
import pjbiblioj.TypeDocument;

class CSVManager {
	private File fichierCSV;
	
	
	CSVManager(){
		
		fichierCSV = new File("resources/Les 1000 titres les plus recherches en 2012.csv")
		
	}
	
	
	/**
	 * Permet de convertir le contenue de la case auteur en un objet Auteur
	 * @param caseAuteur
	 * @return
	 */
	Auteur convertCaseCSVenAuteur(String caseAuteur){	
		
		def auteur = new Auteur()

		if (caseAuteur && caseAuteur.contains(',')){
			
			caseAuteur.splitEachLine(','){ valeurs ->
				auteur.setNom(valeurs[0])
				auteur.setPrenom(valeurs[1])
				return auteur
			}
		}
		else return null
		
	}
	
	/**
	 * Permet d'obtenir les livre contenue dans le csv
	 * @return
	 */
	List<Livre> getLivres(){
		
		def listeDeFichier = new ArrayList<Livre>();
		
		HashMap<String,TypeDocument> uniqueTypeDocument = new HashMap<String,TypeDocument>()
		
		if (fichierCSV.exists()) {
			
			fichierCSV.splitEachLine(';') { row ->	
				
				Livre livre = new Livre(nombreExemplaires:7,nombreExemplairesDisponibles:7);
				livre.setTitre(row[3])
				
				if (uniqueTypeDocument.get(row[1])){
					uniqueTypeDocument.put(row[1], new TypeDocument(intitule:row[1]))
				}
				
				
				livre.setTypeDoc( uniqueTypeDocument.get(row[1]) )
				
				
				Auteur auteurDuLivre = convertCaseCSVenAuteur(row[4])
				if (auteurDuLivre){
					//println auteurDuLivre.getNom() + "" + auteurDuLivre.getPrenom()
					livre.getAuteurs().add(auteurDuLivre);
				}
				   
				   
				listeDeFichier.add(livre);
			 }
			
			// on supprime la ligne de présentation
			listeDeFichier.remove(0)
		}
		
		return listeDeFichier

	}
}