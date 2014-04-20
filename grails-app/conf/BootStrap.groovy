
import com.biblioj.services.LivreService
import com.biblioj.services.UtilisateurService
import com.biblioj.utils.CSVManager
import pjbiblioj.Auteur
import pjbiblioj.Livre
import pjbiblioj.Panier
import pjbiblioj.TypeDocument
import pjbiblioj.Utilisateur

class BootStrap {

    def init = { servletContext ->
		TypeDocument typeDoc = new TypeDocument(intitule:"Nouveauté")
		Auteur auteur2 = new Auteur(nom:"Hugo",prenom:"Victor").save(flush: true)
		
		Livre l5 = new Livre(titre:"Titi",nombreExemplaires:2,nombreExemplairesDisponibles:1,typeDoc:typeDoc)
		l5.addToAuteurs(auteur2)
		l5.save(flush: true)
		
		Livre l6 = new Livre(titre:"Grosminet",nombreExemplaires:2,nombreExemplairesDisponibles:1,typeDoc:typeDoc)
		l6.addToAuteurs(auteur2)
		l6.save(flush: true)
		
		Panier p = new Panier(nom:"paul")
		p.addLivres([l5,l6])
		p.suppLivre(l5)
		p.save(flush: true)
		
		Utilisateur paul = new Utilisateur(nom:"paul",identifiant:"Polodu12",motDePasse:"pass",panier:p)
		paul.save(flush: true)
		
		Utilisateur steve = new Utilisateur(nom:"steve",identifiant:"mgs",motDePasse:"pass",panier:p)
		steve.save(flush: true)
		
		
		
		CSVManager csvMan = new CSVManager()
		List<Livre> listLivres = csvMan.getLivres()
		
		listLivres.each {
			it.save(flush: true)
		}
		
		LivreService service = new LivreService()
		/*def livreR = service.rechercherLivres(new TypeDocument(intitule:"Livre ado"), "", "Collins")
		println livreR.titre*/
		
		def livreRE = service.rechercherLivreAuteur( "Collins" )
		println livreRE.titre
    }
    def destroy = {
    }
}

