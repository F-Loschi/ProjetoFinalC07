package br.inatel.Model.Uteis;
import java.lang.Thread;
import java.util.Scanner;

import static br.inatel.Model.Personagens.Crianca.felicidade;

public class Util {

    public static void esperaAi(int tempo){
        try{
            Thread.sleep(tempo);
        }
        catch(Exception e){
            System.out.println("Deu não, patrão.");
        }
    }

    public static void diminuirFelicidade(int valor) throws NaoPodeSerTristeException {
        if((felicidade-valor)<0){
            throw new NaoPodeSerTristeException();
        }
        else{
            felicidade-=valor;
        }
    }

    public static int lerOpcaoSegura(String mensagem) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                System.out.print(mensagem);
                String entrada = sc.nextLine().trim();

                if (entrada.isEmpty()) {
                    System.out.println("💫 As estrelas sussurram: entrada vazia! Tente novamente... ✨");
                    continue;
                }

                return Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.println("⚡ Os ventos da magia rejeitam sua escolha! Digite apenas números! 🌟");
            }
        }
    }
}
