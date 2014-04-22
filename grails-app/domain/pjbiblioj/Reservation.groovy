package pjbiblioj

import java.util.Set;

class Reservation {

    int code
	Date dateReservation
	Set livres = new HashSet()
	
	static hasMany = [livres:Livre]
	static constraints = {
		code blank: false, unique: true
		//dateReservation
		livres nullable: true
    }
}
