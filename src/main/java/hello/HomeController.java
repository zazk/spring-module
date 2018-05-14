package hello;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class HomeController {

        @Autowired
        private BookRepository bookRepository;

        @Autowired
        private ListRepository listRepository;

        @RequestMapping("/")
        @ResponseBody
        public String index() {
                Book book = bookRepository.findOne(1);
                return book.getTitle();
        }

        
        @RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json")
        @ResponseBody
        public List<Map<String, Object>> list()  {
        		//Get from Query
                System.out.println( listRepository.showListJdbc() );
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
        ){
            System.out.println( "insert the id:" + listRepository.insertRow( title, date) );
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
        ){
            //Get from Query with Params
            System.out.println( listRepository.updateRow( title, date, id) );
            System.out.println( "Update the id:" + id );
            return listRepository.showListJdbc();
        }

        // Weird annotation for date parameteres
        // Example with params http://localhost:8080/list_rutas_params_date?date=2018-05-13
        @RequestMapping(value = "/delete_row", produces = "application/json")
        @ResponseBody
        public List<Map<String, Object>> delete_row(
                // required params
                @RequestParam Integer id
        ){
            //Get from Query with Params
            System.out.println( listRepository.deleteRow( id) );
            System.out.println( "Remove the id:" + id );
            return listRepository.showListJdbc();
        }

        // Weird annotation for date parameteres
        // Example with params http://localhost:8080/list_rutas_params_date?date=2018-05-13
        @RequestMapping(value = "/list_rutas_date", produces = "application/json")
        @ResponseBody
        public List<Map<String, Object>> list_rutas_date(
                // required params
                @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam LocalDate date
        ){
            //Get from Query with Params
            System.out.println( listRepository.showListWithDate( date) );
            System.out.println( "Sure can you print this model:" + date );
            return listRepository.showListWithDate( date );
        }

    // Example with params http://localhost:8080/list_rutas_params?usuario=id=1&title=to
        @RequestMapping(value = "/list_rutas_params", produces = "application/json")
        @ResponseBody
        public List<Map<String, Object>> list_rutas_params(
                // param state setting as a optional
                @RequestParam( required = false ) Integer state,
                // param state setting as a default value
                @RequestParam( defaultValue = "red") String color,
                // required params
                @RequestParam Integer id,
                @RequestParam String title
                ){
                //Get from Query with Params
                System.out.println( listRepository.showListWithParams( id, title) );
                System.out.println( "Sure can you print this model:" + state );
                return listRepository.showListWithParams( id, title);
        }

        // Example with Map params http://localhost:8080/list_rutas_params_multiple?id=1&title=to
        @RequestMapping(value = "/list_rutas_params_multiple", produces = "application/json")
        @ResponseBody
        public List<Map<String, Object>> list_rutas_params_multiple(
                // get multiple params in a varible
                @RequestParam Map<String, String> parameters
        ){
            //Get from Query with Params
            System.out.println( listRepository.showListWithParamsMap( parameters) );
            System.out.println( "Sure can you print this model:" + parameters
                    + " Usuario:"+ parameters.get("id") + " Password:"+ parameters.get("title") );
            return listRepository.showListWithParamsMap( parameters);
        }

        //---------------
        // Rutas PDA
        //---------------

        // Validando usuario
        @RequestMapping(value = "/login_user", produces = "application/json")
        @ResponseBody
        public List<Map<String, Object>> login_user(
                // required params
                @RequestParam String user,
                @RequestParam String pwd
                ){
                //Get from Query with Params
                System.out.println( listRepository.showLoginUser( user, pwd) );
                return listRepository.showLoginUser(user,pwd);
        }


        // Listando visitantes por grupo
        @RequestMapping(value = "/list_visitantexgrupo", produces = "application/json")
        @ResponseBody
        public List<Map<String, Object>> list_visitantexgrupo(
                // required params
                @RequestParam Integer codGrupo
                ){
                //Get from Query with Params
                System.out.println( listRepository.showVisitantexGrupo( codGrupo ) );
                return listRepository.showVisitantexGrupo(codGrupo);
        }


        // ************************ Insertando registros ******************************************

        //Insertando noticias
        @RequestMapping(value = "/insert_news", produces = "application/json")
        @ResponseBody
        public List<Map<String, Object>> insert_news(
                // required params
                @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam LocalDate date,
                @RequestParam String titulo,
                @RequestParam String contenido,
                @RequestParam String user

        ){
            System.out.println( "insert the id:" + listRepository.insertNews( titulo, contenido, date, user ) );
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
        ){
            System.out.println( "insert the id:" + listRepository.insertVisitante( codDocumento, codCategoria, codPais, nombre, apellido, nroDocumento, fNac, sexo ) );
            return listRepository.showListVisitantes();
        }

        //Insertando grupos
        @RequestMapping(value = "/insert_grupo", produces = "application/json")
        @ResponseBody
        public List<Map<String, Object>> insert_grupo(
                // required params
                @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam LocalDate fNac,
                @RequestParam Integer codDocumento,
                @RequestParam Integer codCategoria,
                @RequestParam Integer codPais,
                @RequestParam String nombre,
                @RequestParam String apellido,
                @RequestParam String nroDocumento,
                @RequestParam Integer sexo
        ){
            System.out.println( "insert the id:" + listRepository.insertGrupo( codDocumento, codCategoria, codPais, nombre, apellido, nroDocumento, fNac, sexo ) );
            return listRepository.showListGrupos();
        }


        // ***************************** Fin insertando registros ************************************


        // ******************************** Eliminando Registros ****************************************

        // Eliminando noticias
        @RequestMapping(value = "/delete_news", produces = "application/json")
        @ResponseBody
        public List<Map<String, Object>> delete_news(
                // required params
                @RequestParam Integer id
        ){
            //Get from Query with Params
            System.out.println( listRepository.deleteNews( id) );
            System.out.println( "Remove the id:" + id );
            return listRepository.showListNoticiasActivas();
        }


        // ****************************** Fin eliminando registros ********************************


        // ********************************* Actualizando registros ****************************


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
        ){
            //Get from Query with Params
            System.out.println( listRepository.updateNews( titulo, contenido, date, estado, id) );
            System.out.println( "Update the id:" + id );
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
        ){
            //Get from Query with Params
            System.out.println( listRepository.updateVisitante( codDocumento, codCategoria, codPais, nombre, apellido, nroDocumento, date, sexo, id) );
            System.out.println( "Update the id:" + id );
            return listRepository.showListVisitantes();
        }


        // ********************************** Fin actualizando registros *********************

        // ******************************* Listando ***************************************

        // Lista visitantes
        @RequestMapping(value = "/list_visitantes", method = RequestMethod.GET, produces = "application/json")
        @ResponseBody
        public List<Map<String, Object>> list_visitantes()  {
            //Get from Query
            System.out.println( listRepository.showListVisitantes() );
            return listRepository.showListVisitantes();
        }

        // Lista las rutas
        @RequestMapping(value = "/list_rutas", method = RequestMethod.GET, produces = "application/json")
        @ResponseBody
        public List<Map<String, Object>> list_rutas()  {
            //Get from Query
            System.out.println( listRepository.showListRutas() );
            return listRepository.showListRutas();
        }

        // Lista las categorias
        @RequestMapping(value = "/list_categorias", method = RequestMethod.GET, produces = "application/json")
        @ResponseBody
        public List<Map<String, Object>> list_categorias()  {
                        //Get from Query
                System.out.println( listRepository.showListCategorias() );
                return listRepository.showListCategorias();
        }

        // Lista los paises
        @RequestMapping(value = "/list_paises", method = RequestMethod.GET, produces = "application/json")
        @ResponseBody
        public List<Map<String, Object>> list_paises()  {
                        //Get from Query
                System.out.println( listRepository.showListPaises() );
                return listRepository.showListPaises();
        }

        // Lista los tipos de documentos
        @RequestMapping(value = "/list_tipdocumentos", method = RequestMethod.GET, produces = "application/json")
        @ResponseBody
        public List<Map<String, Object>> list_tipdocumentos()  {
                        //Get from Query
                System.out.println( listRepository.showListTipdocumentos() );
                return listRepository.showListTipdocumentos();
        }

        // Lista las noticias
        @RequestMapping(value = "/list_noticias", method = RequestMethod.GET, produces = "application/json")
        @ResponseBody
        public List<Map<String, Object>> list_noticias()  {
                        //Get from Query
                System.out.println( listRepository.showListNoticias() );
                return listRepository.showListNoticias();
        }

        // Lista las noticias activas
        @RequestMapping(value = "/list_noticias_activas", method = RequestMethod.GET, produces = "application/json")
        @ResponseBody
        public List<Map<String, Object>> list_noticias_activas()  {
                        //Get from Query
                System.out.println( listRepository.showListNoticiasActivas() );
                return listRepository.showListNoticiasActivas();
        }

        // *************************** Fin listando ********************************

        
        @RequestMapping(value = "/get-books", method = RequestMethod.GET, produces = "application/json")
        @ResponseBody
        public List<Map<String, Object>> listPost()  {
        		//Get from Store Procedure
                System.out.println( listRepository.showListStoreProcedures() );
                return listRepository.showListStoreProcedures();
        }
        
        @RequestMapping(value = "/set-book", method = RequestMethod.GET, produces = "application/json")
        @ResponseBody
        public String setBookName()  {
        		//Get from Store Procedure
                System.out.println( listRepository.showListStoreProcedures() );
                listRepository.updateRow("Otro Name", 1);
                return "true";
        }
}