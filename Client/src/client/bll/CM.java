package client.bll;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import server.request.Packet;
import datamanager.dao.DataAccessResponse;
import datamanager.dao.ResponseType;

public class CM {
	public static DataAccessResponse sendPacket(Packet p){
		try {
			Socket s = new Socket("localhost", 12345);
			
			ObjectOutputStream outputStream = new ObjectOutputStream(s.getOutputStream());
			
			outputStream.writeObject(p);
			
			ObjectInputStream inputStream = new ObjectInputStream(s.getInputStream());
			
			Packet pr = (Packet) inputStream.readObject();
			
			outputStream.close();
			inputStream.close();
			s.close();
			
			return (DataAccessResponse)pr.getObject();
		} catch (Exception e){
			return new DataAccessResponse(false, ResponseType.STRING, "Não foi possível conectar com o servidor!");
		}
		
	}
}
