package pjbiblioj

import java.util.Set;

class Livre {

	Long rang
    String titre
	int nombreExemplaires, nombreExemplairesDisponibles
	TypeDocument typeDoc
	Set auteurs = new HashSet()
	
	static belongsTo = Reservation
	static hasMany = [auteurs:Auteur]
	
    static constraints = {
		rang blank: false, nullable: false, unique: true
		titre blank: false, nullable: false
		nombreExemplaires min: 0
		nombreExemplairesDisponibles min: 0, validator: { val, obj ->
			return obj.nombreExemplaires >= val
		}
		typeDoc nullable: true
		auteurs nullable: false
    }
	
	
}
