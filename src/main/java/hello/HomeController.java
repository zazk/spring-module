package hello;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import hello.entities.Visitante;
import hello.storage.StorageService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.core.io.Resource;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class HomeController {

    private final StorageService storageService;
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ListRepository listRepository;

    @Autowired
    public HomeController(StorageService storageService) {
        this.storageService = storageService;
    }

    @RequestMapping("/")
    @ResponseBody
    public String index() {
        Book book = bookRepository.findOne(1);
        return book.getTitle();
    }


    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<Map<String, Object>> list() {
        //Get from Query
        System.out.println(listRepository.showListJdbc());
        return listRepository.showListJdbc();
    }

    // -------------------------------------------
    // Examples
    // -------------------------------------------

    // Weird annotation for date parameteres
    // Example with params http://localhost:8080/list_rutas_params_date?date=2018-05-13
    @RequestMapping(value = "/insert_row", produces = "application/json")
    @ResponseBody
    public List<Map<String, Object>> insert_row(
            // required params
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam LocalDate date,
            @RequestParam String title
    ) {
        System.out.println("insert the id:" + listRepository.insertRow(title, date));
        return listRepository.showListJdbc();
    }

    // Weird annotation for date parameteres
    // Example with params http://localhost:8080/list_rutas_params_date?date=2018-05-13
    @RequestMapping(value = "/update_row", produces = "application/json")
    @ResponseBody
    public List<Map<String, Object>> update_row(
            // required params
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam LocalDate date,
            @RequestParam String title,
            @RequestParam Integer id
    ) {
        //Get from Query with Params
        System.out.println(listRepository.updateRow(title, date, id));
        System.out.println("Update the id:" + id);
        return listRepository.showListJdbc();
    }

    // Weird annotation for date parameteres
    // Example with params http://localhost:8080/list_rutas_params_date?date=2018-05-13
    @RequestMapping(value = "/delete_row", produces = "application/json")
    @ResponseBody
    public List<Map<String, Object>> delete_row(
            // required params
            @RequestParam Integer id
    ) {
        //Get from Query with Params
        System.out.println(listRepository.deleteRow(id));
        System.out.println("Remove the id:" + id);
        return listRepository.showListJdbc();
    }

    // Weird annotation for date parameteres
    // Example with params http://localhost:8080/list_rutas_params_date?date=2018-05-13
    @RequestMapping(value = "/list_rutas_date", produces = "application/json")
    @ResponseBody
    public List<Map<String, Object>> list_rutas_date(
            // required params
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam LocalDate date
    ) {
        //Get from Query with Params
        System.out.println(listRepository.showListWithDate(date));
        System.out.println("Sure can you print this model:" + date);
        return listRepository.showListWithDate(date);
    }

    // Example with params http://localhost:8080/list_rutas_params?usuario=id=1&title=to
    @RequestMapping(value = "/list_rutas_params", produces = "application/json")
    @ResponseBody
    public List<Map<String, Object>> list_rutas_params(
            // param state setting as a optional
            @RequestParam(required = false) Integer state,
            // param state setting as a default value
            @RequestParam(defaultValue = "red") String color,
            // required params
            @RequestParam Integer id,
            @RequestParam String title
    ) {
        //Get from Query with Params
        System.out.println(listRepository.showListWithParams(id, title));
        System.out.println("Sure can you print this model:" + state);
        return listRepository.showListWithParams(id, title);
    }

    // Example with Map params http://localhost:8080/list_rutas_params_multiple?id=1&title=to
    @RequestMapping(value = "/list_rutas_params_multiple", produces = "application/json")
    @ResponseBody
    public List<Map<String, Object>> list_rutas_params_multiple(
            // get multiple params in a varible
            @RequestParam Map<String, String> parameters
    ) {
        //Get from Query with Params
        System.out.println(listRepository.showListWithParamsMap(parameters));
        System.out.println("Sure can you print this model:" + parameters
                + " Usuario:" + parameters.get("id") + " Password:" + parameters.get("title"));
        return listRepository.showListWithParamsMap(parameters);
    }


    // ************************ Insertando registros ******************************************

    //Insertando noticias
    @RequestMapping(value = "/insert_operador", produces = "application/json")
    @ResponseBody
    public List<Map<String, Object>> insert_news(
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


    //Insertando noticias
    @RequestMapping(value = "/insert_usuario", produces = "application/json")
    @ResponseBody
    public List<Map<String, Object>> insert_usuario(
            // required params
            @RequestParam String email,
            @RequestParam String clave

    ) {
        Map obj = listRepository.insertUsuario(email,clave,email);
        System.out.println("insert the id:" + obj);
        return listRepository.showListUsuarios();
    }

    //Insertando noticias
    @RequestMapping(value = "/insert_news", produces = "application/json")
    @ResponseBody
    public List<Map<String, Object>> insert_news(
            // required params
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam LocalDate date,
            @RequestParam String titulo,
            @RequestParam String contenido,
            @RequestParam String user

    ) {
        System.out.println("insert the id:" + listRepository.insertNews(titulo, contenido, date, user));
        return listRepository.showListNoticiasActivas();
    }

    //Insertando visitantes
    @RequestMapping(value = "/insert_visitante", produces = "application/json")
    @ResponseBody
    public List<Map<String, Object>> insert_visitante(
            // required params
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam LocalDate fNac,
            @RequestParam Integer codDocumento,
            @RequestParam Integer codCategoria,
            @RequestParam Integer codPais,
            @RequestParam String nombre,
            @RequestParam String apellido,
            @RequestParam String nroDocumento,
            @RequestParam Integer sexo
    ) {
        System.out.println("insert the id:" + listRepository.insertVisitante(codDocumento, codCategoria, codPais, nombre, apellido, nroDocumento, fNac, sexo));
        return listRepository.showListVisitantes();
    }

    //Insertando grupos
    @RequestMapping(value = "/insert_grupo", produces = "application/json")
    @ResponseBody
    public List<Map<String, Object>> insert_grupo(
            // required params
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam LocalDate fecProgramada,
            @RequestParam String codOperador,
            @RequestParam Integer codRuta,
            @RequestParam Integer nroVisitantes,
            @RequestParam Integer numCosto,
            @RequestParam String insUsuario
    ) {
        System.out.println("insert the id:" + listRepository.insertGrupo(codOperador, codRuta, fecProgramada, nroVisitantes, numCosto, insUsuario));
        return listRepository.showConsultaGrupoOperador(codOperador);
    }


    //Insertando visitantes al grupos
    @RequestMapping(value = "/insert_visitantegrupo", produces = "application/json")
    @ResponseBody
    public List<Map<String, Object>> insert_visitantegrupo(
            // required params
            @RequestParam Integer codGrupo,
            @RequestParam Integer codVisitante
    ) {
        System.out.println("insert the id:" + listRepository.insertVisitanteGrupo(codGrupo, codVisitante));
        return listRepository.showVisitantexGrupo(codGrupo);
    }

    //Insertando visitantes al grupos
    @RequestMapping(value = "/insert_visitantes", produces = "application/json")
    @ResponseBody
    public Visitante[] insert_visitantegrupo(
            // required params
            @RequestParam Visitante[] visitantes,
            @RequestParam Integer codGrupo
    ) {
        //System.out.println("insert the id:" + listRepository.insertVisitanteGrupo(codGrupo, codVisitante));
        return visitantes;
    }

    //Insertando pagos
    @RequestMapping(value = "/insert_pago", produces = "application/json")
    @ResponseBody
    public Map<String, Object> insert_pago(
            // required params
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam LocalDate fecAbono,
            @RequestParam String codOperador,
            @RequestParam String nroOperacion,
            @RequestParam Integer monto,
            @RequestParam String voucher
    ) {
        System.out.println("insert the pago:" + monto);
        return listRepository.insertPago(codOperador, nroOperacion, monto, fecAbono, voucher);
    }


    // ***************************** Fin insertando registros ************************************


    // ******************************** Eliminando Registros ****************************************

    // Eliminando noticias
    @RequestMapping(value = "/delete_news", produces = "application/json")
    @ResponseBody
    public List<Map<String, Object>> delete_news(
            // required params
            @RequestParam Integer codNoticia
    ) {
        //Get from Query with Params
        System.out.println(listRepository.deleteNews(codNoticia));
        System.out.println("Remove the id:" + codNoticia);
        return listRepository.showListNoticiasActivas();
    }

    // Eliminando visitante de grupo
    @RequestMapping(value = "/delete_visitantegrupo", produces = "application/json")
    @ResponseBody
    public List<Map<String, Object>> delete_visitantegrupo(
            // required params
            @RequestParam Integer codGrupo,
            @RequestParam Integer codVisitante
    ) {
        //Get from Query with Params
        System.out.println(listRepository.deleteVisitanteGrupo(codGrupo, codVisitante));
        System.out.println("Remove the id:" + codGrupo + " vistante: " + codVisitante);
        return listRepository.showVisitantexGrupo(codGrupo);
    }


    // ****************************** Fin eliminando registros ********************************


    // ********************************* Actualizando registros ****************************

    // Cambiando contraseña
    @RequestMapping(value = "/update_pwd", produces = "application/json")
    @ResponseBody
    public List<Map<String, Object>> update_pwd(
            // required params
            @RequestParam String newClave,
            @RequestParam Integer codUser
    ) {
        //Get from Query with Params
        System.out.println(listRepository.updatePwd(newClave, codUser));
        System.out.println("Update the id:" + codUser);
        return listRepository.showListNoticiasActivas();
    }


    // Editando noticias
    @RequestMapping(value = "/update_news", produces = "application/json")
    @ResponseBody
    public List<Map<String, Object>> update_news(
            // required params
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam LocalDate date,
            @RequestParam String titulo,
            @RequestParam String contenido,
            @RequestParam Boolean estado,
            @RequestParam Integer id
    ) {
        //Get from Query with Params
        System.out.println(listRepository.updateNews(titulo, contenido, date, estado, id));
        System.out.println("Update the id:" + id);
        return listRepository.showListNoticiasActivas();
    }


    // Editando visitante
    @RequestMapping(value = "/update_visitante", produces = "application/json")
    @ResponseBody
    public List<Map<String, Object>> update_visitante(
            // required params
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam LocalDate date,
            @RequestParam Integer codDocumento,
            @RequestParam Integer codCategoria,
            @RequestParam Integer codPais,
            @RequestParam String nombre,
            @RequestParam String apellido,
            @RequestParam String nroDocumento,
            @RequestParam Integer sexo,
            @RequestParam Integer id
    ) {
        //Get from Query with Params
        System.out.println(listRepository.updateVisitante(codDocumento, codCategoria, codPais, nombre, apellido, nroDocumento, date, sexo, id));
        System.out.println("Update the id:" + id);
        return listRepository.showListVisitantes();
    }

    // Editando grupo
    @RequestMapping(value = "/update_grupo", produces = "application/json")
    @ResponseBody
    public List<Map<String, Object>> update_grupo(
            // required params
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam LocalDate date,
            @RequestParam Integer codRuta,
            @RequestParam Integer codGrupo
    ) {
        //Get from Query with Params
        System.out.println(listRepository.updateGrupo(date, codRuta, codGrupo));
        System.out.println("Update the id:" + codGrupo);
        return listRepository.showConsultaGrupo(codGrupo);
    }

    // Tomar Asistencia al grupo
    @RequestMapping(value = "/update_asistencia", produces = "application/json")
    @ResponseBody
    public List<Map<String, Object>> update_asistencia(
            // required params
            @RequestParam Integer codGrupo,
            @RequestParam Integer codVisitante,
            @RequestParam Boolean asistio
    ) {
        //Get from Query with Params
        System.out.println(listRepository.updateAsistencia(codGrupo, codVisitante, asistio));
        System.out.println("Update the id:" + codGrupo);
        return listRepository.showConsultaGrupo(codGrupo);
    }

    // Verifica visita del grupo
    @RequestMapping(value = "/update_visitagrupo", produces = "application/json")
    @ResponseBody
    public List<Map<String, Object>> update_visitagrupo(
            // required params
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam LocalDate date,
            @RequestParam Integer nroVisitantes,
            @RequestParam Integer nroInasistentes,
            @RequestParam Integer costo,
            @RequestParam Integer estado,
            @RequestParam String documento,
            @RequestParam String userModificacion,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam LocalDate dateModificacion,
            @RequestParam Integer codGrupo
    ) {
        //Get from Query with Params
        System.out.println(listRepository.updateVisitaGrupo(date, nroVisitantes, nroInasistentes, costo, estado, documento, userModificacion, dateModificacion, codGrupo));
        System.out.println("Update the id:" + codGrupo);
        return listRepository.showConsultaGrupo(codGrupo);
    }


    // Agregar documento al grupo
    @RequestMapping(value = "/update_docgrupo", produces = "application/json")
    @ResponseBody
    public List<Map<String, Object>> update_docgrupo(
            // required params
            @RequestParam Integer codGrupo,
            @RequestParam String documento
    ) {
        //Get from Query with Params
        System.out.println(listRepository.updateDocGrupo(codGrupo, documento));
        System.out.println("Update the id:" + codGrupo);
        return listRepository.showConsultaGrupo(codGrupo);
    }

    // Aprobar pago
    @RequestMapping(value = "/update_pagoaprobado", produces = "application/json")
    @ResponseBody
    public Map<String, Object> update_pagoaprobado(
            // required params
            @RequestParam Integer codPago
    ) {
        //Get from Query with Params
        System.out.println("update_pagoaprobado -- Update the id:" + codPago);
        return listRepository.updatePagoAprobado(codPago);
    }

    // Rechazar pago
    @RequestMapping(value = "/update_pagorechazo", produces = "application/json")
    @ResponseBody
    public Map<String, Object> update_pagorechazo(
            // required params
            @RequestParam Integer codPago,
            @RequestParam String motivoRechazo
    ) {
        //Get from Query with Params
        System.out.println("Update the id:" + codPago);
        return listRepository.updatePagoRechazo(codPago, motivoRechazo);
    }


    // ********************************** Fin actualizando registros *********************


    // **************************** Listando con parametros *************************

    // Validando usuario
    @RequestMapping(value = "/login_user", produces = "application/json")
    @ResponseBody
    public Map<String, Object> login_user(
            // required params
            @RequestParam String user,
            @RequestParam String pwd
    ) {
        //Get from Query with Params
        System.out.println(listRepository.showLoginUser(user, pwd));
        return listRepository.showLoginUser(user, pwd);
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
    public List<Map<String, Object>> consulta_operadorxemail(
            // required params
            @RequestParam String email
    ) {
        //Get from Query with Params
        System.out.println(listRepository.showConsultaOperadorxemail(email));
        return listRepository.showConsultaOperadorxemail(email);
    }


    // Consulta visitante
    @RequestMapping(value = "/consulta_visitante", produces = "application/json")
    @ResponseBody
    public List<Map<String, Object>> consulta_visitante(
            // required params
            @RequestParam Integer codVisitante
    ) {
        //Get from Query with Params
        System.out.println(listRepository.showConsultaVisitante(codVisitante));
        return listRepository.showConsultaVisitante(codVisitante);
    }


    // Consulta Grupo
    @RequestMapping(value = "/consulta_grupo", produces = "application/json")
    @ResponseBody
    public List<Map<String, Object>> consulta_grupo(
            // required params
            @RequestParam Integer codGrupo
    ) {
        //Get from Query with Params
        System.out.println(listRepository.showConsultaGrupo(codGrupo));
        return listRepository.showConsultaGrupo(codGrupo);
    }


    // Listando visitantes por grupo
    @RequestMapping(value = "/list_visitantexgrupo", produces = "application/json")
    @ResponseBody
    public List<Map<String, Object>> list_visitantexgrupo(
            // required params
            @RequestParam Integer codGrupo
    ) {
        //Get from Query with Params
        System.out.println(listRepository.showVisitantexGrupo(codGrupo));
        return listRepository.showVisitantexGrupo(codGrupo);
    }


    // Consulta Grupo *********** OPERADOR ****************
    @RequestMapping(value = "/consulta_grupooperador", produces = "application/json")
    @ResponseBody
    public List<Map<String, Object>> consulta_grupooperador(
            // required params
            @RequestParam String codOperador
    ) {
        //Get from Query with Params
        System.out.println(listRepository.showConsultaGrupoOperador(codOperador));
        return listRepository.showConsultaGrupoOperador(codOperador);
    }


    // Consulta Pagos ********* OPERADOR ****************
    @RequestMapping(value = "/consulta_pagooperador", produces = "application/json")
    @ResponseBody
    public List<Map<String, Object>> consulta_pagooperador(
            // required params
            @RequestParam String codOperador
    ) {
        //Get from Query with Params
        System.out.println(listRepository.showConsultaPagoOperador(codOperador));
        return listRepository.showConsultaPagoOperador(codOperador);
    }

    // Filtro Pagos ********** OPERADOR ************
    @RequestMapping(value = "/filtro_pagooperador", produces = "application/json")
    @ResponseBody
    public List<Map<String, Object>> filtro_pagooperador(
            // required params
            @RequestParam String nroOperacion,
            @RequestParam String fecPago,
            @RequestParam Integer estado
    ) {

        System.out.println(listRepository.showFiltroPagoOperador(nroOperacion, fecPago, estado));
        return listRepository.showFiltroPagoOperador(nroOperacion, fecPago, estado);
    }

    // Filtro Pagos ********* RECAUDADOR ************
    @RequestMapping(value = "/filtro_pagorecaudador", produces = "application/json")
    @ResponseBody
    public List<Map<String, Object>> filtro_pagorecaudador(
            // required params
            @RequestParam String nroOperacion,
            @RequestParam String operador
    ) {

        System.out.println(listRepository.showFiltroPagoRecaudador(nroOperacion, operador));
        return listRepository.showFiltroPagoRecaudador(nroOperacion, operador);
    }

    // Filtro Grupos ********** OPERADOR **************
    @RequestMapping(value = "/filtro_grupooperador", produces = "application/json")
    @ResponseBody
    public List<Map<String, Object>> filtro_grupooperador(
            // required params
            @RequestParam String codGrupo,
            @RequestParam String fecVisita,
            @RequestParam Integer estado
    ) {

        System.out.println(listRepository.showFiltroGrupoOperador(codGrupo, fecVisita, estado));
        return listRepository.showFiltroGrupoOperador(codGrupo, fecVisita, estado);
    }

    // ************************* Fin listando con parametros ***********************


    // ******************************* Listando ***************************************

    // Lista visitantes
    @RequestMapping(value = "/list_visitantes", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<Map<String, Object>> list_visitantes() {
        //Get from Query
        System.out.println(listRepository.showListVisitantes());
        return listRepository.showListVisitantes();
    }

    // Lista las rutas
    @RequestMapping(value = "/list_rutas", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<Map<String, Object>> list_rutas() {
        //Get from Query
        System.out.println(listRepository.showListRutas());
        return listRepository.showListRutas();
    }

    // Lista las categorias
    @RequestMapping(value = "/list_categorias", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<Map<String, Object>> list_categorias() {
        //Get from Query
        System.out.println(listRepository.showListCategorias());
        return listRepository.showListCategorias();
    }

    // Lista los paises
    @RequestMapping(value = "/list_paises", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<Map<String, Object>> list_paises() {
        //Get from Query
        System.out.println(listRepository.showListPaises());
        return listRepository.showListPaises();
    }

    // Lista los tipos de documentos
    @RequestMapping(value = "/list_tipdocumentos", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<Map<String, Object>> list_tipdocumentos() {
        //Get from Query
        System.out.println(listRepository.showListTipdocumentos());
        return listRepository.showListTipdocumentos();
    }

    // Lista las noticias
    @RequestMapping(value = "/list_noticias", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<Map<String, Object>> list_noticias() {
        //Get from Query
        System.out.println(listRepository.showListNoticias());
        return listRepository.showListNoticias();
    }

    // Lista las noticias activas
    @RequestMapping(value = "/list_noticias_activas", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<Map<String, Object>> list_noticias_activas() {
        //Get from Query
        System.out.println(listRepository.showListNoticiasActivas());
        return listRepository.showListNoticiasActivas();
    }

    // Lista de grupos
    @RequestMapping(value = "/list_grupos", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<Map<String, Object>> list_grupos() {
        //Get from Query
        System.out.println(listRepository.showListGrupos());
        return listRepository.showListGrupos();
    }

    // *************************** Fin listando ********************************


    @RequestMapping(value = "/get-books", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<Map<String, Object>> listPost() {
        //Get from Store Procedure
        System.out.println(listRepository.showListStoreProcedures());
        return listRepository.showListStoreProcedures();
    }

    @RequestMapping(value = "/set-book", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public String setBookName() {
        //Get from Store Procedure
        System.out.println(listRepository.showListStoreProcedures());
        listRepository.updateRow("Otro Name", 1);
        return "true";
    }

    // *************************** Subir Archivo *********************************

    @RequestMapping(value = "/upload-pago", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String, Object> handleFileUpload(
            @RequestParam("imagen") MultipartFile image
        ) {
        Map<String,Object> map = new HashMap<String, Object>();

        System.out.println("Uploading File============= " +  image.getContentType());
        if (
                image.getContentType().equals("image/jpeg")
                || image.getContentType().equals("image/png")
                ||   image.getContentType().equals("application/pdf") ){
            String filename = storageService.store(image);
            map.put("message", filename);
        }else{
            map.put("error", "Tipo de Archivo no aceptado");
        }
        return map;
    }

    @GetMapping("/file/{filename:.+}")
    @ResponseBody
    public Resource serveFile(@PathVariable String filename) {
        System.out.println("Serving File File============= ");
        Resource file = storageService.loadAsResource(filename);
        return file;
        //ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
         //       "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

}