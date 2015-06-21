package entity;

import java.io.Serializable;

public class Usuario implements Serializable{
	private int id;
	private String nomeDeUsuario;
	private String password;
	private boolean ativo;
	private String expData;

	public Usuario(int id, String nomeDeUsuario, String password, boolean ativo,
			String expData) {
		super();
		this.id = id;
		this.nomeDeUsuario = nomeDeUsuario;
		this.password = password;
		this.ativo = ativo;
		this.expData = expData;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
