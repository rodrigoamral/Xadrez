/*
Essa classe é responsável pelo gerenciamento do jogo, controlando tudo o que acontece no jogo. 
Essa classe contém um tabuleiro, 2 jogadores e o conjunto de 32 peças disponíveis. O jogo sabe o 
estado em que se encontra a cada momento (por exemplo: início do jogo, xeque, xeque-mate). Sabe 
também de que jogador é a vez, controlando as jogadas, as vezes, as checagens, etc, sendo a principal 
responsável pela comunicação com os usuários.Está classe tambem salva as jogadas da partida em um arquivo
e pode tambem carregar uma partida salva em um arquivo para continua-la a seguir.

*/
package pacote1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Jogo {
    private Tabuleiro tabuleiro;
    private Jogador jogador1,jogador2;
    private Peca[] brancas = new Peca[16];
    private Peca[] pretas = new Peca[16];
    private int estado;
    private boolean vez,valido;
    private FileWriter myWriter;
    // estado 0 quer dizer que o jogo iniciou , 1 em xeque e 2 em xeque mate

    public Jogo(String jogador1, String jogador2,File arquivo) throws IOException{
        //iniciar jogadores 
        this.jogador1 = new Jogador(jogador1,true);
        this.jogador2 = new Jogador(jogador2,false);

        //iniciar estado do jogo
        this.estado = 0;

        this.vez = true;
        this.valido = false;

        //iniciar pecas brancas
        for(int i=0; i<8; i++)
            this.brancas[i] = new Peao(true);

        for(int i=8; i<10; i++)
            this.brancas[i] = new Torre(true);

        for(int i=10; i<12; i++)
            this.brancas[i] = new Cavalo(true);      

        for(int i=12; i<14; i++)
            this.brancas[i] = new Bispo(true);
        
        this.brancas[14] = new Rei(true);    
        this.brancas[15] = new Dama(true);

        //inciar pecas pretas
        for(int i=0; i<8; i++)
            this.pretas[i] = new Peao(false);

        for(int i=8; i<10; i++)
            this.pretas[i] = new Torre(false);

        for(int i=10; i<12; i++)
            this.pretas[i] = new Cavalo(false);      

        for(int i=12; i<14; i++)
            this.pretas[i] = new Bispo(false);
        
        this.pretas[14] = new Rei(false);    
        this.pretas[15] = new Dama(false);

        this.jogador1.setConjunto(brancas);
        this.jogador2.setConjunto(pretas);

        //iniciar tabuleiro
        this.tabuleiro = new Tabuleiro(brancas,pretas);

        //abrir escritor
        myWriter = new FileWriter(arquivo);

        //salvar nomes jogadores
        myWriter.write(jogador1 + "\n");
        myWriter.write(jogador2 + "\n");


    }

    public Jogo(File arquivo) throws IOException{
        String jogador1 = "a";
        String jogador2 = "a";



        //abrir escritor
        myWriter = new FileWriter(arquivo,true);
        Scanner myReader = new Scanner(arquivo);

        //carregar jogodores
        try{
            jogador1 = myReader.nextLine();
            jogador2 = myReader.nextLine();
        }catch(Exception x){
            System.out.println("Conteudo do arquivo inválido ! Fim de programa !");
            System.exit(0);
        }



        //iniciar jogadores 
        this.jogador1 = new Jogador(jogador1,true);
        this.jogador2 = new Jogador(jogador2,false);

        //iniciar estado do jogo
        this.estado = 0;

        this.vez = true;
        this.valido = false;

        //iniciar pecas brancas
        for(int i=0; i<8; i++)
            this.brancas[i] = new Peao(true);

        for(int i=8; i<10; i++)
            this.brancas[i] = new Torre(true);

        for(int i=10; i<12; i++)
            this.brancas[i] = new Cavalo(true);      

        for(int i=12; i<14; i++)
            this.brancas[i] = new Bispo(true);
        
        this.brancas[14] = new Rei(true);    
        this.brancas[15] = new Dama(true);

        //inciar pecas pretas
        for(int i=0; i<8; i++)
            this.pretas[i] = new Peao(false);

        for(int i=8; i<10; i++)
            this.pretas[i] = new Torre(false);

        for(int i=10; i<12; i++)
            this.pretas[i] = new Cavalo(false);      

        for(int i=12; i<14; i++)
            this.pretas[i] = new Bispo(false);
        
        this.pretas[14] = new Rei(false);    
        this.pretas[15] = new Dama(false);

        this.jogador1.setConjunto(brancas);
        this.jogador2.setConjunto(pretas);

        //iniciar tabuleiro
        this.tabuleiro = new Tabuleiro(brancas,pretas);     
        
        int linhaOrigem = 0;
        int linhaDestino = 0;
        char colunaOrigem = 0;
        char colunaDestino = 0;
        String peca = "a";
        String linha = "a";
        
        //carregar jogadas
        while (myReader.hasNextLine()) {
            try{
                linha = myReader.nextLine();
            }catch(Exception x){
                System.out.println("Conteudo do arquivo inválido ! Fim de programa !");
                System.exit(0);
            }
            
            try{
                linhaOrigem = Character.getNumericValue(linha.charAt(0));
                linhaDestino = Character.getNumericValue(linha.charAt(4));
                colunaOrigem = linha.charAt(2);
                colunaDestino = linha.charAt(6);
                peca = myReader.nextLine();
            }catch(InputMismatchException x){
                System.out.println("Conteudo do arquivo inválido ! Fim de programa !");
                System.exit(0);
            }

            //arrumar coordenadas
            if(linhaOrigem == 1)
                linhaOrigem = 7; 
            else if(linhaOrigem == 2)
                linhaOrigem = 6;
            else if(linhaOrigem == 3)
                linhaOrigem = 5;
            else if(linhaOrigem == 5)
                linhaOrigem = 3;
            else if(linhaOrigem == 6)
                linhaOrigem = 2;
            else if(linhaOrigem == 7)
                linhaOrigem = 1;
            else if(linhaOrigem == 8)
                linhaOrigem = 0; 

            if(linhaDestino == 1)
                linhaDestino = 7; 
            else if(linhaDestino == 2)
                linhaDestino = 6;
            else if(linhaDestino == 3)
                linhaDestino = 5;
            else if(linhaDestino == 5)
                linhaDestino = 3;
            else if(linhaDestino == 6)
                linhaDestino = 2;
            else if(linhaDestino == 7)
                linhaDestino = 1;
            else if(linhaDestino == 8)
                linhaDestino = 0;  
                
            if(this.vez){
                if(peca.equals("peao")){
                    tabuleiro.movimento(linhaOrigem, linhaDestino, colunaOrigem, colunaDestino, brancas[1]);
                    this.vez = !this.vez;
                }
                if(peca.equals("cavalo")){
                    tabuleiro.movimento(linhaOrigem, linhaDestino, colunaOrigem, colunaDestino, brancas[10]);
                    this.vez = !this.vez;
                }
                if(peca.equals("torre")){
                    tabuleiro.movimento(linhaOrigem, linhaDestino, colunaOrigem, colunaDestino, brancas[8]);
                    this.vez = !this.vez;
                }
                if(peca.equals("bispo")){
                    tabuleiro.movimento(linhaOrigem, linhaDestino, colunaOrigem, colunaDestino, brancas[12]);
                    this.vez = !this.vez;
                }
                if(peca.equals("dama")){
                    tabuleiro.movimento(linhaOrigem, linhaDestino, colunaOrigem, colunaDestino, brancas[15]);
                    this.vez = !this.vez;
                }
                if(peca.equals("rei")){
                    tabuleiro.movimento(linhaOrigem, linhaDestino, colunaOrigem, colunaDestino, brancas[14]);
                    this.vez = !this.vez;
                }
            }
            else{
                if(peca.equals("peao")){
                    tabuleiro.movimento(linhaOrigem, linhaDestino, colunaOrigem, colunaDestino, pretas[1]);
                    this.vez = !this.vez;
                }
                if(peca.equals("cavalo")){
                    tabuleiro.movimento(linhaOrigem, linhaDestino, colunaOrigem, colunaDestino, pretas[10]);
                    this.vez = !this.vez;
                }
                if(peca.equals("torre")){
                    tabuleiro.movimento(linhaOrigem, linhaDestino, colunaOrigem, colunaDestino, pretas[8]);
                    this.vez = !this.vez;
                }
                if(peca.equals("bispo")){
                    tabuleiro.movimento(linhaOrigem, linhaDestino, colunaOrigem, colunaDestino, pretas[12]);
                    this.vez = !this.vez;
                }
                if(peca.equals("dama")){
                    tabuleiro.movimento(linhaOrigem, linhaDestino, colunaOrigem, colunaDestino, pretas[15]);
                    this.vez = !this.vez;
                }
                if(peca.equals("rei")){
                    tabuleiro.movimento(linhaOrigem, linhaDestino, colunaOrigem, colunaDestino, pretas[14]);
                    this.vez = !this.vez;
                }

            }

            
        } 
        
        if(tabuleiro.getXequeB())
            System.out.println("Jogador(a) " + this.jogador1.getNome() + " em xeque");
        if(tabuleiro.getXequeP())
            System.out.println("Jogador(a)" + this.jogador2.getNome() + " em xeque"); 

    }

    public void jogar() throws IOException{
        int linhaOrigem, linhaDestino,opcao;
        String colunaOrigem, colunaDestino , peca ;

        Scanner scan = new Scanner(System.in);

        System.out.println();
        tabuleiro.desenho();



        while(estado != 2){    

            System.out.println("Escolha uma opção: (digite o numero correspondente a sua escolha)");
            System.out.println("1 - Continuar a partida.");
            System.out.println("2 - Pausar a partida.");

            while(true){    
                try{   
                    opcao = scan.nextInt();
                    if(opcao == 1 || opcao == 2)
                        break;
                    else
                        System.out.println("Entrada inválida, digite novamente!");    
                }catch(InputMismatchException x){
                    System.out.println("Entrada inválida, digite novamente!");
                    scan.nextLine();
                }  
            } 

            if(opcao == 2){
                myWriter.close();
                break;
            }
                

            else

            //se vez == true eh a vez do jogador 1
            if(vez){
                System.out.println("Vez do jogador(a) " + jogador1.getNome());
                valido = false;

                
                while(valido == false){
                    
                    System.out.println("Digite a coordenada origem , coordenada destino e peça a ser movida :");

                    while(true){
                        try{
                            linhaOrigem = scan.nextInt();
                            colunaOrigem = scan.next();
                            linhaDestino = scan.nextInt();
                            colunaDestino = scan.next();
                            peca = scan.next();
                            break;
                        }catch(InputMismatchException x){
                            System.out.println("Entrada inválida, digite novamente !");
                            scan.nextLine();

                        }
                    }
    


                    //verifica se a entrada é valida
    
                    if(linhaOrigem < 1 || linhaOrigem > 8){
                        System.out.println("Entrada invalida! Digite novamente.");
                        continue;
                    }
                    
                    if(linhaDestino < 1 || linhaDestino > 8){
                        System.out.println("Entrada invalida! Digite novamente.");
                        continue;
                    }
                    
                    if( !(colunaOrigem.equals("a")) && !(colunaOrigem.equals("b")) && !(colunaOrigem.equals("c")) && !(colunaOrigem.equals("d")) && !(colunaOrigem.equals("e")) && !(colunaOrigem.equals("f")) && !(colunaOrigem.equals("g")) && !(colunaOrigem.equals("h"))){
                        System.out.println("Entrada invalida! Digite novamente.");
                        continue;
                    }
        
                    if( !(colunaDestino.equals("a")) && !(colunaDestino.equals("b")) && !(colunaDestino.equals("c")) && !(colunaDestino.equals("d")) && !(colunaDestino.equals("e")) && !(colunaDestino.equals("f")) && !(colunaDestino.equals("g")) && !(colunaDestino.equals("h"))){
                        System.out.println("Entrada invalida! Digite novamente.");
                        continue;
                    }

                    if( !(peca.equals("peao")) && !(peca.equals("torre")) && !(peca.equals("bispo")) && !(peca.equals("cavalo")) && !(peca.equals("dama")) && !(peca.equals("rei"))){
                        System.out.println("Entrada invalida! Digite novamente.");
                        continue;
                    }

                    
                    //arruma as coordenadas
                    if(linhaOrigem == 1)
                        linhaOrigem = 7; 
                    else if(linhaOrigem == 2)
                        linhaOrigem = 6;
                    else if(linhaOrigem == 3)
                        linhaOrigem = 5;
                    else if(linhaOrigem == 5)
                        linhaOrigem = 3;
                    else if(linhaOrigem == 6)
                        linhaOrigem = 2;
                    else if(linhaOrigem == 7)
                        linhaOrigem = 1;
                    else if(linhaOrigem == 8)
                        linhaOrigem = 0; 

                    if(linhaDestino == 1)
                        linhaDestino = 7; 
                    else if(linhaDestino == 2)
                        linhaDestino = 6;
                    else if(linhaDestino == 3)
                        linhaDestino = 5;
                    else if(linhaDestino == 5)
                        linhaDestino = 3;
                    else if(linhaDestino == 6)
                        linhaDestino = 2;
                    else if(linhaDestino == 7)
                        linhaDestino = 1;
                    else if(linhaDestino == 8)
                        linhaDestino = 0;                         
                    
                    
                    //verifica se o movimento da peca e valida

                    if(peca.equals("peao")){
                        if(tabuleiro.movimento(linhaOrigem, linhaDestino, colunaOrigem.charAt(0), colunaDestino.charAt(0), brancas[1])){                          
                            salvar(arrumarString(linhaOrigem, linhaDestino, colunaOrigem.charAt(0), colunaDestino.charAt(0)));
                            String coordPeca = "peao" + "\n";
                            salvar(coordPeca);
                            valido = true;
                            vez = false;
                            break;
                        }
                        System.out.println("Entrada invalida! Digite novamente.");
                        continue;
                    }
    
                    if(peca.equals("torre")){
                        if(tabuleiro.movimento(linhaOrigem, linhaDestino, colunaOrigem.charAt(0), colunaDestino.charAt(0), brancas[8])){
                            salvar(arrumarString(linhaOrigem, linhaDestino, colunaOrigem.charAt(0), colunaDestino.charAt(0)));
                            String coordPeca = "torre" + "\n";
                            salvar(coordPeca);
                            valido = true;
                            vez = false;
                            break;
                        }
                        System.out.println("Entrada invalida! Digite novamente.");
                        continue;    
                    }
    
                    if(peca.equals("bispo")){
                        if(tabuleiro.movimento(linhaOrigem, linhaDestino, colunaOrigem.charAt(0), colunaDestino.charAt(0), brancas[12])){
                            salvar(arrumarString(linhaOrigem, linhaDestino, colunaOrigem.charAt(0), colunaDestino.charAt(0)));
                            String coordPeca = "bispo" + "\n";
                            salvar(coordPeca);
                            valido = true;
                            vez = false;
                            break;
                        }
                        System.out.println("Entrada invalida! Digite novamente.");
                        continue;
                    }
    
                    if(peca.equals("cavalo")){
                        if(tabuleiro.movimento(linhaOrigem, linhaDestino, colunaOrigem.charAt(0), colunaDestino.charAt(0), brancas[10])){
                            salvar(arrumarString(linhaOrigem, linhaDestino, colunaOrigem.charAt(0), colunaDestino.charAt(0)));
                            String coordPeca = "cavalo" + "\n";
                            salvar(coordPeca);
                            valido = true;
                            vez = false;
                            break;
                        }
                        System.out.println("Entrada invalida! Digite novamente.");
                        continue;
                    }
    
                    if(peca.equals("dama")){
                        if(tabuleiro.movimento(linhaOrigem, linhaDestino, colunaOrigem.charAt(0), colunaDestino.charAt(0), brancas[15])){
                            salvar(arrumarString(linhaOrigem, linhaDestino, colunaOrigem.charAt(0), colunaDestino.charAt(0)));
                            String coordPeca = "dama" + "\n";
                            salvar(coordPeca);
                            valido = true;
                            vez = false;
                            break;
                        }
                        System.out.println("Entrada invalida! Digite novamente.");
                        continue;
                    }
    
                    if(peca.equals("rei")){
                        if(tabuleiro.movimento(linhaOrigem, linhaDestino, colunaOrigem.charAt(0), colunaDestino.charAt(0), brancas[14])){
                            salvar(arrumarString(linhaOrigem, linhaDestino, colunaOrigem.charAt(0), colunaDestino.charAt(0)));
                            String coordPeca = "rei" + "\n";
                            salvar(coordPeca);
                            valido = true;
                            vez = false;
                            break;
                        }
                        System.out.println("Entrada invalida! Digite novamente.");
                        continue;
                    }

                }
                


            }

            else if(vez == false){
                System.out.println("Vez do jogador(a) " + jogador2.getNome());
                valido = false;

                
                while(valido == false){
                    
                    System.out.println("Digite a coordenada origem , coordenada destino e peça a ser movida :");
    
                    while(true){
                        try{
                            linhaOrigem = scan.nextInt();
                            colunaOrigem = scan.next();
                            linhaDestino = scan.nextInt();
                            colunaDestino = scan.next();
                            peca = scan.next();
                            break;
                        }catch(InputMismatchException x){
                            System.out.println("Entrada inválida, digite novamente !");
                            scan.nextLine();
                        }
                    }

                    //verifica se a entrada é valida
    
                    if(linhaOrigem < 1 || linhaOrigem > 8){
                        System.out.println("Entrada invalida! Digite novamente.");
                        continue;
                    }
                    
                    if(linhaDestino < 1 || linhaDestino > 8){
                        System.out.println("Entrada invalida! Digite novamente.");
                        continue;
                    }
                    
                    if( !(colunaOrigem.equals("a")) && !(colunaOrigem.equals("b")) && !(colunaOrigem.equals("c")) && !(colunaOrigem.equals("d")) && !(colunaOrigem.equals("e")) && !(colunaOrigem.equals("f")) && !(colunaOrigem.equals("g")) && !(colunaOrigem.equals("h"))){
                        System.out.println("Entrada invalida! Digite novamente.");
                        continue;
                    }
        
                    if( !(colunaDestino.equals("a")) && !(colunaDestino.equals("b")) && !(colunaDestino.equals("c")) && !(colunaDestino.equals("d")) && !(colunaDestino.equals("e")) && !(colunaDestino.equals("f")) && !(colunaDestino.equals("g")) && !(colunaDestino.equals("h"))){
                        System.out.println("Entrada invalida! Digite novamente.");
                        continue;
                    }

                    if( !(peca.equals("peao")) && !(peca.equals("torre")) && !(peca.equals("bispo")) && !(peca.equals("cavalo")) && !(peca.equals("dama")) && !(peca.equals("rei"))){
                        System.out.println("Entrada invalida! Digite novamente.");
                        continue;
                    }

                    
                    //arruma as coordenadas
                    if(linhaOrigem == 1)
                        linhaOrigem = 7; 
                    else if(linhaOrigem == 2)
                        linhaOrigem = 6;
                    else if(linhaOrigem == 3)
                        linhaOrigem = 5;
                    else if(linhaOrigem == 5)
                        linhaOrigem = 3;
                    else if(linhaOrigem == 6)
                        linhaOrigem = 2;
                    else if(linhaOrigem == 7)
                        linhaOrigem = 1;
                    else if(linhaOrigem == 8)
                        linhaOrigem = 0; 

                    if(linhaDestino == 1)
                        linhaDestino = 7; 
                    else if(linhaDestino == 2)
                        linhaDestino = 6;
                    else if(linhaDestino == 3)
                        linhaDestino = 5;
                    else if(linhaDestino == 5)
                        linhaDestino = 3;
                    else if(linhaDestino == 6)
                        linhaDestino = 2;
                    else if(linhaDestino == 7)
                        linhaDestino = 1;
                    else if(linhaDestino == 8)
                        linhaDestino = 0;                         
                    
                    
                    //verifica se o movimento da peca e valida

                    if(peca.equals("peao")){
                        if(tabuleiro.movimento(linhaOrigem, linhaDestino, colunaOrigem.charAt(0), colunaDestino.charAt(0), pretas[1])){
                            salvar(arrumarString(linhaOrigem, linhaDestino, colunaOrigem.charAt(0), colunaDestino.charAt(0)));
                            String coordPeca = "peao" + "\n";
                            salvar(coordPeca);
                            valido = true;
                            vez = true;
                            break;
                        }
                        System.out.println("Entrada invalida! Digite novamente.");
                        continue;
                    }
    
                    if(peca.equals("torre")){
                        if(tabuleiro.movimento(linhaOrigem, linhaDestino, colunaOrigem.charAt(0), colunaDestino.charAt(0), pretas[8])){
                            salvar(arrumarString(linhaOrigem, linhaDestino, colunaOrigem.charAt(0), colunaDestino.charAt(0)));
                            String coordPeca = "torre" + "\n";
                            salvar(coordPeca);
                            valido = true;
                            vez = true;
                            break;
                        }
                        System.out.println("Entrada invalida! Digite novamente.");
                        continue;    
                    }
    
                    if(peca.equals("bispo")){
                        if(tabuleiro.movimento(linhaOrigem, linhaDestino, colunaOrigem.charAt(0), colunaDestino.charAt(0), pretas[12])){
                            salvar(arrumarString(linhaOrigem, linhaDestino, colunaOrigem.charAt(0), colunaDestino.charAt(0)));
                            String coordPeca = "bispo" + "\n";
                            salvar(coordPeca);
                            valido = true;
                            vez = true;
                            break;
                        }
                        System.out.println("Entrada invalida! Digite novamente.");
                        continue;
                    }
    
                    if(peca.equals("cavalo")){
                        if(tabuleiro.movimento(linhaOrigem, linhaDestino, colunaOrigem.charAt(0), colunaDestino.charAt(0), pretas[10])){
                            salvar(arrumarString(linhaOrigem, linhaDestino, colunaOrigem.charAt(0), colunaDestino.charAt(0)));
                            String coordPeca = "cavalo" + "\n";
                            salvar(coordPeca);
                            valido = true;
                            vez = true;
                            break;
                        }
                        System.out.println("Entrada invalida! Digite novamente.");
                        continue;
                    }
    
                    if(peca.equals("dama")){
                        if(tabuleiro.movimento(linhaOrigem, linhaDestino, colunaOrigem.charAt(0), colunaDestino.charAt(0), pretas[15])){
                            salvar(arrumarString(linhaOrigem, linhaDestino, colunaOrigem.charAt(0), colunaDestino.charAt(0)));
                            String coordPeca = "dama" + "\n";
                            salvar(coordPeca);
                            valido = true;
                            vez = true;
                            break;
                        }
                        System.out.println("Entrada invalida! Digite novamente.");
                        continue;
                    }
    
                    if(peca.equals("rei")){
                        if(tabuleiro.movimento(linhaOrigem, linhaDestino, colunaOrigem.charAt(0), colunaDestino.charAt(0), pretas[14])){
                            salvar(arrumarString(linhaOrigem, linhaDestino, colunaOrigem.charAt(0), colunaDestino.charAt(0)));
                            String coordPeca = "rei" + "\n";
                            salvar(coordPeca);
                            valido = true;
                            vez = true;
                            break;
                        }
                        System.out.println("Entrada invalida! Digite novamente.");
                        continue;
                    }
                }               
            }

            tabuleiro.desenho();
            System.out.println();
            
            if(brancas[14].getEmJogo() == false){
                System.out.println("Xeque Mate");
                System.out.println("Jogador(a) " + jogador2.getNome() + " venceu !");
                break;
            }
            if(pretas[14].getEmJogo() == false){
                System.out.println("Xeque Mate");
                System.out.println("Jogador(a) " + jogador1.getNome() + " venceu !");
                break;
            }
                

            if(tabuleiro.getXequeB())
                System.out.println("Jogador(a) " + jogador1.getNome() + " em xeque");
            if(tabuleiro.getXequeP())
                System.out.println("Jogador(a) " + jogador2.getNome() + " em xeque");  
            System.out.println();
            
            
        }  
        
        scan.close();
        
        
   
    } 

    private void salvar(String jogada) throws IOException{
        myWriter.write(jogada);
    }

    private String arrumarString(int linhaOrigem, int linhaDestino, char colunaOrigem, char colunaDestino){
        int linhaO = 0;
        int linhaD = 0;
        String s;

        if(linhaOrigem == 7)
            linhaO = 1;
        else if(linhaOrigem == 6)
            linhaO = 2;            
        else if(linhaOrigem == 5)
            linhaO = 3;
        else if(linhaOrigem == 3)
            linhaO = 5;
        else if(linhaOrigem == 4)
            linhaO = 4;    
        else if(linhaOrigem == 2)
            linhaO = 6;
        else if(linhaOrigem == 1)
            linhaO = 7;
        else if(linhaOrigem == 0)
            linhaO = 8;    

        if(linhaDestino == 7)
            linhaD = 1;
        else if(linhaDestino == 6)
            linhaD = 2;
        else if(linhaDestino == 5)
            linhaD = 3;
        else if(linhaDestino == 3)
            linhaD = 5;
        else if(linhaDestino == 4)
            linhaD = 4;      
        else if(linhaDestino == 2)
            linhaD = 6;
        else if(linhaDestino == 1)
            linhaD = 7;
        else if(linhaDestino == 7)
            linhaD = 8;   

        s = linhaO + " " + colunaOrigem + " " + linhaD + " " + colunaDestino + "\n";

        return s;
    }


}

