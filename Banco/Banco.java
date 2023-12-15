package Banco;

import java.util.HashMap;
import java.util.Map;

public class Banco{
    private Map<Integer,Conta>contas;
    private int numeroContaLivre;
    private Map<String,Correntista> correntista;

    private String nome;
    public Banco(String nome){
        this.nome = nome;
        this.contas = new HashMap<>();
        this.correntista = new HashMap<>();
    }
    public Conta criarConta(Correntista correntista) {
        try {
            Conta conta = new Conta(correntista,numeroContaLivre,this);
            this.contas.put(contas.size()+1,conta);
            return conta;
        } catch (RuntimeException e){
            throw new BancoException("Correntista não cadastrado no banco:Correntista{cpf="+correntista.getCpf()+", nome="+correntista.getNome()+"}");
        }
    }
    public Conta getConta(int conta){
        for (Conta conta1:this.contas.values()){
            if (conta1.getNumero() == conta) return conta1;
        }
        return null;
    }
    public Conta getConta(Correntista correntista){
        for (Conta conta1:this.contas.values()){
            if (conta1.getCorrentista().equals(correntista)) return conta1;
        }
        return null;
    }
    public void addCorrentista(Correntista correntista){
        try {
            Correntista correntistaExistente = getCorrentista(correntista.getCpf());
            throw new RuntimeException("Correntista já tem conta cadastrada");
        } catch (RuntimeException e) {
            // Se a exceção for lançada, isso significa que o correntista não existe, então você pode adicioná-lo
            if (!Correntista.cpfValido(correntista.getCpf())) {
                throw new RuntimeException("CPF inválido: " + correntista.getCpf());
            }
            this.correntista.put(correntista.getCpf(), correntista);
        }
    }
    public Correntista getCorrentista(String cpf){
        Correntista correntistaExistente = this.correntista.get(cpf);
        if (correntistaExistente != null) {
            throw new BancoException("Correntista já tem conta cadastrada");
        } else {
            // O correntista não existe, você pode lançar uma exceção ou criar um novo correntista
            // dependendo do seu caso de uso.
            throw new RuntimeException("Não existe correntista com CPF: " + cpf);
        }
    }
    private int gerarNumeroConta(){
        return this.numeroContaLivre;
    }
    public String getNome() {
        return nome;
    }
}
