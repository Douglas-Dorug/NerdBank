package Projeto.Front;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaTransferencia extends JFrame {

    private JTextField campoAgenciaDestino;
    private JTextField campoContaDestino;
    private JTextField campoValorTransferencia;

    public TelaTransferencia() {
        // Config da janela
        setTitle("Tela de Transferência");
        setSize(400, 200);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Layout
        setLayout(new GridLayout(4, 2));

        // Componentes
        JLabel labelAgenciaDestino = new JLabel("Agência do Destinatário:");
        campoAgenciaDestino = new JTextField();

        JLabel labelContaDestino = new JLabel("Conta do Destinatário:");
        campoContaDestino = new JTextField();

        JLabel labelValorTransferencia = new JLabel("Valor a Transferir:");
        campoValorTransferencia = new JTextField();

        JButton botaoTransferir = new JButton("Transferir");
        JButton botaoVoltar = new JButton("Voltar");

        //componentes da janela
        add(labelAgenciaDestino);
        add(campoAgenciaDestino);
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
                realizarTransferencia();
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

    private void realizarTransferencia() {
        // Lógica para realizar a transferência
        String agenciaDestino = campoAgenciaDestino.getText();
        String contaDestino = campoContaDestino.getText();

        try {
            double valorTransferencia = Double.parseDouble(campoValorTransferencia.getText());

            // lógica de transferência

            // mensagem
            JOptionPane.showMessageDialog(this, "Transferência de R$ " + valorTransferencia + " realizada com sucesso!");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, insira um valor válido para a transferência.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void voltarParaTelaAnterior() {

        //para voltar para a tela anterior

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Conta().show();
            }
        });
        //fechar a janela
        this.dispose();
    }


}
