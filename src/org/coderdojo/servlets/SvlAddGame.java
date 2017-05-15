package org.coderdojo.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
		String name=(String) request.getAttribute("name");
		int minAge=(Integer) request.getAttribute("minAge");
		String imgLink=(String) request.getAttribute("imgLink");
		String desc=(String) request.getAttribute("description");
		int genreId=(Integer) request.getAttribute("genreId");
		
		Game game = new Game(0,name,minAge,imgLink,desc,genreId);
		addGame(game);
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

