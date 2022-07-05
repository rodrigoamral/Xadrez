/*
Essa classe é responsavel por cada jogador, cada um tem um nome, um conjunto de peças de uma das cores possíveis
e sabe quais peças suas ainda estão ativas no jogo.


*/
package pacote1;

public class Jogador {
    private String nome;
    private Peca conjunto[];
   
    public Jogador(String nome, boolean Conjunto) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setConjunto(Peca vetor[]){
        conjunto = vetor;
    }

}
