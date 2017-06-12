<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="org.coderdojo.utils.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>  
    <head>		
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/> 
		<link href="css/style.css" rel="stylesheet" type="text/css">
    <link rel="shortcut icon" type="image/png" href="https://raw.githubusercontent.com/CoderDojoMaj/Beta-Gaming/master/templogo.png"/>
		<title>BetaGaming-</title>
		<style type="text/css" media="screen">
			body{background-color:black}
					
			.active {
			background-color: #E60026;
			}
			
      body,
      input,
      button {
        font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
      }
      .container { margin: 30px auto 40px auto; width: 800px; text-align: center; }
			
      input[type=text],
      input[type=password] {
        font-size: 13px;
        min-height: 32px;
        margin: 0;
        padding: 7px 8px;
        outline: none;
        color: #333;
        background-color: #fff;
        background-repeat: no-repeat;
        background-position: right center;
        border: 1px solid #ccc;
        border-radius: 3px;
        box-shadow: inset 0 1px 2px rgba(0,0,0,0.075);
        -moz-box-sizing: border-box;
        box-sizing: border-box;
        -webkit-transition: all 0.15s ease-in;
        transition: all 0.15s ease-in;
        vertical-align: middle;
      }
      .btn {
        position: relative;
        display: inline-block;
        padding: 6px 12px;
        font-size: 13px;
        font-weight: bold;
        line-height: 20px;
        color: #333;
        white-space: nowrap;
        vertical-align: middle;
        cursor: pointer;
        background-color: #EEE;
        background-image: -webkit-linear-gradient(#FCFCFC, #EEE);
        background-image: linear-gradient(#FCFCFC, #EEE);
        background-repeat: repeat-x;
        border: 1px solid #d5d5d5;
        border-radius: 3px;
        user-select: none;
        -webkit-appearance: none;
      }

      .btn:focus,
      input[type=text]:focus,
      input[type=password]:focus {
        text-decoration: none;
        border-color: #51a7e8;
        outline: none;
        box-shadow: 0 0 5px rgba(81, 167, 232, 0.5);
      }

      .btn:hover,
      .btn:active {
        text-decoration: none;
        background-color: #ddd;
        background-image: -webkit-linear-gradient(#eee, #ddd);
        background-image: linear-gradient(#eee, #ddd);
        background-repeat: repeat-x;
        border-color: #ccc;
      }

      .btn:active {
        background-color: #dcdcdc;
        background-image: none;
        border-color: #b5b5b5;
        box-shadow: inset 0 2px 4px rgba(0, 0, 0, 0.15);
      }

      label[for=search] {
        display: block;
        text-align: left;
      }
      #search label {
        font-weight: 200;
        padding: 5px 0;
      }
      #search input[type=text] {
        font-size: 18px;
        width: 705px;
      }
      #search .btn {
        padding: 10px;
        width: 90px;
      }
      #suggestions {
        margin-top: 35px;
        color: #ccc;
      }
      #suggestions a {
        color: #666666;
        font-weight: 200;
        font-size: 14px;
        margin: 0 10px;
      }
    </style>
    </head>
    <body>  
        <div id ="contenedor">  
            <div id ="cabecera">
									<img src="https://raw.githubusercontent.com/CoderDojoMaj/Beta-Gaming/master/templogo.png" alt="logo" class="avatar" style="width:70px;height:70px;">
								<button align="center" id="usuario"><% out.println(session.getAttribute("usuario")); %></button>
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
    <div class="container">
      <form accept-charset="UTF-8" id="search" action="search" method="get">
        <font color="white">Find games, people and matches:</font>
        <input type="text" name="q">
        <span> Find </span><select name="t" id="tS">
        	<option value="0">Matches</option>
        	<option value="1">Users</option>
        	<option value="2">Games</option>
        	<option value="3">Everything</option>
        </select>
        <input class="btn" type="submit" value="Search">
      </form>

      <div id="suggestions">
        <a href="#">Contact Support</a> &mdash;
        <a href="#">Homepage</a>
      </div>
      <a>
    </div>
    <script type="text/javascript" src="/_error.js"></script>
						</div> 
						
            <div id ="pie">
						<br><hr color="#d3d3d3" width="80%">
						<center>
							Â© BetaGaming 2017 &mdash; Terms <a href="#"><img style="width:154px;height:33px;" src="Imagenes/templogoB.png" border="0"></a> Help &mdash; Contact BetaGaming
							</center>
						</div> 
						
        </div>  
    </body>  
</html> 