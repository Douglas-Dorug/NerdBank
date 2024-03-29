package Projeto.Repositorio;

import Projeto.Entidades.Operacao;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class OperacaoRepositorio {

    private Session session;

    public OperacaoRepositorio(Session session) {
        this.session = session;
    }

    public void salvarOperacao(Operacao operacao) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(operacao);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Operacao buscarOperacao(Integer id) {
        return session.get(Operacao.class, id);
    }

    public List<Operacao> listarOperacoes() {
        return session.createQuery("FROM operacoes", Operacao.class).list();
    }
}
