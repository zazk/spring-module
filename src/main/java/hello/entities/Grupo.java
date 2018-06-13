package hello.entities;

public class Grupo{

  private String codigo;
  private String codOperador;
  private String ruta;
  private String saldo;
  private String fecha;
  private String fechaObservacion;
  private Integer inasistencias;
  private Integer costo;
  private Integer estado;
  private String documento;
  private String motivoObservado;
  private Visitante[] visitantes;

  public String getCodigo() {
    return codigo;
  }

  public void setCodigo(String codigo) {
    this.codigo = codigo;
  }

  public String getCodOperador() {
    return codOperador;
  }

  public void setCodOperador(String codOperador) {
    this.codOperador = codOperador;
  }

  public String getRuta() {
    return ruta;
  }

  public void setRuta(String ruta) {
    this.ruta = ruta;
  }

  public String getSaldo() {
    return saldo;
  }

  public void setSaldo(String saldo) {
    this.saldo = saldo;
  }

  public String getFecha() {
    return fecha;
  }

  public void setFecha(String fecha) {
    this.fecha = fecha;
  }

  public String getFechaObservacion() {
    return fechaObservacion;
  }

  public void setFechaObservacion(String fechaObservacion) {
    this.fechaObservacion = fechaObservacion;
  }

  public Integer getInasistencias() {
    return inasistencias;
  }

  public void setInasistencias(Integer inasistencias) {
    this.inasistencias = inasistencias;
  }

  public Integer getCosto() {
    return costo;
  }

  public void setCosto(Integer costo) {
    this.costo = costo;
  }

  public Integer getEstado() {
    return estado;
  }

  public void setEstado(Integer estado) {
    this.estado = estado;
  }

  public String getDocumento() {
    return documento;
  }

  public void setDocumento(String documento) {
    this.documento = documento;
  }

  public String getMotivoObservado() {
    return motivoObservado;
  }

  public void setMotivoObservado(String motivoObservado) {
    this.motivoObservado = motivoObservado;
  }

  public void setVisitantes (Visitante[] visitantes){
      this.visitantes = visitantes;
    }

    public Visitante[] getVisitantes(){
      return this.visitantes;
    }
}
