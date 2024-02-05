package org.generation.italy.applicationserver.banca.control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.time.LocalDateTime;
import javax.servlet.http.HttpSession;

import org.generation.italy.applicationserver.banca.model.BancaModelException;
import org.generation.italy.applicationserver.banca.model.TestJdbcBanca;
import org.generation.italy.applicationserver.banca.model.entity.Conto;

/**
 * ControllerServlet.java This servlet acts as a page controller for the
 * application, handling all requests from the user.
 *
 * 
 */
@WebServlet(urlPatterns = {"/apri-conto-cliente", "/form-apri-conto-cliente"})       //java annotation WebServlet: indicazione per il container (GlassFish) con le action della URI inviata dal client che la servlet intende gestire
public class OperatoreBancaServletJ2EE extends HttpServlet {

	
    private static final long serialVersionUID = 1L;

    public void init() {                                                        //metodo che viene richiamato dal container al momento della installazione della webapp in esso con mappatura della servlet (l'altro è 'destroy' (al momento della rimozione della servlet dal container), non gestito in questa servlet).
        //utenteDAO = new UtenteDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) //metodo chiamato dal container (GlassFish), a seguito di ricezione da parte sua del messaggio HTTP-Request, con metodo POST inviato dal client (browser)
            throws ServletException, IOException {
        executeAction(request, response);                                               //re-inoltra al metodo doGet la gestione della action | request e response sono istanze di tipo HttpServletRequest ed HttpServletResponse, create dal container per fornire a e ricevere dalla servlet i dettagli circa i messaggi di HTTP-Request ed HTTP-Response ricevuti da ed inviati al client.
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) //metodo chiamato dal container (GlassFish), a seguito di ricezione da parte sua del messaggio HTTP-Request, con metodo GET inviato dal client (browser)
            throws ServletException, IOException {
        executeAction(request, response);                                               //re-inoltra al metodo doGet la gestione della action | request e response sono istanze di tipo HttpServletRequest ed HttpServletResponse, create dal container per fornire a e ricevere dalla servlet i dettagli circa i messaggi di HTTP-Request ed HTTP-Response ricevuti da ed inviati al client.
   	
    }
    
    protected void executeAction(HttpServletRequest request, HttpServletResponse response) //metodo chiamato dal container (GlassFish), a seguito di ricezione da parte sua del messaggio HTTP-Request, con metodo GET inviato dal client (browser)
            throws ServletException, IOException {
    	
        String actionName = request.getServletPath();                               //parte action della URI: gestione della azione applicativa, la parte della URL dopo il nome della webapp...

		switch (actionName.toLowerCase().trim()) {
		
			//http://localhost:8081/banca/operatore-banca/apri-conto-cliente?iban=ESaa0123456789012345678901234567&codice-fiscale=MRRGVN0123456789&valuta=EUR
			case "/apri-conto-cliente":
				actionApriContoCliente(request, response);
				break;
			
				
			case "/form-apri-conto-cliente":
				actionFormApriContoCliente(request, response);
				break;
				
			default:;
		}
        
    }

	private
	void actionFormApriContoCliente(HttpServletRequest request, HttpServletResponse response) 
															throws ServletException, IOException {
													//throws BancaControlException, BancaModelException	{
        RequestDispatcher dispatcher = request.getRequestDispatcher("form-apri-conto-cliente.jsp");
        //ottiene il riferimento alla apgina JSP
        dispatcher.forward(request, response);
		
	}
    
    
    
	private
	static
	void actionApriContoCliente(HttpServletRequest request, HttpServletResponse response) 
															throws ServletException, IOException {
													//throws BancaControlException, BancaModelException	{
		
		String messageToShow = UserMessages.msgEsitoOkAperturaConto;			//default: messaggio per, successo esito apertura conto
		
		String ibanString = request.getParameter("iban") != null ? request.getParameter("iban") : "";
		String valutaString = request.getParameter("valuta") != null ? request.getParameter("valuta") : "";
		String codiceFiscaleString = request.getParameter("codice-fiscale") != null ? request.getParameter("codice-fiscale") : "";
		
		//controlli sintattici e semantici su parametri da web
		if ( 
			 (!UtilitiesControlliSintatticiSemantici.checkFormatValuta(valutaString))  
			 
			 || 
			 
			 (!UtilitiesControlliSintatticiSemantici.checkSemanticValuta(valutaString))  
			 
			 || 
			 
			 (!UtilitiesControlliSintatticiSemantici.checkFormatCodiceFiscale(codiceFiscaleString)) 
			 
			 || 
			 
			 (!UtilitiesControlliSintatticiSemantici.checkFormatIban(ibanString)) 
		   ) {
			
			messageToShow = UserMessages.msgErroreParametriAperturaConto;
			//throw new BancaControlException("OperatoreBancaSErvlet -> actionApriContoCliente -> Errore nel formato dei dati input!!!");
		}
		else {
		
			
			
			//accede alla fonte dati, istanziando TEstJdbcBanca
			//che ha come attributi i riferimenti ai metodi delle classi DAO.
			Conto conto = 
				new Conto(ibanString
						, codiceFiscaleString
						, valutaString
						, 0.0f); 
			
			
			try {
				TestJdbcBanca testJdbcBanca =  new TestJdbcBanca();
				testJdbcBanca.getContoDao().addConto(conto);
				
				messageToShow = UserMessages.msgEsitoOkAperturaConto;
				
			} catch (BancaModelException e) {
				messageToShow = UserMessages.msgErroreOperazioneAperturaConto;
				//htmlContentPage = e.getMessage().getBytes();
	
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//**************************************************************
		// mostra interfaccia html/jsp di messaggistica per esito operazione
		//**************************************************************	
	    
        request.setAttribute("message-to-show", messageToShow);
        //imposta il parametro nominativoUtenteLoggato

        RequestDispatcher dispatcher = request.getRequestDispatcher("message.jsp");
        //ottiene il riferimento alla apgina JSP
        dispatcher.forward(request, response);
	}
    
	
	
}
