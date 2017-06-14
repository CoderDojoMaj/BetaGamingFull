package org.coderdojo.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.coderdojo.bd.FabricaConexiones;
import org.coderdojo.utils.DateUtils;
import org.coderdojo.utils.User;

/**
 * Servlet implementation class SvlEditProfile
 */
public class SvlEditProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SvlEditProfile() {
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
		HttpSession sesion = request.getSession(false);
		User oldUser = (User) sesion.getAttribute("user");
		String username = request.getParameter("nickname");
		String opwd = request.getParameter("opwd");
		String npwd = request.getParameter("npwd");
		String name = request.getParameter("name");
		String surname = request.getParameter("surname");
		String email = request.getParameter("mail");
		Date bornDate = DateUtils.fromString(request.getParameter("bornDateLong"));
		String skype = request.getParameter("skype");
		String desc = request.getParameter("desc");
		long id = oldUser.getId();
		boolean hasDesc = (desc != null);
		boolean hasNewPass = (npwd != null);
		boolean updatePass = false;
		boolean updateDesc = false;
		if(hasNewPass){
			if(opwd != null){
				if(opwd.equals(oldUser.getPasswordHash())){
					if(!opwd.equals(npwd)){
						updatePass = true;
					}
				}
			}
		}
		
		if(hasDesc){
			if(desc.equals(oldUser.getRawDescription())){
				updateDesc = true;
			}
		}
		
		FabricaConexiones f = FabricaConexiones.getFabrica();
    	Connection conn=null;
    	
		try {
			conn = f.dameConexion();
			String queryCheck = "UPDATE users SET nickname = ?,name = ?,surname = ?,email = ?,born_date = ?,skype = ? WHERE user_id = ?";
	    	PreparedStatement ps = conn.prepareStatement(queryCheck);
	    	ps.setString(1, username);
	    	ps.setString(2, name);
	    	ps.setString(3, surname);
	    	ps.setString(4, email);
	    	ps.setDate(5, bornDate);
	    	ps.setString(6, skype);
	    	ps.setLong(7, id);

	    	System.out.println("Nickname query prepared");

	    	ResultSet resultSet = ps.executeQuery();

	    	System.out.println("Nickname query executed");

	    	while(resultSet.next()){

	    	}

	    	conn.close();

	    	/*
	    	while (true){
	    		//TODO Add a tester
	    		Object r = resultSet.next();
	    		System.out.println("userInDB loop ->" + r.toString());
	    		if((Boolean)r == false){
	    			break;
	    		}
	    	}*/
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		oldUser.setBornDate(bornDate);
		oldUser.setEmail(email);
		oldUser.setName(name);
		oldUser.setSurname(surname);
		oldUser.setNickname(username);
		oldUser.setSkypeUser(skype);
		if(updateDesc){
			conn = null;
			f = FabricaConexiones.getFabrica();
			try {
				conn = f.dameConexion();
				String queryCheck = "UPDATE users SET description = ? WHERE user_id = ?";
				PreparedStatement ps = conn.prepareStatement(queryCheck);
				ps.setString(1, desc);
				ps.setLong(2, id);

				System.out.println("Nickname query prepared");

				ResultSet resultSet = ps.executeQuery();

				System.out.println("Nickname query executed");

				while(resultSet.next()){

				}

				conn.close();

				/*
	    	while (true){
	    		//TODO Add a tester
	    		Object r = resultSet.next();
	    		System.out.println("userInDB loop ->" + r.toString());
	    		if((Boolean)r == false){
	    			break;
	    		}
	    	}*/
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			oldUser.setDescription(desc);
		}
		if(updatePass){
			conn = null;
			f = FabricaConexiones.getFabrica();
			try {
				conn = f.dameConexion();
				String queryCheck = "UPDATE users SET password = ? WHERE user_id = ?";
				PreparedStatement ps = conn.prepareStatement(queryCheck);
				ps.setString(1, npwd);
				ps.setLong(2, id);

				System.out.println("Nickname query prepared");

				ResultSet resultSet = ps.executeQuery();

				System.out.println("Nickname query executed");

				while(resultSet.next()){

				}

				conn.close();

				/*
	    	while (true){
	    		//TODO Add a tester
	    		Object r = resultSet.next();
	    		System.out.println("userInDB loop ->" + r.toString());
	    		if((Boolean)r == false){
	    			break;
	    		}
	    	}*/
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			oldUser.setPasswordHash(npwd);
		}
	}

}
