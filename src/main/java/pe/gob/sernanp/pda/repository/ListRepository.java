package pe.gob.sernanp.pda.repository;

import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;

import pe.gob.sernanp.pda.entities.Grupo;
import pe.gob.sernanp.pda.entities.Ruta;
import pe.gob.sernanp.pda.entities.Visitante;

@Repository
public class ListRepository {

  @PersistenceContext
  protected EntityManager entityManager;

  @Autowired
  private UsuarioRepository usuarioRepository;
  @Autowired
  private TipoDocumentoRepository tipoDocumentoRepository;
  @Autowired
  private RutaRepository rutaRepository;

  @Autowired(required = true)
  private JdbcTemplate jdbcTemplate;

  public EntityManager getEntityManager() {
    return entityManager;
  }

  // Example with Native Query Entity Manager
  public List<Map> showList() {
    @SuppressWarnings("unchecked")
    List<Map> list = entityManager.createNativeQuery("select * from t_categoria").getResultList();
    return list;
  }

  // Example with JDBC - The simplest way.
  public List<Map<String, Object>> showListJdbc() {
    List<Map<String, Object>> list = jdbcTemplate.queryForList("SELECT * FROM t_categoria");
    return list;
  }

  // Example with Arguments.
  // lista las rutas

  // Insert Row
  public Map<String, Object> insertRow(String title, LocalDate date) {
    KeyHolder keyHolder = new GeneratedKeyHolder();

    jdbcTemplate.update(connection -> {
      PreparedStatement ps = connection.prepareStatement("INSERT INTO t_categoria(title,fecha) VALUES(?,?)",
          new String[] { "id" });
      ps.setString(1, title);
      ps.setObject(2, date);
      return ps;
    }, keyHolder);

    Map<String, Object> obj = new HashMap<String, Object>();
    obj.put("id", keyHolder.getKey());
    return obj;
  }

  // Update Row
  public Map<String, Object> updateRow(String title, LocalDate date, Integer id) {
    int status = jdbcTemplate.update("UPDATE t_categoria SET title = ?, fecha= ? WHERE id=? ", title, date, id);
    Map<String, Object> obj = new HashMap<String, Object>();
    obj.put("id", status);
    return obj;
  }

  // Delete Row
  public Map<String, Object> deleteRow(Integer id) {
    int status = jdbcTemplate.update("DELETE FROM t_categoria WHERE id=? ", id);
    Map<String, Object> obj = new HashMap<String, Object>();
    obj.put("id", status);
    return obj;
  }

  // Note the ILIKE operator for case insensitive
  public List<Map<String, Object>> showListWithDate(LocalDate date) {
    List<Map<String, Object>> list = jdbcTemplate.queryForList(
        "SELECT id, title, TO_CHAR(fecha, 'yyyy-mm-dd') fecha FROM t_categoria WHERE date_trunc('day', fecha) = ? ",
        date);
    return list;
  }

  // Note the ILIKE operator for case insensitive
  public List<Map<String, Object>> showListWithParams(Integer id, String title) {
    List<Map<String, Object>> list = jdbcTemplate
        .queryForList("SELECT * FROM t_categoria WHERE id = ? OR title ILIKE ? ", id, ("%" + title + "%"));
    return list;
  }

  // Note the Integer.parseInt case for integers values in the database. Be
  // careful with type
  // Note the ILIKE operator for case insensitive
  // Note the % for ILIKE expressions in PostgreSQL
  public List<Map<String, Object>> showListWithParamsMap(Map<String, String> map) {
    List<Map<String, Object>> list = jdbcTemplate.queryForList(
        "SELECT * FROM t_categoria WHERE id = ? OR title ILIKE ? ", Integer.parseInt(map.get("id")),
        ("%" + map.get("title") + "%"));
    return list;
  }

  // *********************** Insertando registros
  // *********************************

  // Insertando Operador
  public Map<String, Object> insertOperador(String codigo, String ruc, String razonsocial, String direccion,
      String telefono, String email, String web, int saldo, boolean estado

  ) {

    jdbcTemplate.update(connection -> {
      PreparedStatement ps = connection.prepareStatement(
          "INSERT INTO t_operador(var_cod_operador, var_ruc, var_razonsocial, var_direccion, "
              + "var_telefono, var_email, var_web, num_saldo, bol_estado) VALUES(?,?,?,?,?,?,?,?,?)",
          new String[] { "var_cod_operador" });
      ps.setString(1, codigo);
      ps.setString(2, ruc);
      ps.setObject(3, razonsocial);
      ps.setObject(4, direccion);
      ps.setString(5, telefono);
      ps.setString(6, email);
      ps.setString(7, web);
      ps.setInt(8, saldo);
      ps.setBoolean(9, estado);
      return ps;
    });
    usuarioRepository.insertUsuario(email, "", email, "operador");
    Map<String, Object> obj = new HashMap<String, Object>();
    obj.put("var_cod_operador", codigo);
    return obj;
  }

  // Insertando noticias
  public Map<String, Object> insertNews(String titulo, String contenido, LocalDate date, String user) {
    KeyHolder keyHolder = new GeneratedKeyHolder();

    jdbcTemplate.update(connection -> {
      PreparedStatement ps = connection.prepareStatement(
          "INSERT INTO t_noticia(var_titulo, txt_contenido, tsp_fec_publicacion, bol_activo, var_usuario) VALUES(?,?,?,?,?)",
          new String[] { "srl_cod_noticia" });
      ps.setString(1, titulo);
      ps.setString(2, contenido);
      ps.setObject(3, date);
      ps.setObject(4, true);
      ps.setString(5, user);
      return ps;
    }, keyHolder);

    Map<String, Object> obj = new HashMap<String, Object>();
    obj.put("srl_cod_noticia", keyHolder.getKey());
    return obj;
  }

  // Insertando visitantes
  public Map<String, String> insertVisitante(Integer codDocumento, Integer codCategoria, Integer codPais, String nombre,
      String apellido, String nroDocumento, LocalDate fNac, String sexo) {
    KeyHolder keyHolder = new GeneratedKeyHolder();
    Map<String, String> obj = new HashMap<>();
    System.out.println("insertVisitante  ============================ " + codDocumento);

    jdbcTemplate.update(connection -> {
      PreparedStatement ps = connection.prepareStatement(
          "INSERT INTO t_visitante(srl_cod_documento, srl_cod_categoria, srl_cod_pais, var_nombre, var_apellido, var_nro_documento, dte_fec_nacimiento, var_sexo) VALUES (?, ?, ?, ?, ?, ?, ?, ?);",
          new String[] { "srl_cod_visitante" });
      ps.setInt(1, codDocumento);
      ps.setInt(2, codCategoria);
      ps.setInt(3, codPais);
      ps.setString(4, nombre);
      ps.setString(5, apellido);
      ps.setString(6, nroDocumento);
      ps.setObject(7, fNac);
      ps.setString(8, sexo);
      return ps;
    }, keyHolder);

    obj.put("srl_cod_visitante", keyHolder.getKey().toString());
    return obj;
  }

  // Insertando grupos
  public Grupo insertGrupo(String codOperador, Integer codRuta, LocalDate fecProgramada, Integer nroVisitantes,
      Double numCosto, String insUsuario) {
    KeyHolder keyHolder = new GeneratedKeyHolder();

    Integer count = jdbcTemplate.queryForObject("SELECT count(*) " + "FROM t_grupo " + "WHERE var_cod_operador=?",
        new Object[] { codOperador }, Integer.class);
    String masked = String.format("%04d", count + 1);

    System.out.println("================= cod OPeRADOR ============: " + codOperador + " Count Grupos:" + masked);

    jdbcTemplate.update(connection -> {
      PreparedStatement ps = connection.prepareStatement(
          "INSERT INTO t_grupo(var_cod_operador, srl_cod_ruta, dte_fec_programada, int_nro_visitante, num_costo, int_estado, var_usuario, var_cod_grupo) "
              + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
          new String[] { "srl_cod_grupo" });
      ps.setString(1, codOperador);
      ps.setInt(2, codRuta);
      ps.setObject(3, fecProgramada);
      ps.setInt(4, nroVisitantes);
      ps.setDouble(5, numCosto);
      ps.setInt(6, 1);
      ps.setString(7, insUsuario);
      ps.setString(8, codOperador + "-" + fecProgramada.getYear() + "-" + masked);
      return ps;
    }, keyHolder);

    Grupo g = showConsultaGrupo(keyHolder.getKey().intValue());
    System.out.println("Key " + keyHolder.getKey() + "········· Habrá conseguido········" + g);
    return g;
  }

  // Insertando grupos
  public Map<String, Object> insertGrupoConVisitantes(Grupo g, LocalDate fecha) {

    Double costo = rutaRepository.findOne(Integer.parseInt(g.getRuta())).getCostoVisitante() * g.getVisitantes().size();

    System.out.println("*******===== COSTO X VISITANTE: " + costo);

    Grupo grupo = insertGrupo(g.getCodOperador(), Integer.parseInt(g.getRuta()), fecha, g.getVisitantes().size(), costo,
        g.getCodigo());

    System.out.println("GRUPO INSERTADO" + grupo + " GRUPO ID:" + grupo.getId());
    g.setId(grupo.getId());
    addVisitantes(g);

    // Actualizar Saldo Operador
    updateSaldoOperador(g.getCodOperador(), costo, false);

    Map<String, Object> obj = new HashMap<String, Object>();
    obj.put("grupo", grupo);
    obj.put("operador", showConsultaOperador(g.getCodOperador()).get(0));
    return obj;
  }

  // Insertando grupos
  public Map updateGrupoConVisitantes(Grupo g, LocalDate fecha) {

    Double costo = rutaRepository.findOne(Integer.parseInt(g.getRuta())).getCostoVisitante() * g.getVisitantes().size();

    System.out.println("Method: updateGrupoConVisitantes() *******===== COSTO X VISITANTE: " + costo);

    Grupo oldGrupo = showConsultaGrupo(g.getId());

    removeVisitantes(oldGrupo);
    addVisitantes(g);
    // Actualizar Saldo Operador
    if (g.getVisitantes().size() != oldGrupo.getVisitantes().size()) {
      updateSaldoOperador(g.getCodOperador(), costo - oldGrupo.getCosto(), true);
    }

    updateGrupo(parseFecha(g.getFecha()), Integer.parseInt(g.getRuta()), g.getId(), costo);

    Map map = send("grupo", showConsultaGrupo(g.getId()));
    map.put("operador", showConsultaOperador(g.getCodOperador()).get(0));
    return map;
  }

  public void addVisitantes(Grupo grupo) {

    for (int i = 0; i < grupo.getVisitantes().size(); i++) {
      Visitante v = grupo.getVisitantes().get(i);
      LocalDate fechaNacimiento = parseFecha(v.getNacimiento());
      // Insert Visitante
      ObjectMapper oMapper = new ObjectMapper();

      Map<String, Object> map = oMapper.convertValue(v, Map.class);
      System.out.println(" Visitante Map ==============" + v.toString());
      if (!v.getSexo().equals("M")) {
        v.setSexo("F");
      }

      String codVisitante = insertVisitante(Integer.parseInt(v.getTipoDocumento()), v.getCategoria(),
          Integer.parseInt(v.getPais()), v.getNombres(), v.getApellidos(), v.getDni(), fechaNacimiento, v.getSexo())
              .get("srl_cod_visitante");

      System.out.println("ID VISITANTE INSERTADO ============================" + codVisitante);

      String codVisitanteGrupo = insertVisitanteGrupo(grupo.getId(), Integer.parseInt(codVisitante))
          .get("srl_cod_grupo");

      System.out.println("ID GRUPO VISITANTE INSERTADO ============================" + codVisitante);
    }
  }

  public void removeVisitantes(Grupo grupo) {
    for (int i = 0; i < grupo.getVisitantes().size(); i++) {
      Visitante v = grupo.getVisitantes().get(i);
      Map m = deleteVisitanteGrupo(grupo.getId(), v.getId());
      System.out.println("ID GRUPO VISITANTE INSERTADO ============================" + m);
    }
  }

  // Insertando visitantes al grupo
  public Map<String, String> insertVisitanteGrupo(Integer codGrupo, Integer codVisitante) {
    KeyHolder keyHolder = new GeneratedKeyHolder();

    jdbcTemplate.update(connection -> {
      PreparedStatement ps = connection.prepareStatement(
          "INSERT INTO t_grupo_visitante(srl_cod_grupo, srl_cod_visitante) VALUES (?, ?);",
          new String[] { "srl_cod_grupo" });
      ps.setInt(1, codGrupo);
      ps.setInt(2, codVisitante);
      return ps;
    }, keyHolder);

    return new HashMap<String, String>() {
      {
        put("srl_cod_grupo", keyHolder.getKey().toString());
      }
    };
  }

  // Insertando pagos
  public Map<String, Object> insertPago(String codOperador, String nroOperacion, Double monto, LocalDate fecAbono,
      String voucher) {
    KeyHolder keyHolder = new GeneratedKeyHolder();

    jdbcTemplate.update(connection -> {
      PreparedStatement ps = connection.prepareStatement(
          "INSERT INTO t_pago(var_cod_operador, var_operacion, num_monto, dte_fec_abono, var_comprobante, int_estado) VALUES (?, ?, ?, ?, ?, ?);",
          new String[] { "srl_cod_pago" });
      ps.setString(1, codOperador);
      ps.setString(2, nroOperacion);
      ps.setDouble(3, monto);
      ps.setObject(4, fecAbono);
      ps.setString(5, voucher);
      ps.setInt(6, 1);
      return ps;
    }, keyHolder);
    // updateSaldoOperador(codOperador, monto, true);
    Map<String, Object> obj = new HashMap<String, Object>();
    obj.put("srl_cod_pago", keyHolder.getKey());
    obj.put("pago", showConsultaPago(keyHolder.getKey().intValue()));
    obj.put("operador", showConsultaOperador(codOperador).get(0));
    return obj;
  }

  // ************************ Fin de insertando registros
  // ***********************************************

  // ************************ Eliminando registros
  // ****************************************************

  // Eliminar Noticias
  public Map<String, Object> deleteNews(Integer codNoticia) {
    int status = jdbcTemplate.update("DELETE FROM t_noticia WHERE srl_cod_noticia = ? ", codNoticia);
    Map<String, Object> obj = new HashMap<String, Object>();
    obj.put("id", status);
    return obj;
  }

  // Eliminar visitante de grupo
  public Map<String, Object> deleteVisitanteGrupo(Integer codGrupo, Integer codVisitante) {
    int status = jdbcTemplate.update("DELETE FROM t_grupo_visitante WHERE srl_cod_grupo = ? AND srl_cod_visitante = ? ",
        codGrupo, codVisitante);
    int id = jdbcTemplate.update("DELETE FROM t_visitante WHERE srl_cod_visitante = ? ", codVisitante);
    Map<String, Object> obj = new HashMap<String, Object>();
    obj.put("id", status);
    return obj;
  }

  // ********************** Fin eliminando registros
  // ***********************************************

  // *********************** Actualizando registros
  // **************************************

  // Cambiando contraseña
  public Map<String, Object> updatePwd(String newClave, Integer codUser) {
    int status = jdbcTemplate.update("UPDATE t_usuario SET var_clave = ? WHERE srl_cod_usuario = ?", newClave, codUser);
    Map<String, Object> obj = new HashMap<String, Object>();
    obj.put("id", status);
    return obj;
  }

  // Editando Noticias
  public Map<String, Object> updateNews(String titulo, String contenido, LocalDate date, Boolean estado, Integer id) {
    int status = jdbcTemplate.update(
        "UPDATE t_noticia SET var_titulo = ?, txt_contenido = ?, tsp_fec_publicacion= ?, bol_activo = ?  WHERE srl_cod_noticia = ? ",
        titulo, contenido, date, estado, id);
    Map<String, Object> obj = new HashMap<String, Object>();
    obj.put("id", status);
    return obj;
  }

  // Editando Visitante
  public Map<String, Object> updateVisitante(Integer codDocumento, Integer codCategoria, Integer codPais, String nombre,
      String apellido, String nroDocumento, LocalDate fNac, Integer sexo, Integer id) {
    int status = jdbcTemplate.update(
        "UPDATE t_visitante SET srl_cod_documento=?, srl_cod_categoria=?, srl_cod_pais=?, var_nombre=?, var_apellido=?, var_nro_documento=?, dte_fec_nacimiento=?, bol_sexo=?  WHERE srl_cod_visitante = ? ",
        codDocumento, codCategoria, codPais, nombre, apellido, nroDocumento, fNac, sexo, id);
    Map<String, Object> obj = new HashMap<String, Object>();
    obj.put("id", status);
    return obj;
  }

  // Editando Grupo
  public Map<String, Object> updateGrupo(LocalDate date, Integer codRuta, Integer id, Double costo) {
    int status = jdbcTemplate.update(
        "UPDATE t_grupo SET dte_fec_programada = ?, srl_cod_ruta = ?, num_costo = ?  WHERE srl_cod_grupo = ? ", date,
        codRuta, costo, id);
    Map<String, Object> obj = new HashMap<String, Object>();
    obj.put("id", status);
    return obj;
  }

  // Tomando asistencia al grupo
  public Grupo updateAsistenciaGrupo(Grupo grupo) {

    int inasistentes = 0;
    System.out.println("======  updateAsistenciaGrupo == Grupo:" + grupo + "Documento" + grupo.getDocumento()
        + " Visitantes: " + grupo.getVisitantes().size());
    for (Visitante row : grupo.getVisitantes()) {

      System.out.println("====== Visitante:" + row + " Index: " + row.getAsistio());
      updateAsistencia(grupo.getId(), row.getId(), row.getAsistio());
      if (!row.getAsistio()) {
        inasistentes++;
      }
    }
    if (inasistentes > 0) {
      Ruta ruta = rutaRepository.findOne(Integer.parseInt(grupo.getRuta()));
      updateSaldoOperador(grupo.getCodOperador(), inasistentes * ruta.getCostoVisitante(), true);
    }
    verificarVisitaGrupo(inasistentes, 2, grupo.getDocumento(), null, grupo.getId());

    return showConsultaGrupo(grupo.getId());
  }

  // Tomando asistencia visitante
  public Map<String, Object> updateAsistencia(Integer codGrupo, Integer codVisitante, Boolean asistio) {
    int status = jdbcTemplate.update(
        "UPDATE t_grupo_visitante SET bol_ingreso = ? WHERE srl_cod_grupo = ? AND srl_cod_visitante = ?", asistio,
        codGrupo, codVisitante);
    Map<String, Object> obj = new HashMap<String, Object>();
    obj.put("id", status);
    return obj;
  }

  // Verifica visita del grupo
  public Map<String, Object> verificarVisitaGrupo(Integer nroInasistentes, Integer estado, String documento,
      String userModificacion, Integer codGrupo) {

    int status = jdbcTemplate.update(
        "UPDATE t_grupo SET int_nro_inasistente = ?, int_estado = ?, var_documento = ?, var_usuariomodificacion = ?, dte_fec_modificacion = now()  "
            + "WHERE srl_cod_grupo = ? ",
        nroInasistentes, estado, documento, userModificacion, codGrupo);
    Map<String, Object> obj = new HashMap<String, Object>();
    obj.put("id", status);
    return obj;
  }

  // Verifica visita del grupo
  public Map<String, Object> updateVisitaGrupo(LocalDate date, Integer nroVisitantes, Integer nroInasistentes,
      Integer costo, Integer estado, String documento, String userModificacion, LocalDate dateModificacion,
      Integer codGrupo) {
    int status = jdbcTemplate.update(
        "UPDATE t_grupo SET dte_fec_visita = ?, int_nro_visitante = ?, int_nro_inasistente = ?, num_costo = ?, int_estado = ?, var_documento = ?, var_usuariomodificacion = ?, dte_fec_modificacion = ?  WHERE srl_cod_grupo = ? ",
        date, nroVisitantes, nroInasistentes, costo, estado, documento, userModificacion, dateModificacion, codGrupo);
    Map<String, Object> obj = new HashMap<String, Object>();
    obj.put("id", status);
    return obj;
  }

  // Agregar documento al grupo
  public Map<String, Object> updateDocGrupo(Integer codGrupo, String documento) {
    int status = jdbcTemplate.update("UPDATE t_grupo SET var_documento = ? WHERE srl_cod_grupo = ? ", documento,
        codGrupo);
    Map<String, Object> obj = new HashMap<String, Object>();
    obj.put("id", status);
    return obj;
  }

  // Aprobar pago
  public Map<String, Object> updatePagoAprobado(Integer codPago) {
    int status = jdbcTemplate.update("UPDATE t_pago SET int_estado = 2 WHERE srl_cod_pago = ? ", codPago);
    return updatePago(codPago, true);
  }

  public Map<String, Object> updatePago(Integer codPago, boolean agregar) {
    Map<String, Object> pago = showConsultaPago(codPago);
    System.out.println("PAGO" + pago);
    Map<String, Object> operador = updateSaldoOperador(pago.get("var_cod_operador").toString(),
        Double.parseDouble(pago.get("num_monto").toString()), true);
    return operador;
  }

  // Rechazar pago
  public Map<String, Object> updatePagoRechazo(Integer codPago, String motivoRechazo) {
    int status = jdbcTemplate.update("UPDATE t_pago SET int_estado = 3, txt_motivorechazo = ? WHERE srl_cod_pago = ? ",
        motivoRechazo, codPago);
    return updatePago(codPago, false);
  }

  // agregar pago
  public Map<String, Object> updateSaldoOperador(String codOperador, Double monto, boolean agregar) {
    Map<String, Object> operador = showConsultaOperador(codOperador).get(0);
    System.out.println(" ========= Here is my Saldo" + operador.get("num_saldo"));
    Double saldo = Double.parseDouble(operador.get("num_saldo").toString());
    if (agregar) {
      saldo = saldo + monto;
    } else {
      saldo = saldo - monto;
    }
    int status = jdbcTemplate.update("UPDATE t_operador SET num_saldo = ? WHERE var_cod_operador = ? ", saldo,
        codOperador);
    Map<String, Object> obj = new HashMap<String, Object>();
    obj.put("id", status);
    operador.put("num_saldo", saldo);
    obj.put("operador", operador);
    return obj;
  }

  // *********************** Fin Actualizar registros
  // *********************************************

  // ************************ Listando con parametros
  // ***********************************

  // Consultando operador
  public List<Map<String, Object>> showConsultaOperador(String codOperador) {
    List<Map<String, Object>> list = jdbcTemplate.queryForList("SELECT o.* FROM t_operador o "
        + "INNER JOIN t_usuario u ON u.var_email = o.var_email " + "WHERE var_cod_operador = ?  ", codOperador);
    return list;
  }

  public Map<String, Object> showGrupo(Integer codGrupo) {
    List<Map<String, Object>> list = jdbcTemplate.queryForList("SELECT * FROM t_grupo WHERE srl_cod_grupo = ?",
        codGrupo);

    return list.get(0);
  }

  // Consultando operador x usuario
  public List<Map<String, Object>> showConsultaOperadorxemail(String email) {
    List<Map<String, Object>> list = jdbcTemplate.queryForList("SELECT * FROM t_operador WHERE var_email = ? ", email);
    return list;
  }

  // Consultando visitante
  public Visitante showConsultaVisitante(Integer codVisitante) {
    List<Map<String, Object>> list = jdbcTemplate.queryForList("SELECT * FROM t_visitante WHERE srl_cod_visitante = ? ",
        codVisitante);

    return setVisitanteFromMap(list.get(0));
  }

  // Consultando Grupo
  public Grupo showConsultaGrupo(Integer codGrupo) {
    System.out.println("====== showConsultaGrupo( " + codGrupo);

    List<Map<String, Object>> grupoMap = jdbcTemplate
        .queryForList("SELECT g.*, count(gv.srl_cod_grupo) as total_visitantes FROM t_grupo g "
            + "LEFT JOIN t_grupo_visitante gv ON gv.srl_cod_grupo = g.srl_cod_grupo " + "WHERE g.srl_cod_grupo = ?"
            + "GROUP by g.srl_cod_grupo", codGrupo);

    System.out.println("====== showConsultaGrupo( " + grupoMap);

    if (grupoMap.size() == 0) {
      return null;
    }
    Grupo grupo = setGrupoFromMap(grupoMap.get(0));
    grupo.setVisitantes(showVisitantesGrupo(grupo.getId()));
    return grupo;
  }

  public List<Visitante> showVisitantesGrupo(Integer codGrupo) {
    List<Map<String, Object>> rows = jdbcTemplate.queryForList(
        "SELECT v.*,gv.bol_ingreso " + "FROM t_visitante v "
            + "INNER JOIN t_grupo_visitante gv ON gv.srl_cod_visitante = v.srl_cod_visitante  "
            + "INNER JOIN t_grupo g ON g.srl_cod_grupo = gv.srl_cod_grupo " + "WHERE " + "g.srl_cod_grupo  = ? ",
        codGrupo);
    List<Visitante> visitantes = new ArrayList<Visitante>();
    for (Map row : rows) {
      visitantes.add(setVisitanteFromMap(row));
    }
    return visitantes;
  }

  // Consultando Pago
  public Map<String, Object> showConsultaPago(Integer codPago) {
    List<Map<String, Object>> list = jdbcTemplate.queryForList("SELECT * FROM t_pago WHERE srl_cod_pago = ? ", codPago);
    return list.get(0);
  }

  // Listando visitantes por grupo
  public List<Map<String, Object>> showVisitantexGrupo(Integer codGrupo) {
    List<Map<String, Object>> list = jdbcTemplate.queryForList(
        "SELECT * FROM t_visitante INNER JOIN t_tip_documento ON t_visitante.srl_cod_documento = t_tip_documento.srl_cod_documento INNER JOIN t_grupo_visitante ON t_visitante.srl_cod_visitante = t_grupo_visitante.srl_cod_visitante WHERE srl_cod_grupo = ? ",
        codGrupo);
    return list;
  }

  // Consultando Grupos ************* OPERADOR ******************
  public List<Map<String, Object>> showConsultaGrupoOperador(String codOperador) {
    List<Map<String, Object>> list = jdbcTemplate.queryForList("SELECT * FROM t_grupo "

        + " WHERE var_cod_operador = ? ORDER BY dte_fec_programada DESC ", codOperador);
    return list;
  }

  // Consultando Pagos ******************* OPERADOR ****************
  public List<Map<String, Object>> showConsultaPagoOperador(String codOperador) {
    List<Map<String, Object>> list = jdbcTemplate.queryForList("SELECT * FROM t_pago WHERE var_cod_operador = ?",
        codOperador);
    return list;
  }

  // Filtro Pagos ************* OPERADOR ***************+
  public List<Map<String, Object>> showFiltroPagoOperador(String nroOperacion, String fecPago, Integer estado) {
    String query = "SELECT * FROM t_pago WHERE ";

    String qOperacion = ((nroOperacion == "") ? ""
        : " var_operacion = '" + nroOperacion + "'" + ((estado == 0) ? "" : " AND "));
    String qEstado = ((estado == 0) ? "" : " int_estado = " + estado + ((fecPago == "") ? "" : " AND "));
    String qFecha = (fecPago == "") ? "" : " dte_fec_abono = '" + fecPago + "'";

    query = query + qOperacion + qEstado + qFecha;
    List<Map<String, Object>> list = jdbcTemplate.queryForList(query);
    System.out.println(query);
    return list;
  }

  // Filtro Pagos ************** RECAUDADOR ***************
  public List<Map<String, Object>> showFiltroPagoRecaudador(String nroOperacion, String codOperador) {
    String query = "SELECT * FROM t_pago WHERE ";

    String qOperacion = ((nroOperacion == "") ? ""
        : " var_operacion = '" + nroOperacion + "'" + ((codOperador == "") ? "" : " AND "));
    String qOperador = (codOperador == "") ? "" : " var_cod_operador = '" + codOperador + "'";

    query = query + qOperacion + qOperador;
    List<Map<String, Object>> list = jdbcTemplate.queryForList(query);
    System.out.println(query);
    return list;
  }

  // Filtro Grupos *************** OPERADOR *******************
  public List<Map<String, Object>> showFiltroGrupoOperador(String codGrupo, String fecVisita, Integer estado) {
    String query = "SELECT * FROM t_grupo WHERE ";

    String qGrupo = ((codGrupo == "") ? "" : " srl_cod_grupo = " + codGrupo + ((estado == 0) ? "" : " AND "));
    String qEstado = ((estado == 0) ? "" : " int_estado = " + estado + ((fecVisita == "") ? "" : " AND "));
    String qFecha = (fecVisita == "") ? "" : " dte_fec_programada = '" + fecVisita + "'";

    query = query + qGrupo + qEstado + qFecha;
    List<Map<String, Object>> list = jdbcTemplate.queryForList(query);
    System.out.println(query);
    return list;
  }

  // ******************************* Fin listando con parametros
  // *******************************

  // ************************************ Listando
  // ***********************************

  // lista los visitantes
  public List<Map<String, Object>> showListVisitantes() {
    List<Map<String, Object>> list_visitantes = jdbcTemplate.queryForList("SELECT * FROM t_visitante");
    return list_visitantes;
  }

  // lista las rutas
  public List<Map<String, Object>> showListRutas() {
    List<Map<String, Object>> list_rutas = jdbcTemplate.queryForList("SELECT * FROM t_ruta");
    return list_rutas;
  }

  // lista las categorias
  public List<Map<String, Object>> showListCategorias() {
    List<Map<String, Object>> list_categorias = jdbcTemplate.queryForList("SELECT * FROM t_categoria");
    return list_categorias;
  }

  // lista los paises
  public List<Map<String, Object>> showListPaises() {
    List<Map<String, Object>> list_paises = jdbcTemplate.queryForList("SELECT * FROM t_pais ORDER BY var_nombre ASC");
    return list_paises;
  }

  // lista los documentos
  public List<Map<String, Object>> showListTipdocumentos() {
    List<Map<String, Object>> list_tipdocumentos = jdbcTemplate.queryForList("SELECT * FROM t_tip_documento");
    return list_tipdocumentos;
  }

  // lista las noticias
  public List<Map<String, Object>> showListNoticias() {
    List<Map<String, Object>> list_noticias = jdbcTemplate.queryForList("SELECT * FROM t_noticia");
    return list_noticias;
  }

  // lista las noticias activas
  public List<Map<String, Object>> showListNoticiasActivas() {
    List<Map<String, Object>> list_noticias_activas = jdbcTemplate
        .queryForList("SELECT * FROM t_noticia WHERE bol_activo='1'");
    return list_noticias_activas;
  }

  // lista de grupos
  public List<Grupo> showListGrupos() {
    List<Map<String, Object>> rows = jdbcTemplate
        .queryForList("SELECT g.*, count(g.srl_cod_grupo) as total_visitantes FROM t_grupo g "
            + "INNER JOIN t_grupo_visitante gv ON gv.srl_cod_grupo = g.srl_cod_grupo "
            + "GROUP by g.srl_cod_grupo ORDER BY g.dte_fec_programada");
    List<Grupo> grupos = new ArrayList<Grupo>();
    for (Map row : rows) {
      grupos.add(setGrupoFromMap(row));
      System.out.println(row);
    }
    return grupos;
  }

  // lista de grupos
  public List<Grupo> showListGruposHoy() {
    List<Map<String, Object>> rows = jdbcTemplate
        .queryForList("SELECT g.*, count(g.srl_cod_grupo) as total_visitantes FROM t_grupo g "
            + "INNER JOIN t_grupo_visitante gv ON gv.srl_cod_grupo = g.srl_cod_grupo "
            + "WHERE g.dte_fec_programada = CURRENT_DATE "
            + "GROUP by g.srl_cod_grupo ORDER BY g.dte_fec_programada, g.srl_cod_grupo");
    List<Grupo> grupos = new ArrayList<Grupo>();
    for (Map row : rows) {
      grupos.add(setGrupoFromMap(row));
    }
    return grupos;
  }

  private Grupo setGrupoFromMap(Map row) {
    Grupo grupo = new Grupo();

    System.out.print(" >>> LOCAL DATA GRUPO:" + row.get("dte_fec_programada").toString());
    grupo.setCodigo((String) row.get("var_cod_grupo"));
    grupo.setCodOperador((String) row.get("var_cod_operador"));
    grupo.setDocumento((String) row.get("var_documento"));
    grupo.setFecha(parseFechaFromDB(row.get("dte_fec_programada").toString()));
    grupo.setFechaModificacion(
        row.get("dte_fec_modificacion") == null ? null : row.get("dte_fec_modificacion").toString());
    grupo.setFechaCreacion(row.get("dte_fec_creacion").toString());
    grupo.setEstado((Integer) row.get("int_estado"));
    grupo.setCosto((Double) row.get("num_costo"));
    grupo.setId((Integer) row.get("srl_cod_grupo"));
    grupo.setRuta(row.get("srl_cod_ruta").toString());
    grupo.setTotalVisitantes((Long) row.get("total_visitantes"));
    grupo.setInasistencias((Integer) row.get("int_nro_inasistente"));

    return grupo;
  }

  private Visitante setVisitanteFromMap(Map row) {
    Visitante visitante = new Visitante();

    System.out.print(" >>> LOCAL DATA VISITANTE:" + row);

    visitante.setApellidos((String) row.get("var_apellido"));
    visitante.setNombres((String) row.get("var_nombre"));
    visitante.setDni((String) row.get("var_nro_documento"));
    visitante.setTipoDocumento(row.get("srl_cod_documento").toString());
    visitante.setPais(row.get("srl_cod_pais").toString());
    visitante.setSexo((String) row.get("var_sexo"));
    visitante.setId((Integer) row.get("srl_cod_visitante"));
    visitante.setAsistio((Boolean) row.get("bol_ingreso"));
    visitante.setNacimiento(row.get("dte_fec_nacimiento").toString());
    visitante.setCategoria((Integer) row.get("srl_cod_categoria"));

    return visitante;
  }

  // lista de grupos
  public List<Map<String, Object>> showListOperadores() {
    List<Map<String, Object>> list_grupos = jdbcTemplate.queryForList("SELECT * FROM t_operador");
    return list_grupos;
  }

  // lista de pagos
  public List<Map<String, Object>> showListPagos() {
    List<Map<String, Object>> list_pagos = jdbcTemplate.queryForList(
        "SELECT p.int_estado, p.num_monto, p.srl_cod_pago,txt_motivorechazo,p.var_cod_operador, p.var_comprobante,"
            + "p.var_operacion,o.var_razonsocial "
            + "FROM t_pago p INNER JOIN t_operador o ON o.var_cod_operador = p.var_cod_operador ORDER BY p.srl_cod_pago DESC");
    return list_pagos;
  }

  public List<Map<String, Object>> reportePorOperador() {
    return jdbcTemplate.queryForList("select * from (\n" + "select \n" + "    r.var_nombre, \n"
        + "    sum(case when v.srl_cod_pais = '172' then 1 else 0 end) Nacional,\n"
        + "    sum(case when v.srl_cod_pais != '172' then 1 else 0 end) Extranjero,\n"
        + "    count(r.srl_cod_ruta) total\n" + "from t_ruta r\n"
        + "left join t_grupo g on g.srl_cod_ruta = r.srl_cod_ruta\n"
        + "left join t_grupo_visitante gv on gv.srl_cod_grupo = g.srl_cod_grupo\n"
        + "left join t_visitante v on v.srl_cod_visitante = gv.srl_cod_visitante\n" + "group by (r.srl_cod_ruta)\n"
        + "union\n" + "select \n" + "    'Total',\n"
        + "    sum(case when v.srl_cod_pais = '172' then 1 else 0 end) Nacional,\n"
        + "    sum(case when v.srl_cod_pais != '172' then 1 else 0 end) Extranjero,\n" + "    count(*) total\n"
        + "from t_ruta r\n" + "left join t_grupo g on g.srl_cod_ruta = r.srl_cod_ruta\n"
        + "left join t_grupo_visitante gv on gv.srl_cod_grupo = g.srl_cod_grupo\n"
        + "left join t_visitante v on v.srl_cod_visitante = gv.srl_cod_visitante\n" + ") rows \n"
        + "order by var_nombre\n" + ";");
  }

  public List<Map<String, Object>> reportePorAbonos() {
    return jdbcTemplate.queryForList("select \n" + "    '-' Total,\n" + "    dte_fec_creacion,\n"
        + "    dte_fec_abono,\n" + "    dte_fec_validacion,\n" + "    var_operacion,\n" + "    num_monto\n" + "from\n"
        + "    t_pago p\n" + "where \n" + "    var_cod_operador = 'ope-1'\n" + "union \n" + "select \n"
        + "    'Total',\n" + "    null,\n" + "    null,\n" + "    null,\n" + "    null,\n" + "    SUM(num_monto)\n"
        + "from\n" + "    t_pago p\n" + "where \n" + "    var_cod_operador = 'ope-1'");
  }

  public List<Map<String, Object>> reportePorRecaudacion() {
    return jdbcTemplate.queryForList("select \n" + "    o.var_razonsocial,\n" + "\n"
        + "    sum(p.num_monto) TotalDeposito,\n" + "    (sum(p.num_monto) - o.num_saldo) TotalRecaudacion,\n"
        + "    o.num_saldo\n" + "from\n" + "    t_operador o\n"
        + "inner join t_pago p on p.var_cod_operador = o.var_cod_operador\n" + "Group by \n" + "o.var_cod_operador");
  }

  public List<Map<String, Object>> reportePorRecaudacionRuta() {
    return jdbcTemplate.queryForList("select \n" + "   o.var_razonsocial ,\n" + "\n"
        + "    sum(case when g.srl_cod_ruta = 1 then g.num_costo else 0 end) RecaudacionRuta1,\n"
        + "    sum(case when g.srl_cod_ruta = 1 then g.int_nro_visitante else 0 end) VisitantesRuta1,\n" + "\n"
        + "    sum(case when g.srl_cod_ruta = 2 then g.num_costo else 0 end) RecaudacionRuta2,\n"
        + "    sum(case when g.srl_cod_ruta = 2 then g.int_nro_visitante else 0 end) VisitantesRuta2,\n" + "    \n"
        + "    sum(case when g.srl_cod_ruta = 3 then g.num_costo else 0 end) RecaudacionRuta3,\n"
        + "    sum(case when g.srl_cod_ruta = 3 then g.int_nro_visitante else 0 end) VisitantesRuta3,\n" + "    \n"
        + "    sum(case when g.srl_cod_ruta = 4 then g.num_costo else 0 end) RecaudacionRuta4,\n"
        + "    sum(case when g.srl_cod_ruta = 4 then g.int_nro_visitante else 0 end) VisitantesRuta4,\n" + "\n"
        + "    sum(case when g.num_costo is not null then g.num_costo else 0 end) TotalRacaudacion\n" + "from\n"
        + "    t_operador o\n" + "left join t_grupo g on g.var_cod_operador = o.var_cod_operador\n"
        + "Group by o.var_cod_operador\n" + "order by o.var_razonsocial");
  }

  public List<Map<String, Object>> reportePorVisitantes() {
    return jdbcTemplate.queryForList("select \n" + "    v.dte_fec_creacion,\n" + "    o.var_razonsocial,\n"
        + "    g.var_cod_grupo,\n" + "    v.var_apellido,\n" + "    v.var_nombre,\n" + "    v.srl_cod_documento,\n"
        + "    v.var_nro_documento,\n" + "    v.dte_fec_nacimiento,\n" + "    v.srl_cod_pais,\n" + "    v.var_sexo,\n"
        + "    v.srl_cod_categoria,\n" + "    r.var_nombre,\n" + "    g.dte_fec_programada\n" + "from \n"
        + "    t_visitante v\n" + "inner join\n"
        + "    t_grupo_visitante gv On gv.srl_cod_visitante = v.srl_cod_visitante\n" + "inner join\n"
        + "    t_grupo g ON g.srl_cod_grupo = gv.srl_cod_grupo\n" + "inner join \n"
        + "    t_operador o ON o.var_cod_operador = g.var_cod_operador\n" + "inner join \n"
        + "    t_ruta r ON r.srl_cod_ruta = g.srl_cod_ruta\n" + "where \n" + "    gv.bol_ingreso = true;\n"
        + "    ;\n");
  }

  // ****************************** Fin listando
  // ******************************************+

  public List<Map<String, Object>> showListStoreProcedures() {
    List<Map<String, Object>> list = jdbcTemplate.queryForList("SELECT * from get_books()");
    return list;
  }

  public void updateRow(String name, int id) {
    jdbcTemplate.update("update books set title = ? where id = ?", name, id);
  }

  public Grupo parseGrupo(String grupo) {
    Gson gson = new GsonBuilder().create();
    Grupo g = gson.fromJson(grupo, Grupo.class);
    return g;
  }

  public LocalDate parseFecha(String fecha) {
    System.out.println("parseFecha:" + fecha);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    return LocalDate.parse(fecha, formatter);
  }

  public String parseFechaFromDB(String fecha) {
    System.out.println("parseFecha:" + fecha);
    DateTimeFormatter formatterDB = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    DateTimeFormatter formatterWeb = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    return LocalDate.parse(fecha, formatterDB).format(formatterWeb);
  }

  public Map send(String key, Object obj) {
    Map<String, Object> map = new HashMap<String, Object>();
    map.put(key, obj);
    return map;
  }
}
