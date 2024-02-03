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
import java.time.format.DateTimeFormatter;
import java.util.List;

public class TelaListagemOperacoesConta extends JFrame {

    private JFrame frame;
    private DefaultListModel<String> operacoesListModel;
    private JList<String> operacoesList;

    Configuration configuration = new Configuration().configure("persistence.xml");
    SessionFactory sessionFactory = configuration.buildSessionFactory();
    // Criação da sessão do Hibernate
    Session session = sessionFactory.openSession();
    OperacaoRepositorio operacaoRepositorio = new OperacaoRepositorio(session);

    public TelaListagemOperacoesConta(Cliente cliente, ClienteRepositorio clienteRepositorio, Projeto.Entidades.Conta conta, ContaRepositorio contaRepositorio) {
        List<Operacao> listaOperacoes = operacaoRepositorio.listarOperacoesPorConta(conta.getId());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // Configuração do frame
        frame = new JFrame("Operações da Conta");
        frame.setSize(550, 350);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        // Configuração da lista de operações
        operacoesListModel = new DefaultListModel<>();
        for (int i = 0; i < listaOperacoes.size(); i++) {
            operacoesListModel.addElement((i + 1) + "-- " + listaOperacoes.get(i).getDescricao() + " no valor de " + listaOperacoes.get(i).getValor()
                    + " na data " + listaOperacoes.get(i).getDataDaTransacao().format(formatter));
        }

        operacoesList = new JList<>(operacoesListModel);
        operacoesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Configuração do botão Voltar
        JButton voltarButton = new JButton("Voltar");
        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                voltarParaTelaAnterior(cliente, clienteRepositorio, conta, contaRepositorio);
            }
        });

        // Configuração do layout
        frame.setLayout(new BorderLayout());
        frame.add(new JScrollPane(operacoesList), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(voltarButton);

        frame.add(buttonPanel, BorderLayout.SOUTH);
    }

    private void voltarParaTelaAnterior(Cliente cliente, ClienteRepositorio clienteRepositorio, Projeto.Entidades.Conta conta, ContaRepositorio contaRepositorio) {
        // Voltar para a tela anterior
        JFrame mainFrame = this.frame;
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Ajuste conforme necessário para a tela anterior desejada
                new Conta(cliente, clienteRepositorio, conta, contaRepositorio).show();
                mainFrame.dispose();
            }
        });
    }

    public void show() {
        frame.setVisible(true);
    }
}
