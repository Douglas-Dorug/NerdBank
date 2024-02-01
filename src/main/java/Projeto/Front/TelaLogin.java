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

public class TelaLogin extends JFrame {

    private JTextField campoLogin;
    private JPasswordField campoSenha;
    Configuration configuration = new Configuration().configure("persistence.xml");
    SessionFactory sessionFactory = configuration.buildSessionFactory();
    // Criação da sessão do Hibernate
    Session session = sessionFactory.openSession();
    ContaRepositorio contaRepositorio = new ContaRepositorio(session);
    ClienteRepositorio clienteRepositorio = new ClienteRepositorio(session);

    public TelaLogin() {
        // Config da janela
        setTitle("Tela de Login");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Layout
        setLayout(new GridLayout(3, 2));

        // Componentes
        JLabel labelLogin = new JLabel("Login: CPF");
        campoLogin = new JTextField();

        JLabel labelSenha = new JLabel("Senha:");
        campoSenha = new JPasswordField();

        JButton botaoCadastrar = new JButton("Cadastrar");
        JButton botaoEntrar = new JButton("Entrar");

        // adicionando componentes da janela
        add(labelLogin);
        add(campoLogin);
        add(labelSenha);
        add(campoSenha);
        add(botaoCadastrar);
        add(botaoEntrar);

        //botão Cadastrar
        botaoCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarUsuario();
            }
        });

        //botão Entrar
        botaoEntrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                autenticarUsuario();
            }
        });
    }

    private void cadastrarUsuario() {
        //cadastrar usuário
        String login = campoLogin.getText();
        String senha = new String(campoSenha.getPassword());
        //fechar tela login
        this.dispose();
        //cadastro aqui
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TelaCadastro().setVisible(true);
            }
        });

    }

    private void autenticarUsuario() {
        try{
            // autenticar usuário
            String login = campoLogin.getText();
            String senha = new String(campoSenha.getPassword());
            //messagem
            Cliente cliente = clienteRepositorio.buscarClientePorCpf(login);
            if (cliente != null){
                if (cliente.getCPF().equals(login) && cliente.getSenhaAcesso().equals(senha)){
                    JOptionPane.showMessageDialog(this, "Usuário autenticado com sucesso!");
                    // fechar e abrir minhas contas
                    this.dispose();
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            new MinhasContas(cliente,clienteRepositorio, contaRepositorio).show();
                        }
                    });
                }
                else {
                    JOptionPane.showMessageDialog(this, "Usuário não cadastrado ou credenciais incorretas, confira os dados e tente novamente.");
                }
            }
        } catch (Exception e){
            System.out.println(e);
            JOptionPane.showMessageDialog(this, "Usuário não cadastrado ou credenciais incorretas, confira os dados e tente novamente.");
        }

    }




    }

