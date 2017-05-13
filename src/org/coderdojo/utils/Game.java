package org.coderdojo.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import org.coderdojo.bd.FabricaConexiones;

public class Game extends Buscable{
	
	String name;
	int minAge;
	String imgLink;
	String description;
	int genreId;
	
	public Game(long id, String name, int minAge, String imgLink, String description, int genreId) {
		super(id);
		this.name = name;
		this.minAge = minAge;
		this.imgLink = imgLink;
		this.description = description;
		this.genreId = genreId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMinAge() {
		return minAge;
	}

	public void setMinAge(int minAge) {
		this.minAge = minAge;
	}

	public String getImgLink() {
		return imgLink;
	}

	public void setImgLink(String imgLink) {
		this.imgLink = imgLink;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getId() {
		return id;
	}
	
	@Override
	public int getType() {
		return 2;
	}

	@Override
	public String getDisplayName() {
		return name;
	}

	@Override
	public boolean insertInDB() {
		
		
		
		FabricaConexiones f = FabricaConexiones.getFabrica();
    	Connection conn=null;
    	boolean result = false;
		try
		{
			conn = f.dameConexion();
			String queryCheck = "INSERT INTO games(game_name, min_age, img_url, game_desc, genre_id) values(?,?,?,?,?)";
	    	PreparedStatement ps = conn.prepareStatement(queryCheck);
	    	ps.setString(1, name);
	    	ps.setInt(2, minAge);
	    	ps.setString(3, imgLink);
	    	ps.setString(4, description);
	    	ps.setInt(5, genreId);
	    	ps.executeQuery();
	    	
	    	result = true;
//	    	while (resultSet.next()){
//	    		//TODO Add a tester
//	    		int id = resultSet.getInt(0);
//	    		String name = resultSet.getString(1);
//	    		int minAge = resultSet.getInt(2);
//	    		String imgLink = resultSet.getString(3);
//	    		String desc = resultSet.getString(4);
//	    		long genreId = resultSet.getLong(5);
//	    		
//	    		
//	    	}
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
