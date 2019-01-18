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
import interfaces.InterfacePaciente;

import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Color;

/**
 * @author lucas
 *
 */
public class Incio extends JFrame {

	private InterfacePaciente interfacePaciente = Fabrica.criaDAO();
	
	public Incio() {
		try {
			new File("banco_de_dados").mkdir();
			interfacePaciente.criarBanco();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao Criar Base de Dados./n/n log:" + e.getMessage(), "Erro",
					JOptionPane.ERROR_MESSAGE);
		}
		initComponents();
	}

	private void initComponents() {
		setTitle("Inicio");
		setSize(600, 500);
		setResizable(false);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(Color.LIGHT_GRAY);
		setJMenuBar(menuBar);

		JMenu mnCadastro = new JMenu("Cadastro");
		mnCadastro.setIcon(new ImageIcon(""));
		menuBar.add(mnCadastro);

		JMenuItem mntmBares = new JMenuItem("Paciente");
		mntmBares.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaPaciente t = new TelaPaciente();				
			}
		});

		mnCadastro.add(mntmBares);
		
		JMenuItem mntmBebidas = new JMenuItem("Convenio");
		mntmBebidas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//TelaBebida t = new TelaBebida();				
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
			public void run() {
				new Incio().setVisible(true);
			}
		});

	}
}
