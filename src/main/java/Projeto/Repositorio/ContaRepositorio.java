package Projeto.Repositorio;

import Projeto.Entidades.Cliente;
import Projeto.Entidades.Conta;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;


public class ContaRepositorio {
    private Session session;

    public ContaRepositorio(Session session) {
        this.session = session;
    }

    public void salvarConta(Conta conta) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(conta);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Conta buscarConta(Integer id) {
        return session.get(Conta.class, id);
    }

    public List<Conta> listarContas() {
        return session.createQuery("FROM contas", Conta.class).list();
    }

    public List<Conta> listarContaPorCliente(Long clienteId){
        return session.createQuery("FROM contas WHERE cliente.id = :clienteId", Conta.class)
                .setParameter("clienteId", clienteId)
                .list();
    }

    //Função para usar na criação de contas
    public int totalContasCliente(Long clienteId){
        List<Conta> totalContas= session.createQuery("FROM contas WHERE cliente.id = :clienteId", Conta.class)
                .setParameter("clienteId", clienteId)
                .list();

        return totalContas.size();
    }

    public void atualizarConta(Conta conta) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(conta);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void excluirConta(Conta conta) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.delete(conta);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
    
}
