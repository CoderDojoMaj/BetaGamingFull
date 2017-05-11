package org.coderdojo.utils;

public abstract class Buscable {
	protected long id;

	public Buscable(long id) {
		this.id = id;
	}
	
	public int getType(){
		return 0;
	}
	
	public String getDisplayName(){
		return "un objeto buscable (" + this.toString() + ")";
	}
}
