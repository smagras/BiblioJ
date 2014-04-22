
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
		Panier p = new Panier(nom:"steve")
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
		
	/*	LivreService service = new LivreService()
		def livreR = service.rechercherLivres(new TypeDocument(intitule:"Livre adulte"), "Barbe", "")
		println livreR.titre
	
		def livreRE = service.rechercherLivreAuteur( "Ducr" )
		println livreRE.titre
		
		def livreRE = service.rechercherLivreTypeDoc(new TypeDocument(intitule:"Livre adulte")) */
		//println livreRE.titre
    }
    def destroy = {
    }
}

