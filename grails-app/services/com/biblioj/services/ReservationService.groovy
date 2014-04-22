package com.biblioj.services

import pjbiblioj.Livre
import pjbiblioj.Panier
import pjbiblioj.Reservation
import pjbiblioj.Utilisateur


/**
 * Permet de crée des reservation
 *
 */
class ReservationService {
	
	/**
	 * Permet de crée une réservation en fonction de livres
	 * @param panier
	 * @return
	 */
	def obtenirReservation(ArrayList<Livre> livres){
		
		Reservation reservation = new Reservation()
		
		livres.each {
			it.setNombreExemplairesDisponibles(it.getNombreExemplairesDisponibles() - 1)
		}
		Date maDate = new Date();
		maDate = maDate.next()
		
		reservation.setDateReservation(maDate)
		reservation.getLivres().addAll(livres)
		//reservation.setCode(reservation.id)
		
		println reservation.getLivres()
		
		
		
		return reservation
		
	}

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
