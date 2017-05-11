package org.coderdojo.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.coderdojo.bd.FabricaConexiones;
import org.coderdojo.utils.Buscable;
import org.coderdojo.utils.Game;
import org.coderdojo.utils.Partida;
import org.coderdojo.utils.User;

/**
 * Servlet implementation class BuscadorSvl
 */
public class BuscadorSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuscadorSvl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append(" CANNOT USE GET REQUEST ");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int type = (Integer) request.getAttribute("type");
		String term = (String) request.getAttribute("term");
		HttpSession sesion = request.getSession();
		ArrayList<Buscable> result = new ArrayList<Buscable>();
		switch (type){
			case 0:
				result.addAll(buscarPartidas(term));
				break;
			case 1:
				result.addAll(buscarUsuarios(term));
				break;
			case 2:
				result.addAll(buscarJuegos(term));
				break;
			case 3:
				//May not work
				result.addAll(buscarPartidas(term));
				result.addAll(buscarUsuarios(term));
				result.addAll(buscarJuegos(term));
				break;
			default:
				result = null;
				break;
		}
		sesion.setAttribute("searchResult", result);
	}
	
	private ArrayList<Partida> buscarPartidas(String term){
		ArrayList<Partida> result = new ArrayList<Partida>();
		
		FabricaConexiones f = FabricaConexiones.getFabrica();
    	Connection conn=null;
		try
		{
			conn = f.dameConexion();
			//TODO change the query to select the correct matches
			String queryCheck = "SELECT * from partidas WHERE name = ?";
	    	PreparedStatement ps = conn.prepareStatement(queryCheck);
	    	ps.setString(1, term);
	    	ResultSet resultSet = ps.executeQuery();
	    	while (resultSet.next()){
	    		
	    		long id = resultSet.getLong(0);
	    		int maxPlayers = resultSet.getInt(1);
	    		int minPlayers = resultSet.getInt(2);
	    		Date startDate = resultSet.getDate(3);
	    		Date endDate = resultSet.getDate(4);
	    		long ownerId = resultSet.getLong(5);
	    		int gameId = resultSet.getInt(6);
	    		
	    		Partida p = new Partida(id, maxPlayers, gameId, ownerId, minPlayers, startDate, endDate);
	    		result.add(p);
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
		
		return result;
		
	}
	
	private ArrayList<User> buscarUsuarios(String term){
		ArrayList<User> result = new ArrayList<User>();
		
		FabricaConexiones f = FabricaConexiones.getFabrica();
    	Connection conn=null;
		try
		{
			conn = f.dameConexion();
			String queryCheck = "SELECT * from users WHERE nickname = ?";
	    	PreparedStatement ps = conn.prepareStatement(queryCheck);
	    	ps.setString(1, term);
	    	ResultSet resultSet = ps.executeQuery();
	    	while (resultSet.next()){
	    		//TODO Add a tester
	    		int id = resultSet.getInt(0);
	    		String nickname = resultSet.getString(1);
	    		String passwordHash = resultSet.getString(2);
	    		String name = resultSet.getString(3);
	    		String surname = resultSet.getString(4);
	    		String email = resultSet.getString(5);
	    		Date regDate = resultSet.getDate(6);
	    		Date bornDate = resultSet.getDate(7);
	    		String skypeUser = resultSet.getString(8);
	    		
	    		User p = new User(id, nickname, passwordHash, name, surname, email, regDate, bornDate, skypeUser);
	    		result.add(p);
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
		
		return result;
		
	}
	
	private ArrayList<Game> buscarJuegos(String term){
		ArrayList<Game> result = new ArrayList<Game>();
		
		FabricaConexiones f = FabricaConexiones.getFabrica();
    	Connection conn=null;
		try
		{
			conn = f.dameConexion();
			String queryCheck = "SELECT * from juegos WHERE name = ?";
	    	PreparedStatement ps = conn.prepareStatement(queryCheck);
	    	ps.setString(1, term);
	    	ResultSet resultSet = ps.executeQuery();
	    	while (resultSet.next()){
	    		//TODO Add a tester
	    		int id = resultSet.getInt(0);
	    		String name = resultSet.getString(1);
	    		int minAge = resultSet.getInt(2);
	    		String imgLink = resultSet.getString(3);
	    		String desc = resultSet.getString(4);
	    		long genreId = resultSet.getLong(5);
	    		
	    		
	    		Game g = new Game(id,name,minAge,imgLink,desc,genreId);
	    		result.add(g);
	    		
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
		
		return result;
		
	}

}
