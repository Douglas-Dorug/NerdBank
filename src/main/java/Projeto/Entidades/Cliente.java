package Projeto.Entidades;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name ="clientes")
public class Cliente extends Pessoa{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCliente;
   
    public Cliente(String nome, String nomeSocial, LocalDate dataNascimento, String CPF, String email, boolean ativo, String endereco, String senha) {
        super(nome, nomeSocial, dataNascimento, CPF, email, ativo, endereco,senha);
        
    }
    public Cliente(){}

    public void ModificaDados(String nomeSocial, String email, String endereco){
        this.setNomeSocial(nomeSocial);
        this.setEmail(email);
        this.setEndereco(endereco);
    }
    public void AvaliaGerente(Gerente gerente, double nota){
        //Falta implementar
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

}