import pjbiblioj.Auteur
import pjbiblioj.Livre

class BootStrap {

    def init = { servletContext ->
		def auteur1 = new Auteur(nom:"Pommaux",prenom:"Yvan",livres:null).save()
		def livre1 = new Livre(titre:"Ulysse",nombreExemplaires:10,nombreExemplairesDisponibles:3,typeDoc:null).addToAuteurs(auteur1).save()
    }
    def destroy = {
    }
}
