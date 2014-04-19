<%@ page import="pjbiblioj.Livre"%>

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
				<h3>Trouver un livre</h3>
				<p>
					<input class="textbox" type="text" value="<%
					String typeDoc = request.getParameter("typeDoc");
					
					if (typeDoc != null){
						out.print(typeDoc);
					}
					%>" name="typeDoc" />
					
					
					
					
					<input type='submit' value='rerchercher typeDoc'  width=250 class='coolButton' />
					<%
						List<Livre> listeLivres = request.getParameter("livres");
						System.out.println(listeLivres)
						
						for (Livre l : listeLivres) {
							out.print(l.titre + " " + l.nombreExemplairesDisponibles);
						}
					 %>
				</p>


				<p>
					<br />

				</p>



				<div align="left"></div>

			</form>
		</div>
		<!-- SocialBox -->
		<div id="social">

			<div class="socialBox">

				<h2>User</h2>

				<p style="text-align: center;">
					<img src="${resource(dir: 'images', file: 'avatar_icon.jpg')}"
						width="190" height="210">
				</p>

				<div style="text-align: center;"></div>


			</div>


		</div>



	</div>
	<!-- /SocialBox -->




</body>

</html>