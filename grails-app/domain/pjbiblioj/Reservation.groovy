package pjbiblioj

class Reservation {

    int code
	Date dateReservation
	
	static hasMany = [livres:Livre]
	static constraints = {
    }
}
