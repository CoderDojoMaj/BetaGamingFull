package org.coderdojo.utils;

public class Game extends Buscable{
	
	String name;
	int minAge;
	String imgLink;
	String description;
	
	public Game(long id, String name, int minAge, String imgLink, String description) {
		super(id);
		this.name = name;
		this.minAge = minAge;
		this.imgLink = imgLink;
		this.description = description;
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
	
}
