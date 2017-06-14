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
import org.coderdojo.utils.User;

/**
 * Servlet implementation class LoginSvl
 */
public class LoginSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginSvl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		System.out.print("<html><body><h1> GET REQUEST NOT ACCEPTED</h1></body></html>");
		//doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nick = request.getParameter("usuario");
		String pass = request.getParameter("clave");
		User user = null;
		if(checkUser(nick, pass, user)){
			HttpSession sesion=(HttpSession) request.getSession();
			sesion.setAttribute("user", user);
			response.sendRedirect("welcomePage.jsp");
			System.out.println("LOGGING IN"); //Baia Baia 
			//response.sendRedirect("/BetaGamingServlet/HTMLFiles/LoggedIn.html?sesion=" + sesion.getId());
		}else{
			response.sendRedirect("");
		}
		//response.getWriter().append("Usuario: " + user + "\nClave: " + pass);
	}
	
	private boolean checkUser(String nick, String pass, User user){
		boolean resultado=false;
    	FabricaConexiones f = FabricaConexiones.getFabrica();
    	Connection conn=null;
		try
		{
			conn = f.dameConexion();
			String queryCheck = "SELECT password from users WHERE nickname = ?";
	    	PreparedStatement ps = conn.prepareStatement(queryCheck);
	    	ps.setString(1, nick);
	    	ResultSet resultSet = ps.executeQuery();
	    	while (resultSet.next()){
	    		//TODO Add a tester
	    		String claveBuena=resultSet.getString("password");

	    		if(claveBuena.equals( pass))
	    		{
	    			resultado = true;
	    			queryCheck = "SELECT * from users WHERE nickname = ?";
	    			ps = conn.prepareStatement(queryCheck);
	    			ps.setString(1, nick);
	    			ResultSet resultSetUser = ps.executeQuery();
	    			while (resultSetUser.next()){
	    				//Load the user
	    				
	    				user = new User(resultSetUser.getInt("user_id"), nick, pass, resultSetUser.getString("name"), 
	    						resultSetUser.getString("surname"), resultSetUser.getString("email"), resultSetUser.getDate("registry_date"),
	    						resultSetUser.getDate("born_date"), resultSetUser.getString("skype_user"), resultSetUser.getString("description"));
	    				user.setReputation(resultSetUser.getInt("rep"));

	    			}

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
//		try {
//			File usersDat = new File("/Users/juan/Desktop/coder dojo/Workspace/BetaGamingServlet/WebContent/Data/Users.csv");
//			Scanner usersSc = new Scanner(usersDat);
//			do {
//				String line = usersSc.nextLine();
//				String lineArr[] = line.split(",");
//				System.out.println(line);
//				System.out.println(lineArr);
//				if(user.equals(lineArr[1]) && pass.equals(lineArr[2])){
//					uuid = lineArr[0];
//					r = true;
//				}
//			}while(usersSc.hasNextLine());
//			usersSc.close();
//		}catch(FileNotFoundException e){
//			e.printStackTrace();
//		}
//		String correctUser = "Test";
//		String correctPass = "qwerty";
//		if(user.equals(correctUser) && pass.equals(correctPass)){
//			r = true;
//		}
		return resultado;
	}

}
