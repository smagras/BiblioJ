package com.biblioj.services;

import static org.junit.Assert.*;

import org.junit.Test;
import pjbiblioj.Auteur
import pjbiblioj.Livre


class CSVManagerTests {

	@Test
	public void testGetLivres() {
		def monCSVManager = new CSVManager()

		List<Livre> listeDeLivreDuCSV = monCSVManager.getLivres()
		
		Livre premierLivre = listeDeLivreDuCSV.get(0)
		
		assert false == listeDeLivreDuCSV.empty  : "Pas de livres"
		assert "Rien ne s'oppose à la nuit : roman" == premierLivre.getTitre() : "Pas de titre"
		
		HashSet<Auteur> listeAuteurs = premierLivre.getAuteurs()

		assert 1 == listeAuteurs.size() : "Il n'y a pas 1 auteur"	

	}

}
