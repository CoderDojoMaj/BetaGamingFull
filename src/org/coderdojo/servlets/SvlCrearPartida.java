package org.coderdojo.servlets;

import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.coderdojo.bd.FabricaConexiones;
import org.coderdojo.utils.Partida;

/**
 * Servlet implementation class SvlCrearPartida
 */
public class SvlCrearPartida extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private int maxPlayers;
	private int gameId;
	private int ownerId;
	private int minPlayers;
	private Date startDate;
	private Date endDate;
	//private Partida partida;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SvlCrearPartida() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//HttpSession sesion=(HttpSession) request.getSession();
		// Get a numeric uuid instead of a random long
		//id=new BigInteger(130, random).longValueExact();
		maxPlayers=(Integer) request.getAttribute("maxPlayers");
		gameId=(Integer) request.getAttribute("gameId");
		ownerId=(Integer) request.getAttribute("ownerId");
		minPlayers=(Integer) request.getAttribute("minPlayers");
		// TODO get the correct type for input in dates
		startDate=(Date) request.getAttribute("startDate");
		endDate=(Date) request.getAttribute("endDate");
		
		Partida partida = new Partida(0,maxPlayers,gameId,ownerId,minPlayers,startDate,endDate);
		partida.insertInDB();
		
	}
	
}
