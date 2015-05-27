package app;

import java.sql.Connection;
import java.sql.SQLException;


public class Application {
	private static Config config;
	public static void main(String[] args) {
		Config c = Config.load();
		
		if(c == null){
			c = new Config();
			
			c.setDbServer("localhost");
			c.setDbName("tcontrol");
			c.setDbUser("root");
			c.setDbPass("Csrf052400");
			c.setDbPort(3306);
			
			c.save();
		}
		
		config = c;
		
		ConnectionFactory con = new ConnectionFactory();
		Connection co = con.getConnection();
		System.out.println(co);
		
		try {
			co.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Config getConfig(){
		return config;
	}
}
