package infos.controller;

import infos.repository.*;
import infos.exception.*;
import infos.model.*;
import javax.persistence.PersistenceException;
import java.util.Calendar;
import java.util.List;

/**
 * Classe ApuracaoController
 * @author Sol
 * @author Maria Antonia
 * @since 18/04/2024
 */
public class ApuracaoController {

    private FuncionarioRepository funcionarioRepository;
    private RestauranteRepository restauranteRepository;
    private VotoRepository votoRepository;

    /**
     * metodo construtor
     */
    public ApuracaoController() {
        this.funcionarioRepository = new FuncionarioRepository();
        this.restauranteRepository = new RestauranteRepository();
        this.votoRepository = new VotoRepository();
    }
    /**
     * metodo votar
     * @return void
     * @param funcionarioNome String
     * @param restauranteNome String
     * @throws ExecuteException
     */
    public void votar(String funcionarioNome, String restauranteNome) throws ExecuteException {
        Funcionario funcionario = adicionarFuncionario(funcionarioNome);
        Restaurante restaurante = adicionarRestaurante(restauranteNome);
        Calendar data = Calendar.getInstance();

        votarLogica(funcionario, restaurante, data);
    }
    /**
     * metodo votar(sobrecarga)
     * @return void
     * @param funcionarioNome String
     * @param restauranteid int
     * @throws ExecuteException
     */
    public void votar(String funcionarioNome, int restauranteid) throws ExecuteException {
        Funcionario funcionario = adicionarFuncionario(funcionarioNome);
        Restaurante restaurante = restauranteRepository.buscarID(restauranteid);
        Calendar data = Calendar.getInstance();

        votarLogica(funcionario, restaurante, data);
    }

    private void votarLogica(Funcionario funcionario, Restaurante restaurante, Calendar data) throws ExecuteException {
        if (votoRepository.busca(funcionario, data).isEmpty()) {
            Voto voto = new Voto(data, funcionario, restaurante);
            funcionario.votar(voto);
            funcionarioRepository.atualizar(funcionario);
        } else {
            throw new ExecuteException("Funcionário já votou hoje.");
        }
    }

    /**
     * metodo adicionarFuncionario
     * @return Retorna o Funcionario
     * @param nome String
     * @throws ExecuteException
     */
    public Funcionario adicionarFuncionario(String nome) throws ExecuteException {
        Funcionario funcionario = funcionarioRepository.buscarPorNome(nome);
        if (funcionario == null) {
            funcionario = new Funcionario(nome);
            try {
                funcionarioRepository.inserir(funcionario);
            } catch (PersistenceException e) {
                throw new ExecuteException("Falha ao inserir funcionario");
            }
        }
        return funcionario;
    }

    /**
     * metodo adicionarRestaurante
     * @return Retorna o Restaurante
     * @param nome String
     * @throws ExecuteException
     */
    public Restaurante adicionarRestaurante(String nome) throws ExecuteException {
        Restaurante restaurante = restauranteRepository.buscarPorNome(nome);
        if (restaurante == null) {
            restaurante = new Restaurante(nome);
            try {
                restauranteRepository.inserir(restaurante);
            } catch (PersistenceException e) {
                throw new ExecuteException("Falha ao inserir restaurante");
            }
        }
        return restaurante;
    }


    public void removerFuncionario(int id) throws ExecuteException{
        Funcionario funcionario = funcionarioRepository.buscar(id);
        if (funcionario == null){
            throw new ExecuteException("valor inválido");
        }
        funcionarioRepository.remover(funcionario);
    }

    public void removerRestaurante(int id) throws ExecuteException{
        Restaurante restaurante = restauranteRepository.buscarID(id);
        if (restaurante == null){
            throw new ExecuteException("valor inválido");
        }
        restauranteRepository.remover(restaurante);
    }

    /**
     * metodo listarFuncionario
     * @return Retorna uma Lista de Funcionario
     * @throws ExecuteException
     */
    public List<Funcionario> listarFuncionario() throws ExecuteException {
        List<Funcionario> funcionarios = funcionarioRepository.buscar();

        if (funcionarios.isEmpty()) {
            throw new ExecuteException("Não há funcionários registrados");
        }
        return funcionarios;
    }
    /**
     * metodo listarRestaurante
     * @return Retorna uma Lista de Restaurante
     * @throws ExecuteException
     */
    public List<Restaurante> listarRestaurante() throws ExecuteException {
        List<Restaurante> restaurantes = restauranteRepository.buscar();
        if (restaurantes.isEmpty()) {
            throw new ExecuteException("Não há restaurantes registrados");
        }
        return restaurantes;
    }

    /**
     * metodo apurarVotacao
     * @return void
     */
    public void apurarVotacao() {
        Calendar data = Calendar.getInstance();
        List<Voto> votos = votoRepository.apuracao(data);

        if (votos.isEmpty()){
            System.out.println("ainda nao ha votos hoje");
        }
        for(Voto voto : votos) {
            System.out.println("|" + voto.getFuncionario().getNome() + "|");
            System.out.println("|" + voto.getRestaurante().getNome() + "|");
        }
    }
}

