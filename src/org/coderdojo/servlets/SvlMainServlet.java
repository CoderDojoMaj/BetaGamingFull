package org.coderdojo.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		System.out.println("Passed throught MainSvl");
		
		HttpSession laSesion=(HttpSession)request.getSession(false);
		
		//Si no hay sesion (no hay logeo) envialo a la pagina principal
		if (laSesion==null)
		{
			response.sendRedirect("index1.html");
		}
		//Si hay una sesi�n disponible (usuario logeado) enviale a el index2
		else
		{
			response.sendRedirect("index2.html");
		}
		
	}

}
