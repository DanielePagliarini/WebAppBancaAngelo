package org.generation.italy.applicationserver.banca.control;

public class UserMessages {

	
	//Messaggi per l'attore operatore-banca
	public static final String msgEsitoOkAperturaConto = "E' stato creato il nuovo conto per il cliente!";
	public static final String msgErroreOperazioneAperturaConto = "Impossibile aprire il conto indicato: verificare che non sia gi� esistente!";
	public static final String msgErroreParametriAperturaConto = "Errore nel formato dei dati input!";

	
	//Messaggi per l'attore cliente
	public static final String msgEsitoVersamentoOk = "E' stato inserito il nuovo versamento per il cliente!";
	public static final String msgErroreParametriVersamento = "Errore nel formato dei dati input!";
	public static final String msgErroreOperazioneVersamento = "Impossibile effettuare il versamento sul conto indicato: verificare che lo stato del conto!!";
	
	
	public static final String msgNoContiPerCLiente = "Non sono presenti conti per il cliente indicato!";
	public static final String msgErroreParametriRicercaContiCliente = "Errore nel formato dei dati input!";
	public static final String msgErroreOperazioneRicercaContiCliente = "Impossibile effettuare il versamento sul conto indicato: verificare che lo stato del conto!!";
	
	public static final String msgEsitoPrelievoOk = "E' stato inserito il nuovo Prelievo per il cliente!";
	public static final String msgErroreParametriPrelievo = "Errore nel formato dei dati input!";
	public static final String msgErroreOperazionePrelievo = "Impossibile effettuare il prelievo sul conto indicato: verificare che lo stato del conto!!";
	
}
