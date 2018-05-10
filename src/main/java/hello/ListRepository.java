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

    public List<Map> showList(){
    	@SuppressWarnings("unchecked")
		List<Map> list = entityManager.createNativeQuery("select * from routes")
                .getResultList();
    	return list;
    }

    public List<Map<String, Object>> showListJdbc(){
		List<Map<String, Object>> list =  jdbcTemplate.queryForList("SELECT * from routes "); 
    	return list;
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
