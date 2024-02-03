package Projeto.Front;

import Projeto.Entidades.Cliente;
import Projeto.Entidades.Operacao;
import Projeto.Repositorio.ClienteRepositorio;
import Projeto.Repositorio.ContaRepositorio;
import Projeto.Repositorio.OperacaoRepositorio;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaDeposito extends JFrame{
    private JTextField campoValor;
    Configuration configuration = new Configuration().configure("persistence.xml");
    SessionFactory sessionFactory = configuration.buildSessionFactory();
    // Criação da sessão do Hibernate
    Session session = sessionFactory.openSession();
    OperacaoRepositorio operacaoRepositorio = new OperacaoRepositorio(session);

    public TelaDeposito(Cliente cliente, ClienteRepositorio clienteRepositorio, Projeto.Entidades.Conta conta, ContaRepositorio contaRepositorio) {
        // Config da janela
        setTitle("Tela de Depósito");
        setSize(400, 200);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Layout
        setLayout(new GridLayout(3, 2));

        // Componentes
        JLabel labelValor = new JLabel("Valor:");
        campoValor = new JTextField();

        JButton botaoConfirmar = new JButton("Confirmar");
        JButton botaoVoltar = new JButton("Voltar");

        //componentes da janela
        add(labelValor);
        add(campoValor);
        add(botaoConfirmar);
        add(botaoVoltar);

        //botão Confirmar
        botaoConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                realizarOperacao(cliente, clienteRepositorio,conta,contaRepositorio);
            }
        });

        // Configuração do botão Voltar
        botaoVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                voltarParaTelaAnterior(cliente, clienteRepositorio,conta,contaRepositorio);
            }
        });
    }

    private void realizarOperacao(Cliente cliente, ClienteRepositorio clienteRepositorio, Projeto.Entidades.Conta conta, ContaRepositorio contaRepositorio) {
        // realizar depósito ou saque
        try {
            double valor = Double.parseDouble(campoValor.getText());

            if (valor > 0) {
                //lógica de depósito
                conta.Deposito(valor);
                Operacao operacao = new Operacao(conta,valor,"Deposito");
                JOptionPane.showMessageDialog(this, "Depósito de R$ " + valor + " realizado com sucesso!");
                contaRepositorio.atualizarConta(conta);
                operacaoRepositorio.salvarOperacao(operacao);
                voltarParaTelaAnterior(cliente, clienteRepositorio,conta,contaRepositorio);

            } else {
                JOptionPane.showMessageDialog(this, "Por favor, insira um valor válido ou verifique sua operação.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, insira um valor válido.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void voltarParaTelaAnterior(Cliente cliente, ClienteRepositorio clienteRepositorio, Projeto.Entidades.Conta conta, ContaRepositorio contaRepositorio) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Conta(cliente, clienteRepositorio,conta,contaRepositorio).show();
            }
        });

        //  fechar a janela
        this.dispose();
    }

    //public static void main(String[] args) {
    //    SwingUtilities.invokeLater(new Runnable() {
    //        @Override
    //        public void run() {
    //           new TelaDepositoSaque().setVisible(true);
    //       }
    //   });
    // }
}