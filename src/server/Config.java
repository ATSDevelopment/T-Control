package server;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Config implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private static final File dbFile = new File("config.tcontrol");
	
	private String dbServer;
	private String dbName;
	private String dbUser;
	private String dbPass;
	
	private int dbPort;
	
	protected Config(){
		save();
	}
	
	//Methods
	public String getDbServer() {
		return dbServer;
	}

	public void setDbServer(String dbServer) {
		this.dbServer = dbServer;
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public String getDbUser() {
		return dbUser;
	}

	public void setDbUser(String dbUser) {
		this.dbUser = dbUser;
	}

	public String getDbPass() {
		return dbPass;
	}

	public void setDbPass(String dbPass) {
		this.dbPass = dbPass;
	}

	public int getDbPort() {
		return dbPort;
	}

	public void setDbPort(int dbPort) {
		this.dbPort = dbPort;
	}
	
	public void save(){
		try {
			ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(dbFile));
			output.writeObject(this);
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static Config load(){
		try{
			if(dbFile.exists()){
				ObjectInputStream input = new ObjectInputStream(new FileInputStream(dbFile));
				
				Config config = (Config) input.readObject();
				
				input.close();
				
				return config;
			}
		}catch(IOException |ClassNotFoundException e){
			e.printStackTrace();
		}
		
		return null;
	}
}
