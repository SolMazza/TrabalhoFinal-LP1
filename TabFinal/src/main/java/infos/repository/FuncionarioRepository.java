package infos.repository;

import infos.model.*;
import javax.persistence.*;
import java.util.List;

/**
 * class Funcionario
 * @author Sol
 * @author Antonia
 * @since 18/04/2024
 */
public class FuncionarioRepository {

    private EntityManager ent;

    /**
     * metodo construtor
     * inicialização do entity manager
     */
    public FuncionarioRepository() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("trabalhoFinal");
        ent = factory.createEntityManager();
    }

    /**
     * Metodo Inserir
     * @return void inserir funcionario no banco de dados
     * @param funcionario
     */
    public void inserir(Funcionario funcionario) {
        this.ent.getTransaction().begin();
        this.ent.persist(funcionario);
        this.ent.getTransaction().commit();
    }

    /**
     * Metodo Buscar
     * @return Lista Funcionario, retorna uma lista de funcionario sem parametro
     */
    public List<Funcionario> buscar() {
        try {
            TypedQuery<Funcionario> buscarTodosQuery = this.ent.createQuery("select e from Funcionario e", Funcionario.class);
            return buscarTodosQuery.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar os funcionários", e);
        }
    }

    /**
     * Metodo buscarPorNome
     * @param nome String
     * @return funcionario, retorna funcionario pelo nome
     */
    public Funcionario buscarPorNome(String nome) {
        TypedQuery<Funcionario> query = this.ent.createQuery("select e from Funcionario e where e.Nome like :nome", Funcionario.class);
        query.setParameter("nome", nome);

        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
    /**
     * Metodo buscarPorNome
     * @param id  integer
     * @return funcionario, retorna funcionario pelo id
     */
    public Funcionario buscar(Integer id) {
        return this.ent.find(Funcionario.class, id);
    }
    /**
     * Metodo Inserir
     * @return void Atualizar funcionario no banco de dados
     * @param funcionario
     */
    public void atualizar(Funcionario funcionario) {
        this.ent.getTransaction().begin();
        this.ent.merge(funcionario);
        this.ent.getTransaction().commit();
    }
    /**
     * Metodo Inserir
     * @return void Remover funcionario no banco de dados
     * @param funcionario
     */
    public void remover(Funcionario funcionario) {
        this.ent.getTransaction().begin();
        this.ent.remove(funcionario);
        this.ent.getTransaction().commit();
    }
}
