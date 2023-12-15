package Banco;

import java.util.Calendar;

public class Transacao {
    private int id;
    private Calendar data;
    private double valor;


    public Transacao(int id, double valor){
        this.id = id;
        this.valor = valor;
    }


    public int getId() {
        return this.id;
    }


    public Calendar getData() {
        return this.data;
    }

    public double getValor() {
        return this.valor;
    }

    @Override
    public String toString() {
        return "Transacao{ do cliente com o id de número:" +
                id +
                ", na data=" + data +
                ", com o valor de:" + valor +
                '}';
    }

}
