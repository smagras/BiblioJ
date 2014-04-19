
import com.biblioj.services.LivreService
import com.biblioj.services.UtilisateurService
import pjbiblioj.Auteur
import pjbiblioj.Livre
import pjbiblioj.Panier
import pjbiblioj.TypeDocument
import pjbiblioj.Utilisateur

class BootStrap {

    def init = { servletContext ->
		TypeDocument typeDoc = new TypeDocument(intitule:"Nouveauté")
		TypeDocument typeDoc2 = new TypeDocument(intitule:"Livre ado")
		
		Auteur auteur1 = new Auteur(nom:"Pommaux",prenom:"Yvan").save(flush: true)
		Auteur auteur2 = new Auteur(nom:"Hugo",prenom:"Victor").save(flush: true)
		
		Livre livre1 = new Livre(titre:"Ulysse",nombreExemplaires:10,nombreExemplairesDisponibles:11,typeDoc:typeDoc)
		livre1.addToAuteurs(auteur1)
		livre1.addToAuteurs(auteur2)
		livre1.save(flush: true)
		
		
		Livre l2 = new Livre(titre:"Ulysse",nombreExemplaires:2,nombreExemplairesDisponibles:1,typeDoc:typeDoc)
		l2.addToAuteurs(auteur2)
		l2.save(flush: true)
		
		
		Livre l3 = new Livre(titre:"Le petit prince",nombreExemplaires:2,nombreExemplairesDisponibles:1,typeDoc:typeDoc)
		l3.addToAuteurs(auteur2)
		l3.save(flush: true)
		
		
		Livre l4 = new Livre(titre:"Toto",nombreExemplaires:2,nombreExemplairesDisponibles:1,typeDoc:typeDoc2)
		l4.addToAuteurs(auteur2)
		l4.save(flush: true)
		
		
		Livre l5 = new Livre(titre:"Titi",nombreExemplaires:2,nombreExemplairesDisponibles:1,typeDoc:typeDoc)
		l5.addToAuteurs(auteur2)
		l5.save(flush: true)
		
		
		Livre l6 = new Livre(titre:"Grosminet",nombreExemplaires:2,nombreExemplairesDisponibles:1,typeDoc:typeDoc)
		l6.addToAuteurs(auteur2)
		l6.save(flush: true)
		
		/*LivreService service = new LivreService()
		def listeLivre = service.rechercherLivreTitre("smin")
		println listeLivre.titre*/
		//Livre.rechercherLivreTypeDoc(typeDoc)
		//Livre.rechercherLivreAuteur("Hugo")
		
		Panier p = new Panier(nom:"paul")
		p.addLivres([l5,l6])
		p.suppLivre(l5)
		p.save(flush: true)
		
		Utilisateur paul = new Utilisateur(nom:"paul",identifiant:"Polodu12",motDePasse:"pass",panier:p)
		paul.save(flush: true)
		
		Utilisateur steve = new Utilisateur(nom:"steve",identifiant:"mgs",motDePasse:"pass",panier:p)
		steve.save(flush: true)
		

		
    }
    def destroy = {
    }
}

