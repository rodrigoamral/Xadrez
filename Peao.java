/*
Classe responsavel por retornar o elemento que representará a peça no tabuleiro,assim como verifica
se o movimento que o usuário deseja fazer é adequado para o tipo de peca Peão.

*/
package pacote1;

public class Peao extends Peca{
    
    public Peao(boolean branca) { 
        super(branca);
    }


    @Override
    public char desenho()
    {
        if(this.getBranco())
            return 'P';
        return 'p';    
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

        linha = linhaOrigem - linhaDestino;
        coluna = (colunaOrigem - 97) - (colunaDestino - 97);
            
        //verifica se o peao esta na sua posicao inicial
        if(linhaOrigem == 6 || linhaOrigem == 1)
        {
            //verifica se o movimento eh 1 ou 2 casas para frente
            if(coluna == 0 && (linha <= 2 && linha >= -2))
            {
                return true;
            }
              

            //verifica se pode andar na casa diagonal (projeto 2)  
        }
        else
        {
            //verifica se o movimento eh 1 casa para frente
            if(coluna == 0 && (linha == 1 || linha == -1))
            {
                return true;
            }

                //verifica se pode andar na casa diagonal  (projeto 2) 
        }     

        return false;

    }

}
