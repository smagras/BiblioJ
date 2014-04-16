package pjbiblioj

class Auteur {

    String nom, prenom
	
	static belongsTo = Livre
	static hasMany = [livres:Livre]
	
    static constraints = {
		nom nullable: false
		prenom nullable: false
		livres nullable: true, minSize: 0
    }
}
