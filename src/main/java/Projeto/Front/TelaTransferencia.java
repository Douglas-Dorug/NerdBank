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
import javax.transaction.Transactional;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaTransferencia extends JFrame {

    private JTextField campoContaDestino;
    private JTextField campoValorTransferencia;
    Configuration configuration = new Configuration().configure("persistence.xml");
    SessionFactory sessionFactory = configuration.buildSessionFactory();
    // Criação da sessão do Hibernate
    Session session = sessionFactory.openSession();
    OperacaoRepositorio operacaoRepositorio = new OperacaoRepositorio(session);

    public TelaTransferencia(Cliente cliente, ClienteRepositorio clienteRepositorio, Projeto.Entidades.Conta conta, ContaRepositorio contaRepositorio) {
        // Config da janela
        setTitle("Tela de Transferência");
        setSize(400, 200);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Layout
        setLayout(new GridLayout(4, 2));

        // Componentes

        JLabel labelContaDestino = new JLabel("Conta do Destinatário:");
        campoContaDestino = new JTextField();

        JLabel labelValorTransferencia = new JLabel("Valor a Transferir:");
        campoValorTransferencia = new JTextField();

        JButton botaoTransferir = new JButton("Transferir");
        JButton botaoVoltar = new JButton("Voltar");

        //componentes da janela
        add(labelContaDestino);
        add(campoContaDestino);
        add(labelValorTransferencia);
        add(campoValorTransferencia);
        add(botaoTransferir);
        add(botaoVoltar);

        //botão Transferir
        botaoTransferir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                realizarTransferencia(cliente, clienteRepositorio,conta,contaRepositorio);
            }
        });

        //botão Voltar
        botaoVoltar.addActionListener(new ActionListener() {
            @Override

            public void actionPerformed(ActionEvent e) {
                voltarParaTelaAnterior(cliente, clienteRepositorio,conta,contaRepositorio);
            }
        });
    }
    @Transactional
    private void realizarTransferencia(Cliente cliente, ClienteRepositorio clienteRepositorio, Projeto.Entidades.Conta conta, ContaRepositorio contaRepositorio) {
        // Lógica para realizar a transferência
        String contaDestino = campoContaDestino.getText();

        try {
            double valorTransferencia = Double.parseDouble(campoValorTransferencia.getText());
            Projeto.Entidades.Conta contaDestinoDB = contaRepositorio.buscarContaPorNumero(contaDestino);
            // lógica de transferência

            if (valorTransferencia <= conta.consultaSaldo() && valorTransferencia > 0 && contaDestinoDB != null){
                conta.Saque(valorTransferencia);
                Operacao operacaoSaque = new Operacao(conta,-(valorTransferencia),"Transferencia enviada");

                contaDestinoDB.Deposito(valorTransferencia);
                Operacao operacaoDeposito = new Operacao(contaDestinoDB,valorTransferencia,"Transferencia recebida");

                operacaoRepositorio.salvarOperacao(operacaoSaque);
                operacaoRepositorio.salvarOperacao(operacaoDeposito);

                contaRepositorio.atualizarConta(conta);
                contaRepositorio.atualizarConta(contaDestinoDB);

                JOptionPane.showMessageDialog(this, "Transferencia de R$ " + valorTransferencia + " realizado com sucesso!");
            }else {
                JOptionPane.showMessageDialog(this, "Por favor,confira os dados ou insira um valor válido para a transferência.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, insira um valor válido para a transferência.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void voltarParaTelaAnterior(Cliente cliente, ClienteRepositorio clienteRepositorio, Projeto.Entidades.Conta conta, ContaRepositorio contaRepositorio) {

        //para voltar para a tela anterior

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Conta(cliente, clienteRepositorio,conta,contaRepositorio).show();
            }
        });
        //fechar a janela
        this.dispose();
    }


}
