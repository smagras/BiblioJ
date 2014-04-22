<%@ page import="pjbiblioj.Livre" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<meta name="layout" content="struct">

</head>

<body>

	

	<!-- Corp  -->
	<div id="corp">



		<br />

		<!-- article n°2 -->
		<div id="descrition">
			<form name="input" action="" method="get">
				<h3>Trouver un livre</h3>
				<p>
				
					
					Type de Document <br/> <br/>
					<select class="textbox" style='width:300px;height:40px;' name="typeDoc">
						<option id='titre'> </option>
						<%  
							def typesDeDocuments = params["typesDeDocuments"]
							String typeDocChoose = params["typeDoc"]
							typesDeDocuments.each {
								if (typeDocChoose && it.getIntitule() == typeDocChoose  ) out.print("<option selected id='" + it.getIntitule() +"' value='" + it.getIntitule() +"'> " + it.getIntitule() +" </option>")
								else out.print("<option id='" + it.getIntitule() +"' value='" + it.getIntitule() +"'> " + it.getIntitule() +" </option>")
							}
						%>
					</select>
					
					<br/> <br/>
					
					Titre <br/> <br/>
					<input class="textbox" type="text" name="titre" value="<% out.print params["titre"] %>"/>
					
					<br/> <br/>
					
					Auteurs <br/> <br/>
					<input class="textbox" type="text" name="auteurs" value="<% out.print params["auteurs"] %>" />
					
					<br/> <br/>
					<input type='submit' value='rerchercher' width=250 class='coolButton' />
					<br/> <br/> <br/> <br/>
					
					<h4>Résultats de la recherche : </h4>
					<%
						List<Livre> listeLivres = livres
						String pageS = page
						Integer page = Integer.valueOf(pageS);
						if (page <0) page = 0
						int i = page * 5;
						int nbResPages = i+5;
						
						if (listeLivres.isEmpty()) print "Aucun résultats.</br>"
						
						while (i<nbResPages && i<listeLivres.size()) {
							Livre l = listeLivres.get(i);
							
							def iconePanier =  "<img alt='prev' src='${resource(dir: 'images', file: 'shopping_basket_add_256.png')}' style='width:30px;height:30px;'>"
							
							if (l.nombreExemplairesDisponibles > 0) {
								out.print("<a href='/../PJBiblioJ/panier/ajouter?livre="+l.rang+"'>" + iconePanier +"</a> (" + l.nombreExemplairesDisponibles +") <b>" + l.titre + "</b> " + l.auteurs.nom + 
									" <i>" + l.typeDoc.intitule + "</i> "  + " <br/>");
							}
							else {
								out.print(iconePanier + " <p>"+ l.titre + " " + l.auteurs.nom + " " + l.typeDoc.intitule + " " +
									l.nombreExemplairesDisponibles + "</p> <br/>");
							}
							
							i++;
						}
					%>
				</p>
				<br/>
				<br/>
				<div style="text-align: center;">
				<%	
					
					if (!listeLivres.isEmpty()){
						
					
						int pageMoins = page;
						pageMoins--;
						String url = "<a href='"
						url += "/../PJBiblioJ/livre/rechercher"
						url += "?typeDoc="+params["typeDoc"]
						url += "&titre="+params["titre"]
						url += "&auteurs="+params["auteurs"]
						url += "&page=" + pageMoins;
						url += "'><img alt='prev' src='${resource(dir: 'images', file: 'button_prev_100857.jpg')}' style='width:30px;height:30px;'></a>  "
						print(url);
	
						int pagePlus = page;
						pagePlus++;
						url = "<a href='"
						url += "/../PJBiblioJ/livre/rechercher"
						url += "?typeDoc="+params["typeDoc"]
						url += "&titre="+params["titre"]
						url += "&auteurs="+params["auteurs"]
						url += "&page=" + pagePlus;
						url += "'><img alt='prev' src='${resource(dir: 'images', file: 'button_next_100857.jpg')}' style='width:30px;height:30px;'></a>"
						print(url);
					
					}
				%>
				</div>
					
				
				
				<p>
				</p>



				<div align="left"></div>

			</form>
		</div>

				<div style="text-align: center;"></div>


			</div>


		</div>

	</div>
	
</body>

</html>