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
				
					Recherche par : <br/> <br/> <br/>
					
					<select class="textbox" onchange='this.form.submit()' onMouseOver="hide()" style='width:300px;height:40px;' name="typeDoc">
						<option id='titre'> Type de Document </option> 
						<option id='Nouveauté' value='Nouveauté'> Nouveauté </option>
						<option id='Livre ado' value='Livre ado'> Livre ado </option>
					</select>
					
					
					
					<input type='submit' value='rerchercher' width=250 class='coolButton' />
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

				<div style="text-align: center;"></div>


			</div>


		</div>



	</div>
	
	<script type="text/javascript">
		function hide()
		{
			document.getElementById('titre').style.display = 'none';
		}
	</script>

</body>

</html>