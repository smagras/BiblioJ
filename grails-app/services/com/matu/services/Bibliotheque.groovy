package com.matu.services

import pjbiblioj.Livre;
import pjbiblioj.TypeDocument;

class Bibliotheque {



	/**
	 * Permet de retourner une liste de livre en fonction d'un type choisie
	 * @param type
	 * @return
	 */
	List<Livre> getLivresParTypeDeDocument(TypeDocument type){
		
		def listeDeLivres = Livre.list()
		

		def livreTrierParTypeDeDocument = listeDeLivres.findAll {
			getTypeDoc().equals(type)
		}
		
		return livreTrierParTypeDeDocument;
		
	}
}
