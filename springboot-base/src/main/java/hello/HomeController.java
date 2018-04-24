package hello;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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