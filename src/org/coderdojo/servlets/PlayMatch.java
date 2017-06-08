package org.coderdojo.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.coderdojo.bd.FabricaConexiones;
import org.coderdojo.utils.Partida;
import org.coderdojo.utils.User;

/**
 * Servlet implementation class PlayMatch
 */
public class PlayMatch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PlayMatch() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession sesion = request.getSession();
		
		long uId = ((User)sesion.getAttribute("user")).getId();
		long mId = ((Partida)sesion.getAttribute("partida")).getId();
		
		int r = 0;
		
		FabricaConexiones f = FabricaConexiones.getFabrica();
    	Connection conn=null;
		try
		{
			conn = f.dameConexion();
			String queryCheck = "INSERT INTO user_match_list(user_id,match_id) values(?,?)";
	    	PreparedStatement ps = conn.prepareStatement(queryCheck);
	    	ps.setLong(1, uId);
	    	ps.setLong(2, mId);
	    	
	    	ps.execute();
	    	r=1;
	    	
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			r=2;
		}
		finally{
			if (conn!=null){try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				r=3;
			}}
		}
		
		if(r==1){
			response.sendRedirect("/Betagaming_Server2/Partida.jsp");
		}else{
			response.getWriter().append("Error\nError code --> " + r);
		}
		
	}

}
