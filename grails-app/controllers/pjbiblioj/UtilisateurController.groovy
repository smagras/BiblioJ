package pjbiblioj

import com.biblioj.services.UtilisateurService

import org.springframework.dao.DataIntegrityViolationException

class UtilisateurController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }
	
	def inscription(){
		
		UtilisateurService utilisateurService = new UtilisateurService()
		utilisateurService.deconnecter(session)
		
		def ident = params["id"]
		def password  = params["password"]
		def nom  = params["nom"]
		
		if (ident && password && nom){
			
			def estInscrit = utilisateurService.inscrire(ident,password,nom)
			
			if (!estInscrit){
				params.erreur = "Impossible de cr�e ce compte car il existe d�ja."
			}
			else
			{
				redirect(uri: "/../PJBiblioJ/utilisateur/connexion?id="+ident+"&password="+password )
			}
			
			
		}
		else{
			params.erreur = "Il y a des champs non remplie."
		}
		
	}
	
	
	def connexion(){
		
		UtilisateurService utilisateurService = new UtilisateurService()

	
		def ident = params["id"]
		def password  = params["password"]
		
		
		
		
		Utilisateur utilisateur = utilisateurService.getUtilisateurConnecter(session)
		
		if (!utilisateur){
			
			def succesConnection = utilisateurService.connecter(ident, password,session)
			
			if (succesConnection){
				
				if ( request.getHeader('referer').contains("connexion") ){
					 redirect(uri: "/../PJBiblioJ/livre/rechercher" )
				}
				else if ( request.getHeader('referer').contains("inscription") ){
					redirect(uri: "/../PJBiblioJ/livre/rechercher" )
				}
				else{
					redirect(uri: request.getHeader('referer') )
				}
			}
			
		
		}
		else{
			
			utilisateurService.deconnecter(session)
			
			
			redirect(uri: request.getHeader('referer') )
			
		}
		 
	
		
		
	}

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [utilisateurInstanceList: Utilisateur.list(params), utilisateurInstanceTotal: Utilisateur.count()]
    }

    def create() {
        [utilisateurInstance: new Utilisateur(params)]
    }

    def save() {
        def utilisateurInstance = new Utilisateur(params)
        if (!utilisateurInstance.save(flush: true)) {
            render(view: "create", model: [utilisateurInstance: utilisateurInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'utilisateur.label', default: 'Utilisateur'), utilisateurInstance.id])
        redirect(action: "show", id: utilisateurInstance.id)
    }

    def show(Long id) {
        def utilisateurInstance = Utilisateur.get(id)
        if (!utilisateurInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'utilisateur.label', default: 'Utilisateur'), id])
            redirect(action: "list")
            return
        }

        [utilisateurInstance: utilisateurInstance]
    }

    def edit(Long id) {
        def utilisateurInstance = Utilisateur.get(id)
        if (!utilisateurInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'utilisateur.label', default: 'Utilisateur'), id])
            redirect(action: "list")
            return
        }

        [utilisateurInstance: utilisateurInstance]
    }

    def update(Long id, Long version) {
        def utilisateurInstance = Utilisateur.get(id)
        if (!utilisateurInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'utilisateur.label', default: 'Utilisateur'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (utilisateurInstance.version > version) {
                utilisateurInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'utilisateur.label', default: 'Utilisateur')] as Object[],
                          "Another user has updated this Utilisateur while you were editing")
                render(view: "edit", model: [utilisateurInstance: utilisateurInstance])
                return
            }
        }

        utilisateurInstance.properties = params

        if (!utilisateurInstance.save(flush: true)) {
            render(view: "edit", model: [utilisateurInstance: utilisateurInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'utilisateur.label', default: 'Utilisateur'), utilisateurInstance.id])
        redirect(action: "show", id: utilisateurInstance.id)
    }

    def delete(Long id) {
        def utilisateurInstance = Utilisateur.get(id)
        if (!utilisateurInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'utilisateur.label', default: 'Utilisateur'), id])
            redirect(action: "list")
            return
        }

        try {
            utilisateurInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'utilisateur.label', default: 'Utilisateur'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'utilisateur.label', default: 'Utilisateur'), id])
            redirect(action: "show", id: id)
        }
    }
}
