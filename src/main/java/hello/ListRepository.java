package hello;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
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
		List<Map<String, Object>> list =  jdbcTemplate.queryForList("SELECT * FROM t_categoria"); 
    	return list;
    }

    //Example with Arguments.
     // lista las rutas
    // Note the ILIKE operator for case insensitive
    public List<Map<String, Object>> showListWithParams( Integer id, String title ){
        List<Map<String, Object>> list =
                jdbcTemplate.queryForList(
                        "SELECT * FROM t_categoria WHERE id = ? OR title ILIKE ? "
                        , id, ("%" + title + "%")
                        );
        return list;
    }
    // lista las rutas
    // Note the Integer.parseInt case for integers values in the database.
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

    // ---------------------------------------------
    // Valid Queries

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
