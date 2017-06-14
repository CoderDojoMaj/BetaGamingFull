<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="org.coderdojo.utils.*" import="java.util.ArrayList"%>
<html>  
    <head>		
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/> 
		<link href="css/style.css" rel="stylesheet" type="text/css">
    <link rel="shortcut icon" type="image/png" href="https://raw.githubusercontent.com/CoderDojoMaj/Beta-Gaming/master/templogo.png"/>
		<title>BetaGaming-</title>
    </head>
    <body> 
    <% 
    User user = (User) session.getAttribute("user");
    %> 
        <div id ="contenedor">  
            <div id ="cabecera">
									<img src="https://raw.githubusercontent.com/CoderDojoMaj/Beta-Gaming/master/templogo.png" alt="logo" class="avatar" style="width:70px;height:70px;">
								<button align="center" id="usuario"><% out.println(((User) session.getAttribute("user")).getNickname()); %></button>
								<input align="right" type="image" src="Imagenes/lupa.png" width="30" height="30">
						</div> 

            <div id ="izquierda">
							<br><br>
									<div id="wrapper">
								<ul class="menu">
									<li class="item1"><a href="#">Friends <span>340</span></a>
										<ul>
											<li class="subitem1"><a href="#">Cute Kittens <span>14</span></a></li>
											<li class="subitem2"><a href="#">Strange âStuffâ <span>6</span></a></li>
											<li class="subitem3"><a href="#">Automatic Fails <span>2</span></a></li>
										</ul>
									</li>
									<li class="item2"><a href="#">Videos <span>147</span></a>
										<ul>
											<li class="subitem1"><a href="#">Cute Kittens <span>14</span></a></li>
											<li class="subitem2"><a href="#">Strange âStuffâ <span>6</span></a></li>
											<li class="subitem3"><a href="#">Automatic Fails <span>2</span></a></li>
										</ul>
									</li>
									<li class="item3"><a href="#">Galleries <span>340</span></a>
										<ul>
											<li class="subitem1"><a href="#">Cute Kittens <span>14</span></a></li>
											<li class="subitem2"><a href="#">Strange âStuffâ <span>6</span></a></li>
											<li class="subitem3"><a href="#">Automatic Fails <span>2</span></a></li>
										</ul>
									</li>
									<li class="item4"><a href="#">Podcasts <span>222</span></a>
										<ul>
											<li class="subitem1"><a href="#">Cute Kittens <span>14</span></a></li>
											<li class="subitem2"><a href="#">Strange âStuffâ <span>6</span></a></li>
											<li class="subitem3"><a href="#">Automatic Fails <span>2</span></a></li>
										</ul>
									</li>
									<li class="item5"><a href="#">Robots <span>16</span></a>
										<ul>
											<li class="subitem1"><a href="#">Cute Kittens <span>14</span></a></li>
											<li class="subitem2"><a href="#">Strange âStuffâ <span>6</span></a></li>
											<li class="subitem3"><a href="#">Automatic Fails <span>2</span></a></li>
										</ul>
									</li>
								</ul>
							</div>
						</div>
				
            <div id ="derecha">
							<center>
							<table WIDTH=50% border="4"><tr><td><center><h1><% out.println(user.getNickname()); %></h1></center></td></tr><tr><td><form action="EditProfile.jsp" id="Follow"><button type="submit" form="Follow"><img src="https://cdn3.iconfinder.com/data/icons/google-material-design-icons/48/ic_mode_edit_48px-128.png" style="width:15px;height:15px;"><span>Editar</span></button></form></td></tr></table>
								<table>
									<tr>
										<td><table border="3"><tr><td><img src="https://upload.wikimedia.org/wikipedia/commons/thumb/5/53/Google_%22G%22_Logo.svg/1000px-Google_%22G%22_Logo.svg.png" alt="logo" class="avatar" style="width:100px;height:100px;"></td></tr></table></td>
										<td></td>
										<td></td>
										<td></td>
										<td><table border="4">
													<tr>
														<td>Registration date:</td>
														<td>Birthdate:</td>
													</tr>
													<tr>
														<td><% out.println(user.getRegisterDate().toString()); %></td>
														<td><% out.println(user.getBornDate().toString()); %></td>
													</tr>
													<tr>
														<td>Bio:</td>
													</tr>
													<tr>
														<td><textarea rows="4" cols="50" readonly><% out.println(user.getDescription()); %></textarea></td>
													</tr>
												</table></td>
									</tr>
								</table>
							</center>
						</div> 
						
            <div id ="pie">
						<br><hr color="#d3d3d3" width="80%">
						<center>
							Â© BetaGaming 2017 &mdash; Terms <a href=""><img style="width:154px;height:33px;" src="Imagenes/templogoB.png" border="0"></a> Help &mdash; Contact BetaGaming
							</center>
						</div> 
						
        </div>  
    </body>  
</html> 