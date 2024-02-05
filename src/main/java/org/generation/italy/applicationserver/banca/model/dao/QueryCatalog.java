package org.generation.italy.applicationserver.banca.model.dao;

/**
 * @author Angelo Pasquarelli
 * 
 * Classe che raccoglie tutte le query utilizzate nei vari metodi DAO.
 * 
 * Si centralizza il catalogo di modo da avere un controllo globale 
 * su possibili errori sintattici e/o di uso delle query SQL
 * 
 */
public class QueryCatalog {

	
	//localhost:8081/ricerca-cliente?codice-fiscale=PSQNSL00121312412;DROP DATABASE banca;
    public static final String selectFromClienteByPrimaryKey =
              " SELECT codice_fiscale, nominativo, indirizzo	"
            + "   FROM cliente                                  "
            + "  WHERE cliente.codice_fiscale = ? 				";
    
    public static final String selectFromContoByPrimaryKey =
          "SELECT iban, codice_fiscale, valuta, saldo, scoperto, data_ora_intestazione	"
        + "  FROM conto                                     							"
        + " WHERE conto.iban = ?              											";    

    public static final String selectFromMovimentoByPrimaryKey =
              " SELECT id_movimento, importo, tipo_operazione, iban, data_ora_operazione "
            + "   FROM movimento                                     					 "
            + "  WHERE movimento.id_movimento = ?              							 ";    

	public static final String insertCliente = 
			" INSERT INTO cliente (codice_fiscale, nominativo, indirizzo) VALUES (?, ?, ?) ";

	public static final String insertConto =  
			" INSERT INTO conto (iban, codice_fiscale, valuta, scoperto) VALUES (?, ?, ?, ?) ";
	
	public static final String insertMovimento = 
			" INSERT INTO movimento (iban, importo, tipo_operazione) VALUES (?, ?, ?) ";

    public static final String selectFromMovimentoByIban =
            " SELECT id_movimento, importo, tipo_operazione, iban, data_ora_operazione "
          + "   FROM movimento                                     					 "
          + "  WHERE movimento.iban = ?              							 ";    
	
    public static final String deleteFromMovimentoByIban =
            " DELETE 						"
          + "   FROM movimento              "
          + "  WHERE movimento.iban = ?     ";    
    
    public static final String selectFromClienteSenzaConto = 
    		  " SELECT cl.codice_fiscale, cl.nominativo, cl.indirizzo "
    		+ " FROM cliente cl LEFT JOIN conto co ON cl.codice_fiscale = co.codice_fiscale " 
    		+ " WHERE co.iban IS NULL "; 
	
    public static final String selectFromContoByCodiceFiscale = 
          " SELECT iban, codice_fiscale, valuta, saldo, scoperto, data_ora_intestazione	"
  		+ "   FROM conto co  " 
  		+ "  WHERE co.codice_fiscale = ? "; 

    
    public static final String updateSaldoFromContoByIban =
            " UPDATE conto					"
          + "    SET conto.saldo = ?        "
          + "  WHERE conto.iban = ?     	";    
    
}
