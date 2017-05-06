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
		minPlayers=(Integer) request.getAttribute("minPlayers");
		// TODO get the correct type for input in dates
		startDate=(Date) request.getAttribute("startDate");
		endDate=(Date) request.getAttribute("endDate");
		
		//partida = new Partida(id,maxPlayers,gameId,minPlayers,startDate,endDate);
		
		addPartida();
	}
	
	void addPartida(){
		FabricaConexiones f = FabricaConexiones.getFabrica();
		try {
			Connection conn = f.dameConexion();
			String query = " insert into partidas (maxPlayers, gameId, minPlayers, startDate, endDate)"
			        + " values (?, ?, ?, ?, ?)";
			PreparedStatement preStm = conn.prepareStatement(query);
			preStm.setInt(1, maxPlayers);
			preStm.setLong(2, gameId);
			preStm.setInt(3, minPlayers);
			preStm.setDate(4, (java.sql.Date) startDate);
			preStm.setDate(5, (java.sql.Date) endDate);
			
			preStm.execute();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
