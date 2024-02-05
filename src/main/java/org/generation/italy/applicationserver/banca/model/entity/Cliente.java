package org.generation.italy.applicationserver.banca.model.entity;

import java.util.Objects;


/**
 * Classe entity-bean Cliente che effettua il mapping del record della tabella Cliente
 * 
 * @author Angelo Pasquarelli
 */

public class Cliente {
    
    /***********************/
    // DEFINIZIONE ATTRIBUTI
    /***********************/
    private String codiceFiscale;
    private String nominativo;
    private String indirizzo;
    

    /***************/
    // COSTRUTTORI
    /***************/
    
    //NOTA: Polimorfismo sul costruttore poichè il campo indirizzo può essere NULL
    //      in generale tutti i campi NULL generano il polimorfismo sul costruttore 
    //		come di seguito indicato.
    
    public Cliente(String codiceFiscale, String nominativo, String indirizzo) {
        this.codiceFiscale = codiceFiscale;
        this.nominativo = nominativo;
        this.indirizzo = indirizzo;
    }

    public Cliente(String codiceFiscale, String nominativo) {
		this.codiceFiscale = codiceFiscale;
		this.nominativo = nominativo;
		this.indirizzo = null;
	}


	/********************/
    // GETTERS & SETTERS
    /********************/
    
    //NOTA: la scelta sull'uso o meno dei metodi GET per un attributo 
    //      è basata sul seguente principio: tutti gli attributi 
    //		che hanno necessità di lettura e/o aggiornametno dal chiamante 
    //		hanno il relativo metodo GET, salvo quelli che per ragioni di sicurezza 
	//		non debbono essere visibili al chiamante.
    

    //NOTA: la scelta sull'uso o meno dei metodi SET 
    //      è basata sul seguente principio: sono con metodo SET solo quegli attributi 
    //		che, una volta valorizzati nel costruttore, hanno necessità di un aggiornamento nel corso del tempo

    
    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    public String getNominativo() {
        return nominativo;
    }

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}
	public String getIndirizzo() {
		return indirizzo;
	}



    
	/***********************************************************************/
    // METODI DERIVATI DALLA CLASSE OBJECT: toString(), equals(), hashCode()
    /***********************************************************************/  
    
 	@Override
	public int hashCode() {
		return Objects.hash(codiceFiscale, indirizzo, nominativo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(codiceFiscale, other.codiceFiscale) && Objects.equals(indirizzo, other.indirizzo)
				&& Objects.equals(nominativo, other.nominativo);
	}

	@Override
	public String toString() {
		return "Cliente [codiceFiscale=" + codiceFiscale + ", nominativo=" + nominativo + ", indirizzo=" + indirizzo
				+ "]";
	}

   
}
