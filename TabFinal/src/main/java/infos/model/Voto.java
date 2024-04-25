package infos.model;

import javax.persistence.*;
import java.util.Calendar;

/**
 * class Funcionario
 * @author Sol
 * @author ANtonia
 * @since 18/04/2024
 */
@Entity
@Table(name="Voto")
public class Voto {

    /**
     * atributos da classe
     * id integer
     * data calendar
     * funcionario Funcionario
     * restaurante Restaurante
     */
    @Id
    @GeneratedValue(strategy=GenerationType.TABLE , generator="Voto_generator")
    @TableGenerator(name="Voto_generator",
            table="chave",
            pkColumnName="id",
            valueColumnName="valor",
            allocationSize=100)
    @Column(name = "id")
    private Integer id;

    @Temporal(TemporalType.DATE)
    @Column(name = "Data")
    private Calendar data;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_funcionario", referencedColumnName = "id")
    private Funcionario funcionario;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_restaurante", referencedColumnName = "id")
    private Restaurante restaurante;

    /**
     * metodo construtor vazio
     */
    public Voto() {
    }

    /**
     * metodo construtor
     * @param data
     * @param funcionario
     * @param restaurante
     */
    public Voto(Calendar data, Funcionario funcionario, Restaurante restaurante) {
        this.data = data;
        this.funcionario = funcionario;
        this.restaurante = restaurante;


    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Calendar getData() {
        return data;
    }

    public void setData(Calendar data) {
        this.data = data;
    }

    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        Voto voto = (Voto) object;
        return java.util.Objects.equals(id, voto.id);
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public Restaurante getRestaurante() {
        return restaurante;
    }

    public int hashCode() {
        return java.util.Objects.hash(super.hashCode(), id);
    }

    @Override
    public String toString() {
        return "Voto{" +
                "id=" + id +
                ", data=" + data +
                ", funcionario=" + funcionario +
                ", restaurante=" + restaurante +
                '}';
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public void setRestaurante(Restaurante restaurante) {
        this.restaurante = restaurante;
    }
}