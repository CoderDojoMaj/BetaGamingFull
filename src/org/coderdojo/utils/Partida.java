package org.coderdojo.utils;

import java.util.Date;

public class Partida extends Buscable{
	
	private int maxPlayers;
	private int gameId;
	private int minPlayers;
	private Date startDate;
	private Date endDate;
	
	public Partida(long id, int maxPlayers, int gameId, int minPlayers, Date startDate, Date endDate) {
		super(id);
		this.maxPlayers = maxPlayers;
		this.gameId = gameId;
		this.minPlayers = minPlayers;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	public Partida(int id, int maxPlayers, int gameId, int minPlayers, long startDate, long endDate) {
		super(id);
		this.maxPlayers = maxPlayers;
		this.gameId = gameId;
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
	
}
