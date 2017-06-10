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
		System.out.println("CREATING A MATCH");
		
		maxPlayers=Integer.valueOf(request.getParameter("maxPlayers"));
		gameId=Integer.valueOf(request.getParameter("gameId"));
		ownerId=Integer.valueOf(request.getParameter("ownerId"));
		minPlayers=Integer.valueOf(request.getParameter("minPlayers"));
		// TODO get the correct type for input in dates
		//startDate=(Date) request.getAttribute("startDate");
		//endDate=(Date) request.getAttribute("endDate");
		
		System.out.println("SD --> " + request.getParameter("startDate") + "\nED --> " + request.getParameter("endDate"));
		
		//Partida partida = new Partida(0,maxPlayers,gameId,ownerId,minPlayers,startDate,endDate);
		//addPartida(partida);
		
	}
	
	public boolean addPartida(Partida p) {
		FabricaConexiones f = FabricaConexiones.getFabrica();
    	Connection conn=null;
    	boolean result = false;
		try
		{
			conn = f.dameConexion();
			String queryCheck = "INSERT INTO matches(max_players, min_players, start_date, end_date, owner_id, selected_game_id) values(?,?,?,?,?,?)";
	    	PreparedStatement ps = conn.prepareStatement(queryCheck);
	    	ps.setInt(1, p.getMaxPlayers());
	    	ps.setInt(2, p.getMinPlayers());
	    	ps.setDate(3, (java.sql.Date) p.getStartDate());
	    	ps.setDate(4, (java.sql.Date) p.getEndDate());
	    	ps.setLong(5, p.getOwnerId());
	    	ps.setLong(5, p.getGameId());
	    	ps.executeQuery();
	    	
	    	result = true;
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			if (conn!=null){try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}}
		}
		
		return result;
	}
	
}
