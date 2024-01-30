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
        /*
       SessionFactory sessionFactory = new Configuration().configure("persistence.xml").buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        // Realize operações no banco de dados usando a sessão

        transaction.commit();
        session.close();
         */
        
        
        
        
        
        
        
        
        
        
        
         Configuration configuration = new Configuration().configure("persistence.xml");
        SessionFactory sessionFactory = configuration.buildSessionFactory();

        // Criação da sessão do Hibernate
        Session session = sessionFactory.openSession();
        
        ContaRepositorio rep = new ContaRepositorio(session);
        
        ClienteRepositorio clienteRepositorio = new ClienteRepositorio(session);
        
        
        TiposConta c = TiposConta.CONTAPOUPANCA;
        
        
        Cliente jao = clienteRepositorio.buscarCliente(1);
        Conta teste = new Conta(01,jao,500.00,"teste",LocalDate.now(),1.01,"123","0123456789",true,c);
        
        clienteRepositorio.salvarCliente(jao);
        rep.salvarConta(teste);
        
        System.out.println(teste.consultaSaldo()+"R$");
         
    }
}
