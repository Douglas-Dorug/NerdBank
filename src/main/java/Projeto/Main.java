package Projeto;

import Projeto.Entidades.Cliente;
import Projeto.Entidades.Gerente;
import Projeto.Repositorio.ClienteRepositorio;
import Projeto.Repositorio.GerenteRepositorio;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import Projeto.Entidades.Conta;
import Projeto.Enums.TiposConta;
import Projeto.Repositorio.ContaRepositorio;

import java.time.LocalDate;
import java.util.Date;
import org.hibernate.Transaction;

public class Main {
    public static void main(String[] args) {
        
        
        //gerar tabelas

        SessionFactory sessionFactory = new Configuration().configure("persistence.xml").buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        // Realize operações no banco de dados usando a sessão

        transaction.commit();
        session.close();

        
        
        
        
        
        
        
        

         
    }
}
