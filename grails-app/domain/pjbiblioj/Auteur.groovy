package pjbiblioj

class Auteur {

    String nom, prenom
	Set livres = new HashSet()
	
	static belongsTo = Livre
	static hasMany = [livres:Livre]
	
    static constraints = {
		nom blank: false, nullable: false
		prenom blank: false, nullable: false
		livres nullable: true
    }
}
