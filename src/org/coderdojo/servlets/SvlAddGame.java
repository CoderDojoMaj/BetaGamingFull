package org.coderdojo.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.coderdojo.bd.FabricaConexiones;
import org.coderdojo.utils.Game;
import org.coderdojo.utils.Partida;

/**
 * Servlet implementation class SvlAddGame
 */
public class SvlAddGame extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SvlAddGame() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Get request not available");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name=(String) request.getParameter("game_name");
		int minAge=Integer.parseInt((String) request.getParameter("min_age"));
		String imgLink=(String) request.getParameter("img_link");
		String desc=(String) request.getParameter("game_desc");
		int genreId=1;//(Integer) request.getAttribute("genreId");
		
		Game game = new Game(0,name,minAge,imgLink,desc,genreId);
		if(checkGame(game)){
			System.out.println("SvlAddGame --> Game already exists");
			response.getWriter().append("GAME ALREADY EXISTS");
		}else{
			System.out.println("SvlAddGame --> Adding game");
			addGame(game);
			response.getWriter().append("GAME ADDED");
		}
	}
	
	public boolean addGame(Game g) {
		FabricaConexiones f = FabricaConexiones.getFabrica();
    	Connection conn=null;
    	boolean result = false;
		try
		{
			conn = f.dameConexion();
			String queryCheck = "INSERT INTO games(game_name,min_age,img_url,game_desc,genre_id) values(?,?,?,?,?)";
	    	PreparedStatement ps = conn.prepareStatement(queryCheck);
	    	ps.setString(1, g.getName());
	    	ps.setInt(2, g.getMinAge());
	    	ps.setString(3, g.getImgLink());
	    	ps.setString(4, g.getDescription());
	    	ps.setInt(5, g.getGenreId());
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
	
	public boolean checkGame(Game g) {
		FabricaConexiones f = FabricaConexiones.getFabrica();
    	Connection conn=null;
    	boolean result = false;
		try
		{
			conn = f.dameConexion();
			String queryCheck = "SELECT game_name FROM games WHERE game_name=?";
	    	PreparedStatement ps = conn.prepareStatement(queryCheck);
	    	ps.setString(1, g.getName());
	    	ResultSet rs = ps.executeQuery();
	    	if(rs.next()){
	    		result=true;
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
		
		return result;
	}
	
}

