package hello.entities;

public class Visitante {
  private String nombres;
  private String apellidos;
  private String tipoDocumento;
  private String dni;
  private String nacimiento;
  private String genero;
  private String categoria;
  private String pais;
  private String sexo;

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

  public String getGenero() {
    return genero;
  }

  public void setGenero(String genero) {
    this.genero = genero;
  }

  public String getCategoria() {
    return categoria;
  }

  public void setCategoria(String categoria) {
    this.categoria = categoria;
  }
}
