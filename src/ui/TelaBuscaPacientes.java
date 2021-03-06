/**
 * 
 */
package ui;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.Fabrica;
import interfaces.InterfacePaciente;
import objetos.Paciente;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.JButton;

import java.util.ArrayList;

import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author Lucas Felipe Costa
 *
 */
public class TelaBuscaPacientes extends JDialog {

	private static final long serialVersionUID = 1L;
	private Object retorno = null;
	private JTable tblBusca;
	private JScrollPane scrollPane;

	private JTextField txtPesquisa;
	private InterfacePaciente interfacePaciente = Fabrica.criaDAOPaciente();

	private ArrayList<Paciente> listPacientes;

	private DefaultTableModel model = new DefaultTableModel() {

		@Override
		public boolean isCellEditable(int row, int column) {
			// all cells false
			return false;
		}
	};

	public TelaBuscaPacientes() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaBuscaPacientes.class.getResource("/imagens/icons8-exame-de-sa\u00FAde-busca-16.png")));
		initComponents();
	}

	private void initComponents() {
		setTitle("Buscar Pacientes");
		setSize(610, 510);
		setResizable(false);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		scrollPane = new JScrollPane();
		tblBusca = new JTable();
		tblBusca.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyChar() == KeyEvent.VK_ENTER) {
					if (tblBusca.getSelectedRow() != -1) {
						retorno = listPacientes.get(tblBusca.getSelectedRow());
					}
					dispose();
				}
			}
		});
		tblBusca.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2 && !e.isConsumed()) {
					if (tblBusca.getSelectedRow() != -1) {
						retorno = listPacientes.get(tblBusca.getSelectedRow());
					}
					dispose();
				}
			}
		});
		tblBusca.setRowSelectionAllowed(true);
		// tblBusca.setBounds(0, 0, 594, 430);
		getContentPane().add(scrollPane);

		scrollPane.setBounds(5, 0, 594, 402);
		scrollPane.setViewportView(tblBusca);

		tblBusca.setModel(model);

		model.addColumn("Id");
		model.addColumn("Nome");
		model.addColumn("Nome Mãe");

		tblBusca.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tblBusca.getColumnModel().getColumn(0).setPreferredWidth(50);
		tblBusca.getColumnModel().getColumn(1).setPreferredWidth(320);
		tblBusca.getColumnModel().getColumn(2).setPreferredWidth(200);

		JLabel lblPesquisar = new JLabel("Pesquisar");
		lblPesquisar.setBounds(10, 417, 46, 14);
		getContentPane().add(lblPesquisar);

		txtPesquisa = new JTextField();
		txtPesquisa.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyChar() == KeyEvent.VK_ENTER) {
					buscar();
				}
			}
		});
		txtPesquisa.setBounds(63, 414, 422, 20);
		getContentPane().add(txtPesquisa);
		txtPesquisa.setColumns(10);

		JButton button = new JButton("Buscar");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				buscar();
			}
		});
		button.setIcon(new ImageIcon(TelaBuscaPacientes.class.getResource("/imagens/icons8-filtro-16.png")));
		button.setBounds(490, 413, 100, 23);
		getContentPane().add(button);

		JButton button_1 = new JButton("Abrir");
		button_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (tblBusca.getSelectedRow() != -1) {
					retorno = listPacientes.get(tblBusca.getSelectedRow());
				}
				dispose();
			}
		});
		button_1.setIcon(new ImageIcon(TelaBuscaPacientes.class.getResource("/imagens/icons8-selecionado-16.png")));
		button_1.setBounds(385, 442, 100, 23);
		getContentPane().add(button_1);

		JButton button_2 = new JButton("Fechar");
		button_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		button_2.setIcon(new ImageIcon(TelaBuscaPacientes.class.getResource("/imagens/icons8-fechar-janela-16.png")));
		button_2.setBounds(490, 442, 100, 23);
		getContentPane().add(button_2);

		setModal(true);
		setVisible(true);
		setModalExclusionType(java.awt.Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		repaint();
		
		buscar();
	}

	private void buscar() {
		if (listPacientes != null)
			for (int x = listPacientes.size(); x > 0; x--) {
				model.removeRow(x - 1);
			}
		try {
			listPacientes = interfacePaciente.busca(txtPesquisa.getText().toUpperCase().trim());
			for (Paciente bar : listPacientes) {
				Object[] linha = new Object[] { bar.getId(), bar.getNome(), bar.getMae() };
				model.addRow(linha);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao buscar os Pacientes. \n\nLog: " + e.getMessage(), "Erro",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public Object retorno() {
		return retorno;
	}
}
