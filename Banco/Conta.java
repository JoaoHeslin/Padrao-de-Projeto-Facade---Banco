package Banco;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Conta {

    private Correntista correntista;
    private int numero;
    private List<Transacao> transacoes;
    private Banco banco;

    public Conta(Correntista correntista, int numero, Banco banco){
        this.correntista = correntista;
        this.numero = numero;
        this.banco = banco;
        this.transacoes = new ArrayList<>();
    }
    public Transacao depositar(double valor){
        if (valor<= 0) throw new ContaException("Valor insuficiente, deposite valores maiores que 0.");
        Transacao transacao = new Transacao(this.transacoes.size()+1,+valor);
        transacoes.add(transacao);
        return transacao;
    }
    public Transacao sacar(double valor){
        if (valor<= 0) throw new ContaException("Valor insuficiente, saque valores maiores que 0.");
        if (this.saldo()<= 0) throw new ContaException("Saldo insuficiente para saque");
        Transacao transacao = new Transacao(this.transacoes.size()+1,-valor);
        transacoes.add(transacao);
        return transacao;
    }
    public double saldo(){
        double saldo = 0.0;
        for (Transacao transacao:this.transacoes){
            saldo += transacao.getValor();
        }
        return saldo;
    }
    public String extrato() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        StringBuilder extrato = new StringBuilder();
        extrato.append("Extrato da Conta ").append(numero).append(" de ").append(correntista.getNome()).append("\n");
        extrato.append("Data e Hora da Impressão: ").append(dateFormat.format(new Date())).append("\n");
        extrato.append("--------------------------------------------------\n");
        extrato.append("ID Transação  |  Data e Hora  |  Valor  |  Tipo\n");
        extrato.append("--------------------------------------------------\n");

        for (Transacao transacao : transacoes) {
            String tipo = (transacao.getValor() > 0) ? "Depósito" : "Saque";
            extrato.append(String.format("%-13d", transacao.getId()));
            extrato.append(String.format("|  %-13s", dateFormat.format(transacao.getData().getTime())));
            extrato.append(String.format("|  %-8.2f", transacao.getValor()));
            extrato.append(String.format("|  %-7s", tipo));
            extrato.append("\n");
        }

        extrato.append("--------------------------------------------------\n");
        extrato.append("Saldo Atual: R$").append(saldo()).append("\n");
        extrato.append("--------------------------------------------------\n");

        return extrato.toString();
    }
    public int getNumero() {
        return this.numero;
    }
    public Correntista getCorrentista() {
        return this.correntista;
    }

}
