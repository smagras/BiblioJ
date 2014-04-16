package pjbiblioj

import java.util.Set;

class Livre {

    String titre
	int nombreExemplaires, nombreExemplairesDisponibles
	TypeDocument typeDoc
	
	static belongsTo = Reservation
	static hasMany = [auteurs:Auteur]
	
	
    static constraints = {
		titre nullable: false
		nombreExemplaires nullable: false
		nombreExemplairesDisponibles nullable: false
		typeDoc nullable: true
		auteurs nullable: true
    }
}
