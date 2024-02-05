package Projeto.Entidades;

import Projeto.Enums.TiposConta;
import Projeto.Repositorio.OperacaoRepositorio;

import java.time.LocalDate;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.Date;

@Entity
@Table(name ="contas")
public class Conta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "fk_cliente_id", nullable = false)
    private Cliente cliente; //chave estrangeira
    @Column(nullable =false)
    private double saldo;
    private String descricaoCotna;
    private LocalDate dataCriacao;
    private double valorRendimento;
    private String senhaAcesso;
    @Column(length = 10, nullable =false)
    private String numAgenciaEConta;
    private boolean situacaoConta;
    private TiposConta tipoDaConta;

    public Conta(int id, Cliente cliente, double saldo, String descricaoCotna, LocalDate dataCriacao, double valorRendimento, String senhaAcesso, String numAgenciaEConta, boolean situacaoConta, TiposConta tipoDaConta) {
        this.id = id;
        this.cliente = cliente;
        this.saldo = saldo;
        this.descricaoCotna = descricaoCotna;
        this.dataCriacao = dataCriacao;
        this.valorRendimento = valorRendimento;
        this.senhaAcesso = senhaAcesso;
        this.numAgenciaEConta = numAgenciaEConta;
        this.situacaoConta = situacaoConta;
        this.tipoDaConta = tipoDaConta;
    }

    public Conta(Cliente cliente, String senhaAcesso,int totalContas , TiposConta tipoDaConta) {
        this.cliente = cliente;
        this.saldo = 0;
        this.dataCriacao = LocalDate.now();
        this.senhaAcesso = senhaAcesso;
        //Numero da agencia e conta é geerado com numero id cliente e numero da conta
        this.numAgenciaEConta = String.valueOf(cliente.getId() + "0" + (totalContas + 1));
        this.situacaoConta = true;
        if(tipoDaConta.equals(TiposConta.CONTAPOUPANCA)){
            this.tipoDaConta = tipoDaConta;
            this.valorRendimento = 0.1;
            this.descricaoCotna = "Poupança";
        }else {
            this.tipoDaConta = tipoDaConta;
            this.valorRendimento = 0;
            this.descricaoCotna = "Corrente";
        }

    }

    public Conta(){
    }

    @Transactional
    public void Saque(double valor){
        try{
            if (this.saldo >= valor){
                this.saldo -= valor;
            }else {
                throw new RuntimeException();
                }
        }catch (Exception exception){
            throw exception;
        }
    }

    @Transactional
    public void Deposito(double valor){
        try{
            if(valor >= 0){
                this.saldo += valor;
            }else {
                throw new RuntimeException();
            }
        } catch (Exception exception){
            throw exception;
        }
    }

    public double consultaSaldo(){
        return this.getSaldo();
    }

    @Transactional
    public void Transferencia(Conta ContaRecibo, double valor){
        this.Saque(valor);
        ContaRecibo.Deposito(valor);
        
    }
    public void rendimentoConta(){
        //implementar melhor o rendimento para as contas
        this.saldo += this.saldo + (this.saldo * this.valorRendimento);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    private double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getDescricaoCotna() {
        return descricaoCotna;
    }

    public void setDescricaoCotna(String descricaoCotna) {
        this.descricaoCotna = descricaoCotna;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public double getValorRendimento() {
        return valorRendimento;
    }

    public void setValorRendimento(double valorRendimento) {
        this.valorRendimento = valorRendimento;
    }

    public String getSenhaAcesso() {
        return senhaAcesso;
    }

    public void setSenhaAcesso(String senhaAcesso) {
        this.senhaAcesso = senhaAcesso;
    }

    public String getNumAgenciaEConta() {
        return numAgenciaEConta;
    }

    public void setNumAgenciaEConta(String numAgenciaEConta) {
        this.numAgenciaEConta = numAgenciaEConta;
    }

    public TiposConta getTipoDaConta() {
        return tipoDaConta;
    }

    public void setTipoDaConta(TiposConta tipoDaConta) {
        this.tipoDaConta = tipoDaConta;
    }

    public boolean isSituacaoConta() {
        return situacaoConta;
    }

    public void setSituacaoConta(boolean situacaoConta) {
        this.situacaoConta = situacaoConta;
    }

}
