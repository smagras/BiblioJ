package pjbiblioj

class Panier {

	String nom
	Set livres = new HashSet()
	
	static belongsTo = Utilisateur
	static hasMany = [livres:Livre]

    static constraints = {
		nom blank: false, nullable: false
		livres nullable: true
    }
	
	
	def addLivre(Livre livre) {
		livres.add(livre)
	}
	
	
	def addLivres(List liste) {
		livres.addAll(liste)
	}
	
	
	def suppLivre(Livre livre) {
		livres.remove(livre)
	}
}
