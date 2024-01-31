package Projeto.Front;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaDepositoSaque extends JFrame {

    private JTextField campoValor;
    private JRadioButton radioDeposito;
    private JRadioButton radioSaque;

    public TelaDepositoSaque() {
        // Config da janela
        setTitle("Tela de Depósito/Saque");
        setSize(400, 200);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Layout
        setLayout(new GridLayout(4, 2));

        // Componentes
        JLabel labelValor = new JLabel("Valor:");
        campoValor = new JTextField();

        radioDeposito = new JRadioButton("Depósito");
        radioSaque = new JRadioButton("Saque");

        ButtonGroup group = new ButtonGroup();
        group.add(radioDeposito);
        group.add(radioSaque);

        JButton botaoConfirmar = new JButton("Confirmar");
        JButton botaoVoltar = new JButton("Voltar");

        //componentes da janela
        add(labelValor);
        add(campoValor);
        add(radioDeposito);
        add(radioSaque);
        add(botaoConfirmar);
        add(botaoVoltar);

        //botão Confirmar
        botaoConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                realizarOperacao();
            }
        });

        // Configuração do botão Voltar
        botaoVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                voltarParaTelaAnterior();
            }
        });
    }

    private void realizarOperacao() {
        // realizar depósito ou saque
        try {
            double valor = Double.parseDouble(campoValor.getText());

            if (radioDeposito.isSelected()) {
                //lógica de depósito
                JOptionPane.showMessageDialog(this, "Depósito de R$ " + valor + " realizado com sucesso!");
            } else if (radioSaque.isSelected()) {
                //lógica de saque
                JOptionPane.showMessageDialog(this, "Saque de R$ " + valor + " realizado com sucesso!");
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, selecione Depósito ou Saque.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, insira um valor válido.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void voltarParaTelaAnterior() {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Conta().show();
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
