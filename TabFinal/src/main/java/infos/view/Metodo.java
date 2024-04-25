package infos.view;

import infos.controller.ApuracaoController;
import infos.exception.ExecuteException;
import infos.model.Funcionario;
import infos.model.Restaurante;
import infos.repository.RestauranteRepository;
import infos.util.TecladoUtil;

import java.util.List;

/**
 * class Funcionario
 * @author Sol
 * @author Antonia
 * @since 18/04/2024
 */
public class Metodo {

    public static ApuracaoController controller = new ApuracaoController();

    /**
     * Metodo construtor vazio
     */
    public Metodo() {}

    /**
     * Metodo printfMenu
     * @param mensagem
     * @return void, modelo de souf
     */
    private void printfMenu(String mensagem){
        int larguraTotal = 40;
        int espacosAntes = (larguraTotal - mensagem.length()) / 2;
        int espacosDepois = larguraTotal - espacosAntes - mensagem.length();
        String espacosAntesStr = String.format("%" + espacosAntes + "s", "");
        String espacosDepoisStr = String.format("%" + espacosDepois + "s", "");

        System.out.printf("%s%s%s%n", espacosAntesStr, mensagem, espacosDepoisStr);
    }

    /**
     * Metodo Menu
     * Return void, apresenta as opcoes
     */
    public void menu(){

        printfMenu("menu");
        printfMenu("1 - Votar");
        printfMenu("2 - Cadastrar Funcionário");
        printfMenu("3 - Cadastrar Restaurante");
        printfMenu("4 - Listar Funcionários");
        printfMenu("5 - Listar Restaurantes");
        printfMenu("6 - Apurar Votação");

    }

    /**
     * Metodo Votar
     * Return void
     * @throws ExecuteException
     */
    public void votar() throws ExecuteException{
        RestauranteRepository restauranteRepository = new RestauranteRepository();
        printfMenu("CADASTRAR VOTO");

        printfMenu("---- FUNCIONARIO ----");
        String nomeFuncionario = TecladoUtil.lerString("Digite o nome do Funcionario");
        String nomeRestaurante;
        int idRestaurante;

        printfMenu("---- RESTAURANTE ----");
        printfMenu("1-LISTAR/SELECIONAR UM RESTAURANTE");
        printfMenu("2-CADASTRAR NOVO RESTAURANTE");
        int opcao = TecladoUtil.lerInteiro("Informa uma Opcao:");
        switch (opcao){
            case 1:
                List<Restaurante> restaurantes = controller.listarRestaurante();
                printfMenu("LISTA DE Restaurante");
                for (Restaurante restaurante : restaurantes) {
                    System.out.println(restaurante);
                }
                idRestaurante = TecladoUtil.lerInteiro("Digite o codigo do Restaurante");
                restauranteRepository.buscarID(idRestaurante);
                controller.votar(nomeFuncionario, idRestaurante);
                System.out.println("VOTO REGISTRADO");
                break;

            case 2:
                nomeRestaurante = TecladoUtil.lerString("Digite o nome do Restaurante");
                controller.adicionarRestaurante(nomeRestaurante);
                controller.votar(nomeFuncionario, nomeRestaurante);
                System.out.println("VOTO REGISTRADO");
                break;

            default:
                printfMenu("OPCAO INVALIDA!!");
                break;
        }


    }

    /**
     * Metodo cadastrarFuncionario
     * Return void
     * @throws ExecuteException
     */

    public void cadastrarFuncionario() throws ExecuteException{
        printfMenu("CADASTRAR FUNCIONARIO");

        String nome = TecladoUtil.lerString("Digite o nome do Funcionario");
        controller.adicionarFuncionario(nome);
        printfMenu(nome.toUpperCase() + " CADASTRADO!");
    }

    /**
     * Metodo cadastrarRestaurante
     * Return void
     * @throws ExecuteException
     */
    public void cadastrarRestaurante() throws ExecuteException{
        printfMenu("CADASTRAR RESTAURANTE");

        String nome = TecladoUtil.lerString("Digite o nome do Restaurante");
        controller.adicionarRestaurante(nome);
        printfMenu(nome.toUpperCase() + " CADASTRADO!");
    }

    /**
     * Metodo listarFuncionario
     * Return void
     * @throws ExecuteException
     */
    public void listarFuncionario() throws ExecuteException {
        List<Funcionario> funcionarios = controller.listarFuncionario();

        printfMenu("LISTA DE FUNCIOANRIOS");
        for (Funcionario funcionario : funcionarios) {
            System.out.println(funcionario);
        }

    }

    /**
     * Metodo listarRestaurante
     * Return void
     * @throws ExecuteException
     */

    public void listarRestaurante() throws ExecuteException {
        List<Restaurante> restaurantes = controller.listarRestaurante();

        printfMenu("LISTA DE Restaurante");
        for (Restaurante restaurante : restaurantes) {
            System.out.println(restaurante);
        }

    }

    /**
     * Metodo apurarVotos
     * Return void
     * @throws ExecuteException
     */
    public void apurarVotos() {
        printfMenu("APURACAO DOS VOTOS");
        controller.apurarVotacao();
    }
}



