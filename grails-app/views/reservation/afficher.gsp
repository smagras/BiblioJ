<%@ page import="pjbiblioj.Livre" %>
<%@ page import="pjbiblioj.Auteur" %>
<%@ page contentType="text/html;charset=UTF-8" %>


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
				<h3>Ma réservation</h3>
				
				
				<%
					ArrayList<Livre> livresImpossibles = params["livresImpossibles"]
				
					if (!livresImpossibles.isEmpty()){
				
						out.print "<h4> Livres indisponibles </h4>"
				
						livresImpossibles.each{
							out.print it.getTitre() + " " + it.getAuteurs().nom
							out.print "<br/>" 
						}
					}
				 %>
				 
				 <%
					ArrayList<Livre> livresPossibles = params["livresPossibles"]
				
					if (!livresPossibles.isEmpty()){
				
						out.print "<h4> Ma commande </h4>"
				
						livresPossibles.each{
							
							out.print it.getTitre() + " " + it.getAuteurs().nom
							out.print "<br/>" 

						}
					}
				 %>
				 
				 <%
				 
				 	if (!livresPossibles.isEmpty()){
						 out.print("</br></br>")
						 out.print("<a href='/../PJBiblioJ/reservation/actionUtilisateur?order=v' >")
						 out.print("<input type='button'  value='Valider' width=250 class='coolButton' />")
						 out.print("<a/>")
						 out.print("<a href='/../PJBiblioJ/reservation/actionUtilisateur?order=a' >")
						 out.print("<input type='button' value='Annuler' width=250 class='coolButton' />")
						 out.print("<a/>")
						 
				 	}
					else{
						out.print("<blockquote class='style1'><span>")
						out.print("<h4><img src='${resource(dir: 'images', file: 'icone_oeil.gif')}' style='width:20px;height:15px'  /> Informations sur votre réservation</h4>")
						out.print("Il n'y a aucuns livres disponible.")
						out.print("</span></blockquote><br>")
					}
					 
					 
				 %>

				<div align="left"></div>

			</form>
		</div>

				<div style="text-align: center;"></div>


			</div>


		</div>

	</div>

</body>

</html>