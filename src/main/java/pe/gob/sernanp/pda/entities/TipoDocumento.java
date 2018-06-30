package pe.gob.sernanp.pda.entities;
import javax.persistence.*;

@NamedQueries({
})
@Entity
@Table(name="t_tip_documento")
public class TipoDocumento {
  @Id
  @GeneratedValue
  @Column(name = "srl_cod_documento")
  private Integer id;
  @Column(name = "var_nombre")
  private String nombre;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }
}
