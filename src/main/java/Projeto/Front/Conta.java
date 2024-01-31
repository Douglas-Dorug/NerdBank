package Projeto.Front;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Conta extends JFrame {

    private double saldo;

    private JLabel saldoLabel;
    private JButton depositarButton;
    private JButton sacarButton;
    private JButton transferirButton;
    private JButton voltarButton;

    public Conta() {
        // Configurações básicas da janela
        setTitle("Conta");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Inicializa o saldo
        saldo = 1000.0; // Saldo inicial

        // Criação dos componentes
        saldoLabel = new JLabel("Saldo: R$ " + saldo);
        depositarButton = new JButton("Depositar");
        sacarButton = new JButton("Sacar");
        transferirButton = new JButton("Transferir");
        voltarButton = new JButton("Voltar");

        // Adiciona os listeners aos botões
        depositarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                // Lógica para depósito
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        new TelaDepositoSaque().setVisible(true);
                    }

                });

                // (Você pode implementar a lógica de depósito aqui)
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
                        new TelaDepositoSaque().setVisible(true);
                    }
                });

                // (Você pode implementar a lógica de saque aqui)
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
                        new TelaTransferencia().setVisible(true);
                    }
                });

                // (Você pode implementar a lógica de transferência aqui)
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
                        new MinhasContas().show();
                    }
                });
            }
        });

        // Config do layout da janela
        setLayout(new BorderLayout());
        add(saldoLabel, BorderLayout.NORTH);

        // Configuração do painel de botões
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        //botões ao painel
        buttonPanel.add(Box.createVerticalGlue()); // Espaço no topo
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
