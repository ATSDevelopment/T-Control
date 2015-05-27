package res.pessoa;

import java.util.ArrayList;

import res.general.EntityManager;

public class PessoaManager implements EntityManager<Pessoa>{
	@Override
	public int salvar(Pessoa entity) {
		return 0;
	}

	@Override
	public int deletar(Pessoa entity) {
		return 0;
	}

	@Override
	public Pessoa getById(int id) {
		return null;
	}

	@Override
	public ArrayList<Pessoa> listar() {
		return null;
	}

	@Override
	public ArrayList<Pessoa> listarWhere(String key, String value) {
		return null;
	}
}
