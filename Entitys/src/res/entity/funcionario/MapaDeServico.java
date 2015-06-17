package res.entity.funcionario;

import java.util.ArrayList;
import java.util.Calendar;

import res.entity.DiasServico;

public class MapaDeServico {
	private int idFuncionario;
	private int idSetor;
	private ArrayList<DiasServico> dias;
	private Calendar horarioEntrada;
	private Calendar horarioSaida;
	public int getIdFuncionario() {
		return idFuncionario;
	}
	public void setIdFuncionario(int idFuncionario) {
		this.idFuncionario = idFuncionario;
	}
	public int getIdSetor() {
		return idSetor;
	}
	public void setIdSetor(int idSetor) {
		this.idSetor = idSetor;
	}
	public ArrayList<DiasServico> getDias() {
		return dias;
	}
	public void setDias(ArrayList<DiasServico> dias) {
		this.dias = dias;
	}
	public Calendar getHorarioEntrada() {
		return horarioEntrada;
	}
	public void setHorarioEntrada(Calendar horarioEntrada) {
		this.horarioEntrada = horarioEntrada;
	}
	public Calendar getHorarioSaida() {
		return horarioSaida;
	}
	public void setHorarioSaida(Calendar horarioSaida) {
		this.horarioSaida = horarioSaida;
	}
}
