package pjbiblioj



import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Livre)
class LivreTests {

    void testSomething() {
       
		TypeDocument typeDoc = new TypeDocument(intitule:"Nouveauté")
		 
		Auteur auteur = new Auteur(nom:"tudou",prenom:"paul",livres:null)
		Auteur auteur2 = new Auteur(nom:"magras",prenom:"steve",livres:null)
		
		Livre livre = new Livre(rang:1,titre:"toto",nombreExemplaires:5,
			nombreExemplairesDisponibles:2,typeDoc:null,auteurs:[auteur])
		
		Livre livre2 = new Livre(rang:2,titre:"titi",nombreExemplaires:5,
			nombreExemplairesDisponibles:2,typeDoc:typeDoc,auteurs:[auteur])
		
		Livre livre3 = new Livre(rang:3,titre:"tata",nombreExemplaires:5,
			nombreExemplairesDisponibles:10,typeDoc:typeDoc,auteurs:[auteur])
		
		assertEquals livre.rang, 1
		assertEquals livre.titre, "toto"
		assertEquals livre.nombreExemplaires, 5
		assertEquals livre.nombreExemplairesDisponibles, 2
		assertEquals livre.typeDoc, null
		assertTrue livre.auteurs.contains(auteur)
		
		livre.getAuteurs().add(auteur2)
		livre2.getAuteurs().add(auteur)
		assertEquals livre.auteurs.size(), 2
		assertEquals livre2.typeDoc, typeDoc
		
		assertTrue livre.validate()
		assertFalse livre3.validate()
    }
}
