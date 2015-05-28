package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JSeparator;

public class MainFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
					MainFrame frame = new MainFrame();
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
	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 728, 426);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFuncionarios = new JMenu("Funcionarios");
		menuBar.add(mnFuncionarios);
		
		JMenu mnSetores = new JMenu("Setores");
		menuBar.add(mnSetores);
		
		JMenu mnTerminais = new JMenu("Terminais");
		menuBar.add(mnTerminais);
		
		JMenu mnGrupos = new JMenu("Grupos");
		menuBar.add(mnGrupos);
		
		JMenu mnUtilizadores = new JMenu("Utilizadores");
		menuBar.add(mnUtilizadores);
		
		JMenu mnBloqueios = new JMenu("Bloqueios");
		menuBar.add(mnBloqueios);
		
		JMenu mnRegistros = new JMenu("Registros");
		menuBar.add(mnRegistros);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(JSeparator.VERTICAL);
		menuBar.add(separator);
		
		JMenu mnConfiguraes = new JMenu("Configurações");
		menuBar.add(mnConfiguraes);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}

}
