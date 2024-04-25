package infos.repository;

import infos.model.Funcionario;
import infos.model.Voto;

import javax.persistence.*;
import java.util.Calendar;
import java.util.List;

/**
 * class Funcionario
 * @author Sol
 * @author Antonia
 * @since 18/04/2024
 */
public class VotoRepository {

    private EntityManager ent;
    /**
     * metodo construtor
     * inicialização do entity manager
     */
    public VotoRepository() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("trabalhoFinal");
        ent = factory.createEntityManager();
    }
    /**
     * Metodo Buscar
     * @return Lista Voto, retorna uma lista de Voto de um funcionario especifico
     * @param data
     * @param funcionario
     */
    public List<Voto> busca(Funcionario funcionario, Calendar data) {
        TypedQuery<Voto> query = this.ent.createQuery(
                "SELECT e FROM Voto e WHERE e.funcionario = :funcionario AND e.data = :data", Voto.class);

        query.setParameter("funcionario", funcionario);
        query.setParameter("data", data, TemporalType.DATE);

        return query.getResultList();
    }

    /**
     * Metodo Buscar
     * @return Lista Voto, retorna uma lista de Voto de um dia especifico
     * @param data
     */
    public List<Voto> apuracao(Calendar data) {
        TypedQuery<Voto> query = this.ent.createQuery(
                "SELECT e FROM Voto e WHERE e.data = :data", Voto.class);
        query.setParameter("data", data, TemporalType.DATE);

        return query.getResultList();
    }


}
