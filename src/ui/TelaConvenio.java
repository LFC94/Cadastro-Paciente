package ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import objetos.Convenio;
import dao.Fabrica;
import interfaces.InterfaceConvenio;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.ImageIcon;

public class TelaConvenio extends JDialog implements KeyListener {

    /**
     * @author Lucas Felipe Costa
     *
     */
    private static final long serialVersionUID = 1L;

    private JTextField txtId, txtNome;

    private Convenio convenio = null;

    private InterfaceConvenio interfaceConvenio = Fabrica.criaDAOConvenio();

    public TelaConvenio() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(TelaConvenio.class.getResource("/imagens/icons8-hospital-3-16.png")));
        initComponents();
    }

    private void preencherCampos() {
        if (convenio != null) {
            txtId.setText(String.valueOf(convenio.getId()));
            txtNome.setText(convenio.getNome());
            txtNome.setEnabled(true);
            txtNome.requestFocus();
        } else {
            txtId.setText("");
            txtNome.setText("");
            txtNome.setEnabled(false);
        }
    }

    private void initComponents() {
        setTitle("Cadastro Convenio");
        setSize(600, 178);
        setResizable(false);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);

        JLabel lblId = new JLabel("ID");
        lblId.setHorizontalAlignment(SwingConstants.RIGHT);
        lblId.setBounds(10, 40, 75, 14);
        getContentPane().add(lblId);

        txtId = new JTextField();
        txtId.setEnabled(false);
        txtId.setBounds(90, 37, 100, 20);
        txtId.setDisabledTextColor(new Color(120, 120, 120));
        getContentPane().add(txtId);

        JButton btnNovo = new JButton("Novo");
        btnNovo.setIcon(new ImageIcon(TelaConvenio.class.getResource("/imagens/icons8-arquivo-16.png")));
        btnNovo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                convenio = new Convenio();
                preencherCampos();
            }
        });
        btnNovo.setBounds(200, 36, 100, 23);
        getContentPane().add(btnNovo);

        JLabel lblNome = new JLabel("Nome");
        lblNome.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNome.setBounds(10, 68, 75, 14);
        getContentPane().add(lblNome);

        txtNome = new JTextField();
        txtNome.setName("txtNome");
        txtNome.addKeyListener(this);
        txtNome.setDisabledTextColor(new Color(120, 120, 120));
        txtNome.setBounds(90, 65, 460, 20);
        getContentPane().add(txtNome);

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.setIcon(new ImageIcon(TelaConvenio.class.getResource("/imagens/icons8-salvar-16.png")));
        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    convenio.setNome(txtNome.getText());
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(null, e1.getMessage(), "Atenção", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }

                try {
                    interfaceConvenio.cadastra(convenio);
                    preencherCampos();
                    JOptionPane.showMessageDialog(null, "Convenio " + convenio.getNome() + " salvo com sucesso",
                            "Salvo", JOptionPane.PLAIN_MESSAGE);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Erro ao cadastra o Paciente./n/n log:" + e.getMessage(),
                            "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btnSalvar.setBounds(50, 108, 100, 23);
        getContentPane().add(btnSalvar);

        JButton btnExcluir = new JButton("Excluir");
        btnExcluir.setIcon(new ImageIcon(TelaConvenio.class.getResource("/imagens/icons8-excluir-16.png")));
        btnExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {

                if (convenio == null) {
                    JOptionPane.showMessageDialog(null, "Nao possui convenio para excluir", "Atenção",
                            JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                if (JOptionPane.showConfirmDialog(null, "Confirma a exclusão do convenio " + convenio.getNome() + "?",
                        "Confirma", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    try {
                        interfaceConvenio.remove(convenio);
                        convenio = null;
                        preencherCampos();
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Erro ao excluir o bar./n/n log:" + e.getMessage(), "Erro",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }

            }
        });
        btnExcluir.setBounds(183, 108, 100, 23);
        getContentPane().add(btnExcluir);

        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.setIcon(new ImageIcon(TelaConvenio.class.getResource("/imagens/icons8-pesquisar-16.png")));
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                convenio = null;
                TelaBuscaConvenio c = new TelaBuscaConvenio();
                if (c.retorno() != null) {
                    convenio = (Convenio) c.retorno();
                }
                preencherCampos();
            }
        });
        btnBuscar.setBounds(316, 108, 100, 23);
        getContentPane().add(btnBuscar);

        JButton btnFechar = new JButton("Fechar");
        btnFechar.setIcon(new ImageIcon(TelaConvenio.class.getResource("/imagens/icons8-fechar-janela-16.png")));
        btnFechar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                dispose();
            }
        });
        btnFechar.setBounds(450, 108, 100, 23);
        getContentPane().add(btnFechar);

        preencherCampos();
        setModal(true);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent arg0) {
        Component component = (Component) arg0.getSource();
        if (arg0.getKeyChar() == KeyEvent.VK_ENTER) {
            component.nextFocus();
        }
    }

    @Override
    public void keyReleased(KeyEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyTyped(KeyEvent arg0) {
        Component component = (Component) arg0.getSource();
    }

}
