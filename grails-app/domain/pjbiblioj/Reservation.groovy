package pjbiblioj

import java.util.Set;

class Reservation {

    Date dateReservation
	Set livres = new HashSet()
	
	static hasMany = [livres:Livre]
	static constraints = {
		//dateReservation
		livres nullable: true
    }
}
