package pjbiblioj

import javax.servlet.http.HttpSession;

import com.biblioj.services.ReservationService
import com.biblioj.services.UtilisateurService

import org.springframework.dao.DataIntegrityViolationException

class ReservationController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }
	
	def actionUtilisateur(){
		
		UtilisateurService service = new UtilisateurService()
		Utilisateur utilisateur = service.getUtilisateurConnecter(session)
		
		if (utilisateur){

			def order = params["order"]
			if (order){
				if (order == "v"){
					
					ReservationService serviceReservation = new ReservationService()
					
					def livres = serviceReservation.getLivresReservationPossible(utilisateur)
					
					Reservation reservation = serviceReservation.obtenirReservation(livres)
					service.ajouterReservation(session,reservation)
					service.viderPanier(session)
					
				}	
				
				if (order == "a"){
					service.viderPanier(session)
				}
			}
		
		}
		redirect(uri: '/../PJBiblioJ/reservation/afficher' )
	}
	
	def ajouter(){
		
		UtilisateurService service = new UtilisateurService()
		Utilisateur utilisateur = service.getUtilisateurConnecter(session)
		
		if (utilisateur){
			
			ReservationService serviceReservation = new ReservationService()
			
			UtilisateurService utilisateurService = new UtilisateurService()
			Utilisateur utilisateurConnecter =  utilisateurService.getUtilisateurConnecter(session)
			
			ArrayList<Livre> livresImpossibles = serviceReservation.getLivresReservationImpossible(utilisateurConnecter)
			ArrayList<Livre> livresPossibles = serviceReservation.getLivresReservationPossible(utilisateurConnecter)
			
			params.livresImpossibles = livresImpossibles
			params.livresPossibles = livresPossibles
		
		}
		else{
			redirect(uri: '/../PJBiblioJ/livre/rechercher' )
		}
	}
	
	def afficher(){
		UtilisateurService service = new UtilisateurService()
		Utilisateur utilisateur = service.getUtilisateurConnecter(session)
		
		if (utilisateur){
			println "okjyuuy"
			def reservations = service.getReservations( session)
			params.reservationsLivres = reservations
		}
		else
		{
			redirect(uri: '/../PJBiblioJ/livre/rechercher' )
		}	

	}

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [reservationInstanceList: Reservation.list(params), reservationInstanceTotal: Reservation.count()]
    }

    def create() {
        [reservationInstance: new Reservation(params)]
    }

    def save() {
        def reservationInstance = new Reservation(params)
        if (!reservationInstance.save(flush: true)) {
            render(view: "create", model: [reservationInstance: reservationInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'reservation.label', default: 'Reservation'), reservationInstance.id])
        redirect(action: "show", id: reservationInstance.id)
    }

    def show(Long id) {
        def reservationInstance = Reservation.get(id)
        if (!reservationInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'reservation.label', default: 'Reservation'), id])
            redirect(action: "list")
            return
        }

        [reservationInstance: reservationInstance]
    }

    def edit(Long id) {
        def reservationInstance = Reservation.get(id)
        if (!reservationInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'reservation.label', default: 'Reservation'), id])
            redirect(action: "list")
            return
        }

        [reservationInstance: reservationInstance]
    }

    def update(Long id, Long version) {
        def reservationInstance = Reservation.get(id)
        if (!reservationInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'reservation.label', default: 'Reservation'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (reservationInstance.version > version) {
                reservationInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'reservation.label', default: 'Reservation')] as Object[],
                          "Another user has updated this Reservation while you were editing")
                render(view: "edit", model: [reservationInstance: reservationInstance])
                return
            }
        }

        reservationInstance.properties = params

        if (!reservationInstance.save(flush: true)) {
            render(view: "edit", model: [reservationInstance: reservationInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'reservation.label', default: 'Reservation'), reservationInstance.id])
        redirect(action: "show", id: reservationInstance.id)
    }

    def delete(Long id) {
        def reservationInstance = Reservation.get(id)
        if (!reservationInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'reservation.label', default: 'Reservation'), id])
            redirect(action: "list")
            return
        }

        try {
            reservationInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'reservation.label', default: 'Reservation'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'reservation.label', default: 'Reservation'), id])
            redirect(action: "show", id: id)
        }
    }
}
