package org.generation.italy.applicationserver.banca.control;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UtilitiesControlliSintatticiSemantici {

	
	public 
	static
	boolean checkFormatCodiceFiscale(String codiceFiscale) {
		
		Pattern pattern = Pattern.compile("^[a-zA-Z]{6}[0-9]{2}[abcdehlmprstABCDEHLMPRST]{1}[0-9]{2}([a-zA-Z]{1}[0-9]{3})[a-zA-Z]{1}$", Pattern.CASE_INSENSITIVE);
	    Matcher matcher = pattern.matcher(codiceFiscale);
	    return matcher.find();
	    
	}
 
	public 
	static
	boolean checkFormatIban(String iban) {
		
		Pattern pattern = Pattern.compile("^(?:(?:IT|SM)\\d{2}[A-Z]\\d{10}[0-9A-Z]{12}|CY\\d{2}[A-Z]\\d{23}|NL\\d{2}[A-Z]{4}\\d{10}|LV\\d{2}[A-Z]{4}\\d{13}|(?:BG|BH|GB|IE)\\d{2}[A-Z]{4}\\d{14}|GI\\d{2}[A-Z]{4}\\d{15}|RO\\d{2}[A-Z]{4}\\d{16}|KW\\d{2}[A-Z]{4}\\d{22}|MT\\d{2}[A-Z]{4}\\d{23}|NO\\d{13}|(?:DK|FI|GL|FO)\\d{16}|MK\\d{17}|(?:AT|EE|KZ|LU|XK)\\d{18}|(?:BA|HR|LI|CH|CR)\\d{19}|(?:GE|DE|LT|ME|RS)\\d{20}|IL\\d{21}|(?:AD|CZ|ES|MD|SA)\\d{22}|PT\\d{23}|(?:BE|IS)\\d{24}|(?:FR|MR|MC)\\d{25}|(?:AL|DO|LB|PL)\\d{26}|(?:AZ|HU)\\d{27}|(?:GR|MU)\\d{28})$", Pattern.CASE_INSENSITIVE);
	    Matcher matcher = pattern.matcher(iban);
	    return matcher.find();
	    
	}
 	
	
	public 
	static
	boolean checkFormatValuta(String valuta) {
		return (valuta.length() == 3);
	}
	
	public 
	static
	boolean checkSemanticValuta(String valuta) {
		
		String[] valuteAmmesseArray = {"EUR", "GBP", "USD", "YEN"};
		List<String> valuteAmmesseList = Arrays.asList(valuteAmmesseArray);
		
		return (valuteAmmesseList.contains(valuta));
	}
	
	public 
	static
	boolean checkFormatImporto(String importo) {
		
		boolean parsingResult = true;											//default: testo convertibile in numerico
		
		try {
			Float importoValue = Float.parseFloat(importo);
		}
		catch (Exception ex) {
			//gestione dell'eccezzione generica (si possono avere NumberFormatException e NullPointerException (vedi parseFloat)
			//ma in entrambe i casi abbiamo un errore di conversione per cui non entriamo nel dettaglio dell'eccezzione)

			parsingResult = false;
		}
		
		return parsingResult;
	}
}
