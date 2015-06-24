package client.bll;

import server.request.Packet;
import datamanager.BusinessLayoutLayer;
import datamanager.dao.DataAccessResponse;
import datamanager.dao.ResponseType;
import entity.Terminal;


public class TerminalBLL implements BusinessLayoutLayer<Terminal> {

	@Override
	public DataAccessResponse salvar(Terminal entity) {
		Packet p = new Packet("terminais:salvar", entity);
		Packet pr = CM.sendPacket(p);
		
		if(pr == null){
			return new DataAccessResponse(false, ResponseType.STRING, "O Servidor não retornou dados!");
		}else{
			return (DataAccessResponse)pr.getObject();
		}
	}

	@Override
	public DataAccessResponse deletar(Terminal entity) {
		Packet p = new Packet("terminais:deletar", entity);
		Packet pr = CM.sendPacket(p);
		
		if(pr == null){
			return new DataAccessResponse(false, ResponseType.STRING, "O Servidor não retornou dados!");
		}else{
			return (DataAccessResponse)pr.getObject();
		}
	}

	@Override
	public DataAccessResponse getById(int id) {
		Packet p = new Packet("terminais:obter_por_id", id);
		Packet pr = CM.sendPacket(p);
		
		if(pr == null){
			return new DataAccessResponse(false, ResponseType.STRING, "O Servidor não retornou dados!");
		}else{
			return (DataAccessResponse)pr.getObject();
		}
	}

	@Override
	public DataAccessResponse listar() {
		Packet p = new Packet("terminais:listar", null);
		Packet pr = CM.sendPacket(p);
		
		if(pr == null){
			return new DataAccessResponse(false, ResponseType.STRING, "O Servidor não retornou dados!");
		}else{
			return (DataAccessResponse)pr.getObject();
		}
	}
	

}
