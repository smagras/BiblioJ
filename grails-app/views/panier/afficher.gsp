<%@ page import="pjbiblioj.Livre" %>
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
				<h3>Mon panier</h3>
				<% 
					def livresPanier = params["livresPanier"]
					Livre livresEnVue = params["livresEnVue"]
					
					if (livresEnVue != null){
					
						out.print("<blockquote class='style1'><span>")
						out.print("<h4><img src='${resource(dir: 'images', file: 'icone_oeil.gif')}' style='width:20px;height:15px'  /> Informations sur cet article</h4>")
						out.print("Ttire : " + livresEnVue.getTitre() + "<br/>")
						out.print("Auteur : ")
						livresEnVue.getAuteurs().each{
							out.print( it.nom + " " + it.prenom)
						}
						out.print("<br/>")
						out.print("Type de document : " + livresEnVue.getTypeDoc().getIntitule())
						
						out.print("</span></blockquote><br>")
				
					}
				%>
				
				<h5>Articles sélectionner</h5>
				
				<% 

					def htmlCode = ""
					if (livresPanier != null){
					
						livresPanier.each{
				
								if (it){
									htmlCode += "<a href='../panier/afficher?see="+it.getTitre()+"'>"
									htmlCode += "<img src='${resource(dir: 'images', file: 'icone_oeil.gif')}' style='width:20px;height:15px'  />"
									htmlCode += "</a> "
									htmlCode += "<a href='../panier/afficher?delete="+it.getTitre()+"'>"
									htmlCode += "<img src='${resource(dir: 'images', file: 'IC113990.gif')}' style='width:15px;height:15px'  />"
									htmlCode += "</a> "
									htmlCode += it.getTitre() + "</br>"
								}
		
								
							}
							
						out.print(htmlCode)
				
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