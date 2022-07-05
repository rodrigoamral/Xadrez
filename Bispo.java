/*
Classe responsavel por retornar o elemento que representará a peça no tabuleiro,assim como verifica
se o movimento que o usuário deseja fazer é adequado para o tipo de peca Bispo.


*/
package pacote1;

public class Bispo extends Peca{
  
    public Bispo(boolean branca) {
       super(branca);
    }

    @Override
    public char desenho()
    {
        if(this.getBranco())
            return 'B';
        return 'b';    
    }

    @Override
    public boolean checaMovimento(int linhaOrigem, char colunaOrigem, int linhaDestino, char colunaDestino)
    {
        int linha, coluna;
            //tentar movimentar para o mesmo lugar
            if(linhaOrigem == linhaDestino && colunaOrigem == colunaDestino) 
            {
                return false;
            }

            //verificar movimento diagonal
            linha = linhaOrigem - linhaDestino;
            coluna = (colunaOrigem - 97) - (colunaDestino - 97);
            
            if(linha == coluna || (linha + coluna == 0))
            {
                return true;
 
            }
        

        
        return false;
        
    }



    

}
