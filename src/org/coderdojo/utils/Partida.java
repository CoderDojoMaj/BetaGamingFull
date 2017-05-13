package org.coderdojo.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.coderdojo.bd.FabricaConexiones;

public class Partida extends Buscable{
	
	private int maxPlayers;
	private int gameId;
	private long ownerId;
	private int minPlayers;
	private Date startDate;
	private Date endDate;
	
	public Partida(long id, int maxPlayers, int gameId, long ownerId, int minPlayers, Date startDate, Date endDate) {
		super(id);
		this.maxPlayers = maxPlayers;
		this.gameId = gameId;
		this.getOwnerId(ownerId);
		this.minPlayers = minPlayers;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	public Partida(int id, int maxPlayers, int gameId, long ownerId, int minPlayers, long startDate, long endDate) {
		super(id);
		this.maxPlayers = maxPlayers;
		this.gameId = gameId;
		this.getOwnerId(ownerId);
		this.minPlayers = minPlayers;
		this.startDate = new Date(startDate);
		this.endDate = new Date(endDate);
	}

	public int getMaxPlayers() {
		return maxPlayers;
	}

	public void setMaxPlayers(int maxPlayers) {
		this.maxPlayers = maxPlayers;
	}

	public int getGameId() {
		return gameId;
	}

	public void setGameId(int gameId) {
		this.gameId = gameId;
	}

	public int getMinPlayers() {
		return minPlayers;
	}

	public void setMinPlayers(int minPlayers) {
		this.minPlayers = minPlayers;
	}

	public Date getStartDate() {
		return startDate;
	}
	
	public long getStartDateLong() {
		return startDate.getTime();
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	public void setStartDate(long startDate) {
		this.startDate = new Date(startDate);
	}

	public Date getEndDate() {
		return endDate;
	}
	
	public long getEndDateLong() {
		return endDate.getTime();
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	public void setEndDate(long endDate) {
		this.endDate = new Date(endDate);
	}

	public long getId() {
		return id;
	}

	@Override
	public int getType() {
		return 1;
	}

	public long getOwnerId() {
		return ownerId;
	}

	public void getOwnerId(long ownerId) {
		this.ownerId = ownerId;
	}
	
	@Override
	public String getDisplayName(){
		String gameName = "Error (DB DID NOT RESPOND)";
		String ownerNickname = "Error (DB DID NOT RESPOND)";
		
		FabricaConexiones laFabria=FabricaConexiones.getFabrica();
		//pedimios una conexi�n
		try {
			Connection conexion=laFabria.dameConexion();
			//RequestStatement rs;
    		String myQuery = "select nickname from users where user_id = ?";
    		PreparedStatement preStm = conexion.prepareStatement(myQuery);
    		
    		preStm.setLong(0, ownerId);
    		
    		ResultSet result = preStm.executeQuery();
    		while (result.next()){
    			ownerNickname = result.getString("nickname");
    		}
    		
    		myQuery = "select game_name from games where game_id = ?";
    		preStm = conexion.prepareStatement(myQuery);
    		
    		preStm.setInt(0, gameId);
    		
    		result = preStm.executeQuery();
    		while (result.next()){
    			gameName = result.getString("game_name");
    		}
    		conexion.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "Partida de " + gameName + " creada por " + ownerNickname;
	}

	@Override
	public boolean insertInDB() {
		FabricaConexiones f = FabricaConexiones.getFabrica();
    	Connection conn=null;
    	boolean result = false;
		try
		{
			conn = f.dameConexion();
			String queryCheck = "INSERT INTO matches(max_players, min_players, start_date, end_date, owner_id, selected_game_id) values(?,?,?,?,?,?)";
	    	PreparedStatement ps = conn.prepareStatement(queryCheck);
	    	ps.setInt(1, maxPlayers);
	    	ps.setInt(2, minPlayers);
	    	ps.setDate(3, (java.sql.Date) startDate);
	    	ps.setDate(4, (java.sql.Date) endDate);
	    	ps.setLong(5, ownerId);
	    	ps.setLong(5, gameId);
	    	ps.executeQuery();
	    	
	    	result = true;
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
