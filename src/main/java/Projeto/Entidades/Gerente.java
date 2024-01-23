package Projeto.Entidades;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Vector;

@Entity
@Table(name ="gerentes")
public class Gerente extends Pessoa{
    @Column(nullable = false)
    private String codigoBanco;

    public Gerente(String nome, String nomeSocial, LocalDate dataNascimento, String CPF, String email, boolean ativo, String endereco, String codigoBanco) {

        super(nome, nomeSocial, dataNascimento, CPF, email, ativo, endereco);
        this.codigoBanco = codigoBanco;
    }
    public Gerente(){}

    public void ModificaCadastroCliente(Cliente cliente){
        //Falta Implementar
    }
    public void ModificaContaCliente(Cliente cliente, int idConta){
        //Falta Implementar
    }
    public void ListarOperacoesCliente(Cliente cliente){
        //Falta Implementar
    }

    public String getCodigoBanco() {
        return codigoBanco;
    }

    public void setCodigoBanco(String codigoBanco) {
        this.codigoBanco = codigoBanco;
    }

}
