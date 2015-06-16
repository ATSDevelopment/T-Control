package app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	private static Connection conexao;
	
	public Connection getConnection(){
		if(conexao!=null){
			return conexao;
		}else{
			return conexao = generateConnection();
		}
	}
	
	public Connection generateConnection(){
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

			return DriverManager.getConnection(url, user, pass);

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
}