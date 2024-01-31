package Projeto.Front;

import Projeto.Entidades.Cliente;
import Projeto.Repositorio.ClienteRepositorio;
import Projeto.Repositorio.ContaRepositorio;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class TelaCadastro extends JFrame {

    private JTextField campoNome;
    private JTextField campoNomeSocial;
    private JTextField campoCPF;
    private JTextField campoDataNascimento;
    private JPasswordField campoSenha;
    private JTextField campoEmail;
    private JTextField campoEndereco;

    Configuration configuration = new Configuration().configure("persistence.xml");
    SessionFactory sessionFactory = configuration.buildSessionFactory();
    // Criação da sessão do Hibernate
    Session session = sessionFactory.openSession();
    ClienteRepositorio clienteRepositorio = new ClienteRepositorio(session);

    public TelaCadastro() {
        // Config da janela
        setTitle("Tela de Cadastro");
        setSize(400, 300);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Layout
        setLayout(new GridLayout(8, 2));

        // Componentes
        JLabel labelNome = new JLabel("Nome:");
        campoNome = new JTextField();

        JLabel labelNomeSocial = new JLabel("Nome Social:");
        campoNomeSocial = new JTextField();

        JLabel labelCPF = new JLabel("CPF:");
        campoCPF = new JTextField();

        JLabel labelDataNascimento = new JLabel("Data de Nascimento:");
        campoDataNascimento = new JTextField();

        JLabel labelSenha = new JLabel("Senha:");
        campoSenha = new JPasswordField();

        JLabel labelEmail = new JLabel("E-mail:");
        campoEmail = new JTextField();

        JLabel labelEndereco = new JLabel("Endereço:");
        campoEndereco = new JTextField();

        JButton botaoCadastrar = new JButton("Cadastrar");
        JButton botaoVoltar = new JButton("Voltar");

        // componentes da janela
        add(labelNome);
        add(campoNome);
        add(labelNomeSocial);
        add(campoNomeSocial);
        add(labelCPF);
        add(campoCPF);
        add(labelDataNascimento);
        add(campoDataNascimento);
        add(labelSenha);
        add(campoSenha);
        add(labelEmail);
        add(campoEmail);
        add(labelEndereco);
        add(campoEndereco);
        add(botaoCadastrar);
        add(botaoVoltar);

        //botão Cadastrar
        botaoCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarUsuario();
            }
        });

        //botão Voltar
        botaoVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                voltarParaTelaAnterior();
            }
        });
    }

    private void cadastrarUsuario() {
        // cadastrar o usuário
        String nome = campoNome.getText();
        String nomeSocial = campoNomeSocial.getText();
        String cpf = campoCPF.getText();
        LocalDate dataNascimento = LocalDate.now();
        String senha = new String(campoSenha.getPassword());
        String email = campoEmail.getText();
        String endereco = campoEndereco.getText();

        // lógica de cadastro
        Cliente cliente = new Cliente(nome,nomeSocial,dataNascimento,cpf,email,true,endereco,senha);
        clienteRepositorio.salvarCliente(cliente);
        // mensagem
        JOptionPane.showMessageDialog(this, "Usuário cadastrado com sucesso!");
        voltarParaTelaAnterior();
    }

    private void voltarParaTelaAnterior() {
        //voltar para a tela anterior
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TelaLogin().setVisible(true);
            }
        });


        //fechar a janela
        this.dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TelaCadastro().setVisible(true);
            }
        });
    }
}
