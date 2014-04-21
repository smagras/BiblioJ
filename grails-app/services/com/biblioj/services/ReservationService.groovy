package com.biblioj.services

import pjbiblioj.Livre
import pjbiblioj.Panier
import pjbiblioj.Utilisateur

class ReservationService {

	/**
	 * Permet d'obtenir les livre possible a réserver
	 * @param utilisateur
	 * @return
	 */
	def getLivresReservationPossible(Utilisateur utilisateur){
		def listeMauvaisLivre = new ArrayList<Livre>()
		
		if (utilisateur){
			
			Panier panier = utilisateur.getPanier()
			
			if (panier){
			
				HashSet<Livre> livres = panier.getLivres()
				
				
				
				livres.each {
					
					if (it.getNombreExemplairesDisponibles() > 0){
						listeMauvaisLivre.add(it)
					}
					
				}
			
			}
		
		}
		
		return listeMauvaisLivre
	}
	
	
	/**
	 * Permet d'obtenir les livre impossible a réserver
	 * @param utilisateur
	 * @return
	 */
    def getLivresReservationImpossible(Utilisateur utilisateur){
		
		def listeMauvaisLivre = new ArrayList<Livre>()
		
		if (utilisateur){
			
			Panier panier = utilisateur.getPanier()
			
			if (panier){
			
				HashSet<Livre> livres = panier.getLivres()
				
				
				
				livres.each {
					
					if (it.getNombreExemplairesDisponibles() == 0){
						listeMauvaisLivre.add(it)
					}
					
				}
			
			}
		
		}
		
		return listeMauvaisLivre
		
	}
}
