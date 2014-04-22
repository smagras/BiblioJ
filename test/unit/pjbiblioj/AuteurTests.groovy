package pjbiblioj



import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Auteur)
class AuteurTests {

    void testSomething() {
	   
       Auteur auteur = new Auteur(nom:"tudou",prenom:"paul",livres:null)
	   assertEquals auteur.nom, "tudou" 
	   assertEquals auteur.prenom, "paul"
	   assertEquals auteur.livres, null
	   auteur.save()
	   
	   assertTrue auteur.validate()
    }
}
