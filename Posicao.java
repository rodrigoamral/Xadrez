/*
Classe responsavel por elementos da posição , cada posição tem uma cor (branco ou preto),
uma linha (de 1 a 8) e uma coluna (de a a h).Cada posição pode estar vazia ou ocupada por
uma peça e sabe que peça a ocupa.

*/
package pacote1;

public class Posicao {
    private boolean branco,ocupada;
    private int linha;
    private char coluna;
    private Peca pecaOcupada;
    public Posicao(boolean branco, boolean ocupada, int linha, int coluna) {
        this.branco = branco;
        this.linha = linha;
        if(coluna == 0)
            this.coluna = 'a';
        if(coluna == 1)
            this.coluna = 'b';
        if(coluna == 2)
            this.coluna = 'c';
        if(coluna == 3)
            this.coluna = 'd';
        if(coluna == 4)
            this.coluna = 'e';
        if(coluna == 5)
            this.coluna = 'f';
        if(coluna == 6)
            this.coluna = 'g';
        if(coluna == 7)
            this.coluna = 'h';
    }

    public Posicao(boolean branco, boolean ocupada, int linha, int coluna , Peca peca) {
        this.branco = branco;
        this.linha = linha;
        if(coluna == 0)
            this.coluna = 'a';
        if(coluna == 1)
            this.coluna = 'b';
        if(coluna == 2)
            this.coluna = 'c';
        if(coluna == 3)
            this.coluna = 'd';
        if(coluna == 4)
            this.coluna = 'e';
        if(coluna == 5)
            this.coluna = 'f';
        if(coluna == 6)
            this.coluna = 'g';
        if(coluna == 7)
            this.coluna = 'h';
        
        this.pecaOcupada = peca;
    }


    public boolean getBranco() {
        return branco;
    }
    public boolean getOcupada() {
        return ocupada;
    }
    public void setOcupada(boolean ocupada) {
        this.ocupada = ocupada;
    }
    public int getLinha() {
        return this.linha;
    }
    public char getColuna() {
        return this.coluna;
    }
    public Peca getPecaOcupada() {
        return this.pecaOcupada;
    }
    public void setPecaOcupada(Peca pecaOcupada) {
        this.pecaOcupada = pecaOcupada;
    }
    
}
