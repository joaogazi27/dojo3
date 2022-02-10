package Controllers;

import Entidades.*;
import Entidades.Conta.Conta;
import Entidades.Conta.ContaCorrente;
import Entidades.Conta.ContaPoupanca;

import java.util.Random;

public class BancoController
{
    private Banco banco;

    public BancoController()
    {
        this.banco = new Banco();
    }

    public void abrirConta(Cliente cliente, int tipoConta) throws Exception
    {
        if (!banco.hasExactCliente(cliente.cpf()) && (banco.hasExactCliente(cliente.email()) || banco.hasExactCliente(cliente.telefone()))) {
            throw new Exception("Cliente já foi cadastrado. Dados digitados ja foram utilizados.");
        }

        int numeroConta;

        if (cliente.hasContas()) {
            numeroConta = cliente.contas().get(0).numeroConta();
        } else {
            numeroConta = new Random().nextInt(10000);
        }

        Conta conta;
        conta = tipoConta == 1 ? new ContaCorrente() : new ContaPoupanca();
        conta.setNumeroConta(numeroConta);

        cliente.addConta(conta);
        conta.addCliente(cliente);

        banco.contas().add(conta);
        banco.clientes().add(cliente);
        System.out.println("numero da conta: " + conta.numeroConta());
    }

    public void realizarDeposito(int numeroConta, float valor, int tipoConta) throws Exception
    {
        if (!banco.hasExactConta(numeroConta, tipoConta)) {
            throw new Exception("Conta nao encontrada.");
        }

        Conta conta = banco.findConta(numeroConta, tipoConta);
        conta.depositar(valor);

        Extrato extrato = new Extrato(valor, "Depositor realizado com sucesso.", "Deposito");
        conta.addExtrato(extrato);
    }

    public void realizarSaque(int numeroConta, float valor, int tipoConta, String senha) throws Exception
    {
        if (!banco.hasExactConta(numeroConta, tipoConta)) {
            throw new Exception("Conta nao encontrada.");
        }

        Conta conta = banco.findConta(numeroConta, tipoConta);
        Cliente cliente = conta.cliente();

        if (!cliente.senha().equals(senha)) {
            throw new Exception("Senha incorreta.");
        }

        conta.sacar(valor);

        Extrato extrato = new Extrato(valor, "Extrato realizado com sucesso", "Saque");
        conta.addExtrato(extrato);
    }

    public void realizarTransferenciaViaAgencia(
            int numeroContaTransferidor,
            int tipoContaTransferidor,
            int numeroContaRecebedor,
            int tipoContaRecebedor,
            float valor,
            String senha
    ) throws Exception {
        if (!banco.hasExactConta(numeroContaTransferidor, tipoContaTransferidor) || !banco.hasExactConta(numeroContaRecebedor, tipoContaRecebedor)) {
            throw new Exception("Conta nao encontrada.");
        }

        Conta contaTransferidor = banco.findConta(numeroContaTransferidor, tipoContaTransferidor);
        Conta contaRecebedor = banco.findConta(numeroContaRecebedor, tipoContaRecebedor);

        Cliente clienteTransferidor = contaTransferidor.cliente();

        if (!clienteTransferidor.senha().equals(senha)) {
            throw new Exception("Senha incorreta");
        }

        contaTransferidor.transferirViaAgencia(contaRecebedor, valor);

        Extrato extrato = new Extrato(valor, "Transferencia realizada com sucesso.", "Transferencia via agencia.");
        contaTransferidor.addExtrato(extrato);
    }

    public void realizarTransferenciaViaPIX(
            int numeroContaTransferidor,
            int tipoContaTransferidor,
            int tipoChavePix,
            int tipoContaRecebedor,
            float valor,
            String senha
    ) throws Exception {
        //if (!this.hasExactCliente(chavePIX)) {
        //    throw new Exception("Cliente nao encontrado");
        //}
        //
        //Cliente clienteRecebedor = this.buscarCliente(chavePIX);
    }

    public void exibirExtratos(int numeroConta, int tipoConta, String senha) throws Exception
    {
        if (!banco.hasExactConta(numeroConta, tipoConta)) {
            throw new Exception("Conta nao encontrada.");
        }

        Conta conta = banco.findConta(numeroConta, tipoConta);
        String clienteContaSenha = conta.cliente().senha();

        if (verificarSenha(clienteContaSenha, senha)) {
            throw new Exception("Senha incorreta.");
        }

        conta.listarExtratos();
    }

    private boolean verificarSenha(String senha, String senhaASerVerificada)
    {
        return !senha.equals(senhaASerVerificada);
    }

    public boolean clienteJaCadastrado(String cpf)
    {
        return banco.hasExactCliente(cpf);
    }

    public Cliente buscarCliente(String cpf)
    {
        return banco.findCliente(cpf);
    }
}
