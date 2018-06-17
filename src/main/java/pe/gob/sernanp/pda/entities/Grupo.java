package pe.gob.sernanp.pda.entities;

import java.util.List;

public class Grupo{

  private Integer id;

  private String codOperador;
  private String codigo;
  private String ruta;
  private String saldo;
  private String fecha;
  private String fechaObservacion;
  private Integer inasistencias;
  private Double costo;
  private Integer estado;
  private String documento;
  private String motivoObservado;
  private List<Visitante> visitantes;
  private Long totalVisitantes;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }


  public String getCodOperador() {
    return codOperador;
  }

  public void setCodOperador(String codOperador) {
    this.codOperador = codOperador;
  }

  public String getCodigo() {
    return codigo;
  }

  public void setCodigo(String codigo) {
    this.codigo = codigo;
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

  public Double getCosto() {
    return costo;
  }

  public void setCosto(Double costo) {
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

  public Long getTotalVisitantes() {
    return totalVisitantes;
  }

  public void setTotalVisitantes(Long totalVisitantes) {
    this.totalVisitantes = totalVisitantes;
  }

  public void setVisitantes (List<Visitante> visitantes){
      this.visitantes = visitantes;
    }

    public List<Visitante> getVisitantes(){
      return this.visitantes;
    }
}
