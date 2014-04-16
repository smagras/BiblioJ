import pjbiblioj.Auteur
import pjbiblioj.Livre

class BootStrap {

    def init = { servletContext ->
		Livre livre1 = new Livre(titre:"Ulysse",nombreExemplaires:10,nombreExemplairesDisponibles:3,typeDoc:null).save()
		Auteur auteur1 = new Auteur(nom:"Pommaux",prenom:"Yvan").save()
		livre1.addToAuteurs(auteur1)
    }
    def destroy = {
    }
}
