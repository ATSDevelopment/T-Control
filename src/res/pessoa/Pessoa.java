package res.pessoa;

public class Pessoa {
	private int id;
	private String pessoa;
	public Pessoa(int id, String pessoa) {
		super();
		this.id = id;
		this.pessoa = pessoa;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPessoa() {
		return pessoa;
	}
	public void setPessoa(String pessoa) {
		this.pessoa = pessoa;
	}
}
