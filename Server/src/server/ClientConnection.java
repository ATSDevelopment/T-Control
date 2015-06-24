package server;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.Socket;

import server.bll.FuncionarioBLL;
import server.bll.SetorBLL;
import server.bll.TerminalBLL;
import server.request.Packet;
import datamanager.dao.DataAccessResponse;
import datamanager.dao.ResponseType;
import entity.Setor;
import entity.Terminal;
import entity.funcionario.Funcionario;

public class ClientConnection extends Thread{
	private Socket socket;
	public ClientConnection(Socket socket){
		this.socket = socket;
	}
	
	@Override
	public void run(){	
		try {
			Packet res;

			InputStream inputStream = socket.getInputStream();
			
			ObjectInputStream objectIn = new ObjectInputStream(inputStream);
			
			Object objRequest = objectIn.readObject();
			
			if(objRequest instanceof Packet){
				Packet r = (Packet) objRequest;
				
				DataAccessResponse response = proccessRequest(r);
				
				res = new Packet(r.getDescription()+":reply", response);
			}else{
				res = null;
			}
			
			OutputStream outputStream = socket.getOutputStream();
			
			ObjectOutputStream objOut = new ObjectOutputStream(outputStream);
			
			objOut.writeObject(res);
			
			objOut.flush();
			
			objOut.close();
			objectIn.close();
			socket.close();
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private DataAccessResponse proccessRequest(Packet request){
		DataAccessResponse res = null;
		
		switch(request.getDescription()){
		case "funcionarios:salvar":
		{
			Serializable s = request.getObject();
			
			if(s instanceof Funcionario){
				return new FuncionarioBLL().salvar((Funcionario) s); 
			}
		}
		case "funcionarios:deletar":
		{
			Serializable s = request.getObject();
			
			if(s instanceof Funcionario){
				return new FuncionarioBLL().deletar((Funcionario) s); 
			}
		}
		case "funcionarios:listar":
		{
			return new FuncionarioBLL().listar(); 
		}
		case "funcionarios:obter_por_id":
		{}
			break;
		case "setores:salvar":
		{
			Serializable s = request.getObject();
			
			if(s instanceof Setor){
				SetorBLL sbll = new SetorBLL();
				res = sbll.salvar((Setor) s);
			}
		}
			break;
		case "setores:deletar":
		{
			Serializable s = request.getObject();
			
			if(s instanceof Setor){
				SetorBLL sbll = new SetorBLL();
				res = sbll.deletar((Setor) s);
			}
		}
			break;
		case "setores:obter_por_id":
		{
			Serializable s = request.getObject();
			
			if(s instanceof Integer){
				SetorBLL sbll = new SetorBLL();
				res = sbll.getById((Integer) s);
			}
		}
			break;
		case "setores:listar":
		{
			Serializable s = request.getObject();
			
			SetorBLL sbll = new SetorBLL();
			res = sbll.listar();
			
		}
			break;
			
		case "terminais:salvar":
		{
			Serializable s = request.getObject();
			
			if(s instanceof Terminal){
				TerminalBLL tbll = new TerminalBLL();
				res = tbll.salvar((Terminal) s);
			}
		}
			break;
		case "terminais:deletar":
		{
			Serializable s = request.getObject();
			
			if(s instanceof Terminal){
				TerminalBLL tbll = new TerminalBLL();
				res = tbll.deletar((Terminal) s);
			}
		}
			break;
		case "terminais:obter_por_id":
		{
			Serializable s = request.getObject();
			
			if(s instanceof Integer){
				TerminalBLL tbll = new TerminalBLL();
				res = tbll.getById((Integer) s);
			}
		}
			break;
		case "terminais:listar":
		{
			Serializable s = request.getObject();
			
			TerminalBLL tbll = new TerminalBLL();
			res = tbll.listar();
			
		}
			break;
		default:
			res = new DataAccessResponse(false, ResponseType.STRING, "Impossível analisar a requisição");
			break;
		}
		
		return res;
	}
}
