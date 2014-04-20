<%@ page import="pjbiblioj.Livre" %>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type"
	content="application/xhtml+xml; charset=UTF-8" />
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
				
					Recherche par : <br/> <br/> <br/>
					
					Type de Document <br/> <br/>
					<select class="textbox" onchange='this.form.submit()' onMouseOver="hide()" style='width:300px;height:40px;' name="typeDoc">
						<option id='titre' value=''> </option>
						<option id='Nouveauté' value='Nouveauté'> Nouveauté </option>
						<option id='Livre ado' value='Livre ado'> Livre ado </option>
					</select>
					
					<br/> <br/>
					
					Titre <br/> <br/>
					<input class="textbox" type="text" name="titre" />
					
					<br/> <br/>
					
					Auteurs <br/> <br/>
					<input class="textbox" type="text" name="auteurs" />
					
					<br/> <br/>
					<input type='submit' value='rerchercher' width=250 class='coolButton' />
					<br/> <br/>
					
					Résultats de la recherche par type de document : <br/>
					<%
						List<Livre> typeDocLivres = typeDoc
						
						/*if (listeLivres != null) out.print("<p>" + listeLivres + "</p>")
						else out.print("<p>listeLivres null </p>")*/
						
						for (Livre l : typeDocLivres) {
							out.print("<p>"+ l.titre + " " + l.nombreExemplairesDisponibles + "</p>");
						}
					%>
					 
					Résultats de la recherche par titre : <br/>
					<%
						List<Livre> titreLivres = titre
						
						for (Livre l : titreLivres) {
							out.print("<p>"+ l.titre + " " + l.nombreExemplairesDisponibles + "</p>");
						}
					%>
					
					Résultats de la recherche par auteurs : <br/>
					<%
						List<Livre> auteurLivres = auteurs
						
						for (Livre l : auteurLivres) {
							out.print("<p>"+ l.titre + " " + l.nombreExemplairesDisponibles + "</p>");
						}
					%>
				</p>


				<p>
					<br />

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