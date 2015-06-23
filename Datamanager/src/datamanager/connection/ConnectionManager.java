package datamanager.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
	private static Connection open(){
		try {
			Class.forName("com.mysql.jdbc.Driver");

			String server, database, user, pass;
			int port;

			server = Config.getInstance().getDbServer();
			database = Config.getInstance().getDbName();
			user = Config.getInstance().getDbUser();
			pass = Config.getInstance().getDbPass();

			port = Config.getInstance().getDbPort();

			String url = "jdbc:mysql://#server:#port/#database";

			url = url.replaceAll("#server", server);
			url = url.replaceAll("#port", Integer.toString(port));
			url = url.replaceAll("#database", database);

			Connection conexao = DriverManager.getConnection(url, user, pass);
			
			return conexao;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static Connection get(){
		return open();
	}
}