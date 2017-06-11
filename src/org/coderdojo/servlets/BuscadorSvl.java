package org.coderdojo.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
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
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int type = 3;//= (Integer) request.getAttribute("type");
		String term = (String) request.getParameter("q");
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
		HttpSession sesion = request.getSession();
		sesion.setAttribute("searchResult", result);
		System.out.println("Searched correctly " + result.toArray());
		response.sendRedirect("searchResult.jsp?term="+term);
	}
	
	private ArrayList<Partida> buscarPartidas(String term){
		ArrayList<Partida> result = new ArrayList<Partida>();
		
		FabricaConexiones f = FabricaConexiones.getFabrica();
    	Connection conn=null;
    	ArrayList<Game> games = buscarJuegos(term);
    	
    	System.out.println("Search Matches - GOT THE GAMES");
    	
    	for(Game g:games){
		try
		{
			conn = f.dameConexion();
			//TODO change the query to select the correct matches
			String queryCheck = "SELECT * from matches WHERE selected_game_id = ?";
	    	PreparedStatement ps = conn.prepareStatement(queryCheck);
	    	ps.setLong(1, g.getId());
	    	ResultSet resultSet = ps.executeQuery();
	    	while (resultSet.next()){
	    		
	    		long id = resultSet.getLong(1);
	    		int maxPlayers = resultSet.getInt(2);
	    		int minPlayers = resultSet.getInt(3);
	    		Date startDate = resultSet.getDate(4);
	    		Date endDate = resultSet.getDate(5);
	    		long ownerId = resultSet.getLong(6);
	    		
	    		Partida p = new Partida(id, maxPlayers, g.getId(), ownerId, minPlayers, startDate, endDate);
	    		result.add(p);
	    		System.out.println("Search Matches - GOT A MATCH");
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
		return result;
		
	}
	
	private ArrayList<User> buscarUsuarios(String term){
		ArrayList<User> result = new ArrayList<User>();
		
		FabricaConexiones f = FabricaConexiones.getFabrica();
    	Connection conn=null;
		try
		{
			conn = f.dameConexion();
			String queryCheck = "SELECT * from users";
	    	PreparedStatement ps = conn.prepareStatement(queryCheck);
	    	
	    	ResultSet resultSet = ps.executeQuery();
	    	while (resultSet.next()){
	    		//TODO Add a tester
	    		int id = resultSet.getInt(1);
	    		String nickname = resultSet.getString(2);
	    		String passwordHash = resultSet.getString(3);
	    		String name = resultSet.getString(4);
	    		String surname = resultSet.getString(5);
	    		String email = resultSet.getString(6);
	    		int rep = resultSet.getInt(7);
	    		Date regDate = resultSet.getDate(8);
	    		Date bornDate = resultSet.getDate(9);
	    		String skypeUser = resultSet.getString(10);
	    		
	    		User p = new User(id, nickname, passwordHash, name, surname, email, regDate, bornDate, skypeUser);
	    		p.setReputation(rep);
	    		if(p.getNickname().toLowerCase().contains(term.toLowerCase())){
	    			result.add(p);
	    			System.out.println("Search Users - GOT A USER");
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
		
		return result;
		
	}
	
	private ArrayList<Game> buscarJuegos(String term){
		ArrayList<Game> result = new ArrayList<Game>();
		
		FabricaConexiones f = FabricaConexiones.getFabrica();
    	Connection conn=null;
		try
		{
			conn = f.dameConexion();
			String queryCheck = "SELECT * from games";
	    	PreparedStatement ps = conn.prepareStatement(queryCheck);
	    	
	    	ResultSet resultSet = ps.executeQuery();
	    	while (resultSet.next()){
	    		//TODO Add a tester
	    		int id = resultSet.getInt(1);
	    		String name = resultSet.getString(2);
	    		int minAge = resultSet.getInt(3);
	    		String imgLink = resultSet.getString(4);
	    		String desc = resultSet.getString(5);
	    		int genreId = resultSet.getInt(6);
	    		
	    		
	    		Game g = new Game(id,name,minAge,imgLink,desc,genreId);
	    		if(g.getName().toLowerCase().contains(term.toLowerCase())){
	    			result.add(g);
	    			System.out.println("Search Games - GOT A GAME");
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
		
		return result;
		
	}

}
