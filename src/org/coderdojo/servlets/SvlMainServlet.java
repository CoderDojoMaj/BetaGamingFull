package org.coderdojo.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.coderdojo.bd.FabricaConexiones;
import org.coderdojo.utils.User;

/**
 * Servlet implementation class SvlMainServlet
 */
public class SvlMainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SvlMainServlet() {
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
		
		//Test Log
		System.out.println("Passed - throught MainSvl");
		
		HttpSession laSesion=(HttpSession)request.getSession(false);
		System.out.println("Hola caracola");
		response.getWriter().append("LOADING");
		//Si no hay sesion (no hay logeo) envialo a la pagina principal
		if (laSesion==null)
		{
			System.out.println("Jo, qu� chungo");
			
			laSesion = request.getSession(true); //Create a new session
			
			laSesion.setAttribute("mostFollUsr", getMostFollowedUsers());
			
			response.sendRedirect("inicioNoLogIn.jsp");
		}
		//Si hay una sesi�n disponible (usuario logeado) enviale a el index2
		else
		{
			Enumeration<String> attributes = laSesion.getAttributeNames();
			boolean hasUser = false;
			while(attributes.hasMoreElements()){
				if(attributes.nextElement().equals("user")){
					hasUser=true;
				}
			}
			if(hasUser){
				laSesion.setAttribute("mostFollUsr", getMostFollowedUsers());
				
				response.sendRedirect("welcomePage.jsp");
			}else{
				laSesion = request.getSession(true);
				
				laSesion.setAttribute("mostFollUsr", getMostFollowedUsers());
				
				response.sendRedirect("inicioNoLogIn.jsp");
			}
		}
		
	}
	
	private List<User> getMostFollowedUsers(){
		FabricaConexiones f = FabricaConexiones.getFabrica();
		Connection conn = null;
		ArrayList<User> people = new ArrayList<User>();
		try
		{
			conn = f.dameConexion();
			String queryCheck = "SELECT * FROM users";
			PreparedStatement ps = conn.prepareStatement(queryCheck);
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()){
				long uid = resultSet.getLong(1);
	    		String nickname = resultSet.getString(2);
	    		String passwordHash = resultSet.getString(3);
	    		String name = resultSet.getString(4);
	    		String surname = resultSet.getString(5);
	    		String email = resultSet.getString(6);
	    		int rep = resultSet.getInt(7);
	    		Date regDate = resultSet.getDate(8);
	    		Date bornDate = resultSet.getDate(9);
	    		String skypeUser = resultSet.getString(10);
	    		String desc = resultSet.getString(11);
	    		
	    		User u = new User(uid, nickname, passwordHash, name, surname, email, regDate, bornDate, skypeUser);
	    		u.setReputation(rep);
	    		if(desc != null){
	    			u.setDescription(desc);
	    		}
	    		people.add(u);
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
		
		Collections.sort(people);
		
		//Return the 5 most followed people
		return people.subList(0, 4);
	}

}
