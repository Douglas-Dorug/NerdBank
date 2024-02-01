package Projeto.Front;

import Projeto.Entidades.Cliente;
import Projeto.Entidades.Conta;
import Projeto.Enums.TiposConta;
import Projeto.Repositorio.ClienteRepositorio;
import Projeto.Repositorio.ContaRepositorio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CriarConta extends JFrame {

    private JComboBox<TiposConta> tipoContaComboBox;
    private JTextField senhaField;

    public CriarConta(Cliente cliente, ClienteRepositorio clienteRepositorio, ContaRepositorio contaRepositorio) {
        // Configda janela
        setTitle("Criar Conta");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Layout
        setLayout(new GridLayout(3, 2));

        // Componentes
        JLabel tipoContaLabel = new JLabel("Tipo de Conta:");
        TiposConta[] tiposConta = {TiposConta.CONTACORRENTE,TiposConta.CONTAPOUPANCA};
        tipoContaComboBox = new JComboBox<>(tiposConta);

        JLabel senhaLabel = new JLabel("Senha:");
        senhaField = new JTextField();

        JButton criarButton = new JButton("Criar");
        criarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                criarConta(cliente,clienteRepositorio,contaRepositorio);
                JOptionPane.showMessageDialog(CriarConta.this, "Conta criada com sucesso!");
                voltarParaTelaAnterior(cliente,clienteRepositorio,contaRepositorio);
            }
        });

        JButton voltarButton = new JButton("Voltar");
        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                voltarParaTelaAnterior(cliente,clienteRepositorio,contaRepositorio);
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

    private void criarConta(Cliente cliente, ClienteRepositorio clienteRepositorio, ContaRepositorio contaRepositorio) {
        TiposConta tipoConta = (TiposConta) tipoContaComboBox.getSelectedItem();
        String senha = senhaField.getText();
        int totalContasCliente = contaRepositorio.totalContasCliente(cliente.getId());
        Conta conta = new Conta(cliente,senha,totalContasCliente,tipoConta);
        //lógica para criar a conta com os dados inseridos
        contaRepositorio.salvarConta(conta);
    }


    private void voltarParaTelaAnterior(Cliente cliente, ClienteRepositorio clienteRepositorio, ContaRepositorio contaRepositorio) {

        //para voltar para a tela anterior

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MinhasContas(cliente,clienteRepositorio,contaRepositorio).show();
            }
        });
        //fechar a janela
        this.dispose();
    }
}
