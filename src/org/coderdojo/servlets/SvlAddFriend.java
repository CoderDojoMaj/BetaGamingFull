package org.coderdojo.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.coderdojo.bd.FabricaConexiones;
import org.coderdojo.utils.Game;
import org.coderdojo.utils.User;

/**
 * Servlet implementation class SvlAddFriend
 */
public class SvlAddFriend extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SvlAddFriend() {
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
		HttpSession sesion=request.getSession();
		
		User ferUser = (User) sesion.getAttribute("user");
		User fedUser = (User) sesion.getAttribute("fedUser");
		sesion.removeAttribute("fedUser");
		
		FabricaConexiones f = FabricaConexiones.getFabrica();
    	Connection conn=null;
    	boolean isAlreadyFollowing=false;
		try
		{
			conn = f.dameConexion();
			String queryCheck = "SELECT followed_user_id FROM friend_list WHERE follower_user_id=?";
	    	PreparedStatement ps = conn.prepareStatement(queryCheck);
	    	ps.setLong(1, ferUser.getId());
	    	ResultSet resultSet = ps.executeQuery();
	    	while (resultSet.next()){
	    		if(fedUser.getId()==resultSet.getLong(1)){
	    			isAlreadyFollowing=true;
	    		}
	    		
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
		if(!isAlreadyFollowing){
			try
			{
				conn = f.dameConexion();
				String queryCheck = "INSERT INTO friend_list(followed_user_id,follower_user_id) VALUES(?,?)";
		    	PreparedStatement ps = conn.prepareStatement(queryCheck);
		    	ps.setLong(1, fedUser.getId());
		    	ps.setLong(2, ferUser.getId());
		    	ps.execute();
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
		}
		
		response.sendRedirect("SvlGetFriendList");
	}

}
