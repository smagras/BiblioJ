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
				
					Rechercher par : <br/> <br/> <br/>
					
					Type de Document <br/> <br/>
					<select class="textbox" style='width:300px;height:40px;' name="typeDoc">
						<option id='titre'> </option>
						<option id='Nouveauté' value='Nouveauté'> Nouveauté </option>
						<option id='Livre ado' value='Livre ado'> Livre ado </option>
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
					<input class="textbox" type="text" name="titre" />
					
					<br/> <br/>
					
					Auteurs <br/> <br/>
					<input class="textbox" type="text" name="auteurs" />
					
					<br/> <br/>
					<input type='submit' value='rerchercher' width=250 class='coolButton' />
					<br/> <br/> <br/> <br/>
					
					Résultats de la recherche : <br/> <br/>
					<%
						List<Livre> listeLivres = livres
						String pageS = page
						Integer page = Integer.valueOf(pageS);
						
						int i = page * 5;
						int nbResPages = i+5;
						
						while (i<nbResPages && i<listeLivres.size()) {
							Livre l = listeLivres.get(i);
							
							if (l.nombreExemplairesDisponibles > 0) {
								out.print("<a href='/../PJBiblioJ/panier/ajouter?livre="+l.rang+"'>" + l.titre + " " + l.auteurs.nom + 
									" " + l.typeDoc.intitule + " " + l.nombreExemplairesDisponibles + "</a> <br/>");
							}
							else {
								out.print("<p>"+ l.titre + " " + l.auteurs.nom + " " + l.typeDoc.intitule + " " +
									l.nombreExemplairesDisponibles + "</p> <br/>");
							}
							
							i++;
						}
					%>
				</p>

				
				<a href="<%	
					int pageMoins = page;
					pageMoins--;
					String url = "../livre/rechercher"
					url += "?typeDoc="+params["typeDoc"]
					url += "&titre="+params["titre"]
					url += "&auteurs="+params["auteurs"]
					url += "&page=" + pageMoins;
					print(url);
				%>">
					<img alt="prev" src="${resource(dir: 'images', file: 'button_prev_100857.jpg')}"> 
				</a>
				
				
				<a href="<%	
					int pagePlus = page;
					pagePlus++;
					url = "../livre/rechercher"
					url += "?typeDoc="+params["typeDoc"]
					url += "&titre="+params["titre"]
					url += "&auteurs="+params["auteurs"]
					url += "&page=" + pagePlus;
					print(url);
				%>"> 
					<img alt="next" src="${resource(dir: 'images', file: 'button_next_100857.jpg')}"> 
				</a>
				
				
				<p>
					<br/>
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