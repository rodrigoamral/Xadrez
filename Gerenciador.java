/* 
Classe que vai disparar o jogo informando quem são os dois jogadores e a 
suas escolhas de peças. Representa a classe principal (com o main)

*/
package pacote1;

import java.io.File;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Gerenciador {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        Scanner scan2 = new Scanner(System.in);
        int a = 0;
        System.out.println("Escolha uma opção : (digite o numero corresponte a sua escolha)");
        System.out.println("1 - Novo jogo");
        System.out.println("2 - Carregar jogo");
        
        while(true){    
            try{   
                a = scan.nextInt();
                if(a == 1 || a == 2)
                    break;
                else
                    System.out.println("Entrada inválida, digite novamente!");    
            }catch(InputMismatchException x){
                System.out.println("Entrada inválida, digite novamente!");
                scan.nextLine();
            }  
        }    

        String file = "a";
        
        if(a == 1){
            
            while(true){
                while(true){
                    try{
                        System.out.println("Digite o nome do arquivo em que deseja salvar o progresso de seu jogo:");
                        file = scan2.nextLine();
                        break;
                    }catch(Exception x){
                        System.out.println("Entrada inválida, digite novamente!");
                    }
                }
                

                try {
                    File myObj = new File(file + ".txt");
                    if (myObj.createNewFile()) {
                      System.out.println("Arquivo criado: " + myObj.getName());
                      System.out.println("Digite o nome do jogador que terá as peças brancas , em sequida , o nome do jogador que terá as peças pretas:");
                      String jogador1 = scan.next();
                      String jogador2 = scan.next();
          
          
                      Jogo jogo = new Jogo(jogador1, jogador2,myObj);
                      jogo.jogar();
                      break;
                    } else {
                      System.out.println("Arquivo já existe , digite outro nome para o arquivo !");
                    }
                }catch (IOException e) {
                    System.out.println("Entrada inválida, digite novamente!");
                }    
            }



        }

        else if( a ==2){
            while(true){
                while(true){
                    try{
                        System.out.println("Digite o nome do arquivo a ser carregado:");
                        file = scan2.nextLine();
                        break;
                    }catch(Exception x){
                        System.out.println("Entrada inválida, digite novamente!");
                    }
                }

                try{  
                    File myObj = new File(file + ".txt");
                    if (!myObj.createNewFile()) {
                        Jogo jogo = new Jogo(myObj);
                        jogo.jogar();
                        break;
                    }
                    else{
                        System.out.println("Arquivo inexistente,digite novamente !");
                    }
                }catch (IOException e) {
                    System.out.println("Entrada inválida, digite novamente!");
                }
            }

        }
        
        scan.close();
        scan2.close();
        System.out.println("Fim do programa!");
    }

    
}