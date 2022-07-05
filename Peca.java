/*
Classe que representa as funcionalidades gerais comuns a todas as peças, e serve de base para todas as outras.


*/
package pacote1;


abstract class Peca {
    private boolean emJogo, branco ;
    private boolean inicio = true;

    public Peca(boolean branca){
        this.branco = branca;
        this.emJogo = true;
    }

    public boolean getEmJogo() { 
        return this.emJogo;
    }

    public boolean getInicio() {
        return this.inicio;
    }

    public void foraInicio(){
        this.inicio = false;
    }

    public void setEmJogo(boolean emjogo) {
        this.emJogo = emjogo;
    }

    public boolean getBranco() {
        return this.branco;
    }


    public abstract char desenho();
    public abstract boolean checaMovimento(int linhaOrigem, char colunaOrigem, int linhaDestino, char colunaDestino);
}
