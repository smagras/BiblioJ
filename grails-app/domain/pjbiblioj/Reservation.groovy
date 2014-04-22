package pjbiblioj

import java.util.Set;

class Reservation {

    int code
	Date dateReservation
	Set livres = new HashSet()
	
	static hasMany = [livres:Livre]
	static constraints = {
		code blank: false, nullable: false, unique: true
		//dateReservation
		livres nullable: true
    }
	
	static mapping = {
		//id column: 'reservation_id', generator: 'assigned'
		code generator: 'increment'
	}
}
