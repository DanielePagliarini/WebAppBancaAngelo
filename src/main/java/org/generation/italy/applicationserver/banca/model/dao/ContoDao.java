package org.generation.italy.applicationserver.banca.model.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.generation.italy.applicationserver.banca.model.BancaModelException;
import org.generation.italy.applicationserver.banca.model.entity.Cliente;
import org.generation.italy.applicationserver.banca.model.entity.Conto;

/**
 * @author Angelo Pasquarelli
 * 
 * Classe per le operazioni CRUD (CREATE READ UPDATE DELETE) su tabella conto
 */

public class ContoDao extends ADao {

	public ContoDao(Connection dbConnectionToDatabase) {
		super(dbConnectionToDatabase);
		// TODO Auto-generated constructor stub
	}

    /***********************/
    // METODI DI LETTURA DATI
    /***********************/
	/**
	 * Esecuzione di una query di SELECT con output i campi del record della tabella Conto
	 * 
	 * NOTA: il metodo private generalizza la ncessità di caricare l'elenco ogni volta che si ha una SQL-SELECT su tabella Conto 
	 *  	  
	 * @param preparedstatement query SQL che ritorna dei record Conto
	 * @return elenco dei record Conto trovati
	 * 
	 * @throws BancaModelException : eccezione normalizzata
	 */
	
	private 
    List<Conto> loadContiByQuery(PreparedStatement preparedstatement) throws BancaModelException {

        List<Conto> elencoConti = new ArrayList<Conto>();

        try {

            ResultSet rsSelect
                    = preparedstatement.executeQuery();

            while (rsSelect.next()) {

                String iban = rsSelect.getString("iban");
                if (rsSelect.wasNull()) { iban = ""; }
                
                String codiceFiscale = rsSelect.getString("codice_fiscale");
                if (rsSelect.wasNull()) { codiceFiscale = ""; }

                String valuta = rsSelect.getString("valuta");
                if (rsSelect.wasNull()) { valuta = ""; }

                LocalDateTime dataOraIntestazione = rsSelect.getTimestamp("data_ora_intestazione").toLocalDateTime();
		        if (rsSelect.wasNull()) {dataOraIntestazione = LocalDateTime.of(LocalDate.of(0,0,0), LocalTime.of(0, 0, 0));}
		        
		        Float saldo = rsSelect.getFloat("saldo");
		        if (rsSelect.wasNull()) {saldo = 0.0f;}

		        Float scoperto = rsSelect.getFloat("scoperto");
		        if (rsSelect.wasNull()) {scoperto = 0.0f;}
		        
		        
                Conto conto = new Conto(iban, codiceFiscale, valuta, saldo, scoperto, dataOraIntestazione);

                elencoConti.add(conto);

            }

        } catch (SQLException sqlException) {

            throw new BancaModelException("ContoDao -> loadContiByQuery -> " + sqlException.getMessage());	
            																	// normalizzazione dell'eccezione SQLException

        }
        
        return elencoConti;

    }	


	public Conto loadContoByPrimaryKey(String iban) 
												throws BancaModelException {
        
        Conto Conto = null;
        
        try {
        	
            List<Conto> elencoConti = new ArrayList<Conto>();
                
            PreparedStatement preparedStatement = this.jdbcConnectionToDatabase.prepareStatement(QueryCatalog.selectFromContoByPrimaryKey);

            preparedStatement.setString(1, iban);
                      
            elencoConti = loadContiByQuery(preparedStatement);                                        

            if (elencoConti.size() == 1) {
                Conto = elencoConti.get(0);
            }
            
        } catch (SQLException sqlException) {                                  
           
            throw new BancaModelException("ContoDao -> loadContoByPrimaryKey -> " + sqlException.getMessage());
        }

        return Conto;

    }	
	
	public List<Conto> loadContoByCodiceFiscale(String codiceFiscale) throws BancaModelException {

		List<Conto> elencoContiByCodiceFiscale = new ArrayList<Conto>();

		try {

			PreparedStatement preparedStatement = this.jdbcConnectionToDatabase
					.prepareStatement(QueryCatalog.selectFromContoByCodiceFiscale);

            preparedStatement.setString(1, codiceFiscale);

			elencoContiByCodiceFiscale = loadContiByQuery(preparedStatement);

		} catch (SQLException sqlException) {

			throw new BancaModelException("ContoDao -> loadContoByCodiceFiscale -> " + sqlException.getMessage());
		}

		return elencoContiByCodiceFiscale;

	}
	
    /***********************/
    // METODI DI SCRITTURA DATI
    /***********************/

    public 
    void addConto(Conto conto) 
    						throws BancaModelException {
        
        try {           
            
            PreparedStatement preparedStatement = this.jdbcConnectionToDatabase.prepareStatement(QueryCatalog.insertConto);
            
            preparedStatement.setString(1, conto.getIban());
            preparedStatement.setString(2, conto.getCodiceFiscale());            
            preparedStatement.setString(3, conto.getValuta());            
            preparedStatement.setFloat(4, conto.getScoperto());            
            
            preparedStatement.executeUpdate();
    
        }catch (SQLException sqlException) {
        	
            throw new BancaModelException("ContoDao -> addConto -> " + sqlException.getMessage());
            
        }
        
    }
	
	
}
