package org.coderdojo.servlets;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginSvl
 */
public class LoginSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String uuid;
       
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
		System.out.print("Incorrenct way of login");
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user = request.getParameter("usuario");
		String pass = request.getParameter("clave");
		HttpSession sesion=(HttpSession) request.getSession();
		if(checkUser(user, pass)){
			sesion.setAttribute("uuid", uuid);
			sesion.setAttribute("usuario",user);
			sesion.setAttribute("clave", pass);
			sesion.setAttribute("loggedIn", true);
			response.sendRedirect("loginSuccess.html");
			System.out.println("LOGGING IN");
			//response.sendRedirect("/BetaGamingServlet/HTMLFiles/LoggedIn.html?sesion=" + sesion.getId());
		}else{
			response.sendRedirect("loginError.html");
		}
		//response.getWriter().append("Usuario: " + user + "\nClave: " + pass);
	}
	
	private boolean checkUser(String user, String pass){
		Boolean r=false;
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
		return r;
	}

}
