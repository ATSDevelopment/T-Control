package client.bll;

import server.request.Packet;
import datamanager.BusinessLayoutLayer;
import datamanager.dao.DataAccessResponse;
import entity.funcionario.Funcionario;

public class FuncionarioBLL implements BusinessLayoutLayer<Funcionario>{

	@Override
	public DataAccessResponse salvar(Funcionario entity) {
		Packet p = new Packet("funcionarios:salvar", entity);
		return CM.sendPacket(p);
	}

	@Override
	public DataAccessResponse deletar(Funcionario entity) {
		Packet p = new Packet("funcionarios:deletar", entity);
		return CM.sendPacket(p);
	}

	@Override
	public DataAccessResponse getById(int id) {
		Packet p = new Packet("funcionarios:obter_por_id", id);
		return CM.sendPacket(p);
	}

	@Override
	public DataAccessResponse listar() {
		Packet p = new Packet("funcionarios:listar", null);
		return CM.sendPacket(p);
	}

}
