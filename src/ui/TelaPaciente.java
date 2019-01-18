package ui;

import java.awt.Color;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import objetos.Paciente;



public class TelaPaciente extends JDialog {

	private JTextField txtId, txtNome;
	private JButton btnAdicionar, btnRemover;
	
	private Paciente paciente = null;
	
	public TelaPaciente() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(""));
		initComponents();
	}
	
	private void preencherCampos() {
		if (paciente != null) {
			txtId.setText(String.valueOf(paciente.getId()));
			txtNome.setText(paciente.getNome());
			txtNome.setEnabled(true);
			btnAdicionar.setEnabled(true);
			btnRemover.setEnabled(true);
		} else {
			txtId.setText("");
			txtNome.setText("");
			txtNome.setEnabled(false);
			btnAdicionar.setEnabled(false);
			btnRemover.setEnabled(false);
		}
	}
	
	private void initComponents() {
		setTitle("Cadastro Bares");
		setSize(600, 500);
		setResizable(false);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		JLabel lblId = new JLabel("ID");
		lblId.setHorizontalAlignment(SwingConstants.RIGHT);
		lblId.setBounds(50, 57, 75, 14);
		getContentPane().add(lblId);

		txtId = new JTextField();
		txtId.setEnabled(false);
		txtId.setBounds(130, 54, 100, 20);
		txtId.setDisabledTextColor(new Color(120, 120, 120));
		getContentPane().add(txtId);

		JLabel lblNome = new JLabel("Nome");
		lblNome.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNome.setBounds(50, 85, 75, 14);
		getContentPane().add(lblNome);

		txtNome = new JTextField();
		txtNome.setDisabledTextColor(new Color(120, 120, 120));
		txtNome.setBounds(130, 82, 400, 20);
		getContentPane().add(txtNome);
		
		
		preencherCampos();
		setModal(true);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		repaint();
	}

}
