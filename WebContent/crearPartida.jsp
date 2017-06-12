<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="org.coderdojo.utils.*" import="java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
		<link href="estilos.css" rel="stylesheet" type="text/css">
    <link rel="shortcut icon" type="image/png" href="https://raw.githubusercontent.com/CoderDojoMaj/Beta-Gaming/master/templogo.png"/>
		<title>BetaGaming-Create a new Match</title>
    </head>
    <body>
        <div id ="contenedor">
            <div id ="cabecera">
									<img src="https://raw.githubusercontent.com/CoderDojoMaj/Beta-Gaming/master/templogo.png" alt="logo" class="avatar" style="width:70px;height:70px;">
								<button align="center" id="usuario"></button>
								<input align="right" type="image" src="Imagenes/lupa.png" width="30" height="30">
						</div>

            <div id ="izquierda">
							<br><br>
									<div id="wrapper">
								<ul class="menu">
									<li class="item1"><a href="#">Friends <span>340</span></a>
										<ul>
											<li class="subitem1"><a href="#">Cute Kittens <span>14</span></a></li>
											<li class="subitem2"><a href="#">Strange “Stuff” <span>6</span></a></li>
											<li class="subitem3"><a href="#">Automatic Fails <span>2</span></a></li>
										</ul>
									</li>
									<li class="item2"><a href="#">Videos <span>147</span></a>
										<ul>
											<li class="subitem1"><a href="#">Cute Kittens <span>14</span></a></li>
											<li class="subitem2"><a href="#">Strange “Stuff” <span>6</span></a></li>
											<li class="subitem3"><a href="#">Automatic Fails <span>2</span></a></li>
										</ul>
									</li>
									<li class="item3"><a href="#">Galleries <span>340</span></a>
										<ul>
											<li class="subitem1"><a href="#">Cute Kittens <span>14</span></a></li>
											<li class="subitem2"><a href="#">Strange “Stuff” <span>6</span></a></li>
											<li class="subitem3"><a href="#">Automatic Fails <span>2</span></a></li>
										</ul>
									</li>
									<li class="item4"><a href="#">Podcasts <span>222</span></a>
										<ul>
											<li class="subitem1"><a href="#">Cute Kittens <span>14</span></a></li>
											<li class="subitem2"><a href="#">Strange “Stuff” <span>6</span></a></li>
											<li class="subitem3"><a href="#">Automatic Fails <span>2</span></a></li>
										</ul>
									</li>
									<li class="item5"><a href="#">Robots <span>16</span></a>
										<ul>
											<li class="subitem1"><a href="#">Cute Kittens <span>14</span></a></li>
											<li class="subitem2"><a href="#">Strange “Stuff” <span>6</span></a></li>
											<li class="subitem3"><a href="#">Automatic Fails <span>2</span></a></li>
										</ul>
									</li>
								</ul>
							</div>
						</div>

            <div id ="derecha">
							<center>
			<table>
				<tr>
						<td bgcolor="#ffffff"><h1 bgcolor="#ffffff">Create a new match</h1></td>
				</tr>
				</table>
			<table bgcolor="#ffffff" style="width:50%">
				<tr>
					<td>Maximum players</td>
					<td>Minimum Players</td>
					<td>Game</td>
					<td>Start date</td>
					<td>End date</td>
				</tr>
				<tr>
				<form action="SvlCrearPartida" method="POST">
					<td><input type="number" name="maxPlayers" min="2">
						</td>
						<td><input type="number" name="minPlayers" min="2">
						</td>
					<td><select name="gameName">
							 <% 
							 HttpSession sesion = request.getSession();
							 ArrayList<Game> games = (ArrayList<Game>)sesion.getAttribute("games"); 
							 sesion.removeAttribute("games");
							 for(Game g:games){
								 out.println("<option value=\"" + g.getName() + "\">" + g.getName() + "</option>");
							 }
							 %>
						</select></td>
					<td><input type="datetime-local" name="startDate"></td>
          			<td><input type="datetime-local" name="endDate"></td>
          			<td><input type="submit" value="Create"></td>
          		</form>
				</tr>
				<tr>
					<td><table border="1">
						<tr><td><div style="background-color: #FF0000"><font size="5">1 </font><img src="#" alt="logo" class="avatar" style="width:20px;height:20px;"><font size="4">Name</font> &mdash; Level:XX<td></div></tr>
						<tr><td><div style="background-color: #008FFF"><font size="5">1 </font><img src="#" alt="logo" class="avatar" style="width:20px;height:20px;"><font size="4">Name</font> &mdash; Level:XX<td></div></tr>
						<tr><td><div style="background-color: #707070"><font size="5">1 </font><img src="#" alt="logo" class="avatar" style="width:20px;height:20px;"><font size="4">Name</font> &mdash; Level:XX<td></div></tr>
						<tr><td><div style="background-color: #FF0000"><font size="5">1 </font><img src="#" alt="logo" class="avatar" style="width:20px;height:20px;"><font size="4">Name</font> &mdash; Level:XX<td></div></tr>
						<tr><td><div style="background-color: #008FFF"><font size="5">1 </font><img src="#" alt="logo" class="avatar" style="width:20px;height:20px;"><font size="4">Name</font> &mdash; Level:XX<td></div></tr>
						<tr><td><div style="background-color: #707070"><font size="5">1 </font><img src="#" alt="logo" class="avatar" style="width:20px;height:20px;"><font size="4">Name</font> &mdash; Level:XX<td></div></tr>
					</table></td>
				</tr>
				<tr>
					<td><input type="checkbox" name="out" value="Bike">Invite BetaGaming friends<br></td>
				</tr>
			</table>
			</center>
						</div>

            <div id ="pie">
						<br><hr color="#d3d3d3" width="80%">
						<center>
							© BetaGaming 2017 &mdash; Terms <a href="#"><img style="width:154px;height:33px;" src="Imagenes/templogoB.png" border="0"></a> Help &mdash; Contact BetaGaming
							</center>
						</div>

        </div>
    </body>
</html>
