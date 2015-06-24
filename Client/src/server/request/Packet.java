package server.request;

import java.io.Serializable;


public class Packet implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String description;
	private Serializable object;
	
	public Packet(String description, Serializable object) {
		this.description = description;
		this.object = object;
	}
	public String getDescription() {
		return description;
	}
	public Serializable getObject() {
		return object;
	}
}