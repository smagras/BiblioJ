package pjbiblioj

class Utilisateur {

	String nom, identifiant, motDePasse
	Panier panier
	
    static constraints = {
		nom blank: false, nullable: false, matches: "[a-zA-Z]+"
		identifiant blank: false, nullable: false, matches: "[a-zA-Z0-9]+"
		motDePasse blank: false, nullable: false, matches: "[a-zA-Z0-9]+"
		panier nullable: false
    }
}
