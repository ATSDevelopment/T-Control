package entity;

import java.io.Serializable;

public class Terminal implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String hostname;
	private String ipAddress;
	private boolean ativo;
	private Setor setor;

	public Terminal(int id, String hostname, String ipAddress, boolean ativo,
			Setor setor) {
		super();
		this.id = id;
		this.hostname = hostname;
		this.ipAddress = ipAddress;
		this.ativo = ativo;
		this.setor = setor;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public Setor getSetor() {
		return setor;
	}

	public void setSetor(Setor setor) {
		this.setor = setor;
	}

}
