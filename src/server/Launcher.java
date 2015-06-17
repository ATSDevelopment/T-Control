package server;

import datamanager.connection.Config;

public class Launcher {
	
	private static Config config;
	
	public static void main(String[] args) {
		Config c = Config.load();
		
		if(c == null){
			c = new Config();
			
			c.setDbServer("localhost");
			c.setDbName("tcontrol");
			c.setDbUser("root");
			c.setDbPass("");
			c.setDbPort(3306);
			
			c.save();
		}
		
		config = c;
	}
	
	public static Config getConfig(){
		return config;
	}
}