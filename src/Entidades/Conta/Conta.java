package Entidades.Conta;

import Entidades.Cliente;
import Entidades.Extrato;

import java.util.ArrayList;

abstract public class Conta
{
    protected int numeroConta;
    protected float saldo;
    protected int agencia;
    protected Cliente cliente;
    protected ArrayList<Extrato> extratos;
    /**
     * Tipo 1 para Conta Corrente
     * Tipo 2 para Conta Poupança
     */
    protected int tipo;

    public Conta()
    {
        this.saldo = 0;
        this.cliente = null;
        this.agencia = 0001;
        this.extratos = new ArrayList<>();
    }

    public int tipo()
    {
        return this.tipo;
    }

    public int numeroConta() {
        return numeroConta;
    }

    public float saldo() {
        return saldo;
    }

    public Cliente cliente() {
        return cliente;
    }

    public ArrayList<Extrato> extratos()
    {
        return this.extratos;
    }

    public void setNumeroConta(int numero)
    {
        this.numeroConta = numero;
    }

    public void addCliente(Cliente cliente)
    {
        this.cliente = cliente;
    }

    public void addExtrato(Extrato extrato)
    {
        this.extratos.add(extrato);
    }

    public void depositar(float valor) throws Exception
    {
        if (valor < 1) {
            throw new Exception("Valor inválido");
        }

        this.saldo += valor;
    }

    abstract public void sacar(float valor) throws Exception;

    public void transferirViaAgencia(Conta recebedor, float valor) throws Exception
    {
        this.sacar(valor);
        recebedor.depositar(valor);
    }

    public void transferirViaPIX()
    {

    }

    public void listarExtratos()
    {
        for (Extrato extrato : this.extratos) {
            System.out.println("DATA: " + extrato.data());
            System.out.println("Tipo de operaçao: " + extrato.tipoOperacao());
            System.out.println("Descricao: " + extrato.descricao());
            System.out.println("Valor: " + extrato.valor());
            System.out.println("");
        }
    }

}
