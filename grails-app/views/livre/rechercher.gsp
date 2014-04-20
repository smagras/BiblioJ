<%@ page import="pjbiblioj.Livre" %>

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
					
					
					
					
					<input type='submit' value='rerchercher typeDoc' onclick='url()' width=250 class='coolButton' />
					<%
						List<Livre> listeLivres = livres
						
						/*if (listeLivres != null) out.print("<p> listeLivres </p>")
						else out.print("<p>listeLivres null </p>")*/
						
						for (Livre l : listeLivres) {
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

	<script type="text/javascript">
		function url() {
			var uri = document.location.href;
			var dec = decodeURI(uri);

			//alert(uri + '\n' + dec);
			document.location.href = dec;
		}
	</script>


</body>

</html>