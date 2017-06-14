<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="org.coderdojo.utils.*" import="java.util.ArrayList"%>
<html>
    <head>
			<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
			<link href="css/style.css" rel="stylesheet" type="text/css">
			<link rel="shortcut icon" type="image/png" href="https://raw.githubusercontent.com/CoderDojoMaj/Beta-Gaming/master/templogo.png"/>
			<title>BetaGaming-Welcome to BetaGaming!!</title>
    </head>
    <body>
    <% 
    User user = (User) session.getAttribute("user");
    %>
			<div id ="contenedor">
        <div id ="cabecera">
					<button align="center" id="usuario"><!--<% out.println(session.getAttribute("usuario")); %>--></button>
					<input align="right" type="image" src="images/lupa.png" width="30" height="30">

				</div>
				

            <div id="formulario">
											<center>
									<form method="post" action="SvlEditProfile" id="signup">

								<table bgcolor="white">
									<tr>
											<td><h1>Create an account in Beta Gaming</h1></td>
									</tr>
									</table>
								<table bgcolor="white" style="width:50%">
									<tr>
										<td>Username</td>
										<td>Email</td>
										<td>Old password</td>
										<td>New password</td>
									</tr>
									<tr>
										<td><input value="<% out.println(user.getNickname()); %>" id="field_username" title="Username must not be blank and contain only letters, numbers and underscores." type="text" required pattern="\w+" name="nickname"></td>
										<td><input value="<% out.println(user.getEmail()); %>" type="email" name="mail" required></td>
										<td><input id="field_pwd1" title="Old password" placeholder="Old password" type="password" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}" name="opwd"></td>
										<td><input id="field_pwd1" title="New password" placeholder="New password" type="password" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}" name="npwd"></td>
									</tr>
									<tr>
										<td>Name</td>
										<td>Surname</td>
										<td>Day of birth</td>
									</tr>
									<tr>
										<td><input value="<% out.println(user.getName()); %>" id="field_pwd2" title="Your name" type="text" name="name" pattern="\w+"></td>
										<td><input value="<% out.println(user.getSurname()); %>" type="text" size="15" maxlength="30" name="surname" pattern="\w+"></td>
										<td><input value="<% out.println(DateUtils.toString(user.getBornDate())); %>" type="date" name="bornDateLong"></td>
									</tr>
										<tr>
											<td>Skype account</td>
											<td>A little description about yourself</td>
										</tr>
										<tr>
											<td><input value="<% out.println(user.getSkypeUser()); %>" type="text" name="skype" required pattern="\w+"></td>
											<td><textarea rows="4" cols="50" form="signup" name="desc"><% out.println(user.getRawDescription()); %></textarea></td>
										</tr>
										<tr>
										<td></td>
										</tr>
										<tr>
										</tr>

										<tr>
											<td>
												<br><br><button id="saveEdit" form="signup" type="submit">Save</button>
											</td>
										</tr>
								</table>
								</form>
								</center>
						</div>

            <div id ="pie">
						<br><hr color="#d3d3d3" width="80%">
						<center>
							Â© BetaGaming 2017 &mdash; Terms <a href="#"><img style="width:154px;height:33px;" src="Images/templogoB.png" border="0"></a> Help &mdash; Contact BetaGaming
							</center>
						</div>

			</div>
    </body>
</html>
