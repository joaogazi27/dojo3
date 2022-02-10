package Entidades;

import Interfaces.TransacaoEmConta;

import java.util.Date;

public class Extrato implements TransacaoEmConta
{
    private float valor;
    private String descricao;
    private String tipoOperacao;
    private Date data;

    public Extrato(float valor, String descricao, String tipoOperacao) {
        this.valor = valor;
        this.descricao = descricao;
        this.tipoOperacao = tipoOperacao;
        this.data = new Date();
    }

    public float valor()
    {
        return this.valor;
    }

    public String descricao()
    {
        return this.descricao;
    }

    public String tipoOperacao()
    {
        return this.tipoOperacao;
    }

    public Date data()
    {
        return this.data;
    }

}
