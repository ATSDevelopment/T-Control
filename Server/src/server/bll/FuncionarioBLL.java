package server.bll;

import server.dao.FuncionarioDAO;
import datamanager.BusinessLayoutLayer;
import datamanager.dao.DataAccessResponse;
import entity.funcionario.Funcionario;

public class FuncionarioBLL implements BusinessLayoutLayer<Funcionario>{
	@Override
	public DataAccessResponse salvar(Funcionario entity) {
		return new FuncionarioDAO().salvar(entity, entity.getId()==0);
	}

	@Override
	public DataAccessResponse deletar(Funcionario entity) {
		return new FuncionarioDAO().deletar(entity);
	}

	@Override
	public DataAccessResponse getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataAccessResponse listar() {
		return new FuncionarioDAO().listar();
	}

}
