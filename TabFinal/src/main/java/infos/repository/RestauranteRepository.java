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
public class RestauranteRepository {

    private EntityManager ent;
    /**
     * metodo construtor
     * inicialização do entity manager
     */
    public RestauranteRepository() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("trabalhoFinal");
        ent = factory.createEntityManager();
    }
    /**
     * Metodo Inserir
     * @return void inserir restaurante no banco de dados
     * @param restaurante
     */
    public void inserir(Restaurante restaurante) {
        this.ent.getTransaction().begin();
        this.ent.persist(restaurante);
        this.ent.getTransaction().commit();
    }
    /**
     * Metodo buscarPorNome
     * @param nome String
     * @return Restaurante, retorna Restaurante  pelo nome
     */
    public Restaurante buscarPorNome(String nome) {
        TypedQuery<Restaurante> query = this.ent.createQuery("select e from Restaurante e where e.Nome like :nome", Restaurante.class);
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
     * @return Restaurante, retorna Restaurante pelo id
     */
    public Restaurante buscarID(int id) {
        try {
            return ent.find(Restaurante.class, id);
        } catch (NoResultException e) {
            return null;
        }
    }
    /**
     * Metodo Buscar
     * @return Lista Restaurante, retorna uma lista de Restaurante sem parametro
     */
    public List<Restaurante> buscar() {
        TypedQuery<Restaurante> buscarTodosQuery = this.ent.createQuery("select e from Restaurante e", Restaurante.class);
        return buscarTodosQuery.getResultList();
    }
    /**
     * Metodo Inserir
     * @return void Remover Restaurante no banco de dados
     * @param restaurante
     */

    public void remover(Restaurante restaurante) {
        this.ent.getTransaction().begin();
        this.ent.remove(restaurante);
        this.ent.getTransaction().commit();
    }
}
