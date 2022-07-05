/* 
Essa classe é responsável pela configuração inicial do tabuleiro, manutenção
da configuração do tabuleiro a cada jogada e pelas checagens de adequação dos 
movimentos solicitados (por exemplo, se está adequado para cada peça (chamando 
os métodos de cada peça), se está dentro dos limites do tabuleiro,se o caminho 
está livre para a peça se movimentar, etc.), bem como pelo desenho do tabuleiro 
(com as peças nas posições ocupadas)na tela.


*/
package pacote1;


public class Tabuleiro {
    private Posicao tab[][];
    private Posicao reiB , reiP;
    private Peca[] brancas;
    private Peca[] pretas;
    private boolean xequeB,xequeP;

    public Tabuleiro(Peca brancas[],Peca pretas[]) 
    {
        this.tab = new Posicao[8][8];
        this.brancas = brancas;
        this.pretas = pretas;
        this.xequeB = false;
        this.xequeP = false;
        //inicializar o tabuleiro vazio
        //variavel x decide se a cor da posicao eh branca ou preta
        for(int i=0,x=0; i<8; i++)
            for(int j=0; j<8; j++)
            { 
                if(x==0)
                {
                   if(j==7)
                        this.tab[i][j] = new Posicao(true,false,i,j);

                   else
                   {
                        this.tab[i][j] = new Posicao(true,false,i,j);
                        x=1;
                   }
                    
                }
                else
                {
                    if(j==7)
                        this.tab[i][j] = new Posicao(false,false,i,j);
                    else
                    {
                        this.tab[i][j] = new Posicao(false,false,i,j);
                        x=0;
                    }
                }
            } 
            
        setarPecas();   
    }


    private void setarPecas(){
        this.setarBispo();
        this.setarCavalo();
        this.setarDama();
        this.setarPeao();
        this.setarRei();
        this.setarTorre();
    }

    private void setarPeao(){
        //setar peoes pretos
        for(int i=1,j=0; j<8; j++){
            this.tab[i][j].setPecaOcupada(this.pretas[j]);
            this.tab[i][j].setOcupada(true);
        }  
        //setar peoes brancos
        for(int i=6,j=0; j<8; j++){
            this.tab[i][j].setPecaOcupada(this.brancas[j]);
            this.tab[i][j].setOcupada(true);
        }               
    }

    private void setarTorre(){
        //setar torres pretas
        this.tab[0][0].setPecaOcupada(this.pretas[8]);
        this.tab[0][0].setOcupada(true);

        this.tab[0][7].setPecaOcupada(this.pretas[9]);
        this.tab[0][7].setOcupada(true);

        //setar torres brancas
        this.tab[7][0].setPecaOcupada(this.brancas[8]);
        this.tab[7][0].setOcupada(true);

        this.tab[7][7].setPecaOcupada(this.brancas[9]);
        this.tab[7][7].setOcupada(true);
    }

    private void setarBispo(){
        //setar torres pretas
        this.tab[0][2].setPecaOcupada(this.pretas[12]);
        this.tab[0][2].setOcupada(true);

        this.tab[0][5].setPecaOcupada(this.pretas[13]);
        this.tab[0][5].setOcupada(true);

        //setar torres brancas
        this.tab[7][2].setPecaOcupada(this.brancas[12]);
        this.tab[7][2].setOcupada(true);

        this.tab[7][5].setPecaOcupada(this.brancas[13]);
        this.tab[7][5].setOcupada(true);        
    }

    private void setarCavalo(){
        //setar torres pretas
        this.tab[0][1].setPecaOcupada(this.pretas[10]);
        this.tab[0][1].setOcupada(true);

        this.tab[0][6].setPecaOcupada(this.pretas[11]);
        this.tab[0][6].setOcupada(true);

        //setar torres brancas
        this.tab[7][1].setPecaOcupada(this.brancas[10]);
        this.tab[7][1].setOcupada(true);

        this.tab[7][6].setPecaOcupada(this.brancas[11]);
        this.tab[7][6].setOcupada(true);
    }

    private void setarRei(){
        //setar rei preto
        this.tab[0][4].setPecaOcupada(this.pretas[14]);
        this.tab[0][4].setOcupada(true);
        this.reiP = this.tab[0][4];

        //setar rei branco
        this.tab[7][4].setPecaOcupada(this.brancas[14]);
        this.tab[7][4].setOcupada(true);
        this.reiB = this.tab[7][4];
        
    }

    private void setarDama(){
        //setar dama preta
        this.tab[0][3].setPecaOcupada(this.pretas[15]);
        this.tab[0][3].setOcupada(true);

        //setar dama branca
        this.tab[7][3].setPecaOcupada(this.brancas[15]);
        this.tab[7][3].setOcupada(true);        
    }

    public boolean movimento(int linhaOrigem, int linhaDestino, char colunaOrigem, char colunaDestino, Peca peca)
    {
        
        if(tab[linhaOrigem][colunaOrigem-97].getOcupada() == false)
            return false;  

        //se for movimento de um cavalo
        if(peca instanceof Cavalo){
            if(!(tab[linhaOrigem][colunaOrigem-97].getPecaOcupada() instanceof Cavalo))
                return false;
            if(movimentoCavalo(linhaOrigem, linhaDestino, colunaOrigem, colunaDestino, peca)){
                verificaXeque(linhaDestino, colunaDestino, peca);
                return true;
            }
        }    
                       
        //se for movimento de um peao        
        if(peca instanceof Peao){
            if(!(tab[linhaOrigem][colunaOrigem-97].getPecaOcupada() instanceof Peao)){
                return false;
            }
                
            if(movimentoPeao(linhaOrigem, linhaDestino, colunaOrigem, colunaDestino, peca)){
                verificaXeque(linhaDestino, colunaDestino, peca);
                return true;
            }
        }    
                        
        //se for movimento de um rei        
        if(peca instanceof Rei){
            if(!(tab[linhaOrigem][colunaOrigem-97].getPecaOcupada() instanceof Rei))
                return false;
            if(movimentoRei(linhaOrigem, linhaDestino, colunaOrigem, colunaDestino, peca)){
                verificaXeque(linhaDestino, colunaDestino, peca);
                return true; 
            }
        }    
                        
        //se for movimento de dama    
        if(peca instanceof Dama){
            if(!(tab[linhaOrigem][colunaOrigem-97].getPecaOcupada() instanceof Dama))
                return false;
            if(movimentoGeral(linhaOrigem, linhaDestino, colunaOrigem, colunaDestino, peca)){
                verificaXeque(linhaDestino, colunaDestino, peca);
                return true;
            }
        }    
                         
        //se for movimento de bispo
        if(peca instanceof Bispo){
            if(!(tab[linhaOrigem][colunaOrigem-97].getPecaOcupada() instanceof Bispo))
                return false;
            if(movimentoGeral(linhaOrigem, linhaDestino, colunaOrigem, colunaDestino, peca)){
                verificaXeque(linhaDestino, colunaDestino, peca);
                return true;
            }
        }    
                        
        //se for movimento de torre
        if(peca instanceof Torre){
            if(!(tab[linhaOrigem][colunaOrigem-97].getPecaOcupada() instanceof Torre))
                return false;
            if(movimentoGeral(linhaOrigem, linhaDestino, colunaOrigem, colunaDestino, peca)){
                verificaXeque(linhaDestino, colunaDestino, peca);
                return true;
            }
        }    

        //se for movimento de rei
        if(peca instanceof Rei){
            if(!(tab[linhaOrigem][colunaOrigem-97].getPecaOcupada() instanceof Rei))
                return false;
            if(movimentoRei(linhaOrigem, linhaDestino, colunaOrigem, colunaDestino, peca))
                return true;    
        }        
                
        return false;        
    }

    private boolean movimentoPeao(int linhaOrigem, int linhaDestino, char colunaOrigem, char colunaDestino, Peca peca){
        
        if(peca.getInicio()){
            if(limiteTabuleiro(linhaDestino, colunaDestino))    
                if(peca.checaMovimento(linhaOrigem, colunaOrigem, linhaDestino, colunaDestino) || checaPeao(linhaOrigem, colunaOrigem,linhaDestino, colunaDestino, peca)){
                    if(colunaOrigem != colunaDestino){
                        matarPeca(linhaDestino, colunaDestino);
                        executaMovimento(linhaOrigem, linhaDestino, colunaOrigem, colunaDestino, peca);
                        peca.foraInicio();
                        return true;
                    }

                    if(colunaOrigem == colunaDestino)
                        if(verificarCaminho(linhaOrigem, linhaDestino, colunaOrigem, colunaDestino, peca)){
                            executaMovimento(linhaOrigem, linhaDestino, colunaOrigem, colunaDestino, peca);
                            peca.foraInicio();
                            return true;
                        }
                }
        }

        if(peca.getInicio() == false)
            if(limiteTabuleiro(linhaDestino, colunaDestino))    
                if(peca.checaMovimento(linhaOrigem, colunaOrigem, linhaDestino, colunaDestino) || checaPeao(linhaOrigem, colunaOrigem,linhaDestino, colunaDestino, peca)){
                    if(colunaOrigem != colunaDestino){
                        matarPeca(linhaDestino, colunaDestino);
                        executaMovimento(linhaOrigem, linhaDestino, colunaOrigem, colunaDestino, peca);  
                        return true;
                    }

                    if(colunaOrigem == colunaDestino)
                        if(verificarCaminho(linhaOrigem, linhaDestino, colunaOrigem, colunaDestino, peca)){
                            executaMovimento(linhaOrigem, linhaDestino, colunaOrigem, colunaDestino, peca);
                            return true;
                        }
                }
        
        return false;
    }

    private boolean movimentoCavalo(int linhaOrigem, int linhaDestino, char colunaOrigem, char colunaDestino, Peca peca){
        //verifica se o movimento esta na limite do tabuleiro
        if(limiteTabuleiro(linhaDestino, colunaDestino))
            //verifica se o movimento eh adequado para a peca
            if(peca.checaMovimento(linhaOrigem, colunaOrigem, linhaDestino, colunaDestino)){
                //se a peca eh branca e a casa eh ocupada por uma peca preta(matar a peca preta)
                if(peca.getBranco() && casaOcupada(linhaDestino, colunaDestino) == 1){
                    matarPeca(linhaDestino, colunaDestino);
                    executaMovimento(linhaOrigem, linhaDestino, colunaOrigem, colunaDestino, peca);
                    return true;
                }

                //se a peca eh preta e a casa eh ocupada por uma peca branca(matar a peca branca)
                if((peca.getBranco() == false) && casaOcupada(linhaDestino, colunaDestino) == 0){
                    matarPeca(linhaDestino, colunaDestino);
                    executaMovimento(linhaOrigem, linhaDestino, colunaOrigem, colunaDestino, peca);
                    return true;
                }                    
                     
                //se a casa destino estiver vazia (independe da cor da peca)
                if(casaOcupada(linhaDestino, colunaDestino) == 2){
                    executaMovimento(linhaOrigem, linhaDestino, colunaOrigem, colunaDestino, peca);
                    return true;
                }
            }    
                        
        return false;
    }

    private boolean movimentoGeral(int linhaOrigem, int linhaDestino, char colunaOrigem, char colunaDestino, Peca peca){
        //verifica se o movimento esta na limite do tabuleiro
        if(limiteTabuleiro(linhaDestino, colunaDestino))
            //verifica se o movimento eh adequado para a peca
            if(peca.checaMovimento(linhaOrigem, colunaOrigem, linhaDestino, colunaDestino))
                //verifica se nao ha pecas no deslocamento impedindo
                if(verificarCaminho(linhaOrigem, linhaDestino, colunaOrigem, colunaDestino, peca)){
                    //se a peca eh branca e a casa eh ocupada por uma peca preta(matar a peca preta)
                    if(peca.getBranco() && casaOcupada(linhaDestino, colunaDestino) == 1){
                            matarPeca(linhaDestino, colunaDestino);
                            executaMovimento(linhaOrigem, linhaDestino, colunaOrigem, colunaDestino, peca);
                            return true;   
                    }

                    //se a peca eh preta e a casa eh ocupada por uma peca branca(matar a peca branca)
                    if((peca.getBranco() == false) && (casaOcupada(linhaDestino, colunaDestino) == 0)){
                        matarPeca(linhaDestino, colunaDestino);
                        executaMovimento(linhaOrigem, linhaDestino, colunaOrigem, colunaDestino, peca);
                        return true;
                    }                    
                     
                    //se a casa destino estiver vazia (independe da cor da peca)
                    if(casaOcupada(linhaDestino, colunaDestino) == 2){
                        executaMovimento(linhaOrigem, linhaDestino, colunaOrigem, colunaDestino, peca);
                        return true;
                    }
                }    
                        
        return false;                     
    }

    private boolean movimentoRei(int linhaOrigem, int linhaDestino, char colunaOrigem, char colunaDestino, Peca peca){
        //verifica se o movimento esta na limite do tabuleiro
        if(limiteTabuleiro(linhaDestino, colunaDestino))
            //verifica se o movimento eh adequado para a peca
            if(peca.checaMovimento(linhaOrigem, colunaOrigem, linhaDestino, colunaDestino)){
                //se a peca eh branca e a casa eh ocupada por uma peca preta(matar a peca preta)
                if(peca.getBranco() && casaOcupada(linhaDestino, colunaDestino) == 1){
                    boolean cor = true;
                    Peca origem = tab[linhaOrigem][colunaOrigem-97].getPecaOcupada();
                    Peca destino = tab[linhaDestino][colunaDestino-97].getPecaOcupada();
                    matarPeca(linhaDestino, colunaDestino);
                    executaMovimento(linhaOrigem, linhaDestino, colunaOrigem, colunaDestino, peca);
                    if(peca.getBranco()){
                        cor = true;
                        reiB = tab[linhaDestino][colunaDestino-97];
                    }
                        
                    if(peca.getBranco() == false){
                        cor = false;
                        reiP = tab[linhaDestino][colunaDestino-97]; 
                    }
                           
                    

                    if(xeque(cor) == false)
                        return true;
                    else{
                        retornaMovimentoMatar(linhaOrigem, linhaDestino, colunaOrigem, colunaDestino, origem , destino);  
                        if(peca.getBranco())
                            reiB = tab[linhaOrigem][colunaOrigem-97];
                        if(peca.getBranco() == false)
                            reiP = tab[linhaOrigem][colunaOrigem-97]; 
                    }   
                }

                //se a peca eh preta e a casa eh ocupada por uma peca branca(matar a peca branca)
                if((peca.getBranco() == false) && casaOcupada(linhaDestino, colunaDestino) == 0){
                    boolean cor=true;
                    Peca origem = tab[linhaOrigem][colunaOrigem-97].getPecaOcupada();
                    Peca destino = tab[linhaDestino][colunaDestino-97].getPecaOcupada();
                    matarPeca(linhaDestino, colunaDestino);
                    executaMovimento(linhaOrigem, linhaDestino, colunaOrigem, colunaDestino, peca);
                    if(peca.getBranco()){
                        cor = true;
                        reiB = tab[linhaDestino][colunaDestino-97];
                    }
                        
                    if(peca.getBranco() == false){
                        cor = false;
                        reiP = tab[linhaDestino][colunaDestino-97]; 
                    }   
                    

                    if(xeque(cor) == false)
                        return true;
                    else{
                        retornaMovimentoMatar(linhaOrigem, linhaDestino, colunaOrigem, colunaDestino, origem , destino);  
                        if(peca.getBranco())
                            reiB = tab[linhaOrigem][colunaOrigem-97];
                        if(peca.getBranco() == false)
                            reiP = tab[linhaOrigem][colunaOrigem-97]; 
                    }
                }                    
                     
                //se a casa destino estiver vazia (independe da cor da peca)
                if(casaOcupada(linhaDestino, colunaDestino) == 2){
                    boolean cor=true;
                    Peca origem = tab[linhaOrigem][colunaOrigem-97].getPecaOcupada();
                    executaMovimento(linhaOrigem, linhaDestino, colunaOrigem, colunaDestino, peca);
                    if(peca.getBranco()){
                        cor = true;
                        reiB = tab[linhaDestino][colunaDestino-97];
                    }
                        
                    if(peca.getBranco() == false){
                        cor = false;
                        reiP = tab[linhaDestino][colunaDestino-97]; 
                    }   
                    

                    if(xeque(cor) == false)
                        return true;
                    else{
                        retornaMovimento(linhaOrigem, linhaDestino, colunaOrigem, colunaDestino, origem); 
                        if(peca.getBranco())
                            reiB = tab[linhaOrigem][colunaOrigem-97];
                        if(peca.getBranco() == false)
                            reiP = tab[linhaOrigem][colunaOrigem-97]; 
                    }
                        
                }
            }    
                        
        return false;
    }

    private void executaMovimento(int linhaOrigem, int linhaDestino, char colunaOrigem, char colunaDestino, Peca peca){
        tab[linhaOrigem][colunaOrigem-97].setOcupada(false);
        tab[linhaDestino][colunaDestino-97].setPecaOcupada(peca);
        tab[linhaDestino][colunaDestino-97].setOcupada(true);
    }

    private void matarPeca(int linha, char coluna){
        this.tab[linha][coluna-97].getPecaOcupada().setEmJogo(false);
        this.tab[linha][coluna-97].setOcupada(false);
    }

    private boolean limiteTabuleiro(int linhaDestino, char colunaDestino){
        if(  0 <= linhaDestino && linhaDestino <= 7 && 'a' <= colunaDestino && colunaDestino <= 'h')
            return true;
        else
            return false;   
    }

    private int casaOcupada(int linhaDestino, char colunaDestino){
        //caso da casa ser ocupada por peca branca
        if(tab[linhaDestino][colunaDestino-97].getOcupada() && tab[linhaDestino][colunaDestino-97].getPecaOcupada().getBranco())
            return 0;
        //caso da casa ser ocupada por peca preta    
        if(tab[linhaDestino][colunaDestino-97].getOcupada() && (tab[linhaDestino][colunaDestino-97].getPecaOcupada().getBranco() == false))
            return 1;
        //casa vazia    
        if(tab[linhaDestino][colunaDestino-97].getOcupada() == false)
            return 2;   
        return 3;        
    }

    private boolean verificaXeque(int linhaOrigem,char colunaOrigem, Peca peca){
       
        int linhaRei ;
        char colunaRei;

        if(peca.getBranco()){
            linhaRei = getReiPreto().getLinha();
            colunaRei = getReiPreto().getColuna();
        }  
        else{
            linhaRei = getReiBranco().getLinha();
            colunaRei = getReiBranco().getColuna();  
        }    


        if(peca instanceof Peao)
            if(verificaXequePeao(linhaOrigem, colunaOrigem, linhaRei, colunaRei, peca)){
                if(peca.getBranco())
                    this.xequeP = true;
                if(peca.getBranco() == false)
                    this.xequeB = true;    
                return true;
            }
                
        
        if(peca instanceof Torre)
            if(verificaXequeGeral(linhaOrigem, colunaOrigem, linhaRei, colunaRei, peca)){
                if(peca.getBranco())
                    this.xequeP = true;
                if(peca.getBranco() == false)
                    this.xequeB = true; 
                return true;
            }
                
        if(peca instanceof Bispo)
            if(verificaXequeGeral(linhaOrigem, colunaOrigem, linhaRei, colunaRei, peca)){
                if(peca.getBranco())
                    this.xequeP = true;
                if(peca.getBranco() == false)
                    this.xequeB = true; 
                return true;
            }
                
        
        if(peca instanceof Cavalo)
            if(verificaXequeCavalo(linhaOrigem, colunaOrigem, linhaRei, colunaRei, peca)){
                if(peca.getBranco())
                    this.xequeP = true;
                if(peca.getBranco() == false)
                    this.xequeB = true; 
                return true;
            }
                
        
        if(peca instanceof Dama)
            if(verificaXequeGeral(linhaOrigem, colunaOrigem, linhaRei, colunaRei, peca)){
                if(peca.getBranco())
                    this.xequeP = true;
                if(peca.getBranco() == false)
                    this.xequeB = true; 
                return true;
            }
                
        return false;
    }

    private boolean xeque(boolean branco){
        if(branco){
            for(int i=0; i<8; i++)
                for(int j=0; j<8; j++)
                    if(tab[i][j].getOcupada())                    
                        if(tab[i][j].getPecaOcupada().getBranco() == false) 
                            if(verificaXeque(tab[i][j].getLinha(),tab[i][j].getColuna(),tab[i][j].getPecaOcupada())){
                                this.xequeB = true;
                                return true;
                            }
    
            this.xequeB = false;
            return false;
        } 
        
        if(branco == false){
            for(int i=0; i<8; i++)
                for(int j=0; j<8; j++)
                    if(tab[i][j].getOcupada())                    
                        if(tab[i][j].getPecaOcupada().getBranco()) 
                            if(verificaXeque(tab[i][j].getLinha(),tab[i][j].getColuna(),tab[i][j].getPecaOcupada())){
                                this.xequeP = true;
                                return true;
                        }

        this.xequeP = false;
        return false;
        }

        return false;
    }

    public void desenho()
    {
        //imprimir apenas o tabuleiro vazio que nao depende da classe peca
        for(int k=8,i=0,x=0; i<8; i++,k--)
            for(int j=0; j<8; j++)
            {
                if(x==0)
                {
                    if(j==7)
                        if(tab[i][j].getOcupada())
                            System.out.println("|  " + tab[i][j].getPecaOcupada().desenho() + " | " + k );
                        else    
                            System.out.println("|    | " + k);
                    else
                    {
                        if(tab[i][j].getOcupada()){
                            System.out.print("|  " + tab[i][j].getPecaOcupada().desenho() + " ");
                            x=1;
                        }    
                        else{    
                            System.out.print("|    ");
                            x=1;
                        }    
                    }
                    
                }
                else
                {
                    if(j==7)
                        if(tab[i][j].getOcupada())
                            System.out.println("|* " + tab[i][j].getPecaOcupada().desenho() + " | " + k);
                        else    
                            System.out.println("|*   | " + k);
                    else
                    {
                        if(tab[i][j].getOcupada()){
                            System.out.print("|* " + tab[i][j].getPecaOcupada().desenho() + " ");
                            x=0;
                        }    
                        else{    
                            System.out.print("|*   ");
                            x=0;
                        }
                    }
                }
            }

        System.out.println("   a    b    c    d    e    f    g    h");    
    } 

    private boolean verificarCaminho(int linhaOrigem, int linhaDestino, char colunaOrigem, char colunaDestino, Peca peca){
        //verificar se o caminho e de uma torre
        if(peca instanceof Torre){
            //se for movimento horizontal
            if(linhaOrigem == linhaDestino)
                if(verificaHorizontal(linhaOrigem, colunaOrigem, colunaDestino))
                    return true;
            //se for movimento vertical        
            if(colunaOrigem == colunaDestino)
                if(verificaVertical(linhaOrigem, linhaDestino, colunaOrigem))
                    return true;      
        }

        //verificar se o caminho e de um bispo    
        if(peca instanceof Bispo)
            if(verificaDiagonal(linhaOrigem, linhaDestino, colunaOrigem, colunaDestino))
                return true;
        
        //verificar se o caminho e de um peao    
        if(peca instanceof Peao)
            if(verificaVertical(linhaOrigem, linhaDestino, colunaOrigem))
                return true;
          
        //verificar se o caminho e de uma dama
        if(peca instanceof Dama){
            //se for movimento horizontal
            if(linhaOrigem == linhaDestino)
                if(verificaHorizontal(linhaOrigem, colunaOrigem, colunaDestino))
                    return true;
            //se for movimento vertical        
            if(colunaOrigem == colunaDestino)
                if(verificaVertical(linhaOrigem, linhaDestino, colunaOrigem))      
                    return true; 
            //se for movimento diagonal        
            if((linhaOrigem - linhaDestino) == (colunaOrigem - colunaDestino) || ((linhaOrigem - linhaDestino) + (colunaOrigem - colunaDestino) == 0))
                if(verificaDiagonal(linhaOrigem, linhaDestino, colunaOrigem, colunaDestino))    
                    return true;   
        }   

        return false;
    }

    private boolean verificaDiagonal(int linhaOrigem, int linhaDestino, char colunaOrigem, char colunaDestino){
        int i,j;
        //diagonal cima - direita
        if(linhaOrigem < linhaDestino && colunaOrigem < colunaDestino)
            for( i=linhaOrigem+1, j=(colunaOrigem-97)+1 ; i<linhaDestino && j<colunaDestino-97; i++ , j++)
                if(tab[i][j].getOcupada())
                    return false;

        //diagonal cima - esquerda
        if(linhaOrigem < linhaDestino && colunaOrigem > colunaDestino)
            for( i=linhaOrigem+1, j=(colunaOrigem-97)-1 ; i<linhaDestino && j>colunaDestino-97; i++ , j--)
                if(tab[i][j].getOcupada())
                    return false;
        
        //diagonal baixo - direita
        if(linhaOrigem > linhaDestino && colunaOrigem < colunaDestino)
            for( i=linhaOrigem-1, j=(colunaOrigem-97)+1 ; i>linhaDestino && j<colunaDestino-97; i--, j++)
                if(tab[i][j].getOcupada())
                    return false;        

        //diagonal baixo - esquerda
        if(linhaOrigem > linhaDestino && colunaOrigem > colunaDestino)
            for( i=linhaOrigem-1, j=(colunaOrigem-97)-1 ; i>linhaDestino && j>colunaDestino-97; i-- , j--)
                if(tab[i][j].getOcupada())
                    return false;            
        
        return true;
    }

    private boolean verificaVertical(int linhaOrigem, int linhaDestino, char colunaOrigem){
        //verifica vertical para cima
        if(linhaOrigem < linhaDestino)
            for(int i = linhaOrigem+1; i<linhaDestino; i++)
                if(tab[i][colunaOrigem-97].getOcupada())
                    return false;
        //verifica vertical para baixo            
        if(linhaOrigem > linhaDestino)
            for(int j = linhaOrigem-1; j>linhaDestino; j--)
                if(tab[j][colunaOrigem-97].getOcupada())
                    return false;        
        //caso nao tenha peca no caminho retorna true
        return true;
    }

    private boolean verificaHorizontal(int linhaOrigem, char colunaOrigem, char colunaDestino){
        //verifica horizontal para cima
        if(colunaOrigem < colunaDestino)
            for(int i = (colunaOrigem-97)+1; i<colunaDestino-97; i++)
                if(tab[linhaOrigem][i].getOcupada())
                    return false;
        //verifica horizontal para baixo            
        if(colunaOrigem > colunaDestino)
            for(int j = (colunaOrigem-97)-1; j>colunaDestino-97; j--)
                if(tab[linhaOrigem][j].getOcupada())
                    return false;        
        //caso nao tenha peca no caminho retorna true
        return true;
    }   

    private boolean checaPeao(int linhaOrigem,char colunaOrigem,int linhaDestino, char colunaDestino,Peca peca){
   
        if(peca.getBranco() == false){
            if((linhaDestino == linhaOrigem + 1) && (colunaDestino == colunaOrigem+1 || colunaDestino == colunaOrigem-1))
                if(casaOcupada(linhaDestino, colunaDestino) == 0)
                    return true;
        }

        if(peca.getBranco())
            if((linhaDestino == linhaOrigem - 1) && (colunaDestino == colunaOrigem+1 || colunaDestino == colunaOrigem-1))
                if(casaOcupada(linhaDestino, colunaDestino) == 1)
                    return true;
        
        return false;
    }

    private boolean verificaXequePeao(int linhaOrigem,char colunaOrigem,int linhaRei,char colunaRei, Peca peca){
        if(checaPeao(linhaOrigem, colunaOrigem, linhaRei, colunaRei, peca))
            return true;
        return false;
    }

    private boolean verificaXequeGeral(int linhaOrigem,char colunaOrigem,int linhaRei,char colunaRei, Peca peca){  
        if(peca.checaMovimento(linhaOrigem, colunaOrigem, linhaRei, colunaRei))
            if(verificarCaminho(linhaOrigem, linhaRei, colunaOrigem, colunaRei, peca))
                return true;
        return false;
    }

    private boolean verificaXequeCavalo(int linhaOrigem,char colunaOrigem,int linhaRei,char colunaRei, Peca peca){
        if(peca.checaMovimento(linhaOrigem, colunaOrigem, linhaRei, colunaRei))
            return true;
        return false;
    }

    private Posicao getReiBranco(){
        return this.reiB;
    }

    private Posicao getReiPreto(){
        return this.reiP;
    }

    private void retornaMovimentoMatar(int linhaOrigem, int linhaDestino, char colunaOrigem, char colunaDestino, Peca origem, Peca destino){
        
        executaMovimento(linhaDestino, linhaOrigem, colunaDestino, colunaOrigem, origem);

        destino.setEmJogo(true);
        tab[linhaDestino][colunaDestino-97].setPecaOcupada(destino);
        tab[linhaDestino][colunaDestino-97].setOcupada(true);
    }

    private void retornaMovimento(int linhaOrigem, int linhaDestino, char colunaOrigem, char colunaDestino,Peca peca){
        executaMovimento(linhaDestino, linhaOrigem, colunaDestino, colunaOrigem, peca);
    }

    public boolean getXequeB(){
        xeque(true);
        return this.xequeB;
    }

    public boolean getXequeP(){
        xeque(false);
        return this.xequeP;
    }
}

