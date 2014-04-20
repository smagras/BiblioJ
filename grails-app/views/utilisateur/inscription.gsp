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
				<h3>Incription </h3>
				
				<%
					def erreur = params["erreur"] 
					if (erreur){
						out.print(erreur)
					}
				 %>
				
				<h4> Informations d'indentification </h4>
				Identifiant <br/>
				<input class='textbox' type='text' value='' style='width:200px' name='id' /><br/><br/>
				Mot de passe <br/>
				<input class='textbox' type='password' value='' style='width:200px' name='password' /><br/><br/>
				
				<h4> Informations personnelles </h4>
				Nom<br/>
				<input class='textbox' type='text' value='' style='width:200px' name='nom' /><br/><br/>
				
				<br/><br/><input type='submit' value='Validée'  style='width:200px' class='coolButton' />


				
				</form>

				<div align="left"></div>

			</form>
		</div>




	</div>


</body>

</html>