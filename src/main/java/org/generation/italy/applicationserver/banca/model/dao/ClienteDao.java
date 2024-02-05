package org.generation.italy.applicationserver.banca.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.generation.italy.applicationserver.banca.model.BancaModelException;
import org.generation.italy.applicationserver.banca.model.entity.Cliente;
import org.generation.italy.applicationserver.banca.model.entity.Movimento;


/**
 * 
 * @author Angelo Pasquarelli
 * 
 * Classe per le operazioni CRUD (CREATE READ UPDATE DELETE) su tabella cliente
 */

public class ClienteDao extends ADao {

	public ClienteDao(Connection jdbcConnectionToDatabase) {
		super(jdbcConnectionToDatabase);
		// TODO Auto-generated constructor stub
	}

	
    /***********************/
    // METODI DI LETTURA DATI
    /***********************/
	
	/**
	 * Esecuzione di una query di SELECT con output i campi del record della tabella cliente
	 * 
	 * NOTA: il metodo private generalizza la ncessità di caricare l'elenco ogni volta che si ha una SQL-SELECT su tabella cliente 
	 *  	  
	 * @param preparedstatement query SQL che ritorna dei record cliente
	 * @return elenco dei record cliente trovati
	 * 
	 * @throws BancaModelException : eccezione normalizzata
	 */
	
	private 
    List<Cliente> loadClientiByQuery(PreparedStatement preparedstatement) throws BancaModelException {

        List<Cliente> elencoClienti = new ArrayList<Cliente>();

        try {

            ResultSet rsSelect
                    = preparedstatement.executeQuery();

            while (rsSelect.next()) {

                String codFiscale = rsSelect.getString("codice_fiscale");
                if (rsSelect.wasNull()) {
                    codFiscale = "";
                }

                String nominativo
                        = rsSelect.getString("nominativo");
                if (rsSelect.wasNull()) {
                    nominativo = "";
                }

                String indirizzo = rsSelect.getString("indirizzo");
		        if (rsSelect.wasNull()) {
		        	indirizzo = "";
		        }
                Cliente cliente = new Cliente(codFiscale, nominativo, indirizzo);

//                String cap = rsSelect.getString("cap");
//		        if (rsSelect.wasNull()) {
//		        	cap = "";
//		        }
		        
//                Cliente cliente = new Cliente(codFiscale, nominativo, indirizzo); //, cap);
                
                
                elencoClienti.add(cliente);

            }

        } catch (SQLException sqlException) {

            throw new BancaModelException("ClienteDao -> loadCliente -> " + sqlException.getMessage());	
            																	// normalizzazione dell'eccezione SQLException

        }
        
        return elencoClienti;

    }	
	

	public 
	Cliente loadClienteByPrimaryKey(String codiceFiscale) 
    											throws BancaModelException {
        
        Cliente cliente = null;
        
        try {
        	
            List<Cliente> elencoClienti = new ArrayList<Cliente>();
                
            PreparedStatement preparedStatement = this.jdbcConnectionToDatabase.prepareStatement(QueryCatalog.selectFromClienteByPrimaryKey);

            preparedStatement.setString(1, codiceFiscale);
                      
            elencoClienti = loadClientiByQuery(preparedStatement);                                        

            if (elencoClienti.size() == 1) {
                cliente = elencoClienti.get(0);
            }
            
        } catch (SQLException sqlException) {                                  
           
            throw new BancaModelException("ClienteDao -> loadClienteByPrimaryKey -> " + sqlException.getMessage());
        }

        return cliente;

    }	
	
	public List<Cliente> loadClienteSenzaConto() 
								throws BancaModelException {

		List<Cliente> elencoClientiSenzaConto = new ArrayList<Cliente>();

		try {

			PreparedStatement preparedStatement = this.jdbcConnectionToDatabase
					.prepareStatement(QueryCatalog.selectFromClienteSenzaConto);

			elencoClientiSenzaConto = loadClientiByQuery(preparedStatement);

		} catch (SQLException sqlException) {

			throw new BancaModelException("ClienteDao -> loadClienteSenzaConto -> " + sqlException.getMessage());
		}

		return elencoClientiSenzaConto;

	}
	
//	public loadClienteByNominativo(String nominativo) {
//		
//		//SELECT * FROM cliente WHERE nominativo LIKE '%ANGELO%'
//        PreparedStatement preparedStatement = this.jdbcConnectionToDatabase.prepareStatement(QueryCatalog.selectFromClienteByNominativo);
//        
//        elencoClienti = loadClientiByQuery(preparedStatement);
//	}

	
    /***********************/
    // METODI DI SCRITTURA DATI
    /***********************/
	
    public 
    void addCliente(Cliente cliente) 
    						throws BancaModelException {
        
        try {           
            
            PreparedStatement preparedStatement = this.jdbcConnectionToDatabase.prepareStatement(QueryCatalog.insertCliente);
            
            preparedStatement.setString(1, cliente.getCodiceFiscale());
            preparedStatement.setString(2, cliente.getNominativo());            
            
            if (cliente.getIndirizzo() == null) {
            	preparedStatement.setNull(3, java.sql.Types.VARCHAR);
        	}
            else {
                preparedStatement.setString(3, cliente.getIndirizzo());            
            }
            
            preparedStatement.executeUpdate();
    
        } catch (SQLException sqlException) {
        	
            throw new BancaModelException("ClienteDao -> addCliente -> " + sqlException.getMessage());
            
        }
        
    }
	
}
