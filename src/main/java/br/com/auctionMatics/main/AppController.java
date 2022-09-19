package br.com.auctionMatics.main;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.google.gson.Gson;

@RestController
public class AppController {

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/index")
    public String indexView() {
        return "<html><body><a href=\"http://localhost:8080/obtemdadosbasicos?leilao=380\">Link</a></body></html>";
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/obtemdadosbasicos")
    public String controller(@RequestParam Integer leilao) {

    	ObtemDadosBasicos odb = new ObtemDadosBasicos(leilao);
    	odb.start();
    	
    	StringBuilder sb = new StringBuilder();
    	
    	
    	sb.append("<!DOCTYPE html>");
    	sb.append("<html>");
    	sb.append("<head>");
    	sb.append("<meta charset=\"UTF-8\">");
    	sb.append("<title>Insert title here</title>");
    	sb.append("</head>");
    	sb.append("<body>");
    	sb.append("Estamos preparando sua consulta....");
    	sb.append("</body>");
    	sb.append("</html>");
    	
        return sb.toString();
    }
    
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/checastatusconsulta")
    public String testeView(@RequestParam Integer leilao) {

    	StringBuilder sb = new StringBuilder();

    	sb.append("<!DOCTYPE html>");
    	sb.append("<html>");
    	sb.append("<head>");
    	sb.append("<meta charset=\"UTF-8\">");
    	sb.append("<title>Insert title here</title>");
    	sb.append("</head>");
    	sb.append("<body>");

    	StatusConsultaCRUD sc = new StatusConsultaCRUD();
    	String respostaConsulta = sc.buscaStatusConsulta(leilao);
    			
    	if ( respostaConsulta.equals("INI") ) {
        	sb.append("Estamos preparando sua consulta.... Retorno mais tarde");
    	}else if ( respostaConsulta.equals("FIM") ) {
        	sb.append("Consulta finalizada.... Clique aqui para ver o resultado");
			
		}
    	
    	sb.append("</body>");
    	sb.append("</html>");
    	    	   	
    	    	
        return sb.toString();
    }
    
    
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/buscaTodosLotes")
    public String buscaTodosLotes(@RequestParam Integer leilao) {

    	StringBuilder sb = new StringBuilder();
    	
    	StatusConsultaCRUD sc = new StatusConsultaCRUD();
    	String respostaConsulta = sc.buscaStatusConsulta(leilao);
    	
    	System.out.println("AppController.buscaTodosLotes: " + "Buscando leilao, resultado: " + respostaConsulta);		

    	String json;
    	if ( respostaConsulta.equals("FIM") ) {
    		DadosLoteCRUD dlote = new DadosLoteCRUD();
        	List<DadosLotes> listaLotes = dlote.buscaTodosLotes(leilao);
        	json = new Gson().toJson(listaLotes);
		}else if (respostaConsulta.equals("0")) {
			ObtemDadosBasicos odb = new ObtemDadosBasicos(leilao);
			odb.start();
			json = "<p> Estamos preparando sua consulta. Por favor retorne em alguns minutos.</p>";
		}else{ 
			json = "<p> Estamos preparando sua consulta. Por favor retorne em alguns minutos.</p>";
		} 	
    	
        return json;
    }
    
}
