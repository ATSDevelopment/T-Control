package res.funcionario;

import res.pessoa.Pessoa;

public class Funcionario extends Pessoa{
	private String telefone;
	private String nomeDeUsuario;
	private String password;
	private boolean ativo;
	private String expData;
	public Funcionario(int id, String nome, String telefone,
			String nomeDeUsuario, String password, boolean ativo, String expData) {
		super(id, nome);
		this.telefone = telefone;
		this.nomeDeUsuario = nomeDeUsuario;
		this.password = password;
		this.ativo = ativo;
		this.expData = expData;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getNomeDeUsuario() {
		return nomeDeUsuario;
	}
	public void setNomeDeUsuario(String nomeDeUsuario) {
		this.nomeDeUsuario = nomeDeUsuario;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isAtivo() {
		return ativo;
	}
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	public String getExpData() {
		return expData;
	}
	public void setExpData(String expData) {
		this.expData = expData;
	}
}
