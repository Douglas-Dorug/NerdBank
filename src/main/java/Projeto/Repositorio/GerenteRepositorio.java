package Projeto.Repositorio;

import Projeto.Entidades.Gerente;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class GerenteRepositorio {

    private Session session;

    public GerenteRepositorio(Session session) {
        this.session = session;
    }

    //Apagar depois, só esta aqui a titulo de teste
    public void salvarGerente(Gerente gerente) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(gerente);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Gerente buscarGerente(Integer id) {
        return session.get(Gerente.class, id);
    }

    public List<Gerente> listarGerentes() {
        return session.createQuery("FROM gerentes", Gerente.class).list();
    }

    public void atualizarGerente(Gerente gerente) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(gerente);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void excluirGerente(Gerente gerente) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.delete(gerente);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
