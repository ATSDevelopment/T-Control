package client.bll;

import server.request.Packet;
import datamanager.BusinessLayoutLayer;
import datamanager.dao.DataAccessResponse;
import datamanager.dao.ResponseType;
import entity.funcionario.Funcionario;

public class FuncionarioBLL implements BusinessLayoutLayer<Funcionario>{

	@Override
	public DataAccessResponse salvar(Funcionario entity) {
		Packet p = new Packet("funcionarios:salvar", entity);
		Packet pr = CM.sendPacket(p);
		
		if(pr == null){
			return new DataAccessResponse(false, ResponseType.STRING, "O Servidor não retornou dados!");
		}else{
			return (DataAccessResponse)pr.getObject();
		}
	}

	@Override
	public DataAccessResponse deletar(Funcionario entity) {
		Packet p = new Packet("funcionarios:deletar", entity);
		Packet pr = CM.sendPacket(p);
		
		if(pr == null){
			return new DataAccessResponse(false, ResponseType.STRING, "O Servidor não retornou dados!");
		}else{
			return (DataAccessResponse)pr.getObject();
		}
	}

	@Override
	public DataAccessResponse getById(int id) {
		Packet p = new Packet("funcionarios:obter_por_id", id);
		Packet pr = CM.sendPacket(p);
		
		if(pr == null){
			return new DataAccessResponse(false, ResponseType.STRING, "O Servidor não retornou dados!");
		}else{
			return (DataAccessResponse)pr.getObject();
		}
	}

	@Override
	public DataAccessResponse listar() {
		Packet p = new Packet("funcionarios:listar", null);
		Packet pr = CM.sendPacket(p);
		
		if(pr == null){
			return new DataAccessResponse(false, ResponseType.STRING, "O Servidor não retornou dados!");
		}else{
			return (DataAccessResponse)pr.getObject();
		}
	}

}
