package Entidades.Conta;

public class ContaCorrente extends Conta
{
    private float chequeEspecial = 3000;
    private float saldoTotal;

    public ContaCorrente()
    {
        super();
        this.tipo = 1;
        this.saldoTotal = this.saldo + chequeEspecial;
    }

    public void sacar(float valor) throws Exception
    {
        System.out.println(this.saldoTotal);
        if (valor < 1) {
            throw new Exception("Valor inválido");
        }

        if (valor > this.saldoTotal) {
            throw new Exception("Saldo insuficiente");
        }

        if (valor <= this.saldo) {
            this.saldo -= valor;
        } else {
            float diff = valor - this.saldo;
            this.saldo -= this.saldo;
            this.chequeEspecial -= diff;
        }
        this.saldoTotal -= valor;
    }

}
