package pjbiblioj

import java.util.Set;

class Livre {

    String titre
	int nombreExemplaires, nombreExemplairesDisponibles
	TypeDocument typeDoc
	Set auteurs = new HashSet()
	
	static belongsTo = Reservation
	static hasMany = [auteurs:Auteur]
	
    static constraints = {
		titre blank: false, nullable: false
		nombreExemplaires min: 0
		nombreExemplairesDisponibles min: 0, validator: { val, obj ->
			return obj.nombreExemplaires >= val
		}
		typeDoc nullable: true
		auteurs nullable: false
    }
	
	
	
}
