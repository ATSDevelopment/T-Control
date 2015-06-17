package entity;

public class Grupo {
	private int id;
	private String sigla;
	private String nome;
	private boolean ativo;
	
	public Grupo(int id, String sigla, String nome, boolean ativo) {
		super();
		this.id = id;
		this.sigla = sigla;
		this.nome = nome;
		this.ativo = ativo;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSigla() {
		return sigla;
	}
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public boolean getAtivo() {
		return ativo;
	}
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	
}
