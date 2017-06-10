package org.coderdojo.servlets;

import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.coderdojo.bd.FabricaConexiones;
import org.coderdojo.exceptions.DBException;
import org.coderdojo.utils.Partida;
import org.coderdojo.utils.User;

/**
 * Servlet implementation class SvlCrearPartida
 */
public class SvlCrearPartida extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private int maxPlayers;
	private String gameName;
	private long ownerId;
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
		HttpSession sesion = request.getSession();
		User user = (User) sesion.getAttribute("user");
		System.out.println("CREATING A MATCH");
		
		maxPlayers=Integer.valueOf(request.getParameter("maxPlayers"));
		gameName=request.getParameter("gameName");
		ownerId=user.getId();
		minPlayers=Integer.valueOf(request.getParameter("minPlayers"));
		// TODO get the correct type for input in dates
		startDate=fromString( request.getParameter("startDate"));
		endDate=fromString( request.getParameter("endDate"));
		
		//System.out.println("SD --> " + request.getParameter("startDate") + "\nED --> " + request.getParameter("endDate"));
		
		Partida partida;
		try {
			partida = new Partida(0,maxPlayers,getGameId(gameName),ownerId,minPlayers,startDate,endDate);
			addPartida(partida);
			sesion.setAttribute("partida", partida);
			response.sendRedirect("Partida.jsp");
		} catch (DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.sendError(512, "Database did not respond");
		}
		
	}
	
	private int getGameId(String name) throws DBException{
		FabricaConexiones f = FabricaConexiones.getFabrica();
    	Connection conn=null;
    	int result = -1;
		try
		{
			conn = f.dameConexion();
			String queryCheck = "SELECT game_id FROM games WHERE game_name=?";
	    	PreparedStatement ps = conn.prepareStatement(queryCheck);
	    	ps.setString(1, name);
	    	ResultSet rs = ps.executeQuery();
	    	
	    	while(rs.next()){
	    		result = rs.getInt(1);
	    	}
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
		
		if(result != -1){
			return result;
		}else{
			throw new DBException("The database did not respond");
		}
	}
	
	@Deprecated
	private Date fromString(String s){
		Date d = new Date(0);
		String calD = s.split("T")[0];
		String time = s.split("T")[1];
		d.setYear(Integer.valueOf(calD.split("-")[0]));
		d.setMonth(Integer.valueOf(calD.split("-")[1]));
		d.setDate(Integer.valueOf(calD.split("-")[2]));
		//d.setHours(Integer.valueOf(time.split(":")[0]));
		//d.setMinutes(Integer.valueOf(time.split(":")[1]));
		return d;
		
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
	    	ps.setDate(3, p.getStartDate());
	    	ps.setDate(4, p.getEndDate());
	    	ps.setLong(5, p.getOwnerId());
	    	ps.setLong(6, p.getGameId());
	    	ps.execute();
	    	
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
