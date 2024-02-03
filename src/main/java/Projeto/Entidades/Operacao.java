package Projeto.Entidades;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name ="operacoes")
public class Operacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "fk_conta_id")
    private Conta conta;
    private double valor;
    private LocalDate dataDaTransacao;
    private String descricao;

    public Operacao(){}

    public Operacao(Conta conta, double valor, String descricao) {
        this.conta = conta;
        this.valor = valor;
        this.dataDaTransacao = LocalDate.now();
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getDataDaTransacao() {
        return dataDaTransacao;
    }

    public void setDataDaTransacao(LocalDate dataDaTransacao) {
        this.dataDaTransacao = dataDaTransacao;
    }
}
