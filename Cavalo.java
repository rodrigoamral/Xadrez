/*
Classe responsavel por retornar o elemento que representará a peça no tabuleiro,assim como verifica
se o movimento que o usuário deseja fazer é adequado para o tipo de peca Cavalo.


*/

package pacote1;

public class Cavalo extends Peca {

  public Cavalo(boolean branca) {
    super(branca);
  }   
    
  @Override
  public char desenho()
  {
    if(this.getBranco())
      return 'C';
    return 'c';     
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
      
      if(((coluna == 1 || coluna == -1) && (linha == 2 || linha == -2)))
        return true;
      if(((coluna == 2 || coluna == -2) && (linha == 1 || linha == -1)))
        return true;  
    

    return false;
  }
    
} 