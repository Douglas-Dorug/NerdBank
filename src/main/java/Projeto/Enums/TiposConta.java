package Projeto.Enums;

public enum TiposConta {

    CONTACORRENTE("Conta corrente"),
    CONTAPOUPANCA("Conta Poupança");

    TiposConta(String descricao) {
        this.descricao = descricao;
    }

    private String descricao;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
