package res.setores;

public class Setor {
	private int id;
	private String sigla;
	private String nome;
	
	public Setor(int id, String sigla, String nome) {
		super();
		this.id = id;
		this.sigla = sigla;
		this.nome = nome;
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
	
	
	
	

}
