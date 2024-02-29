package model;

public class Inscricao {
    private String nome;
    private int idade;
    private String posicao;
    private String time;
    
    public Inscricao() {
        this.nome = "";
        this.idade = -1;
        this.posicao = "";
        this.time = "";
    }
    
    public Inscricao(String nome, int idade, String posicao, String time) {
        this.nome = nome;
        this.idade = idade;
        this.posicao = posicao;
        this.time = time;
    }

    public String getnome() {
        return nome;
    }

    public void setnome(String nome) {
        this.nome = nome;
    }

    public int getidade() {
        return idade;
    }

    public void setidade(int idade) {
        this.idade = idade;
    }

    public String getposicao() {
        return posicao;
    }

    public void setposicao(String posicao) {
        this.posicao = posicao;
    }

    public String gettime() {
        return time;
    }

    public void settime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Inscricao [nome=" + nome + ", idade=" + idade + ", posicao=" + posicao + ", time=" + time + "]";
    }    
}