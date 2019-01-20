package ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.text.MaskFormatter;

import objetos.Convenio;
import objetos.Paciente;

import com.github.lgooddatepicker.components.*;

import dao.Fabrica;
import interfaces.InterfaceConvenio;
import interfaces.InterfacePaciente;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.ImageIcon;

public class TelaPaciente extends JDialog implements KeyListener {

    /**
     * @author Lucas Felipe Costa
     *
     */
    private static final long serialVersionUID = 1L;

    private final String[] sUF = new String[]{"AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS",
        "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO"};

    private JTextField txtId, txtNome, txtMae, txtPai, txtProfissao, txtEndereco, txtBairro, txtCidade, txtNumero,
            txtTelefone;

    private DatePicker txtDataNasc;
    private JFormattedTextField txtCep;
    private JComboBox cmbConvenio, cmbUf;
    private JLabel lblIdade;

    private Paciente paciente = null;

    private ArrayList<Convenio> convenios = new ArrayList<Convenio>();
    private InterfacePaciente interfacePaciente = Fabrica.criaDAOPaciente();
    private InterfaceConvenio interfaceConvenio = Fabrica.criaDAOConvenio();

    public TelaPaciente() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(TelaPaciente.class.getResource("/imagens/icons8-exame-de-sa\u00FAde-16.png")));
        try {
            convenios = interfaceConvenio.busca("");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Atenção", JOptionPane.INFORMATION_MESSAGE);
        }
        initComponents();
    }

    private void preencherCampos() {
        if (paciente != null) {
            txtId.setText(String.valueOf(paciente.getId()));
            txtNome.setText(paciente.getNome());
            txtNome.setEnabled(true);
            txtNome.requestFocus();
            txtMae.setText(paciente.getMae());
            txtMae.setEnabled(true);
            txtPai.setText(paciente.getPai());
            txtPai.setEnabled(true);
            txtProfissao.setText(paciente.getProfissao());
            txtProfissao.setEnabled(true);
            txtEndereco.setText(paciente.getEndereco());
            txtEndereco.setEnabled(true);
            txtBairro.setText(paciente.getBairro());
            txtBairro.setEnabled(true);
            txtCidade.setText(paciente.getCidade());
            txtCidade.setEnabled(true);
            txtNumero.setText(paciente.getNumero());
            txtNumero.setEnabled(true);
            txtDataNasc.setText(paciente.getDataNascimento());
            txtDataNasc.setEnabled(true);
            txtCep.setText(paciente.getCep());
            txtCep.setEnabled(true);
            txtTelefone.setText(paciente.getTelefone());
            txtTelefone.setEnabled(true);
            cmbConvenio.setSelectedIndex(Convenio.buscarConvenio(convenios, paciente.getConvenio()));
            cmbConvenio.setEnabled(true);
            cmbUf.setSelectedItem(paciente.getUf());
            cmbUf.setEnabled(true);
            getIdade();
        } else {
            txtId.setText("");
            txtNome.setText("");
            txtNome.setEnabled(false);

            txtMae.setText("");
            txtMae.setEnabled(false);
            txtPai.setText("");
            txtPai.setEnabled(false);
            txtProfissao.setText("");
            txtProfissao.setEnabled(false);
            txtEndereco.setText("");
            txtEndereco.setEnabled(false);
            txtBairro.setText("");
            txtBairro.setEnabled(false);
            txtCidade.setText("");
            txtCidade.setEnabled(false);
            txtNumero.setText("");
            txtNumero.setEnabled(false);
            txtDataNasc.setText("");
            txtDataNasc.setEnabled(false);
            txtCep.setText("");
            txtCep.setEnabled(false);
            txtTelefone.setText("");
            txtTelefone.setEnabled(false);
            cmbConvenio.setSelectedIndex(-1);
            cmbConvenio.setEnabled(false);
            cmbUf.setSelectedIndex(12);
            cmbUf.setEnabled(false);
            lblIdade.setText("Idade:");
        }
    }

    private void initComponents() {
        setTitle("Cadastro Pacientes");
        setSize(600, 500);
        setResizable(false);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);

        JLabel lblId = new JLabel("ID");
        lblId.setHorizontalAlignment(SwingConstants.RIGHT);
        lblId.setBounds(10, 27, 75, 14);
        getContentPane().add(lblId);

        txtId = new JTextField();
        txtId.setEnabled(false);
        txtId.setBounds(90, 24, 100, 20);
        txtId.setDisabledTextColor(new Color(120, 120, 120));
        getContentPane().add(txtId);

        JButton btnNovo = new JButton("Novo");
        btnNovo.setIcon(new ImageIcon(TelaPaciente.class.getResource("/imagens/icons8-arquivo-16.png")));
        btnNovo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                paciente = new Paciente();
                preencherCampos();
            }
        });
        btnNovo.setBounds(200, 23, 100, 23);
        getContentPane().add(btnNovo);

        lblIdade = new JLabel("Idade:");
        lblIdade.setHorizontalAlignment(SwingConstants.RIGHT);
        lblIdade.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblIdade.setBounds(308, 27, 242, 14);
        getContentPane().add(lblIdade);

        JLabel lblNome = new JLabel("Nome");
        lblNome.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNome.setBounds(10, 55, 75, 14);
        getContentPane().add(lblNome);

        txtNome = new JTextField();
        txtNome.setName("txtNome");
        txtNome.addKeyListener(this);
        txtNome.setDisabledTextColor(new Color(120, 120, 120));
        txtNome.setBounds(90, 52, 460, 20);
        getContentPane().add(txtNome);

        JLabel lblDataNasc = new JLabel("Data de Nasc.");
        lblDataNasc.setHorizontalAlignment(SwingConstants.RIGHT);
        lblDataNasc.setBounds(10, 83, 75, 14);
        getContentPane().add(lblDataNasc);

        DatePickerSettings dateSettings = new DatePickerSettings();
        dateSettings.setFormatForDatesCommonEra("dd/MM/yyyy");
        dateSettings.setFormatForDatesBeforeCommonEra("dd/MM/uuuu");

        txtDataNasc = new DatePicker(dateSettings);
        txtDataNasc.setName("txtDataNasc");
        txtDataNasc.getComponentDateTextField().addKeyListener(this);
        txtDataNasc.getComponentDateTextField().setName("txtDataNasc");
        txtDataNasc.setBounds(90, 80, 162, 20);
        getContentPane().add(txtDataNasc);

        JLabel lblConvenio = new JLabel("Convenio");
        lblConvenio.setHorizontalAlignment(SwingConstants.RIGHT);
        lblConvenio.setBounds(291, 83, 49, 14);
        getContentPane().add(lblConvenio);

        cmbConvenio = new JComboBox();
        cmbConvenio.setName("cmbConvenio");
        cmbConvenio.setBounds(350, 80, 200, 20);
        cmbConvenio.setModel(new DefaultComboBoxModel<String>(Convenio.arrayConvenio(convenios)));
        getContentPane().add(cmbConvenio);

        JLabel lblEndereco = new JLabel("Endereço");
        lblEndereco.setHorizontalAlignment(SwingConstants.RIGHT);
        lblEndereco.setBounds(10, 111, 75, 14);
        getContentPane().add(lblEndereco);

        txtEndereco = new JTextField();
        txtEndereco.setName("txtEndereco");
        txtEndereco.addKeyListener(this);
        txtEndereco.setDisabledTextColor(new Color(120, 120, 120));
        txtEndereco.setBounds(90, 108, 375, 20);
        getContentPane().add(txtEndereco);

        JLabel lblNumero = new JLabel("Nº");
        lblNumero.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNumero.setBounds(475, 111, 16, 14);
        getContentPane().add(lblNumero);

        txtNumero = new JTextField();
        txtNumero.setName("txtNumero");
        txtNumero.addKeyListener(this);
        txtNumero.setDisabledTextColor(new Color(120, 120, 120));
        txtNumero.setBounds(501, 108, 49, 20);
        getContentPane().add(txtNumero);

        JLabel lblBairro = new JLabel("Bairro");
        lblBairro.setHorizontalAlignment(SwingConstants.RIGHT);
        lblBairro.setBounds(10, 139, 75, 14);
        getContentPane().add(lblBairro);

        txtBairro = new JTextField();
        txtBairro.setName("txtBairro");
        txtBairro.addKeyListener(this);
        txtBairro.setDisabledTextColor(new Color(120, 120, 120));
        txtBairro.setBounds(90, 136, 200, 20);
        getContentPane().add(txtBairro);

        JLabel lblCidade = new JLabel("Cidade");
        lblCidade.setHorizontalAlignment(SwingConstants.RIGHT);
        lblCidade.setBounds(291, 139, 49, 14);
        getContentPane().add(lblCidade);

        txtCidade = new JTextField();
        txtCidade.setName("txtCidade");
        txtCidade.addKeyListener(this);
        txtCidade.setDisabledTextColor(new Color(120, 120, 120));
        txtCidade.setBounds(350, 136, 200, 20);
        getContentPane().add(txtCidade);

        JLabel lblUf = new JLabel("UF");
        lblUf.setHorizontalAlignment(SwingConstants.RIGHT);
        lblUf.setBounds(10, 167, 75, 14);
        getContentPane().add(lblUf);

        cmbUf = new JComboBox();
        cmbUf.setName("cmbUf");
        cmbUf.setModel(new DefaultComboBoxModel(sUF));
        cmbUf.setSelectedIndex(12);
        cmbUf.setBounds(90, 164, 43, 20);
        getContentPane().add(cmbUf);

        JLabel lblCep = new JLabel("CEP");
        lblCep.setHorizontalAlignment(SwingConstants.RIGHT);
        lblCep.setBounds(143, 167, 47, 14);
        getContentPane().add(lblCep);

        MaskFormatter format_txtCep = null;
        try {
            format_txtCep = new MaskFormatter("#####-###");
        } catch (Exception e1) {
        }
        txtCep = new JFormattedTextField(format_txtCep);
        txtCep.setName("txtCep");
        txtCep.setBounds(200, 164, 90, 20);
        txtCep.addKeyListener(this);
        getContentPane().add(txtCep);

        JLabel lblTelefone = new JLabel("Telefone");
        lblTelefone.setHorizontalAlignment(SwingConstants.RIGHT);
        lblTelefone.setBounds(291, 167, 47, 14);
        getContentPane().add(lblTelefone);

        txtTelefone = new JTextField();
        txtTelefone.setName("txtTelefone");
        txtTelefone.addKeyListener(this);
        txtTelefone.setBounds(350, 164, 115, 20);
        getContentPane().add(txtTelefone);

        JLabel lblMae = new JLabel("Mãe");
        lblMae.setHorizontalAlignment(SwingConstants.RIGHT);
        lblMae.setBounds(10, 195, 75, 14);
        getContentPane().add(lblMae);

        txtMae = new JTextField();
        txtMae.setName("txtMae");
        txtMae.addKeyListener(this);
        txtMae.setDisabledTextColor(new Color(120, 120, 120));
        txtMae.setBounds(90, 192, 460, 20);
        getContentPane().add(txtMae);

        JLabel lblPai = new JLabel("Pai");
        lblPai.setHorizontalAlignment(SwingConstants.RIGHT);
        lblPai.setBounds(10, 223, 75, 14);
        getContentPane().add(lblPai);

        txtPai = new JTextField();
        txtPai.setName("txtPai");
        txtPai.addKeyListener(this);
        txtPai.setDisabledTextColor(new Color(120, 120, 120));
        txtPai.setBounds(90, 220, 460, 20);
        getContentPane().add(txtPai);

        JLabel lblProfissao = new JLabel("Profissão");
        lblProfissao.setHorizontalAlignment(SwingConstants.RIGHT);
        lblProfissao.setBounds(10, 251, 75, 14);
        getContentPane().add(lblProfissao);

        txtProfissao = new JTextField();
        txtProfissao.setName("txtProfissao");
        txtProfissao.addKeyListener(this);
        txtProfissao.setDisabledTextColor(new Color(120, 120, 120));
        txtProfissao.setBounds(90, 248, 460, 20);
        getContentPane().add(txtProfissao);

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.setIcon(new ImageIcon(TelaPaciente.class.getResource("/imagens/icons8-salvar-16.png")));
        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    paciente.setNome(txtNome.getText());
                    paciente.setBairro(txtBairro.getText());
                    paciente.setCep(txtCep.getText());
                    paciente.setCidade(txtCidade.getText());
                    paciente.setConvenio(cmbConvenio.getSelectedIndex() < 0 ? 0
                            : convenios.get(cmbConvenio.getSelectedIndex()).getId());
                    paciente.setDataNasc(txtDataNasc.getText());
                    paciente.setEndereco(txtEndereco.getText());
                    paciente.setMae(txtMae.getText());
                    paciente.setNumero(txtNumero.getText());
                    paciente.setPai(txtPai.getText());
                    paciente.setProfissao(txtProfissao.getText());
                    paciente.setTelefone(txtTelefone.getText());
                    paciente.setUf(sUF[cmbUf.getSelectedIndex()]);
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(null, e1.getMessage(), "Atenção", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }

                try {
                    interfacePaciente.cadastra(paciente);
                    preencherCampos();
                    JOptionPane.showMessageDialog(null, "Paciente " + paciente.getNome() + " salvo com sucesso",
                            "Salvo", JOptionPane.PLAIN_MESSAGE);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Erro ao cadastra o Paciente./n/n log:" + e.getMessage(),
                            "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btnSalvar.setBounds(50, 437, 100, 23);
        getContentPane().add(btnSalvar);

        JButton btnExcluir = new JButton("Excluir");
        btnExcluir.setIcon(new ImageIcon(TelaPaciente.class.getResource("/imagens/icons8-excluir-16.png")));
        btnExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {

                if (paciente == null) {
                    JOptionPane.showMessageDialog(null, "Nao possui paciente para excluir", "Atenção",
                            JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                if (JOptionPane.showConfirmDialog(null, "Confirma a exclussão do paciente " + paciente.getNome() + "?",
                        "Confirma", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    try {
                        interfacePaciente.remove(paciente);
                        paciente = null;
                        preencherCampos();
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Erro ao excluir o Paciente./n/n log:" + e.getMessage(), "Erro",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }

            }
        });
        btnExcluir.setBounds(183, 437, 100, 23);
        getContentPane().add(btnExcluir);

        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.setIcon(new ImageIcon(TelaPaciente.class.getResource("/imagens/icons8-pesquisar-16.png")));
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                paciente = null;
                TelaBuscaPacientes p = new TelaBuscaPacientes();
                if (p.retorno() != null) {
                    paciente = (Paciente) p.retorno();
                }
                preencherCampos();
            }
        });
        btnBuscar.setBounds(316, 437, 100, 23);
        getContentPane().add(btnBuscar);

        JButton btnFechar = new JButton("Fechar");
        btnFechar.setIcon(new ImageIcon(TelaPaciente.class.getResource("/imagens/icons8-fechar-janela-16.png")));
        btnFechar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                dispose();
            }
        });
        btnFechar.setBounds(450, 437, 100, 23);
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
        if (component.getName() == txtDataNasc.getName()) {
            getIdade();
        }
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

        if (component.getName() == txtTelefone.getName()) {
            char c = arg0.getKeyChar();
            if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE) || (c == '-')
                    || (c == '(') || (c == ')') || (c == ' '))) {
                arg0.consume();
            }
        }
    }

    public int getIdade() {
        GregorianCalendar nascimento = new GregorianCalendar();
        ;
        GregorianCalendar hj = new GregorianCalendar();
        ;
        SimpleDateFormat df1 = new SimpleDateFormat("dd/MM/yyyy");
        try {
            nascimento.setTime(df1.parse(txtDataNasc.getText()));
        } catch (ParseException e) {
            lblIdade.setText("Idade: ano(s)");
            return 0;
        }

        Calendar c = Calendar.getInstance();
        c.setTime(nascimento.getTime());
        int ultimoDia = c.getActualMaximum(Calendar.DAY_OF_MONTH);

        int anos = hj.get(Calendar.YEAR) - nascimento.get(Calendar.YEAR);
        if (hj.get(Calendar.DAY_OF_YEAR) < nascimento.get(Calendar.DAY_OF_YEAR)) {
            anos--;
        }
        int mes = 12 - nascimento.get(Calendar.MONTH) - hj.get(Calendar.MONTH);

        int dia = mes == 12 ? hj.get(Calendar.DAY_OF_YEAR) - nascimento.get(Calendar.DAY_OF_YEAR)
                : c.getActualMaximum(Calendar.DAY_OF_YEAR)
                - (nascimento.get(Calendar.DAY_OF_YEAR) - hj.get(Calendar.DAY_OF_YEAR));

        String ret = anos + " ano(s)";
        if (anos <= 0) {
            ret = mes + " mes(es)";
            if (dia < ultimoDia) {

                ret = dia + " dia(s)";
            }
        }

        lblIdade.setText("Idade:" + ret);
        return anos;
    }

}
