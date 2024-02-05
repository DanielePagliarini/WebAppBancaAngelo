package org.generation.italy.applicationserver.banca.model.entity;

import java.time.LocalDateTime;
import java.util.Objects;

public class Movimento {

    /***********************/
    // DEFINIZIONE ATTRIBUTI
    /***********************/
	
	private Long idMovimento;
	private LocalDateTime dataOraOperazione;
	private Float importo;
	private String tipoOperazione;
	private String iban;
	
    /***************/
    // COSTRUTTORI
    /***************/

    //NOTA: Polimorfismo sul costruttore: poichè i campi id_movimento e data_ora_operazione sono valorizzati dal DBMS (vedi valore default: current_timstamp())
    //      si possono ometttere nell'istanziazione della classe per scrivere il record (query di INSERT) relativo mentre è necessario indicarli nel costruttore per leggerli (query di SELECT) 
	
	public Movimento(Long idMovimento, String iban, Float importo, String tipoOperazione, LocalDateTime dataOraOperazione) {
		super();
		this.idMovimento = idMovimento;
		this.dataOraOperazione = dataOraOperazione;
		this.importo = importo;
		this.tipoOperazione = tipoOperazione;
		this.iban = iban;
	}

	public Movimento(String iban, Float importo, String tipoOperazione) {
		super();
		this.idMovimento = idMovimento;
		this.iban = iban;
		this.dataOraOperazione = dataOraOperazione;
		this.importo = importo;
		this.tipoOperazione = tipoOperazione;
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

	
	public Long getIdMovimento() {
		return idMovimento;
	}

	public LocalDateTime getDataOraOperazione() {
		return dataOraOperazione;
	}

	public Float getImporto() {
		return importo;
	}

	public String getTipoOperazione() {
		return tipoOperazione;
	}

	public String getIban() {
		return iban;
	}

	
	/***********************************************************************/
    // METODI DERIVATI DALLA CLASSE OBJECT: toString(), equals(), hashCode()
    /***********************************************************************/  

	@Override
	public int hashCode() {
		return Objects.hash(dataOraOperazione, iban, idMovimento, importo, tipoOperazione);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Movimento other = (Movimento) obj;
		return Objects.equals(dataOraOperazione, other.dataOraOperazione) && Objects.equals(iban, other.iban)
				&& Objects.equals(idMovimento, other.idMovimento) && Objects.equals(importo, other.importo)
				&& Objects.equals(tipoOperazione, other.tipoOperazione);
	}

	@Override
	public String toString() {
		return "Movimento [idMovimento=" + idMovimento + ", dataOraOperazione=" + dataOraOperazione + ", importo="
				+ importo + ", tipoOperazione=" + tipoOperazione + ", iban=" + iban + "]";
	}
	
	
}
