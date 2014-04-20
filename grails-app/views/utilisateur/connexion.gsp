<%@ page import="pjbiblioj.Livre" %>
<%@ page import="pjbiblioj.Utilisateur" %>
<%@ page import="com.biblioj.services.UtilisateurService" %>

<!DOCTYPE html>
<html>
<head>

<meta name="layout" content="struct">

</head>

<body>

	

	<!-- Corp  -->
	<div id="corp">



		<br />

		<!-- article n°2 -->
		<div id="descrition">
			<form name="input" action="" method="get">
				<h3>Erreur de connection </h3>
				

				<% 
				
				UtilisateurService utilisateurService = new UtilisateurService()
				Utilisateur utilisateurConnecter =  utilisateurService.getUtilisateurConnecter(session)
				

					
				 %>
				 

		
				
				
				<form action="../utilisateur/connexion" method="get">
					<% 
					String htmlCode =""
					
					if (utilisateurConnecter){

						htmlCode += "<strong>Nom : </strong>" + utilisateurConnecter.getNom() 
						htmlCode += "<br/><input type='submit' value='Deconnexion' width=250 class='coolButton' />"

					}
					else{

						htmlCode += "Identifiant<br/>"
						htmlCode += "<input class='textbox' type='text' value='' style='width:200px' name='id' /><br/><br/>"
						htmlCode += "Mot de passe<br/>"
						htmlCode += "<input class='textbox' type='password'  style='width:200px' name='password' /><br/><br/>"
						htmlCode += "<input type='submit' value='Connexion'  width=250 class='coolButton' />"
	
					}
					
					
					out.print(htmlCode)
					%>
				
				</form>

				<div align="left"></div>

			</form>
		</div>




	</div>


</body>

</html>