<%@ page import="pjbiblioj.Livre" %>
<%@ page import="pjbiblioj.Auteur" %>
<%@ page import="pjbiblioj.Reservation" %>
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
				<h3>Mes réservations</h3>
				
				
				
				<%
					def reservationsLivres = params["reservationsLivres"]
				
					if (reservationsLivres){
				
			
						
						reservationsLivres.each{
							Reservation reservation = it
							out.print "<h4>"
							out.print " ( CODE : " + reservation.getCode() + " ) Réservation disponible dès " + reservation.getDateReservation() + "</h4>"
							
							reservation.getLivres().each{ livre ->
								out.print "- " + livre.getTitre() + "<br/>"
							}
				
							
							
						}
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