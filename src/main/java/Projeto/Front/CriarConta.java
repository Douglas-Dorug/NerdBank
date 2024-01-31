package Projeto.Front;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CriarConta extends JFrame {

    private JComboBox<String> tipoContaComboBox;
    private JTextField senhaField;

    public CriarConta() {
        // Configda janela
        setTitle("Criar Conta");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Layout
        setLayout(new GridLayout(3, 2));

        // Componentes
        JLabel tipoContaLabel = new JLabel("Tipo de Conta:");
        String[] tiposConta = {"Poupança", "Corrente"};
        tipoContaComboBox = new JComboBox<>(tiposConta);

        JLabel senhaLabel = new JLabel("Senha:");
        senhaField = new JTextField();

        JButton criarButton = new JButton("Criar");
        criarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                criarConta();
                JOptionPane.showMessageDialog(CriarConta.this, "Conta criada com sucesso!");
            }
        });

        JButton voltarButton = new JButton("Voltar");
        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                voltarParaTelaAnterior();
            }
        });


        // Adicionando componentes à janela
        add(tipoContaLabel);
        add(tipoContaComboBox);
        add(senhaLabel);
        add(senhaField);
        add(criarButton);
        add(voltarButton);
    }

    private void criarConta() {
        String tipoConta = (String) tipoContaComboBox.getSelectedItem();
        String senha = senhaField.getText();

        //lógica para criar a conta com os dados inseridos
        // Exemplo de impressão para verificar os valores
        System.out.println("Tipo de Conta: " + tipoConta);
        System.out.println("Senha: " + senha);
    }


    private void voltarParaTelaAnterior() {

        //para voltar para a tela anterior

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MinhasContas().show();
            }
        });
        //fechar a janela
        this.dispose();
    }
}
