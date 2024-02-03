package Projeto.Front;

import Projeto.Entidades.Cliente;
import Projeto.Repositorio.ClienteRepositorio;
import Projeto.Repositorio.ContaRepositorio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Conta extends JFrame {

    private double saldo;
    private JLabel nomeClienteLabel;
    private JLabel agenciaContaLabel;
    private JLabel saldoLabel;
    private JButton operacoesButton;
    private JButton depositarButton;
    private JButton sacarButton;
    private JButton transferirButton;
    private JButton voltarButton;

    public Conta(Cliente cliente, ClienteRepositorio clienteRepositorio, Projeto.Entidades.Conta conta, ContaRepositorio contaRepositorio) {
        // Configurações básicas da janela
        setTitle("Conta");
        setSize(350, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Inicializa o saldo
        saldo = conta.consultaSaldo();
        // Criação dos componentes
        saldoLabel = new JLabel("Saldo: R$ " + saldo);
        nomeClienteLabel = new JLabel("Cliente: " + cliente.getNome());
        agenciaContaLabel = new JLabel("Agência e Conta: " + conta.getNumAgenciaEConta());
        operacoesButton = new JButton("Operações");
        depositarButton = new JButton("Depositar");
        sacarButton = new JButton("Sacar");
        transferirButton = new JButton("Transferir");
        voltarButton = new JButton("Voltar");

        // Adiciona os listeners aos botões
        operacoesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                //logica para Operacoes
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        new TelaListagemOperacoesConta(cliente, clienteRepositorio,conta,contaRepositorio).setVisible(true);
                    }
                });
            }
        });

        depositarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                // Lógica para depósito
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        new TelaDeposito(cliente, clienteRepositorio,conta,contaRepositorio).setVisible(true);
                    }
                });
            }
        });

        sacarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                // Lógica para saque
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        new TelaSaque(cliente, clienteRepositorio,conta,contaRepositorio).setVisible(true);
                    }
                });
            }
        });

        transferirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                // Lógica para transferência
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        new TelaTransferencia(cliente, clienteRepositorio,conta,contaRepositorio).setVisible(true);
                    }
                });
            }
        });

        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Fechar a janela quando o botão "Voltar" for clicado
                dispose();
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        new MinhasContas(cliente, clienteRepositorio,contaRepositorio).show();
                    }
                });
            }
        });

        // Config do layout da janela
        setLayout(new BorderLayout());

        // Configuração do painel superior
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(3, 1));

        topPanel.add(nomeClienteLabel);
        topPanel.add(agenciaContaLabel);
        topPanel.add(saldoLabel);

        add(topPanel, BorderLayout.NORTH);

        // Configuração do painel de botões
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        //botões ao painel
        buttonPanel.add(Box.createVerticalGlue()); // Espaço no topo
        buttonPanel.add(createButton(operacoesButton, 150,30));
        buttonPanel.add(createButton(depositarButton, 150, 30));
        buttonPanel.add(createButton(sacarButton, 150, 30));
        buttonPanel.add(createButton(transferirButton, 150, 30));
        buttonPanel.add(Box.createVerticalGlue()); // Espaço entre os botões
        buttonPanel.add(createButton(voltarButton, 120, 25)); // Botão "Voltar" menor
        buttonPanel.add(Box.createVerticalGlue()); // Espaço na parte inferior

        add(buttonPanel, BorderLayout.CENTER);
    }

    private JButton createButton(JButton button, int width, int height) {
        button.setMaximumSize(new Dimension(width, height));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        return button;
    }


}
