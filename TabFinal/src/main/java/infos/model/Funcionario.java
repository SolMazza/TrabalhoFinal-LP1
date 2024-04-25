package infos.model;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * class Funcionario
 * @author Sol
 * @author ANtonia
 * @since 18/04/2024
 */
@Entity
@Table(name="Funcionario")
public class Funcionario {

    /**
     * atributos da classe
     * id integer
     * nome String
     * voto List Voto
     */
    @Id
    @GeneratedValue(strategy=GenerationType.TABLE , generator="Funcionario_generator")
    @TableGenerator(name="Funcionario_generator",
            table="chave",
            pkColumnName="id",
            valueColumnName="valor",
            allocationSize=100)
    @Column(name = "id")
    private Integer id;

    @Column(name = "Nome")
    private String Nome;

    @OneToMany(fetch = FetchType.EAGER,  mappedBy = "funcionario", cascade = CascadeType.ALL)
    private List<Voto> votos;

    /**
     * Metodo construtor vazio obrigatorio
     */

    public Funcionario(){}
    /**
     * Metodo Construtor
     * @param nome
     */
    public Funcionario(String nome) {
        Nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public List<Voto> getVotos() {
        return votos;
    }
    public void setVotos(List<Voto> votos) {
        this.votos = votos;
    }


    /**
     * metodo votar
     * @return void Adiciona o voto a lista de votos
     * @param voto
     */
    public void votar(Voto voto){
        if(votos == null){
            votos = new ArrayList<>();
        }
        voto.setFuncionario(this);
        votos.add(voto);
    }

    public int hashCode() {
        return java.util.Objects.hash(super.hashCode(), id);
    }

    @Override
    public String toString() {
        return "Funcionario{" +
                "id=" + id +
                ", Nome='" + Nome + '\'' +
                '}';
    }
}
