package org.coderdojo.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.coderdojo.bd.FabricaConexiones;
import org.coderdojo.utils.Game;

/**
 * Servlet implementation class SvlPrePlayGame
 */
public class SvlPrePlayMatch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SvlPrePlayMatch() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ArrayList<Game> games = new ArrayList<Game>();
		
		FabricaConexiones f = FabricaConexiones.getFabrica();
    	Connection conn=null;
    	
		try
		{
			conn = f.dameConexion();
			String queryCheck = "SELECT * FROM games";
	    	PreparedStatement ps = conn.prepareStatement(queryCheck);
	    	ResultSet rs = ps.executeQuery();
	    	
	    	while(rs.next()){
	    		games.add(new Game(rs.getLong(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5),rs.getInt(6)));
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
		
		HttpSession sesion = request.getSession(false);
		
		sesion.setAttribute("games", games);
		
		response.sendRedirect("crearPartida.jsp");
		
	}

}
