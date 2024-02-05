package org.generation.italy.applicationserver.banca.model.dao;

import java.sql.Connection;
/**
 * @author Angelo Pasquarelli
 * 
 * Classe derivata dalla generalizzazione dell'attributo jdbcConnectionToDatabase
 * 
 * Ogni classe DAO eredita ADAO. ADao memorizza il valore della connessione JDBC 
 * per conto della classe DAO che la eredita.
 * 
 * La classe è astratta (non istanziabile con la parola chiave' new') 
 * in quanto perde di significato la sua istanziazione senza istanziare la classe DAO che la eredita.
 *   
 */
public 
abstract
class ADao {
	
	protected Connection jdbcConnectionToDatabase;

	public ADao(Connection jdbcConnectionToDatabase) {
		this.jdbcConnectionToDatabase = jdbcConnectionToDatabase;
	}
	
}
