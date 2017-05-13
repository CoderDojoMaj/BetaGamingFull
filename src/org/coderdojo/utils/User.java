package org.coderdojo.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

import org.coderdojo.bd.FabricaConexiones;

public class User extends Buscable{
	
	private String nickname;
	private String passwordHash;
	private String name;
	private String surname;
	private String email;
	private int reputation;
	private Date registerDate;
	private Date bornDate;
	private String skypeUser;
	
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
	
	@Override
	public boolean insertInDB()
	{
		try{
		
		FabricaConexiones f = FabricaConexiones.getFabrica();
    	Connection conn;
    	
    	conn = f.dameConexion();
		String queryCheck = "insert into users(nickname, password, name, surname, email, rep, registry_date, born_date, skype_user) values (?,?,?,?,?,0,?,?,?)";
	
    	PreparedStatement ps = conn.prepareStatement(queryCheck);
    	
    	ps.setString(1, this.nickname);
    	ps.setString(2, this.passwordHash);
    	ps.setString(3, this.name);
    	ps.setString(4, this.surname);
    	ps.setString(5, this.email);
    	//6 is reputation
    	ps.setDate(7, (java.sql.Date) this.registerDate);
    	ps.setDate(8, (java.sql.Date) this.bornDate);
    	ps.setString(9, this.skypeUser);
    	
    	System.out.println("Usuario metido en la DB correctamente (supongo)");
    	
    	ResultSet resultSet = ps.executeQuery();
    	conn.close();
    	
		return true;
		
		}
		
		catch(Exception e)
		{	
			e.printStackTrace();
		};
		
		return true;
	}
	
	
	@Override
	public int getType() {
		return 3;
	}
	
	@Override
	public String getDisplayName() {
		return nickname;
	}
	
}
