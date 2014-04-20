package pjbiblioj

import com.biblioj.services.LivreService
import com.biblioj.services.UtilisateurService
import org.springframework.dao.DataIntegrityViolationException

class LivreController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [livreInstanceList: Livre.list(params), livreInstanceTotal: Livre.count()]
    }
	
	def rechercher() {
		/*UtilisateurService utilisateurService = new UtilisateurService()
		utilisateurService.connecter("mgs", "pass",session)
		Utilisateur u = utilisateurService.getUtilisateurConnecter(session)
		
		println "ggg " + u.getNom()
		println "ggg " + u.getIdentifiant()
		println "ggg " + u.getMotDePasse()
		
		utilisateurService.deconnecter(session)
		 
		params.valeurFun = "sdfdsfdsfdfs"*/
		
		
		def listeDesLivres
		LivreService servicePourLivre = new LivreService()
		
		
		String url = request.getRequestURL().toString()
		url = url.substring(0, 55)
		
		String typeDocLivre = params["typeDoc"]
		String titreLivre = params["titre"]
		String auteurs = params["auteurs"]
		
		if (typeDocLivre != null) {
			
			url += '?' + request.queryString
			//url = URLEncoder.encode(url, "UTF-8")
			//url = URLDecoder.decode(url, "ISO8859-1")
			url = URLDecoder.decode(url, "UTF-8")
			println url
			
			TypeDocument typeDoc = new TypeDocument(intitule:typeDocLivre)
			
			listeDesLivres = servicePourLivre.rechercherLivreTypeDoc(typeDoc)
			println listeDesLivres
			//[typeDoc: listeDesLivres]
			
			//redirect(url: url)//"/PJBiblioJ/grails/livre/rechercher?typeDoc=Nouveauté")
		}
		
		
		if (titreLivre != null) {
			listeDesLivres = servicePourLivre.rechercherLivreTitre(titreLivre)
			//[titre: listeDesLivres]
		}
		
		if (auteurs != null) {
			listeDesLivres = servicePourLivre.rechercherLivreAuteur(auteurs)
			//[auteurs: listeDesLivres]
		}
		
		[typeDoc: listeDesLivres, titre: listeDesLivres, auteurs: listeDesLivres]
	}
	

    def create() {
        [livreInstance: new Livre(params)]
    }

    def save() {
        def livreInstance = new Livre(params)
        if (!livreInstance.save(flush: true)) {
            render(view: "create", model: [livreInstance: livreInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'livre.label', default: 'Livre'), livreInstance.id])
        redirect(action: "show", id: livreInstance.id)
    }

    def show(Long id) {
        def livreInstance = Livre.get(id)
        if (!livreInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'livre.label', default: 'Livre'), id])
            redirect(action: "list")
            return
        }

        [livreInstance: livreInstance]
    }

    def edit(Long id) {
        def livreInstance = Livre.get(id)
        if (!livreInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'livre.label', default: 'Livre'), id])
            redirect(action: "list")
            return
        }

        [livreInstance: livreInstance]
    }

    def update(Long id, Long version) {
        def livreInstance = Livre.get(id)
        if (!livreInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'livre.label', default: 'Livre'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (livreInstance.version > version) {
                livreInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'livre.label', default: 'Livre')] as Object[],
                          "Another user has updated this Livre while you were editing")
                render(view: "edit", model: [livreInstance: livreInstance])
                return
            }
        }

        livreInstance.properties = params

        if (!livreInstance.save(flush: true)) {
            render(view: "edit", model: [livreInstance: livreInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'livre.label', default: 'Livre'), livreInstance.id])
        redirect(action: "show", id: livreInstance.id)
    }

    def delete(Long id) {
        def livreInstance = Livre.get(id)
        if (!livreInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'livre.label', default: 'Livre'), id])
            redirect(action: "list")
            return
        }

        try {
            livreInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'livre.label', default: 'Livre'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'livre.label', default: 'Livre'), id])
            redirect(action: "show", id: id)
        }
    }
}
