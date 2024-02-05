package org.generation.italy.applicationserver.banca.model;

/**
 * @author Angelo Pasquarelli
 * 
 * La classe rappresenta la normalizzazione delle varie eccezioni che avvengono nel componente model.
 * 
 * Normalizzare con una classe di questo tipo significa dire che qualunque eccezione avvenga viene normalizzata come un eccezione di tipo classe BancaModelException.   
 * Nel costruttore si passa il messaggio di eccezione opportunamente formattato:
 * 		-> nome della classe dove è avvenuta l'eccezione
 * 		-> nome del metodo dov è avvenuta l'eccezione
 * 		-> messagio testuale dell'eccezione da normalizzare
 */

public class BancaModelException extends Exception {

	public BancaModelException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	
}
