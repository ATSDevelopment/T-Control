package server;

import datamanager.connection.Config;
import entity.Setor;

public class Launcher {
	
	private static Config config;
	
	public static void main(String[] args) {
		Setor s = new Setor(0, "", "");
		System.out.println(s);
	}
	
	public static Config getConfig(){
		return config;
	}
}