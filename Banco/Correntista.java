package Banco;

public class Correntista {
    private String cpf;
    private String nome;
    private Conta conta;
    public Correntista(String cpf, String nome){
        this.cpf = cpf;
        this.nome = nome;

    }

    public String getCpf() {
        return cpf;
    }


    public String getNome() {
        return nome;
    }


    public void setNome(String nome) {
        this.nome = nome;
    }


    public Conta getConta() {
        return this.conta;
    }


    public void setConta(Conta conta) {
        this.conta = conta;
    }
    public static boolean cpfValido(String cpf) {
        // Remove caracteres não numéricos
        cpf = cpf.replaceAll("[^0-9]", "");

        // Verifica se o CPF tem 11 dígitos
        if (cpf.length() != 11) {
            return false;
        }

        // Verifica se todos os dígitos são iguais
        if (cpf.matches("(\\d)\\1{10}")) {
            return false;
        }

        // Calcula o primeiro dígito verificador
        int soma = 0;
        for (int i = 0; i < 9; i++) {
            soma += (10 - i) * Character.getNumericValue(cpf.charAt(i));
        }
        int primeiroDigito = 11 - (soma % 11);
        if (primeiroDigito >= 10) {
            primeiroDigito = 0;
        }

        // Calcula o segundo dígito verificador
        soma = 0;
        for (int i = 0; i < 10; i++) {
            soma += (11 - i) * Character.getNumericValue(cpf.charAt(i));
        }
        int segundoDigito = 11 - (soma % 11);
        if (segundoDigito >= 10) {
            segundoDigito = 0;
        }
        return Character.getNumericValue(cpf.charAt(9)) == primeiroDigito &&
                Character.getNumericValue(cpf.charAt(10)) == segundoDigito;

    }

    public static String removeSeparadores(String cpf) {
        // Remove os separadores (pontos e traços) do CPF
        cpf = cpf.replaceAll("[^0-9]", "");
        return cpf;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true; // São a mesma instância
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false; // Tipos diferentes
        }
        Correntista other = (Correntista) obj;
        return cpf.equals(other.cpf); // Comparação baseada no CPF
    }
}
