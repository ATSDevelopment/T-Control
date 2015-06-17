package entity.funcionario;

import java.util.ArrayList;

import entity.Pessoa;
import entity.Usuario;

public class Funcionario extends Pessoa{
	private String telefone;
	private String email;
	private String dataSaida;
	private Usuario usuario;
	private ArrayList<MapaDeServico> mapasDeServico;
	public Funcionario(int id, String nome, String telefone, String email,
			String dataSaida, Usuario usuario,
			ArrayList<MapaDeServico> mapasDeServico) {
		super(id, nome);
		this.telefone = telefone;
		this.email = email;
		this.dataSaida = dataSaida;
		this.usuario = usuario;
		this.mapasDeServico = mapasDeServico;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDataSaida() {
		return dataSaida;
	}
	public void setDataSaida(String dataSaida) {
		this.dataSaida = dataSaida;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public ArrayList<MapaDeServico> getMapasDeServico() {
		return mapasDeServico;
	}
	public void setMapasDeServico(ArrayList<MapaDeServico> mapasDeServico) {
		this.mapasDeServico = mapasDeServico;
	}
}
