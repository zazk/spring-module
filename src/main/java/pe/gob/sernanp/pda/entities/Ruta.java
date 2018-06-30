package pe.gob.sernanp.pda.entities;

import javax.persistence.*;

@NamedQueries({
})
@Entity
@Table(name="t_ruta")
public class Ruta {
  @Id
  @GeneratedValue
  @Column(name = "srl_cod_ruta")
  private Integer id;
  @Column(name = "bol_estado")
  private boolean estado;
  @Column(name = "num_costo_visitante")
  private Double costoVisitante;
  @Column(name = "var_nombre")
  private String nombre;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public boolean isEstado() {
    return estado;
  }

  public void setEstado(boolean estado) {
    this.estado = estado;
  }

  public Double getCostoVisitante() {
    return costoVisitante;
  }

  public void setCostoVisitante(Double costoVisitante) {
    this.costoVisitante = costoVisitante;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }
}
