package pjbiblioj

import com.biblioj.services.LivreService
import com.biblioj.services.UtilisateurService
import org.springframework.dao.DataIntegrityViolationException

class PanierController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [panierInstanceList: Panier.list(params), panierInstanceTotal: Panier.count()]
    }
	
	def afficher(){
		
		UtilisateurService utilisateurService = new UtilisateurService()
		Utilisateur utilisateurConnecter =  utilisateurService.getUtilisateurConnecter(session)
		params.livresPanier = null
		params.livresEnVue = null
		if (utilisateurConnecter){
			
			Panier monPanier = utilisateurConnecter.getPanier()
			HashSet<Livre> livresPanier = monPanier.getLivres()
			params.livresPanier = livresPanier
			
			def deleteP = params["delete"]
			if (deleteP){
				LivreService serviceL = new LivreService()
				Livre livreDelete = serviceL.rechercherLivreRang(deleteP)
				monPanier.suppLivre(livreDelete)
				redirect(uri: request.getHeader('referer') )
			}
			
			def see = params["see"]
			if (see){
				LivreService serviceL = new LivreService()
				Livre livreSee = serviceL.rechercherLivreRang(see)
				params.livresEnVue = livreSee
				
			}

		}
		else{
			redirect(uri: '/../PJBiblioJ/livre/rechercher' )
		}
		
	}
	
	def ajouter() {
		
		LivreService servicePourLivre = new LivreService()
		UtilisateurService utilisateurService = new UtilisateurService()
		Utilisateur utilisateurConnecter =  utilisateurService.getUtilisateurConnecter(session)
		
		String rangLivre = params["livre"]
		Livre livre = servicePourLivre.rechercherLivreRang(rangLivre)
		
		utilisateurService.ajouterPanier(session, livre)
		
		redirect(uri: request.getHeader('referer'))
	}

    def create() {
        [panierInstance: new Panier(params)]
    }

    def save() {
        def panierInstance = new Panier(params)
        if (!panierInstance.save(flush: true)) {
            render(view: "create", model: [panierInstance: panierInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'panier.label', default: 'Panier'), panierInstance.id])
        redirect(action: "show", id: panierInstance.id)
    }

    def show(Long id) {
        def panierInstance = Panier.get(id)
        if (!panierInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'panier.label', default: 'Panier'), id])
            redirect(action: "list")
            return
        }

        [panierInstance: panierInstance]
    }

    def edit(Long id) {
        def panierInstance = Panier.get(id)
        if (!panierInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'panier.label', default: 'Panier'), id])
            redirect(action: "list")
            return
        }

        [panierInstance: panierInstance]
    }

    def update(Long id, Long version) {
        def panierInstance = Panier.get(id)
        if (!panierInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'panier.label', default: 'Panier'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (panierInstance.version > version) {
                panierInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'panier.label', default: 'Panier')] as Object[],
                          "Another user has updated this Panier while you were editing")
                render(view: "edit", model: [panierInstance: panierInstance])
                return
            }
        }

        panierInstance.properties = params

        if (!panierInstance.save(flush: true)) {
            render(view: "edit", model: [panierInstance: panierInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'panier.label', default: 'Panier'), panierInstance.id])
        redirect(action: "show", id: panierInstance.id)
    }

    def delete(Long id) {
        def panierInstance = Panier.get(id)
        if (!panierInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'panier.label', default: 'Panier'), id])
            redirect(action: "list")
            return
        }

        try {
            panierInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'panier.label', default: 'Panier'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'panier.label', default: 'Panier'), id])
            redirect(action: "show", id: id)
        }
    }
}
