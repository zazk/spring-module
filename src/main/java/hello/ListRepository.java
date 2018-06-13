package hello;

import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class ListRepository {

    @PersistenceContext
    protected EntityManager entityManager;

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
        List<Map<String, Object>> list = jdbcTemplate
                .queryForList("SELECT id, title, TO_CHAR(fecha, 'yyyy-mm-dd') fecha FROM t_categoria");
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
        insertUsuario(email, "", email);
        Map<String, Object> obj = new HashMap<String, Object>();
        obj.put("srl_cod_noticia", "saved");
        return obj;
    }

    // Insertando Usuario
    public Map<String, Object> insertUsuario(String usuario, String clave, String email) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO t_usuario(var_usuario, var_clave, bol_estado, var_email) VALUES(?,?,?,?)",
                    new String[] { "srl_cod_usuario" });
            ps.setString(1, usuario);
            ps.setString(2, clave);
            ps.setBoolean(3, true);
            ;
            ps.setString(4, email);
            return ps;
        }, keyHolder);

        Map<String, Object> obj = new HashMap<String, Object>();
        obj.put("srl_cod_usuario", keyHolder.getKey());
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
    public Map<String, Object> insertVisitante(Integer codDocumento, Integer codCategoria, Integer codPais,
            String nombre, String apellido, String nroDocumento, LocalDate fNac, Integer sexo) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO t_visitante(srl_cod_documento, srl_cod_categoria, srl_cod_pais, var_nombre, var_apellido, var_nro_documento, dte_fec_nacimiento, bol_sexo) VALUES (?, ?, ?, ?, ?, ?, ?, ?);",
                    new String[] { "srl_cod_visitante" });
            ps.setInt(1, codDocumento);
            ps.setInt(2, codCategoria);
            ps.setInt(3, codPais);
            ps.setString(4, nombre);
            ps.setString(5, apellido);
            ps.setString(6, nroDocumento);
            ps.setObject(7, fNac);
            ps.setInt(8, sexo);
            return ps;
        }, keyHolder);

        Map<String, Object> obj = new HashMap<String, Object>();
        obj.put("srl_cod_visitante", keyHolder.getKey());
        return obj;
    }

    // Insertando grupos
    public Map<String, Object> insertGrupo(String codOperador, Integer codRuta, LocalDate fecProgramada,
            Integer nroVisitantes, Integer numCosto, String insUsuario) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO t_grupo(var_cod_operador, srl_cod_ruta, dte_fec_programada, int_nro_visitante, num_costo, int_estado, var_usuario)VALUES (?, ?, ?, ?, ?, ?, ?)",
                    new String[] { "srl_cod_grupo" });
            ps.setString(1, codOperador);
            ps.setInt(2, codRuta);
            ps.setObject(3, fecProgramada);
            ps.setInt(4, nroVisitantes);
            ps.setInt(5, numCosto);
            ps.setInt(6, 1);
            ps.setString(7, insUsuario);
            return ps;
        }, keyHolder);

        Map<String, Object> obj = new HashMap<String, Object>();
        obj.put("srl_cod_grupo", keyHolder.getKey());
        return obj;
    }

    // Insertando visitantes al grupo
    public Map<String, Object> insertVisitanteGrupo(Integer codGrupo, Integer codVisitante) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO t_grupo_visitante(srl_cod_grupo, srl_cod_visitante) VALUES (?, ?);",
                    new String[] { "srl_cod_grupo" });
            ps.setInt(1, codGrupo);
            ps.setInt(2, codVisitante);
            return ps;
        }, keyHolder);

        Map<String, Object> obj = new HashMap<String, Object>();
        obj.put("srl_cod_grupo", keyHolder.getKey());
        return obj;
    }

    // Insertando pagos
    public Map<String, Object> insertPago(String codOperador, String nroOperacion, Integer monto, LocalDate fecAbono,
            String voucher) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO t_pago(var_cod_operador, var_operacion, num_monto, dte_fec_abono, var_comprobante, int_estado) VALUES (?, ?, ?, ?, ?, ?);",
                    new String[] { "srl_cod_pago" });
            ps.setString(1, codOperador);
            ps.setString(2, nroOperacion);
            ps.setInt(3, monto);
            ps.setObject(4, fecAbono);
            ps.setString(5, voucher);
            ps.setInt(6, 1);
            return ps;
        }, keyHolder);
        updateSaldoOperador(codOperador, monto, true);
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
        int status = jdbcTemplate.update(
                "DELETE FROM t_grupo_visitante WHERE srl_cod_grupo = ? AND srl_cod_visitante = ? ", codGrupo,
                codVisitante);
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
        int status = jdbcTemplate.update("UPDATE t_usuario SET var_clave = ? WHERE srl_cod_usuario = ?", newClave,
                codUser);
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
    public Map<String, Object> updateVisitante(Integer codDocumento, Integer codCategoria, Integer codPais,
            String nombre, String apellido, String nroDocumento, LocalDate fNac, Integer sexo, Integer id) {
        int status = jdbcTemplate.update(
                "UPDATE t_visitante SET srl_cod_documento=?, srl_cod_categoria=?, srl_cod_pais=?, var_nombre=?, var_apellido=?, var_nro_documento=?, dte_fec_nacimiento=?, bol_sexo=?  WHERE srl_cod_visitante = ? ",
                codDocumento, codCategoria, codPais, nombre, apellido, nroDocumento, fNac, sexo, id);
        Map<String, Object> obj = new HashMap<String, Object>();
        obj.put("id", status);
        return obj;
    }

    // Editando Grupo
    public Map<String, Object> updateGrupo(LocalDate date, Integer codRuta, Integer id) {
        int status = jdbcTemplate.update(
                "UPDATE t_grupo SET dte_fec_programada = ?, srl_cod_ruta = ?  WHERE srl_cod_grupo = ? ", date, codRuta,
                id);
        Map<String, Object> obj = new HashMap<String, Object>();
        obj.put("id", status);
        return obj;
    }

    // Tomando asistencia al grupo
    public Map<String, Object> updateAsistencia(Integer codGrupo, Integer codVisitante, Boolean asistio) {
        int status = jdbcTemplate.update(
                "UPDATE t_grupo_visitante SET bol_ingreso = ? WHERE srl_cod_grupo = ? AND srl_cod_visitante = ?",
                asistio, codGrupo, codVisitante);
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
                date, nroVisitantes, nroInasistentes, costo, estado, documento, userModificacion, dateModificacion,
                codGrupo);
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
        Map<String, Object> obj = new HashMap<String, Object>();
        obj.put("id", status);
        return obj;
    }

    // Rechazar pago
    public Map<String, Object> updatePagoRechazo(Integer codPago, String motivoRechazo) {
        int status = jdbcTemplate.update(
                "UPDATE t_pago SET int_estado = 3, txt_motivorechazo = ? WHERE srl_cod_pago = ? ", motivoRechazo,
                codPago);
        Map<String, Object> pago = showConsultaPago(codPago);
        System.out.println("PAGO" +  pago);
        Map<String, Object> operador = updateSaldoOperador(
                pago.get("var_cod_operador").toString(), 
                Integer.parseInt(pago.get("num_monto").toString()),
                false);
        return operador;
    }

    // agregar pago
    public Map<String, Object> updateSaldoOperador(String codOperador, Integer monto, boolean agregar) {
        Map<String, Object> operador = showConsultaOperador(codOperador).get(0);
        System.out.println(" ========= Here is my Saldo" + operador.get("num_saldo"));
        Integer saldo = Integer.parseInt(operador.get("num_saldo").toString());
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
        obj.put("operador", operador );
        return obj;
    }

    // *********************** Fin Actualizar registros
    // *********************************************

    // ************************ Listando con parametros
    // ***********************************

    // Validando usuario
    public Map<String, Object> showLoginUser(String user, String pwd) {
        Map<String, Object> map = new HashMap<String, Object>();
        List<Map<String, Object>> list = jdbcTemplate
                .queryForList("SELECT * FROM t_usuario u " + "INNER JOIN t_operador o ON u.var_email = o.var_email "
                        + "WHERE u.var_email = ? AND var_clave = ? AND u.bol_estado = '1' ", user, pwd);
        if (list.size() > 0) {
            map.put("user", list.get(0));
            return map;
        } else {
            map.put("error", "No se ha encontrado usuario");
            return map;
        }
    }

    // Consultando operador
    public List<Map<String, Object>> showConsultaOperador(String codOperador) {
        List<Map<String, Object>> list = jdbcTemplate
                .queryForList("SELECT * FROM t_operador WHERE var_cod_operador = ? ", codOperador);
        return list;
    }

    // Consultando operador x usuario
    public List<Map<String, Object>> showConsultaOperadorxemail(String email) {
        List<Map<String, Object>> list = jdbcTemplate.queryForList("SELECT * FROM t_operador WHERE var_email = ? ",
                email);
        return list;
    }

    // Consultando visitante
    public List<Map<String, Object>> showConsultaVisitante(Integer codVisitante) {
        List<Map<String, Object>> list = jdbcTemplate
                .queryForList("SELECT * FROM t_visitante WHERE srl_cod_visitante = ? ", codVisitante);
        return list;
    }

    // Consultando Grupo
    public List<Map<String, Object>> showConsultaGrupo(Integer codGrupo) {
        List<Map<String, Object>> list = jdbcTemplate
                .queryForList("SELECT * FROM t_visitante WHERE srl_cod_visitante = ? ", codGrupo);
        return list;
    }

    // Consultando Pago
    public Map<String, Object> showConsultaPago(Integer codPago) {
        List<Map<String, Object>> list = jdbcTemplate.queryForList("SELECT * FROM t_pago WHERE srl_cod_pago = ? ",
                codPago);
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
        List<Map<String, Object>> list = jdbcTemplate.queryForList("SELECT * FROM t_grupo WHERE var_cod_operador = ?",
                codOperador);
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

    // lista los usuarios
    public List<Map<String, Object>> showListUsuarios() {
        List<Map<String, Object>> list_usuarios = jdbcTemplate.queryForList("SELECT * FROM t_usuario");
        return list_usuarios;
    }

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
        List<Map<String, Object>> list_paises = jdbcTemplate.queryForList("SELECT * FROM t_pais");
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
    public List<Map<String, Object>> showListGrupos() {
        List<Map<String, Object>> list_grupos = jdbcTemplate.queryForList("SELECT * FROM t_grupo");
        return list_grupos;
    }

    // lista de grupos
    public List<Map<String, Object>> showListOperadores() {
        List<Map<String, Object>> list_grupos = jdbcTemplate.queryForList("SELECT * FROM t_operador");
        return list_grupos;
    }
    // lista de pagos
    public List<Map<String, Object>> showListPagos() {
        List<Map<String, Object>> list_pagos = jdbcTemplate.queryForList(
            "SELECT p.*,o.var_razonsocial FROM t_pago p INNER JOIN t_operador o ON o.var_cod_operador = p.var_cod_operador");
        return list_pagos;
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
}
