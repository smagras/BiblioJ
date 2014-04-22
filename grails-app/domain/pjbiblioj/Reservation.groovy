package pjbiblioj

import java.util.Set;

class Reservation {

    Date dateReservation
	Set livres = new HashSet()
	
	static hasMany = [livres:Livre]
	static constraints = {
		/*dateReservation validator: { val ->
			val >= new Date()
		}*/
		livres nullable: true
    }
}
