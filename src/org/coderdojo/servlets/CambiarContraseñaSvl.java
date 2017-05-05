package org.coderdojo.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.coderdojo.bd.FabricaConexiones;


public class CambiarContraseñaSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public CambiarContraseñaSvl() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1 pedimos la fabrica
		FabricaConexiones laFabria=FabricaConexiones.getFabrica();
		//pedimios una conexi�n
		try {
			Connection conexion=laFabria.dameConexion();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Listo para usar la conexion a la DB
		
		//Get The user email
		String targetEmail = request.getParameter("targetEmail");
		String oldPass = request.getParameter("oldPass");
		String newPass = request.getParameter("newPass");
		
		
	}

}
