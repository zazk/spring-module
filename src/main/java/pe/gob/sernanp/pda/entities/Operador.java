package pe.gob.sernanp.pda.entities;

import javax.persistence.*;

@NamedQueries({
})
@Entity
@Table(name="t_operador")
public class Operador {

  @Id
  @Column(name = "var_cod_operador")
  private String codigo;
  @Column(name = "bol_estado")
  private boolean estado;
  @Column(name = "num_saldo")
  private Double saldo;
  @Column(name = "var_razonsocial")
  private String razonsocial;
  @Column(name = "var_direccion")
  private String direccion;
  @Column(name = "var_telefono")
  private String telefono;
  @Column(name = "var_ruc")
  private String ruc;
  @Column(name = "var_email")
  private String email;
  @Column(name = "var_web")
  private String web;

  public String getCodigo() {
    return codigo;
  }

  public void setCodigo(String codigo) {
    this.codigo = codigo;
  }

  public boolean isEstado() {
    return estado;
  }

  public void setEstado(boolean estado) {
    this.estado = estado;
  }

  public Double getSaldo() {
    return saldo;
  }

  public void setSaldo(Double saldo) {
    this.saldo = saldo;
  }

  public String getRazonsocial() {
    return razonsocial;
  }

  public void setRazonsocial(String razonsocial) {
    this.razonsocial = razonsocial;
  }

  public String getDireccion() {
    return direccion;
  }

  public void setDireccion(String direccion) {
    this.direccion = direccion;
  }

  public String getTelefono() {
    return telefono;
  }

  public void setTelefono(String telefono) {
    this.telefono = telefono;
  }

  public String getRuc() {
    return ruc;
  }

  public void setRuc(String ruc) {
    this.ruc = ruc;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getWeb() {
    return web;
  }

  public void setWeb(String web) {
    this.web = web;
  }

  @Override
  public String toString() {
    return "Operador{" +
      "codigo='" + codigo + '\'' +
      ", estado=" + estado +
      ", saldo=" + saldo +
      ", razonsocial='" + razonsocial + '\'' +
      ", direccion='" + direccion + '\'' +
      ", telefono='" + telefono + '\'' +
      ", ruc='" + ruc + '\'' +
      ", email='" + email + '\'' +
      ", web='" + web + '\'' +
      '}';
  }
}

