package org.generation.italy.applicationserver.banca.model.entity;

import java.time.LocalDateTime;
import java.util.Objects;

public class Conto {
	
    /***********************/
    // DEFINIZIONE ATTRIBUTI
    /***********************/
	
	private String iban;
	private String valuta;
	private Float scoperto;
	private String codiceFiscale;
	private LocalDateTime dataOraIntestazione;
	private Float saldo;
	
    /***************/
    // COSTRUTTORI
    /***************/

    //NOTA: Polimorfismo sul costruttore: poichè il campo data_ora_intestazione è valorizzato dal DBMS (vedi valore default: current_timstamp())
    //      si può ometttere nell'istanziazione della classe per scrivere il record relativo mentre è necessario indicarlo nel costruttore per leggerlo (query di SELECT) 
	
	public Conto(String iban, String codiceFiscale, String valuta, Float saldo, Float scoperto, LocalDateTime dataOraIntestazione) {
		super();
		this.iban = iban;
		this.valuta = valuta;
		this.scoperto = scoperto;
		this.codiceFiscale = codiceFiscale;
		this.dataOraIntestazione = dataOraIntestazione;
		this.saldo = saldo;
	}

	public Conto(String iban, String codiceFiscale, String valuta, Float scoperto) {
		super();
		this.iban = iban;
		this.valuta = valuta;
		this.scoperto = scoperto;
		this.codiceFiscale = codiceFiscale;
		this.dataOraIntestazione = null;
		this.saldo = 0.0f;
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

	
	public String getIban() {
		return iban;
	}


	public void setValuta(String valuta) {
		this.valuta = valuta;
	}
	public String getValuta() {
		return valuta;
	}


	public void setScoperto(Float scoperto) {
		this.scoperto = scoperto;
	}
	public Float getScoperto() {
		return scoperto;
	}


	public String getCodiceFiscale() {
		return codiceFiscale;
	}


	public LocalDateTime getDataOraIntestazione() {
		return dataOraIntestazione;
	}

	
	public Float getSaldo() {
		return saldo;
	}

	/***********************************************************************/
    // METODI DERIVATI DALLA CLASSE OBJECT: toString(), equals(), hashCode()
    /***********************************************************************/  	
	
	@Override
	public int hashCode() {
		return Objects.hash(codiceFiscale, dataOraIntestazione, iban, saldo, scoperto, valuta);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Conto other = (Conto) obj;
		return Objects.equals(codiceFiscale, other.codiceFiscale)
				&& Objects.equals(dataOraIntestazione, other.dataOraIntestazione) && Objects.equals(iban, other.iban)
				&& Objects.equals(saldo, other.saldo) && Objects.equals(scoperto, other.scoperto)
				&& Objects.equals(valuta, other.valuta);
	}

	@Override
	public String toString() {
		return "Conto [iban=" + iban + ", valuta=" + valuta + ", scoperto=" + scoperto + ", codiceFiscale="
				+ codiceFiscale + ", dataOraIntestazione=" + dataOraIntestazione + ", saldo=" + saldo + "]";
	}
	
	



	
	
	
}
