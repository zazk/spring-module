package pe.gob.sernanp.pda.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pe.gob.sernanp.pda.repository.ListRepository;
import pe.gob.sernanp.pda.repository.UsuarioRepository;

import java.util.Map;

@Controller
public class UserController {

  @Autowired
  private ListRepository listRepository;

  @Autowired
  private UsuarioRepository usuarioRepository;

  // Validando usuario
  @RequestMapping(value = "/login_user", produces = "application/json")
  @ResponseBody
  public Map<String, Object> login_user(
    // required params
    @RequestParam String user,
    @RequestParam String pwd
  ) {
    //Get from Query with Params
    System.out.println(usuarioRepository.showLoginUser(user, pwd));
    return usuarioRepository.showLoginUser(user, pwd);
  }


  // Validando usuario
  @RequestMapping(value = "/login_sernanp", produces = "application/json")
  @ResponseBody
  public Map<String, Object> login_sernanp(
    // required params
    @RequestParam String user,
    @RequestParam String pwd
  ) {
    //Get from Query with Params
    System.out.println(usuarioRepository.showLoginSernanp(user, pwd));
    return usuarioRepository.showLoginSernanp(user, pwd);
  }

}
