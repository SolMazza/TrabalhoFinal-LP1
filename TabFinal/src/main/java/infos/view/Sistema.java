package infos.view;

import infos.exception.ExecuteException;
import infos.util.TecladoUtil;
/**
 * class Funcionario
 * @author Sol
 * @author Antonia
 * @since 18/04/2024
 */

public class Sistema {
    private static boolean sair = false;
    private static Metodo metodo = new Metodo();

    public static void main(String[] args) throws ExecuteException {
     while (!sair){
         metodo.menu();
         int opcao = TecladoUtil.lerInteiro("Selecione uma opcao");
         executaAcao(opcao);
     }
    }

    /**
     * Metodo executaAcao
     * @return void, mostra um switch case que chama os metodos
     * @throws ExecuteException
     * @param opcao
     */
    private static void executaAcao(int opcao) {
        try {
            switch (opcao) {
                case 1:
                    metodo.votar();
                    break;
                case 2:
                    metodo.cadastrarFuncionario();
                    break;
                case 3:
                    metodo.cadastrarRestaurante();
                    break;
                case 4:
                    metodo.listarFuncionario();
                    break;
                case 5:
                    metodo.listarRestaurante();
                    break;
                case 6:
                    metodo.apurarVotos();
                    break;
                case 7:
                    sair = true;
                    break;
                default:
                    System.out.println("Opcao invalida!!");
                    break;
            }
        }catch (ExecuteException e) {
            System.out.println(e.getMessage());
        }
    }
}
