package Projeto.Repositorio;

import Projeto.Entidades.Cliente;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class ClienteRepositorio {
    private Session session;

    public ClienteRepositorio(Session session) {
        this.session = session;
    }

    public void salvarCliente(Cliente cliente) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(cliente);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Cliente buscarCliente(Integer id) {
        return session.get(Cliente.class, id);
    }

    public List<Cliente> listarClientes() {
        return session.createQuery("FROM clientes", Cliente.class).list();
    }

    public void atualizarCliente(Cliente cliente) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(cliente);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void excluirCliente(Cliente cliente) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.delete(cliente);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
