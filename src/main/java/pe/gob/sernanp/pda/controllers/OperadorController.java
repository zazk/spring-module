package pe.gob.sernanp.pda.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pe.gob.sernanp.pda.entities.Grupo;
import pe.gob.sernanp.pda.entities.Operador;
import pe.gob.sernanp.pda.repository.ListRepository;
import pe.gob.sernanp.pda.repository.OperadorRepository;

import java.util.List;
import java.util.Map;

@Controller
public class OperadorController {

  @Autowired
  private OperadorRepository operadorRepository;

  @Autowired
  private ListRepository listRepository;

  //Insertando Operador
  @RequestMapping(value = "/insert_operador", produces = "application/json")
  @ResponseBody
  public List<Map<String, Object>> insert_operador(
    // required params
    @RequestParam String codigo,
    @RequestParam String ruc,
    @RequestParam String razonsocial,
    @RequestParam String direccion,
    @RequestParam String telefono,
    @RequestParam String email,
    @RequestParam String web,
    @RequestParam int saldo,
    @RequestParam boolean estado
  ) {
    listRepository.insertOperador(
      codigo,
      ruc,
      razonsocial,
      direccion,
      telefono,
      email,
      web,
      saldo,
      estado
    );
    System.out.println("insert the id:" + codigo);
    return listRepository.showListOperadores();
  }



  //Insertando Operador Entity
  @RequestMapping(value = "/save_operador", produces = "application/json")
  @ResponseBody
  public Operador save_operador(
    // required params
    @RequestParam String  operador
    ) {
    Gson gson = new GsonBuilder().create();
    Operador o = gson.fromJson(operador, Operador.class);
    operadorRepository.save(o);
    System.out.println("insert the id:" + o.getCodigo());
    return o;
  }

  //Insertando Operador Entity
  @RequestMapping(value = "/save_op", produces = "application/json")
  @ResponseBody
  public Operador save_operador(
    // required params
    @RequestParam Operador  operador
  ) {
    // operadorRepository.save(o);
    System.out.println("insert the id:" + operador.getCodigo());
    return operador;
  }

  // Consulta operador
  @RequestMapping(value = "/consulta_operador", produces = "application/json")
  @ResponseBody
  public List<Map<String, Object>> consulta_operador(
    // required params
    @RequestParam String codOperador
  ) {
    //Get from Query with Params
    System.out.println(listRepository.showConsultaOperador(codOperador));
    return listRepository.showConsultaOperador(codOperador);
  }


  // Consulta operador x email
  @RequestMapping(value = "/consulta_operadorxemail", produces = "application/json")
  @ResponseBody
  public Map<String, Object> consulta_operadorxemail(
    // required params
    @RequestParam String email
  ) {
    //Get from Query with Params
    System.out.println(listRepository.showConsultaOperadorxemail(email));
    return listRepository.showConsultaOperadorxemail(email).get(0);
  }

}
