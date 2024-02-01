package Projeto.Front;

import Projeto.Entidades.Cliente;
import Projeto.Repositorio.ClienteRepositorio;
import Projeto.Repositorio.ContaRepositorio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MinhasContas {
    private JFrame frame;
    private DefaultListModel<String> contasListModel;
    private JList<String> contasList;

    public MinhasContas(Cliente cliente, ClienteRepositorio clienteRepositorio, ContaRepositorio contaRepositorio) {
        // Lista de contas
        contasListModel = new DefaultListModel<>();
        List<Projeto.Entidades.Conta> listadeContas = contaRepositorio.listarContaPorCliente(cliente.getId());

        for(int i = 0; i < listadeContas.size(); i++) {
            contasListModel.add(i, listadeContas.get(i).getNumAgenciaEConta() + " " + listadeContas.get(i).getTipoDaConta());
        }

        // Configuracao do frame
        frame = new JFrame("Minhas Contas");
        frame.setSize(400, 200);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        // Configuracao da lista de contas
        contasList = new JList<>(contasListModel);
        contasList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Configuracao do botão Acessar
        JButton acessarButton = new JButton("Acessar");
        acessarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int selectedIndex = contasList.getSelectedIndex();
                Projeto.Entidades.Conta contaAcesso = listadeContas.get(selectedIndex);
                if (selectedIndex != -1) {
                    String contaSelecionada = contasListModel.getElementAt(selectedIndex);

                        frame.dispose();
                        new Conta(cliente,clienteRepositorio,contaAcesso,contaRepositorio).setVisible(true);

                } else {
                    JOptionPane.showMessageDialog(frame, "Selecione uma conta antes de acessar.");
                }
            }
        });

        // Config botões na parte inferior

        JButton voltarButton = new JButton("Voltar");
        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                voltarParaTelaAnterior();
            }
        });

        JButton criarContaButton = new JButton("Criar Conta");
        criarContaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        new CriarConta(cliente,clienteRepositorio,contaRepositorio).setVisible(true);
                    }
                });
            }
        });

        // Configuracao do layout
        frame.setLayout(new BorderLayout());
        frame.add(new JScrollPane(contasList), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(acessarButton);
        buttonPanel.add(voltarButton);
        buttonPanel.add(criarContaButton);

        frame.add(buttonPanel, BorderLayout.SOUTH);
    }

    private void voltarParaTelaAnterior() {
        // para voltar para a tela anterior
        JFrame mainFrame = this.frame;
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TelaLogin().show();
                mainFrame.dispose();
            }
        });
    }

    public void show() {
        frame.setVisible(true);
    }


}
