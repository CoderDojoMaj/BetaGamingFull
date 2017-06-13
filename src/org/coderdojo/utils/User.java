package org.coderdojo.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.coderdojo.bd.FabricaConexiones;

public class User  extends Buscable implements Comparable<User>{
	
	private String nickname;
	private String passwordHash;
	private String name;
	private String surname;
	private String email;
	private int reputation;
	private Date registerDate;
	private Date bornDate;
	private String skypeUser;
	private String description;
	
	public User(long id, String nickname, String passwordHash, String name, String surnames, String email,
			Date registerDate, Date bornDate, String skypeUser) {
		super(id);
		this.reputation = 0;
		this.nickname = nickname;
		this.passwordHash = passwordHash;
		this.name = name;
		this.surname = surnames;
		this.email = email;
		this.registerDate = registerDate;
		this.bornDate = bornDate;
		this.skypeUser = skypeUser;
		this.description=null;
	}
	
	public User(long id, String nickname, String passwordHash, String name, String surnames, String email,
			long registerLong, long bornLong, String skypeUser) {
		super(id);
		this.reputation = 0;
		this.nickname = nickname;
		this.passwordHash = passwordHash;
		this.name = name;
		this.surname = surnames;
		this.email = email;
		this.registerDate = new Date(registerLong);
		this.bornDate = new Date(bornLong);
		this.skypeUser = skypeUser;
		this.description=null;
	}
	
	public User(long id, String nickname, String passwordHash, String name, String surnames, String email,
			Date registerDate, Date bornDate, String skypeUser, String description) {
		super(id);
		this.reputation = 0;
		this.nickname = nickname;
		this.passwordHash = passwordHash;
		this.name = name;
		this.surname = surnames;
		this.email = email;
		this.registerDate = registerDate;
		this.bornDate = bornDate;
		this.skypeUser = skypeUser;
		this.description=description;
	}
	
	public User(long id, String nickname, String passwordHash, String name, String surnames, String email,
			long registerLong, long bornLong, String skypeUser, String description) {
		super(id);
		this.reputation = 0;
		this.nickname = nickname;
		this.passwordHash = passwordHash;
		this.name = name;
		this.surname = surnames;
		this.email = email;
		this.registerDate = new Date(registerLong);
		this.bornDate = new Date(bornLong);
		this.skypeUser = skypeUser;
		this.description=description;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getReputation() {
		return reputation;
	}

	public void setReputation(int reputation) {
		this.reputation = reputation;
	}

	public Date getBornDate() {
		return bornDate;
	}
	
	public long getBornDateLong() {
		return bornDate.getTime();
	}

	public void setBornDate(Date bornDate) {
		this.bornDate = bornDate;
	}
	
	public void setBornDate(long bornDate) {
		this.bornDate = new Date(bornDate);
	}

	public String getSkypeUser() {
		return skypeUser;
	}

	public void setSkypeUser(String skypeUser) {
		this.skypeUser = skypeUser;
	}

	public long getId() {
		return id;
	}

	public Date getRegisterDate() {
		return registerDate;
	}
	
	public long getRegisterDateLong() {
		return registerDate.getTime();
	}
	
	public String getDescription() {
		if(description == null){
			return "This user hasn't got a description";
		}else{
			return description;
		}
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int getType() {
		return 3;
	}
	
	@Override
	public String getDisplayName() {
		return nickname;
	}
	
	public int getFollowers() {
		FabricaConexiones f = FabricaConexiones.getFabrica();
		Connection conn = null;
		int followerCount = 0;
		try
		{
			conn = f.dameConexion();
			String queryCheck = "SELECT follower_user_id FROM friend_list WHERE followed_user_id=?";
			PreparedStatement ps = conn.prepareStatement(queryCheck);
			ps.setLong(1, id);
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()){
				
	    		followerCount++;
				
	    		System.out.println("Added a follower");
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
		return followerCount;
	}
	
	public int compareTo(User compareUser) {

		int compareQuantity = ((User) compareUser).getFollowers();

		//ascending order
		//return this.quantity - compareQuantity;

		//descending order
		return compareQuantity - this.getFollowers();

	}
	
}