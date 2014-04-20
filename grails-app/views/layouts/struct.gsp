<%@ page import="pjbiblioj.Utilisateur" %>
<%@ page import="com.biblioj.services.UtilisateurService" %>
<!DOCTYPE html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="en" class="no-js"><!--<![endif]-->
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<title><g:layoutTitle default="LivresAndCo"/></title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link rel="shortcut icon" href="${resource(dir: 'images', file: 'favicon.ico')}" type="image/x-icon">


    
    	<link rel="stylesheet" href="${resource(dir: 'css', file: 'menu.css')}" type="text/css" media="screen">
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'inputs.css')}" type="text/css" media="screen">
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'struct.css')}" type="text/css" media="screen">
		
		<g:layoutHead/>
		<r:layoutResources />
	</head>
	<body>
	
		<header>
		<!-- menu du haut -->
		<nav>
			<ul id="menu">
				<li><a href="home"> <img
						src="${resource(dir: 'images', file: 'logo.bmp')}"
						style="vertical-align: middle;" />
				</a></li>
				<li style="margin-left: 50px"><a href="home">Livres</a></li>
				<li><a href="velo">Mon Panier</a></li>
				<li><a href="monTrajet">Mon Trajet</a></li>
			</ul>
		</nav>
		</header>
		<!-- Banière -->
		<div id="banner"
			style="background-image:url(${resource(dir: 'images', file: 'bannerAcceul.jpg')})">
			<br /> <br /> <br /> <br /> <br /> <br /> <br /> <br /> <br />
			<br />
			<h1 class="headerTitle">BiblioJ</h1>
		</div>
	
	
	
		<g:layoutBody/>
		
		<!-- SocialBox -->
		<div id="social">

			<div class="socialBox">

				<h2><% 
				UtilisateurService utilisateurService = new UtilisateurService()
				Utilisateur utilisateurConnecter =  utilisateurService.getUtilisateurConnecter(session)
				
				if (utilisateurConnecter){
					
					out.print(utilisateurConnecter.getIdentifiant())
					
				}
				else{
					out.print("Compte")
				}
					
				 %>
				 </h2>

				<p style="text-align: center;">
					<img src="${resource(dir: 'images', file: 'avatar_icon.jpg')}"
						width="130" height="130">
				</p>
				
		
				
				
				<form action="../utilisateur/connexion" method="get">
					<% 
					String htmlCode =""
					
					if (utilisateurConnecter){
						htmlCode += "<div style='text-align: center;'>"
						htmlCode += "<strong>Nom : </strong>" + utilisateurConnecter.getNom() +"<br/>"
						htmlCode += "<br/><input type='submit' value='Deconnexion' width=250 class='coolButton' />"
						htmlCode += "</div>"
					}
					else{
						htmlCode += "<div style='text-align: center;'>"
						htmlCode += "Identifiant<br/>"
						htmlCode += "<input class='textbox' type='text' value='' style='width:200px' name='id' /><br/>"
						htmlCode += "Mot de passe<br/>"
						htmlCode += "<input class='textbox' type='password'  style='width:200px' name='password' /><br/><br/>"
						htmlCode += "<a href='../utilisateur/inscription' >Inscrivez vous</a></br></br>"
						htmlCode += "<input type='submit' value='Connexion'  width=250 class='coolButton' />"
						
						htmlCode += "</div>"		
					}
					
					
					
					
					out.print(htmlCode)
					%>
				
				</form>


			</div>


		</div>
		
		
		
		<r:layoutResources />
		
		
		
		<!-- foot -->
		<div style="text-align: center;" id="foot">
			Magras Steve, Paul Tudou , 2014
		</div>
	</body>

</html>
