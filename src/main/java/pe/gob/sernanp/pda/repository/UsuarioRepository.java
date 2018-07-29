package pe.gob.sernanp.pda.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UsuarioRepository {

  @Autowired(required = true)
  private JdbcTemplate jdbcTemplate;
  @Autowired
  private TipoDocumentoRepository tipoDocumentoRepository;
  @Autowired
  private RutaRepository rutaRepository;

  // Insertando Usuario
  public Map<String, Object> insertUsuario(String usuario, String clave, String email, String rol) {
    KeyHolder keyHolder = new GeneratedKeyHolder();

    jdbcTemplate.update(connection -> {
      PreparedStatement ps = connection.prepareStatement(
        "INSERT INTO t_usuario(var_usuario, var_clave, bol_estado, var_email, var_rol) VALUES(?,?,?,?,?)",
        new String[]{"srl_cod_usuario"});
      ps.setString(1, usuario);
      ps.setString(2, clave);
      ps.setBoolean(3, true);
      ps.setString(4, email);
      ps.setString(5, rol);
      return ps;
    }, keyHolder);

    Map<String, Object> obj = new HashMap<String, Object>();
    obj.put("srl_cod_usuario", keyHolder.getKey());
    return obj;
  }

  // Validando usuario
  public Map<String, Object> showLoginSernanp(String user, String pwd) {
    Map<String, Object> map = new HashMap<String, Object>();
    List<Map<String, Object>> list = jdbcTemplate
      .queryForList("SELECT * FROM t_usuario u " + "WHERE u.var_email = ? AND var_clave = ? AND u.bol_estado = '1' "
        + "AND ( var_rol = 'recaudador' OR var_rol = 'puesto' ) ", user, pwd);
    if (list.size() > 0) {
      map.put("user", list.get(0));
      return map;
    } else {
      map.put("error", "Usuario y/o Clave Incorrecta");
      return map;
    }
  }

  // Validando usuario
  public Map<String, Object> showLoginUser(String user, String pwd) {
    Map<String, Object> map = new HashMap<String, Object>();
    List<Map<String, Object>> list = jdbcTemplate
      .queryForList("SELECT o.*, u.srl_cod_usuario, u.var_usuario, u.bol_estado, u.var_email, u.var_rol " +
        " FROM t_usuario u " + "INNER JOIN t_operador o ON u.var_email = o.var_email "
        + "WHERE u.var_email = ? AND var_clave = ? AND u.bol_estado = '1' ", user, pwd);
    if (list.size() > 0) {
      map.put("user", list.get(0));
      map.put("tipoDocumento", tipoDocumentoRepository.findAll());
      map.put("rutas", rutaRepository.findAll());
      return map;
    } else {
      map.put("error", "Usuario y/o Clave Incorrecta");
      return map;
    }
  }

  // lista los usuarios
  public List<Map<String, Object>> showListUsuarios() {
    List<Map<String, Object>> list_usuarios = jdbcTemplate.queryForList("SELECT * FROM t_usuario");
    return list_usuarios;
  }

}
