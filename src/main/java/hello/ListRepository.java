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
    public List<Map> showList(){
    	@SuppressWarnings("unchecked")
		List<Map> list = entityManager.createNativeQuery("select * from t_categoria")
                .getResultList();
    	return list;
    }

    // Example with JDBC - The simplest way.
    public List<Map<String, Object>> showListJdbc(){
		List<Map<String, Object>> list =  jdbcTemplate.queryForList(
		        "SELECT id, title, TO_CHAR(fecha, 'yyyy-mm-dd') fecha FROM t_categoria");
    	return list;
    }

    //Example with Arguments.
     // lista las rutas

    // Insert Row
    public Map<String, Object> insertRow( String title, LocalDate date ){
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(
            connection -> {
                PreparedStatement ps = connection.prepareStatement(
                        "INSERT INTO t_categoria(title,fecha) VALUES(?,?)", new String[]{"id"});
                ps.setString(1, title);
                ps.setObject(2, date);
                return ps;
            }, keyHolder);

        Map<String, Object> obj = new HashMap<String,Object>();
        obj.put("id", keyHolder.getKey() );
        return obj;
    }

    // Update Row
    public Map<String, Object> updateRow( String title, LocalDate date, Integer id ){
        int status = jdbcTemplate.update(
            "UPDATE t_categoria SET title = ?, fecha= ? WHERE id=? ", title ,date, id);
        Map<String, Object> obj = new HashMap<String,Object>();
        obj.put("id", status);
        return obj;
    }

    // Delete Row
    public Map<String, Object> deleteRow( Integer id ){
        int status = jdbcTemplate.update(
            "DELETE FROM t_categoria WHERE id=? ", id);
        Map<String, Object> obj = new HashMap<String,Object>();
        obj.put("id", status);
        return obj;
    }

    // Note the ILIKE operator for case insensitive
    public List<Map<String, Object>> showListWithDate( LocalDate date ){
        List<Map<String, Object>> list =
                jdbcTemplate.queryForList(
                    "SELECT id, title, TO_CHAR(fecha, 'yyyy-mm-dd') fecha FROM t_categoria WHERE date_trunc('day', fecha) = ? "
                    , date
                );
        return list;
    }


    // Note the ILIKE operator for case insensitive
    public List<Map<String, Object>> showListWithParams( Integer id, String title ){
        List<Map<String, Object>> list =
                jdbcTemplate.queryForList(
                        "SELECT * FROM t_categoria WHERE id = ? OR title ILIKE ? "
                        , id, ("%" + title + "%")
                        );
        return list;
    }

    // Note the Integer.parseInt case for integers values in the database. Be careful with type
    // Note the ILIKE operator for case insensitive
    // Note the % for ILIKE expressions in PostgreSQL
    public List<Map<String, Object>> showListWithParamsMap( Map<String, String> map ){
        List<Map<String, Object>> list =
                jdbcTemplate.queryForList(
                        "SELECT * FROM t_categoria WHERE id = ? OR title ILIKE ? "
                        , Integer.parseInt(map.get("id")), ("%" + map.get("title") + "%")
                );
        return list;
    }


    // Validando usuario
    public List<Map<String, Object>> showLoginUser( String user, String pwd ){
        List<Map<String, Object>> list =
                jdbcTemplate.queryForList(
                        "SELECT * FROM t_usuario WHERE var_usuario = ? AND var_clave = ? AND bol_estado = '1' "
                        , user, pwd
                        );
        return list;
    }


    // Listando visitantes por grupo
    public List<Map<String, Object>> showVisitantexGrupo( Integer codGrupo ){
        List<Map<String, Object>> list =
                jdbcTemplate.queryForList(
                        "SELECT * FROM t_usuario WHERE var_usuario = ? AND bol_estado = '1' "
                        , codGrupo
                        );
        return list;
    }


    // *********************** Insertando registros *********************************

    //Insertando noticias
    public Map<String, Object> insertNews( String titulo, String contenido, LocalDate date, String user ){
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(
            connection -> {
                PreparedStatement ps = connection.prepareStatement(
                        "INSERT INTO t_noticia(var_titulo, txt_contenido, tsp_fec_publicacion, bol_activo, var_usuario) VALUES(?,?,?,?,?)", new String[]{"srl_cod_noticia"});
                ps.setString(1, titulo);
                ps.setString(2, contenido);
                ps.setObject(3, date);
                ps.setObject(4, true);
                ps.setString(5, user);
                return ps;
            }, keyHolder);

        Map<String, Object> obj = new HashMap<String,Object>();
        obj.put("srl_cod_noticia", keyHolder.getKey() );
        return obj;
    }


    //Insertando visitantes
    public Map<String, Object> insertVisitante( Integer codDocumento, Integer codCategoria, Integer codPais, String nombre, String apellido, String nroDocumento, LocalDate fNac, Integer sexo ){
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(
            connection -> {
                PreparedStatement ps = connection.prepareStatement(
                        "INSERT INTO t_visitante(srl_cod_documento, srl_cod_categoria, srl_cod_pais, var_nombre, var_apellido, var_nro_documento, dte_fec_nacimiento, bol_sexo) VALUES (?, ?, ?, ?, ?, ?, ?, ?);", new String[]{"srl_cod_visitante"});
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

        Map<String, Object> obj = new HashMap<String,Object>();
        obj.put("srl_cod_visitante", keyHolder.getKey() );
        return obj;
    }

    //Insertando grupos
    public Map<String, Object> insertGrupo( String codOperador, Integer codRuta, Integer codPais, String nombre, String apellido, String nroDocumento, LocalDate fNac, Integer sexo ){
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(
            connection -> {
                PreparedStatement ps = connection.prepareStatement(
                        "INSERT INTO t_grupo(var_cod_operador, srl_cod_ruta, dte_fec_programada, dte_fec_visita, int_nro_visitante, int_nro_inasistente, num_costo, int_estado, var_documento, txt_motivoobservado, dte_fec_observado, var_usuario, var_usuariomodificacion, dte_fec_modificacion)VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", new String[]{"srl_cod_grupo"});
                ps.setString(1, codDocumento);
                ps.setInt(2, codRuta);
                ps.setInt(3, codPais);
                ps.setString(4, nombre);
                ps.setString(5, apellido);
                ps.setString(6, nroDocumento);
                ps.setObject(7, fNac);
                ps.setInt(8, sexo);
                return ps;
            }, keyHolder);

        Map<String, Object> obj = new HashMap<String,Object>();
        obj.put("srl_cod_grupo", keyHolder.getKey() );
        return obj;
    }



    // ************************ Fin de insertando registros ***********************************************


    // ************************ Eliminando registros ****************************************************

    // Eliminar Noticias
    public Map<String, Object> deleteNews( Integer id ){
        int status = jdbcTemplate.update(
            "DELETE FROM t_noticia WHERE srl_cod_noticia = ? ", id);
        Map<String, Object> obj = new HashMap<String,Object>();
        obj.put("id", status);
        return obj;
    }





    // ********************** Fin eliminando registros ***********************************************


    // *********************** Actualizando registros **************************************

    // Editando Noticias
    public Map<String, Object> updateNews( String titulo, String contenido, LocalDate date, Boolean estado, Integer id ){
        int status = jdbcTemplate.update(
            "UPDATE t_noticia SET var_titulo = ?, txt_contenido = ?, tsp_fec_publicacion= ?, bol_activo = ?  WHERE srl_cod_noticia = ? ", titulo,contenido,date,estado,id);
        Map<String, Object> obj = new HashMap<String,Object>();
        obj.put("id", status);
        return obj;
    }

    // Editando Visitante
    public Map<String, Object> updateVisitante( Integer codDocumento, Integer codCategoria, Integer codPais, String nombre, String apellido, String nroDocumento, LocalDate fNac, Integer sexo, Integer id ){
        int status = jdbcTemplate.update(
            "UPDATE t_visitante SET srl_cod_documento=?, srl_cod_categoria=?, srl_cod_pais=?, var_nombre=?, var_apellido=?, var_nro_documento=?, dte_fec_nacimiento=?, bol_sexo=?  WHERE srl_cod_visitante = ? ", codDocumento, codCategoria, codPais, nombre, apellido, nroDocumento, fNac, sexo, id);
        Map<String, Object> obj = new HashMap<String,Object>();
        obj.put("id", status);
        return obj;
    }


    // *********************** Fin Actualizar registros *********************************************

    // ---------------------------------------------
    // Valid Queries

    // lista los visitantes
        public List<Map<String, Object>> showListVisitantes(){
        List<Map<String, Object>> list_visitantes =  jdbcTemplate.queryForList("SELECT * FROM t_visitante"); 
        return list_visitantes;
    }

    // lista las rutas
        public List<Map<String, Object>> showListRutas(){
        List<Map<String, Object>> list_rutas =  jdbcTemplate.queryForList("SELECT * FROM t_ruta"); 
        return list_rutas;
    }

    // lista las categorias
        public List<Map<String, Object>> showListCategorias(){
        List<Map<String, Object>> list_categorias =  jdbcTemplate.queryForList("SELECT * FROM t_categoria"); 
        return list_categorias;
    }

    // lista los paises
        public List<Map<String, Object>> showListPaises(){
        List<Map<String, Object>> list_paises =  jdbcTemplate.queryForList("SELECT * FROM t_pais"); 
        return list_paises;
    }

    // lista los documentos
        public List<Map<String, Object>> showListTipdocumentos(){
        List<Map<String, Object>> list_tipdocumentos =  jdbcTemplate.queryForList("SELECT * FROM t_tip_documento"); 
        return list_tipdocumentos;
    }

    // lista las noticias
        public List<Map<String, Object>> showListNoticias(){
        List<Map<String, Object>> list_noticias =  jdbcTemplate.queryForList("SELECT * FROM t_noticia"); 
        return list_noticias;
    }

    // lista las noticias activas
        public List<Map<String, Object>> showListNoticiasActivas(){
        List<Map<String, Object>> list_noticias_activas =  jdbcTemplate.queryForList("SELECT * FROM t_noticia WHERE bol_activo='1'"); 
        return list_noticias_activas;
    }
    
    public List<Map<String, Object>> showListStoreProcedures(){
		List<Map<String, Object>> list =  jdbcTemplate.queryForList("SELECT * from get_books()"); 
    	return list;
    }

    public void updateRow( String name, int id){
		jdbcTemplate.update(
                "update books set title = ? where id = ?", 
                name, id);
    }
}
