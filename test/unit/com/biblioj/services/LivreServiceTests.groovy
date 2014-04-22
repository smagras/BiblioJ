package com.biblioj.services;

import pjbiblioj.Auteur
import pjbiblioj.Livre
import pjbiblioj.TypeDocument;



/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(LivreService)
@Mock( [Auteur,Livre,TypeDocument] )
class LivreServiceTests {
	
	TypeDocument typeDoc1
	TypeDocument typeDoc2
	
	@Before
	public void setUp(){
		typeDoc1 = new TypeDocument(intitule:"Roman")
		typeDoc2 = new TypeDocument(intitule:"Science")
		
		Auteur auteur1 = new Auteur(nom:"Davis",prenom:"Miles").save(flush: true)
		Auteur auteur2 = new Auteur(nom:"Bob",prenom:"Sinclar").save(flush: true)
		
		Livre livre1 = new Livre(titre:"Petit Bateau",nombreExemplaires:10,nombreExemplairesDisponibles:3,typeDoc:typeDoc1)
		livre1.addToAuteurs(auteur1)
		livre1.addToAuteurs(auteur2)
		livre1.save(flush: true)
		
		
		Livre l2 = new Livre(titre:"Grand Bateau",nombreExemplaires:2,nombreExemplairesDisponibles:1,typeDoc:typeDoc1)
		l2.addToAuteurs(auteur2)
		l2.save(flush: true)
		
		
		Livre l3 = new Livre(titre:"Moyen Bateau",nombreExemplaires:2,nombreExemplairesDisponibles:1,typeDoc:typeDoc1)
		l3.addToAuteurs(auteur2)
		l3.save(flush: true)
		
		
		Livre l4 = new Livre(titre:"Tata",nombreExemplaires:2,nombreExemplairesDisponibles:1,typeDoc:typeDoc1)
		l4.addToAuteurs(auteur2)
		l4.save(flush: true)
		
		
		Livre l5 = new Livre(titre:"Titi",nombreExemplaires:2,nombreExemplairesDisponibles:1,typeDoc:typeDoc2)
		l5.addToAuteurs(auteur2)
		l5.save(flush: true)
		
		
		Livre l6 = new Livre(titre:"Kepler",nombreExemplaires:2,nombreExemplairesDisponibles:1,typeDoc:typeDoc2)
		l6.addToAuteurs(auteur2)
		l6.save(flush: true)
	}
	
	
	@Test
	public void testRechercherLivreTypeDoc() {
		LivreService service = new LivreService()
		List<Livre> livres = service.rechercherLivreTypeDoc(typeDoc1)
		
		/*livres.each { l->
			println 
		}*/
		assert "Petit Bateau".equals(livres.get(0).getTitre() )
		assert "Grand Bateau".equals( livres.get(1).getTitre() )
		assert "Moyen Bateau".equals( livres.get(2).getTitre() )
		assert "Tata".equals( livres.get(3).getTitre() )
	
		assert true == true
		
	}
	
	
	
}

