package org.coderdojo.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
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
import org.coderdojo.utils.User;

/**
 * Servlet implementation class SvlGetFriendList
 */
public class SvlGetFriendList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SvlGetFriendList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession sesion = request.getSession(false);
		System.out.println("getting the friends");
		sesion.setAttribute("friends", getFriends(((User) sesion.getAttribute("user")).getId()));
		response.sendRedirect("friendList.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private ArrayList<User> getFriends(long id){
		ArrayList<User> r = new ArrayList<User>();
		ArrayList<Long> ids = new ArrayList<Long>();
		
		FabricaConexiones f = FabricaConexiones.getFabrica();
    	Connection conn=null;
		try
		{
			conn = f.dameConexion();
			String queryCheck = "SELECT followed_user_id FROM friend_list WHERE follower_user_id=?";
	    	PreparedStatement ps = conn.prepareStatement(queryCheck);
	    	ps.setLong(1, id);
	    	ResultSet resultSet = ps.executeQuery();
	    	while (resultSet.next()){
	    		ids.add(resultSet.getLong(1));
	    		System.out.println("Got an ID");
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

		for(long uid:ids){
			try
			{
				conn = f.dameConexion();
				String queryCheck = "SELECT * FROM users WHERE user_id=?";
				PreparedStatement ps = conn.prepareStatement(queryCheck);
				ps.setLong(1, uid);
				ResultSet resultSet = ps.executeQuery();
				while (resultSet.next()){
					
		    		String nickname = resultSet.getString(2);
		    		String passwordHash = resultSet.getString(3);
		    		String name = resultSet.getString(4);
		    		String surname = resultSet.getString(5);
		    		String email = resultSet.getString(6);
		    		int rep = resultSet.getInt(7);
		    		Date regDate = resultSet.getDate(8);
		    		Date bornDate = resultSet.getDate(9);
		    		String skypeUser = resultSet.getString(10);
		    		
		    		User u = new User(uid, nickname, passwordHash, name, surname, email, regDate, bornDate, skypeUser);
		    		u.setReputation(rep);
		    		r.add(u);
		    		System.out.println("Added a user");
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
		}
		
		return r;
	}

}
