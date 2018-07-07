package pe.gob.sernanp.pda.entities;

import javax.persistence.*;

@NamedQueries({
})
@Entity
@Table(name="t_visitante")
public class Visitante {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "srl_cod_visitante")
  private Integer id;

  @Column(name = "var_nombre")
  private String nombres;

  @Column(name = "var_apellido")
  private String apellidos;

  @Column(name = "srl_cod_documento")
  private String tipoDocumento;

  @Column(name = "var_nro_documento")
  private String dni;

  @Column(name = "dte_fec_nacimiento")
  private String nacimiento;

  @Column(name = "srl_cod_categoria")
  private Integer categoria;

  @Column(name = "srl_cod_pais")
  private String pais;

  @Column(name = "var_sexo")
  private String sexo;

  @Transient
  private Boolean asistio;

  public Boolean getAsistio() {
    return asistio;
  }

  public void setAsistio(Boolean asistio) {
    this.asistio = asistio;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getSexo() {
    return sexo;
  }

  public void setSexo(String sexo) {
    this.sexo = sexo;
  }

  public String getPais() {

    return pais;
  }

  public void setPais(String pais) {
    this.pais = pais;
  }

  public String getNombres() {
    return nombres;
  }

  public void setNombres(String nombres) {
    this.nombres = nombres;
  }

  public String getApellidos() {
    return apellidos;
  }

  public void setApellidos(String apellidos) {
    this.apellidos = apellidos;
  }

  public String getTipoDocumento() {
    return tipoDocumento;
  }

  public void setTipoDocumento(String tipoDocumento) {
    this.tipoDocumento = tipoDocumento;
  }

  public String getDni() {
    return dni;
  }

  public void setDni(String dni) {
    this.dni = dni;
  }

  public String getNacimiento() {
    return nacimiento;
  }

  public void setNacimiento(String nacimiento) {
    this.nacimiento = nacimiento;
  }


  public Integer getCategoria() {
    return categoria;
  }

  public void setCategoria(Integer categoria) {
    this.categoria = categoria;
  }

  @Override
  public String toString() {
    return "Visitante{" +
      "nombres='" + nombres + '\'' +
      ", apellidos='" + apellidos + '\'' +
      ", tipoDocumento='" + tipoDocumento + '\'' +
      ", dni='" + dni + '\'' +
      ", nacimiento='" + nacimiento + '\'' +
      ", categoria='" + categoria + '\'' +
      ", pais='" + pais + '\'' +
      ", sexo='" + sexo + '\'' +
      ", id=" + id +
      ", asistio=" + asistio +
      '}';
  }
}
