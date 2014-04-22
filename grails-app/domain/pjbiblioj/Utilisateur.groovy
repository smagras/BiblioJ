package pjbiblioj

import java.util.Set;

class Utilisateur {

	String nom, identifiant, motDePasse
	Panier panier
	
	Set reservations = new HashSet()

	static hasMany = [reservations:Reservation]
	
    static constraints = {
		nom blank: false, nullable: false, matches: "[a-zA-Z]+"
		identifiant blank: false, nullable: false, matches: "[a-zA-Z0-9]+"
		motDePasse blank: false, nullable: false, matches: "[a-zA-Z0-9]+"
		panier nullable: false
		reservations nullable: false
    }
}
