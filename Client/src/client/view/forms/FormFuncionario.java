package client.view.forms;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import atsmod.textfield.InputType;
import atsmod.textfield.TitledTextField;
import entity.Usuario;
import entity.funcionario.Funcionario;
import entity.funcionario.MapaDeServico;

public class FormFuncionario extends AbstractForm<Funcionario>{
	private Funcionario funcionario;

	private JPanel panel;

	private JTextField tfDataDem;
	private JTextField tfDataExp;

	private TitledTextField tfNome;
	private TitledTextField tfTel;
	private TitledTextField tfMail;
	private TitledTextField tfUser;
	private TitledTextField tfSenha;

	private JCheckBox cbxAtivo;
	private JCheckBox cbxAdmin;	

	public FormFuncionario() {			
		GridBagLayout gbl_this = new GridBagLayout();
		gbl_this.columnWidths = new int[]{0, 0, 0};
		gbl_this.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_this.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_this.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		this.setLayout(gbl_this);

		tfNome = new TitledTextField("Nome", InputType.ALL);
		GridBagConstraints gbc_tfNome = new GridBagConstraints();
		gbc_tfNome.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfNome.gridwidth = 2;
		gbc_tfNome.insets = new Insets(0, 0, 5, 0);
		gbc_tfNome.gridx = 0;
		gbc_tfNome.gridy = 0;
		this.add(tfNome, gbc_tfNome);

		tfTel = new TitledTextField("Telefone", InputType.ALL);
		GridBagConstraints gbc_tfTel = new GridBagConstraints();
		gbc_tfTel.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfTel.insets = new Insets(0, 0, 5, 5);
		gbc_tfTel.gridx = 0;
		gbc_tfTel.gridy = 1;
		this.add(tfTel, gbc_tfTel);

		tfMail = new TitledTextField("Email", InputType.ALL);
		GridBagConstraints gbc_tfMail = new GridBagConstraints();
		gbc_tfMail.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfMail.insets = new Insets(0, 0, 5, 0);
		gbc_tfMail.gridx = 1;
		gbc_tfMail.gridy = 1;
		this.add(tfMail, gbc_tfMail);

		JLabel lblDataDemisso = new JLabel("Data Demissão: ");
		GridBagConstraints gbc_lblDataDemisso = new GridBagConstraints();
		gbc_lblDataDemisso.anchor = GridBagConstraints.EAST;
		gbc_lblDataDemisso.insets = new Insets(0, 0, 5, 5);
		gbc_lblDataDemisso.gridx = 0;
		gbc_lblDataDemisso.gridy = 2;
		this.add(lblDataDemisso, gbc_lblDataDemisso);

		tfDataDem = new JTextField();
		GridBagConstraints gbc_tfDataDem = new GridBagConstraints();
		gbc_tfDataDem.insets = new Insets(0, 0, 5, 0);
		gbc_tfDataDem.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfDataDem.gridx = 1;
		gbc_tfDataDem.gridy = 2;
		this.add(tfDataDem, gbc_tfDataDem);
		tfDataDem.setColumns(10);

		tfUser = new TitledTextField("Usuário", InputType.ALL);
		GridBagConstraints gbc_tfUser = new GridBagConstraints();
		gbc_tfUser.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfUser.insets = new Insets(0, 0, 5, 5);
		gbc_tfUser.gridx = 0;
		gbc_tfUser.gridy = 3;
		this.add(tfUser, gbc_tfUser);

		tfSenha = new TitledTextField("Senha", InputType.ALL);
		GridBagConstraints gbc_tfSenha = new GridBagConstraints();
		gbc_tfSenha.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfSenha.insets = new Insets(0, 0, 5, 0);
		gbc_tfSenha.gridx = 1;
		gbc_tfSenha.gridy = 3;
		this.add(tfSenha, gbc_tfSenha);

		cbxAtivo = new JCheckBox("Ativo");
		GridBagConstraints gbc_cbxAtivo = new GridBagConstraints();
		gbc_cbxAtivo.insets = new Insets(0, 0, 5, 5);
		gbc_cbxAtivo.gridx = 0;
		gbc_cbxAtivo.gridy = 4;
		this.add(cbxAtivo, gbc_cbxAtivo);

		cbxAdmin = new JCheckBox("Admin");
		GridBagConstraints gbc_cbxAdmin = new GridBagConstraints();
		gbc_cbxAdmin.insets = new Insets(0, 0, 5, 0);
		gbc_cbxAdmin.gridx = 1;
		gbc_cbxAdmin.gridy = 4;
		this.add(cbxAdmin, gbc_cbxAdmin);

		JLabel lblExpiraEm = new JLabel("Expira em: ");
		GridBagConstraints gbc_lblExpiraEm = new GridBagConstraints();
		gbc_lblExpiraEm.anchor = GridBagConstraints.EAST;
		gbc_lblExpiraEm.insets = new Insets(0, 0, 0, 5);
		gbc_lblExpiraEm.gridx = 0;
		gbc_lblExpiraEm.gridy = 5;
		this.add(lblExpiraEm, gbc_lblExpiraEm);

		tfDataExp = new JTextField();
		GridBagConstraints gbc_tfDataExp = new GridBagConstraints();
		gbc_tfDataExp.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfDataExp.gridx = 1;
		gbc_tfDataExp.gridy = 5;
		this.add(tfDataExp, gbc_tfDataExp);
		tfDataExp.setColumns(10);
	}

	@Override
	public void setObjectEdit(Funcionario f) {
		resetForm();
		
		tfNome.setText(f.getNome());
		tfTel.setText(f.getTelefone());
		tfMail.setText(f.getEmail());
		tfUser.setText(f.getUsuario().getNomeDeUsuario());
		
		tfDataDem.setText(f.getDataSaida());
		tfDataExp.setText(f.getUsuario().getExpData());
		
		cbxAtivo.setSelected(f.getUsuario().isAtivo());
		
		this.funcionario = f;
	}

	@Override
	public Funcionario getEditedObject() {
		String nome = tfNome.getText();
		String telefone = tfTel.getText();
		String email = tfMail.getText();
		String usuario = tfUser.getText();
		String senha = tfSenha.getText() == null ? "":tfSenha.getText();

		String dataDem = tfDataDem.getText();
		dataDem = dataDem.isEmpty() ? null:dataDem;

		String dataExp = tfDataExp.getText();
		dataExp = dataExp.isEmpty() ? null:dataExp;

		boolean ativo = cbxAtivo.isSelected();
		boolean admin = cbxAdmin.isSelected();

		if(funcionario == null){
			Usuario u = new Usuario(0, usuario, senha, ativo, dataExp);

			funcionario = new Funcionario(0, nome, telefone, email, dataDem, u, new ArrayList<MapaDeServico>());
		}else{
			funcionario.setNome(nome);
			funcionario.setTelefone(telefone);
			funcionario.setEmail(email);
			funcionario.getUsuario().setNomeDeUsuario(usuario);
			funcionario.getUsuario().setPassword(senha);
			funcionario.setDataSaida(dataDem);
			funcionario.getUsuario().setAtivo(ativo);
			funcionario.getUsuario().setExpData(dataExp);
		}

		return funcionario;
	}

	@Override
	public void resetForm() {
		tfNome.setText("");
		tfTel.setText("");
		tfMail.setText("");
		tfUser.setText("");
		
		tfDataDem.setText("");
		tfDataExp.setText("");
		
		cbxAtivo.setSelected(false);
	}
}