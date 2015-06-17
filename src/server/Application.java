package server;

import res.pessoa.Pessoa;
import res.pessoa.PessoaDAO;
import datamanager.dao.DataAccessResponse;

public class Application {
	
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
		
		Pessoa p = new Pessoa(1, "Carlos Souza Rodrigues Filho"){};
		
		PessoaDAO dao = new PessoaDAO();
		
		DataAccessResponse response = dao.deletar(p);
		
		System.out.println("Status : "+(response.getStatus() ? "OK":"ERROR"));
		
		System.out.println("Message: "+response.getResponse());
	}
	
	public static Config getConfig(){
		return config;
	}
}