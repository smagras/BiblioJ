import pjbiblioj.Auteur
import pjbiblioj.Livre

class BootStrap {

    def init = { servletContext ->
		Auteur auteur1 = new Auteur(nom:"Pommaux",prenom:"Yvan").save()
		Livre livre1 = new Livre(titre:"Ulysse",nombreExemplaires:10,nombreExemplairesDisponibles:3)
		livre1.addToAuteurs(auteur1)
		livre1.save()
    }
    def destroy = {
    }
}
