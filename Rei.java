/*
Classe responsavel por retornar o elemento que representará a peça no tabuleiro,assim como verifica
se o movimento que o usuário deseja fazer é adequado para o tipo de peca Rei.


*/

package pacote1;

public class Rei extends Peca{

  public Rei(boolean branca) {
    super(branca);
  }  

  @Override
  public char desenho()
  {
    if(this.getBranco())
      return 'R';
    return 'r';    
  }

  @Override
  public boolean checaMovimento(int linhaOrigem, char colunaOrigem, int linhaDestino, char colunaDestino)
  {
    int linha, coluna;
      //tentar movimentar para o mesmo lugar
      if(linhaOrigem == linhaDestino && colunaOrigem == colunaDestino)
        return false;

      linha = linhaOrigem - linhaDestino;
      coluna = (colunaOrigem - 97) - (colunaDestino - 97);    

      //verificar movimento diagonal
      if((linha == 1 && coluna == 1) || (linha == -1 && coluna == -1) || (linha == 1 && coluna == -1) || (linha == -1 && coluna == 1) )
        return true;

      //verifica movimento vertical            
      if((linha == 1 || linha == -1) && coluna == 0)
        return true;

      //verifica movimento horizontal  
      if(linha == 0 && (coluna == 1 || coluna == -1))
        return true;  

    return false;
  }
    
}
