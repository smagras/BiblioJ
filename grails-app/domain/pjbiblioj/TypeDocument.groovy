package pjbiblioj

class TypeDocument {

    String intitule
	static belongsTo = Livre
	
	static constraints = {
		intitule blank: false, nullable: false
    }
	
	
	def isEmpty() {
		intitule == ""
	}
}
