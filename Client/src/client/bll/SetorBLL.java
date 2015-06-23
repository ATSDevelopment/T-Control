package client.bll;

import java.io.Serializable;

import server.request.Packet;
import datamanager.BusinessLayoutLayer;
import datamanager.dao.DataAccessResponse;
import datamanager.dao.ResponseType;
import entity.Setor;

public class SetorBLL implements BusinessLayoutLayer<Setor>{

	@Override
	public DataAccessResponse salvar(Setor entity) {
		Packet p = new Packet("setores:salvar", entity);
		Packet pr = CM.sendPacket(p);
		
		if(pr == null){
			return new DataAccessResponse(false, ResponseType.STRING, "O Servidor não retornou dados!");
		}else{
			return (DataAccessResponse)pr.getObject();
		}
	}

	@Override
	public DataAccessResponse deletar(Setor entity) {
		Packet p = new Packet("setores:deletar", entity);
		Packet pr = CM.sendPacket(p);
		
		if(pr == null){
			return new DataAccessResponse(false, ResponseType.STRING, "O Servidor não retornou dados!");
		}else{
			return (DataAccessResponse)pr.getObject();
		}
	}

	@Override
	public DataAccessResponse getById(int id) {
		Packet p = new Packet("setores:obter_por_id", id);
		Packet pr = CM.sendPacket(p);
		
		if(pr == null){
			return new DataAccessResponse(false, ResponseType.STRING, "O Servidor não retornou dados!");
		}else{
			return (DataAccessResponse)pr.getObject();
		}
	}

	@Override
	public DataAccessResponse listar() {
		Packet p = new Packet("setores:listar", null);
		Packet pr = CM.sendPacket(p);
		
		if(pr == null){
			return new DataAccessResponse(false, ResponseType.STRING, "O Servidor não retornou dados!");
		}else{
			return (DataAccessResponse)pr.getObject();
		}
	}
}
