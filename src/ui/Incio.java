/**
 * 
 */
package ui;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import dao.Fabrica;
import interfaces.InterfaceConvenio;
import interfaces.InterfacePaciente;

import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JLabel;

/**
 * @author lucas
 *
 */
public class Incio extends JFrame {

	private InterfacePaciente interfacePaciente = Fabrica.criaDAOPaciente();
	private InterfaceConvenio interfaceConvenio = Fabrica.criaDAOConvenio();
	
	public Incio() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		try {
			new File("banco_de_dados").mkdir();
			interfacePaciente.criarBanco();
			interfaceConvenio.criarBanco();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao Criar Base de Dados./n/n log:" + e.getMessage(), "Erro",
					JOptionPane.ERROR_MESSAGE);
		}
		initComponents();
	}

	private void initComponents() {
		setTitle("Inicio");
		setSize(700, 600);
		setResizable(false);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setIcon(new ImageIcon(Incio.class.getResource("/imagens/LFC (2).png")));
		lblNewLabel.setBounds(5, 11, 684, 528);
		getContentPane().add(lblNewLabel);

		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(Color.LIGHT_GRAY);
		setJMenuBar(menuBar);

		JMenu mnCadastro = new JMenu("Cadastro");
		mnCadastro.setIcon(new ImageIcon(""));
		menuBar.add(mnCadastro);

		JMenuItem mntmBares = new JMenuItem("Paciente");
		mntmBares.setIcon(new ImageIcon(Incio.class.getResource("/imagens/icons8-exame-de-sa\u00FAde-16.png")));
		mntmBares.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				TelaPaciente t = new TelaPaciente();				
			}
		});

		mnCadastro.add(mntmBares);
		
		JMenuItem mntmBebidas = new JMenuItem("Convenio");
		mntmBebidas.setIcon(new ImageIcon(Incio.class.getResource("/imagens/icons8-hospital-3-16.png")));
		mntmBebidas.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				TelaConvenio t = new TelaConvenio();			
			}
		});
		mnCadastro.add(mntmBebidas);

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Windows".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(Incio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(Incio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(Incio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(Incio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		java.awt.EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				new Incio().setVisible(true);
			}
		});

	}
}
