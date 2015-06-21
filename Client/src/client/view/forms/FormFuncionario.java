package client.view.forms;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import atsmod.textfield.InputType;
import atsmod.textfield.TitledTextField;

public class FormFuncionario extends JFrame {

	private JPanel contentPane;
	private JPanel panel;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
					FormFuncionario frame = new FormFuncionario();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FormFuncionario() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 778, 418);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.WEST);
		
		JList list = new JList();
		scrollPane.setViewportView(list);
		
		panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		TitledTextField lblNome = new TitledTextField("Nome", InputType.ALL);
		GridBagConstraints gbc_lblNome = new GridBagConstraints();
		gbc_lblNome.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNome.gridwidth = 2;
		gbc_lblNome.insets = new Insets(0, 0, 5, 0);
		gbc_lblNome.gridx = 0;
		gbc_lblNome.gridy = 0;
		panel.add(lblNome, gbc_lblNome);
		
		TitledTextField lblTelefone = new TitledTextField("Telefone", InputType.ALL);
		GridBagConstraints gbc_lblTelefone = new GridBagConstraints();
		gbc_lblTelefone.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblTelefone.insets = new Insets(0, 0, 5, 5);
		gbc_lblTelefone.gridx = 0;
		gbc_lblTelefone.gridy = 1;
		panel.add(lblTelefone, gbc_lblTelefone);
		
		TitledTextField lblEmail = new TitledTextField("Email", InputType.ALL);
		GridBagConstraints gbc_lblEmail = new GridBagConstraints();
		gbc_lblEmail.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblEmail.insets = new Insets(0, 0, 5, 0);
		gbc_lblEmail.gridx = 1;
		gbc_lblEmail.gridy = 1;
		panel.add(lblEmail, gbc_lblEmail);
		
		JLabel lblDataDemisso = new JLabel("Data Demissão: ");
		GridBagConstraints gbc_lblDataDemisso = new GridBagConstraints();
		gbc_lblDataDemisso.anchor = GridBagConstraints.EAST;
		gbc_lblDataDemisso.insets = new Insets(0, 0, 5, 5);
		gbc_lblDataDemisso.gridx = 0;
		gbc_lblDataDemisso.gridy = 2;
		panel.add(lblDataDemisso, gbc_lblDataDemisso);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 2;
		panel.add(textField, gbc_textField);
		textField.setColumns(10);
		
		TitledTextField lblUsurio = new TitledTextField("Usuário", InputType.ALL);
		GridBagConstraints gbc_lblUsurio = new GridBagConstraints();
		gbc_lblUsurio.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblUsurio.insets = new Insets(0, 0, 5, 5);
		gbc_lblUsurio.gridx = 0;
		gbc_lblUsurio.gridy = 3;
		panel.add(lblUsurio, gbc_lblUsurio);
		
		TitledTextField lblSenha = new TitledTextField("Senha", InputType.ALL);
		GridBagConstraints gbc_lblSenha = new GridBagConstraints();
		gbc_lblSenha.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblSenha.insets = new Insets(0, 0, 5, 0);
		gbc_lblSenha.gridx = 1;
		gbc_lblSenha.gridy = 3;
		panel.add(lblSenha, gbc_lblSenha);
		
		JCheckBox chckbxAtivo = new JCheckBox("Ativo");
		GridBagConstraints gbc_chckbxAtivo = new GridBagConstraints();
		gbc_chckbxAtivo.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxAtivo.gridx = 0;
		gbc_chckbxAtivo.gridy = 4;
		panel.add(chckbxAtivo, gbc_chckbxAtivo);
		
		JCheckBox chckbxAdmin = new JCheckBox("Admin");
		GridBagConstraints gbc_chckbxAdmin = new GridBagConstraints();
		gbc_chckbxAdmin.insets = new Insets(0, 0, 5, 0);
		gbc_chckbxAdmin.gridx = 1;
		gbc_chckbxAdmin.gridy = 4;
		panel.add(chckbxAdmin, gbc_chckbxAdmin);
		
		JLabel lblExpiraEm = new JLabel("Expira em: ");
		GridBagConstraints gbc_lblExpiraEm = new GridBagConstraints();
		gbc_lblExpiraEm.anchor = GridBagConstraints.EAST;
		gbc_lblExpiraEm.insets = new Insets(0, 0, 0, 5);
		gbc_lblExpiraEm.gridx = 0;
		gbc_lblExpiraEm.gridy = 5;
		panel.add(lblExpiraEm, gbc_lblExpiraEm);
		
		textField_1 = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 1;
		gbc_textField_1.gridy = 5;
		panel.add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);
	}
}
