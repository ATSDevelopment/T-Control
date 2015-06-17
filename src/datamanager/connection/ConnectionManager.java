package datamanager.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import server.Application;

public class ConnectionManager {
	private static Connection conexao;

	public static boolean open(){
		try {
			Class.forName("com.mysql.jdbc.Driver");

			String server, database, user, pass;
			int port;

			server = Application.getConfig().getDbServer();
			database = Application.getConfig().getDbName();
			user = Application.getConfig().getDbUser();
			pass = Application.getConfig().getDbPass();

			port = Application.getConfig().getDbPort();

			String url = "jdbc:mysql://#server:#port/#database";

			url = url.replaceAll("#server", server);
			url = url.replaceAll("#port", Integer.toString(port));
			url = url.replaceAll("#database", database);

			conexao = DriverManager.getConnection(url, user, pass);
			
			return true;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static Connection get(){
		return conexao;
	}
	
	public static boolean close(){
		try {
			conexao.close();
			
			conexao = null;
			
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return false;
		}
	}
}