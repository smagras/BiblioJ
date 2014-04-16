package com.biblioj.services;

import static org.junit.Assert.*;

import org.junit.Test;
import pjbiblioj.Auteur
import pjbiblioj.Livre


class CSVManagerTests {

	@Test
	public void testGetLivres() {
		def monCSVManager = new CSVManager()
		println "gogog"
		List<Livre> listeDeLivreDuCSV = monCSVManager.getLivres()
		
		println listeDeLivreDuCSV.get(0).getTitre()
		
		//HashSet<Auteur> listeAuteurs = listeDeLivreDuCSV.get(0).getAuteurs()
		//listeAuteurs.getAt("")
		/*for (Auteur auteur : listeAuteurs){ 
			println auteur.getNom()
			println auteur.getPrenom()
		}*/
		//fail("Not yet implemented");
		//succes("eee")
	}

}
