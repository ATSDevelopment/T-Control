package datamanager.dao;

import java.io.Serializable;


public class DataAccessResponse implements Serializable{
	public boolean status;
	public ResponseType type;
	public Serializable response;
	
	public DataAccessResponse(boolean status, ResponseType type, Serializable response) {
		super();
		this.status = status;
		this.type = type;
		this.response = response;
	}
	public boolean getStatus() {
		return status;
	}
	public ResponseType getType() {
		return type;
	}
	public Object getResponse() {
		return response;
	}
}