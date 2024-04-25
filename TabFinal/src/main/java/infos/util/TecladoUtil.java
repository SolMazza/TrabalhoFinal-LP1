package infos.util;

import java.util.Scanner;
/**
 * class Funcionario
 * @author Sol
 * @author Antonia
 * @since 18/04/2024
 */
public class TecladoUtil {

    /**
     * Metodo lerString
     * @param mensagem
     */
    public static String lerString(String mensagem) {
        return  inicializaTeclado(mensagem).next();

    }

    /**
     * Metodo lerInteiro
     * @param mensagem
     */
    public static  Integer lerInteiro(String mensagem) {

        return  inicializaTeclado(mensagem).nextInt();

    }

    /**
     * Metodo inicializaTeclado
     * @param mensagem
     * incia o Scanner do Teclado
     */
    private static Scanner inicializaTeclado(String mensagem) {
        System.out.println(mensagem);
        return new Scanner(System.in);
    }
}
