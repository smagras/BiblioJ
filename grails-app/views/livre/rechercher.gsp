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
				
					Rechercher par : <br/> <br/> <br/>
					
					Type de Document <br/> <br/>
					<select class="textbox" onMouseOver="hide()" style='width:300px;height:40px;' name="typeDoc">
						<option id='titre'> </option>
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
					<br/> <br/> <br/> <br/>
					
					Résultats de la recherche : <br/> <br/>
					<%
						List<Livre> listeLivres = livres
						
						int i=0;
						while (i<5 && i<listeLivres.size()) {
							Livre l = listeLivres.get(i);
							out.print("<p>"+ l.titre + " " + l.nombreExemplairesDisponibles + "</p>");
							i++;
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