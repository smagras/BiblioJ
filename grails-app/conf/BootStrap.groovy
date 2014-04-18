import pjbiblioj.Auteur
import pjbiblioj.Livre
import pjbiblioj.Panier
import pjbiblioj.TypeDocument

class BootStrap {

    def init = { servletContext ->
		TypeDocument typeDoc = new TypeDocument(intitule:"Nouveauté")
		TypeDocument typeDoc2 = new TypeDocument(intitule:"Livre ado")
		
		Auteur auteur1 = new Auteur(nom:"Pommaux",prenom:"Yvan").save()
		Auteur auteur2 = new Auteur(nom:"Hugo",prenom:"Victor").save()
		
		Livre livre1 = new Livre(titre:"Ulysse",nombreExemplaires:10,nombreExemplairesDisponibles:3,typeDoc:typeDoc)
		livre1.addToAuteurs(auteur1)
		livre1.addToAuteurs(auteur2)
		livre1.save()
		
		
		Livre l2 = new Livre(titre:"Ulysse",nombreExemplaires:2,nombreExemplairesDisponibles:1,typeDoc:typeDoc)
		l2.addToAuteurs(auteur2)
		l2.save()
		
		
		Livre l3 = new Livre(titre:"Le petit prince",nombreExemplaires:2,nombreExemplairesDisponibles:1,typeDoc:typeDoc)
		l3.addToAuteurs(auteur2)
		l3.save()
		
		
		Livre l4 = new Livre(titre:"Toto",nombreExemplaires:2,nombreExemplairesDisponibles:1,typeDoc:typeDoc)
		l4.addToAuteurs(auteur2)
		l4.save()
		
		
		Livre l5 = new Livre(titre:"Titi",nombreExemplaires:2,nombreExemplairesDisponibles:1,typeDoc:typeDoc)
		l5.addToAuteurs(auteur2)
		l5.save()
		
		
		Livre l6 = new Livre(titre:"Grosminet",nombreExemplaires:2,nombreExemplairesDisponibles:1,typeDoc:typeDoc)
		l6.addToAuteurs(auteur2)
		l6.save()
		
		
		//Livre.rechercherLivreTitre("Ulysse")
		//Livre.rechercherLivreTypeDoc(typeDoc)
		//Livre.rechercherLivreAuteur("Hugo")
		
		Panier p = new Panier(nom:"steve")
		p.addLivres([l5,l6])
		p.suppLivre(l5)
		p.save()
    }
    def destroy = {
    }
}
