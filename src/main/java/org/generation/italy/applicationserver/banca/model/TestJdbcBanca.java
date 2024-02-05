package org.generation.italy.applicationserver.banca.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.generation.italy.applicationserver.banca.model.dao.ClienteDao;
import org.generation.italy.applicationserver.banca.model.dao.ContoDao;
import org.generation.italy.applicationserver.banca.model.dao.MovimentoDao;
import org.generation.italy.applicationserver.banca.model.dao.QueryCatalog;
import org.generation.italy.applicationserver.banca.model.dao.Trigger;
import org.generation.italy.applicationserver.banca.model.entity.Cliente;
import org.generation.italy.applicationserver.banca.model.entity.Conto;
import org.generation.italy.applicationserver.banca.model.entity.Movimento;

public class TestJdbcBanca {

	Connection dbConnection;
	private ClienteDao clienteDao;
	private ContoDao contoDao;
	private MovimentoDao movimentoDao;

	public TestJdbcBanca() throws BancaModelException {

		this.dbConnection = JdbcConnection.readJdbcConnectionInstance("mariadb", "org.mariadb.jdbc.Driver", "localhost",
				"3306", "banca", "root", "").getDbConnection();

		this.clienteDao = new ClienteDao(this.dbConnection);
		this.contoDao = new ContoDao(this.dbConnection);
		this.movimentoDao = new MovimentoDao(this.dbConnection);

		Trigger.clienteDao = this.clienteDao;
		Trigger.movimentoDao = this.movimentoDao;
		Trigger.contoDao = this.contoDao;

	}

	/**
	 * Viene inizializzato il database rimuovendo eventuali dati presenti nelle
	 * tabelle
	 * 
	 * Le query qui presenti sono potenzialmente dannose ed è per questo che non
	 * sono presenti nel query catalog (classe QueryCatalog).
	 * 
	 * Sono qui presenti solo ed esclusivametne ai fini della predisposizone del
	 * test di funzionamento del componente model che approfondiremo nelle prossime
	 * lezioni
	 * 
	 * I comandi SQL-DELETE sono svolti a partire dalla tabella movimento. Infatti,
	 * questa è tabella correlata (join) con la tabella conto sul campo iban. A
	 * seguire, anche conto è tabella correlata con cliente sul campo
	 * codice_fiscale, per cui si elimineranno prima i record della tabella conto e
	 * successivamente quelli di cliente.
	 * 
	 */

	private void clearDatabase() throws BancaModelException {

		try {

			// DELETE FROM movimento
			PreparedStatement preparedStatement1 = this.dbConnection.prepareStatement(" DELETE FROM movimento ");
			preparedStatement1.executeUpdate();

			// DELETE FROM conto
			PreparedStatement preparedStatement2 = this.dbConnection.prepareStatement(" DELETE FROM conto ");
			preparedStatement2.executeUpdate();

			// DELETE FROM cliente
			PreparedStatement preparedStatement3 = this.dbConnection.prepareStatement(" DELETE FROM cliente ");
			preparedStatement3.executeUpdate();

		} catch (SQLException sqlException) {
			throw new BancaModelException("TestJdbcBanca -> clearDatabase -> " + sqlException.getMessage());
		}

		// DELETE FROM conto
		// DELETE FROM cliente
	}

	/**
	 * Metodo usato per popolare il database con valori scelti dal programmatore ai
	 * fini del test. Richiama clearDatabase() per garantirsi che il database sia
	 * senza dati
	 */
	public void popolaDatabase() throws BancaModelException {

		clearDatabase();

		// popolamento tabella cliente
		clienteDao.addCliente(new Cliente("BNCFRL0123456789", "Bianca Fiorelli"));
		clienteDao.addCliente(new Cliente("GTNBGD0123456789", "Gaetano Badalamenti"));
		clienteDao.addCliente(new Cliente("MRRGVN0123456789", "Giovanni Marrone"));
		clienteDao.addCliente(new Cliente("SVCNGB0123456789", "Anselmo Persichetti"));
		clienteDao.addCliente(new Cliente("VLDBCC0123456789", "Vladimiro Bocci"));

		// popolamento tabella conto
		contoDao.addConto(new Conto("DEaa0123456789012345678901234567", "MRRGVN0123456789", "GBP", 1345.0f));
		contoDao.addConto(new Conto("DEbb0123456789012345678901234567", "MRRGVN0123456789", "GBP", 2523.0f));
		contoDao.addConto(new Conto("ESaa0123456789012345678901234567", "VLDBCC0123456789", "EUR", 3955.0f));
		contoDao.addConto(new Conto("EScc0123456789012345678901234567", "GTNBGD0123456789", "EUR", 1150.0f));
		contoDao.addConto(new Conto("ESdd0123456789012345678901234567", "SVCNGB0123456789", "EUR", 2345.0f));
		contoDao.addConto(new Conto("ESee0123456789012345678901234567", "SVCNGB0123456789", "USD", 2150.0f));
		contoDao.addConto(new Conto("FRaa0123456789012345678901234567", "MRRGVN0123456789", "GBP", 1987.0f));
		contoDao.addConto(new Conto("ITbb0123456789012345678901234567", "VLDBCC0123456789", "EUR", 500.0f));
		contoDao.addConto(new Conto("ITdd0123456789012345678901234567", "VLDBCC0123456789", "EUR", 955.0f));
		contoDao.addConto(new Conto("ITee0123456789012345678901234567", "GTNBGD0123456789", "EUR", 1230.0f));
		contoDao.addConto(new Conto("ITff0123456789012345678901234567", "SVCNGB0123456789", "EUR", 1000.0f));

		// popolamento tabella movimento
		// ATTENZIONE=> il trigger è temporaneamente disattivaot per consentitre di verificare il codice
		movimentoDao.addMovimento(new Movimento("ITbb0123456789012345678901234567", 850.0f, "V")); // scatta il trigger per superamento scoperto
		movimentoDao.addMovimento(new Movimento("ESaa0123456789012345678901234567", 450.0f, "V"));
		movimentoDao.addMovimento(new Movimento("ITff0123456789012345678901234567", 555.0f, "V"));
		movimentoDao.addMovimento(new Movimento("ITff0123456789012345678901234567", 555.0f, "V"));
		movimentoDao.addMovimento(new Movimento("ESee0123456789012345678901234567", 1350.0f, "P"));
		movimentoDao.addMovimento(new Movimento("ITff0123456789012345678901234567", 9807.0f, "V"));
		movimentoDao.addMovimento(new Movimento("ESaa0123456789012345678901234567", 1200.0f, "P"));
		movimentoDao.addMovimento(new Movimento("ITbb0123456789012345678901234567", 789.0f, "V"));
		movimentoDao.addMovimento(new Movimento("ESdd0123456789012345678901234567", 660.0f, "P"));
		movimentoDao.addMovimento(new Movimento("ESaa0123456789012345678901234567", 1345.0f, "V"));
		movimentoDao.addMovimento(new Movimento("ESee0123456789012345678901234567", 780.0f, "V"));
		movimentoDao.addMovimento(new Movimento("ESaa0123456789012345678901234567", 65.0f, "V"));
		movimentoDao.addMovimento(new Movimento("ITee0123456789012345678901234567", 1876.0f, "V"));
		movimentoDao.addMovimento(new Movimento("ESaa0123456789012345678901234567", 598.0f, "V"));
		movimentoDao.addMovimento(new Movimento("ESaa0123456789012345678901234567", 1234.0f, "P"));
		movimentoDao.addMovimento(new Movimento("ITff0123456789012345678901234567", 110.0f, "V"));
		movimentoDao.addMovimento(new Movimento("ESee0123456789012345678901234567", 2400.0f, "V"));
		movimentoDao.addMovimento(new Movimento("ESaa0123456789012345678901234567", 450.0f, "V"));
		movimentoDao.addMovimento(new Movimento("ITff0123456789012345678901234567", 555.0f, "V"));
		movimentoDao.addMovimento(new Movimento("ITff0123456789012345678901234567", 555.0f, "V"));
		movimentoDao.addMovimento(new Movimento("ESee0123456789012345678901234567", 650.0f, "P"));
		movimentoDao.addMovimento(new Movimento("ITff0123456789012345678901234567", 9807.0f, "V"));
		movimentoDao.addMovimento(new Movimento("ESaa0123456789012345678901234567", 1200.0f, "P"));
		movimentoDao.addMovimento(new Movimento("ITbb0123456789012345678901234567", 789.0f, "V"));
		movimentoDao.addMovimento(new Movimento("ESdd0123456789012345678901234567", 660.0f, "P"));
		movimentoDao.addMovimento(new Movimento( "ESaa0123456789012345678901234567", 1345.0f, "V"));
		movimentoDao.addMovimento(new Movimento("ESee0123456789012345678901234567", 9800.0f, "V"));
		movimentoDao.addMovimento(new Movimento("ESaa0123456789012345678901234567", 65.0f, "V"));
		movimentoDao.addMovimento(new Movimento("ITee0123456789012345678901234567", 776.0f, "P"));
		movimentoDao.addMovimento(new Movimento("ESaa0123456789012345678901234567", 598.0f, "V"));
		movimentoDao.addMovimento(new Movimento("ESaa0123456789012345678901234567", 1234.0f, "P"));
		movimentoDao.addMovimento(new Movimento("ITff0123456789012345678901234567", 110.0f,"V"));
		movimentoDao.addMovimento(new Movimento("ESee0123456789012345678901234567", 2400.0f,"P"));
		movimentoDao.addMovimento(new Movimento("ESaa0123456789012345678901234567", 225.0f,"V"));
		movimentoDao.addMovimento(new Movimento("DEaa0123456789012345678901234567", 678.0f,"V"));
		movimentoDao.addMovimento(new Movimento("DEaa0123456789012345678901234567", 543.0f,"V"));
		movimentoDao.addMovimento(new Movimento("DEaa0123456789012345678901234567", 963.0f,"V"));
		movimentoDao.addMovimento(new Movimento("DEaa0123456789012345678901234567", 367.0f,"P"));
		movimentoDao.addMovimento(new Movimento("DEaa0123456789012345678901234567", 1543.0f,"P"));
		movimentoDao.addMovimento(new Movimento("DEbb0123456789012345678901234567", 78.0f,"V"));
		movimentoDao.addMovimento(new Movimento("DEbb0123456789012345678901234567", 133.0f,"V"));
		movimentoDao.addMovimento(new Movimento("DEbb0123456789012345678901234567", 6923.0f,"V"));
		movimentoDao.addMovimento(new Movimento("DEbb0123456789012345678901234567", 635.0f,"P"));
		movimentoDao.addMovimento(new Movimento("DEbb0123456789012345678901234567", 4990.0f,"P"));		

	}

	public static void main(String[] args) {
		
		
		try {
			TestJdbcBanca testJdbcBanca =  new TestJdbcBanca();
			testJdbcBanca.popolaDatabase();
			
			System.out.println("Database 'banca' popolato corretamente");
			
			/*--------------------------------------------------------------------------------*/
			//stampare elenco movimenti attuale pre-eliminazione
			List<Movimento> elencoMovimentiPreEliminazione =
					testJdbcBanca.movimentoDao.loadMovimentoByIban("DEbb0123456789012345678901234567");
			System.out.println("elenco movimenti pre-eliminazione");
			System.out.println(elencoMovimentiPreEliminazione.toString());

			//effettuare l'eliminazione
			testJdbcBanca.movimentoDao.removeMovimentoByIban("DEbb0123456789012345678901234567");
			
			//stampare elenco movimenti post-eliminazione
			//stampare elenco movimeti dopo eliminazine conti senza movimento
			List<Movimento> elencoMovimentiPostEliminazione =
					testJdbcBanca.movimentoDao.loadMovimentoByIban("DEbb0123456789012345678901234567");
			
			System.out.println("elenco movimenti post-eliminazione");
			System.out.println(elencoMovimentiPostEliminazione.toString());

			
			/*--------------------------------------------------------------------------------*/
			/*
			 * a) stampare l'elenco dei clienti senza conto 
			 */
			List<Cliente> clientiSenzaConto=testJdbcBanca.clienteDao.loadClienteSenzaConto();
			System.out.println("Elenco clienti senza conto: ");
			System.out.println(clientiSenzaConto);
			
			/*
			 * b) aggiungere un conto per uno dei clienti senza conto
			 * c) aggiungere un ulteriore conto per il cliente di cui al punto b)
			 * 	  => addConto() => metodo implementato precedentemente => ContoDao	
			 */
	
			Cliente cliente=null;
			if (clientiSenzaConto.size() == 0) {
				System.out.println("Non ci sono Clienti senza conti!!!");
			}
			else {		//quando abbiamo almeno un cliente senza conto
				cliente = clientiSenzaConto.get(0);
				Conto conto = new Conto("ITcc0213456789012345678901234567", cliente.getCodiceFiscale(), "EUR", 876.0f); 
				testJdbcBanca.contoDao.addConto(conto);
				Conto conto1 = new Conto("ITzz2213456489012343678401234567", cliente.getCodiceFiscale(), "EUR", 92347.0f);
				testJdbcBanca.contoDao.addConto(conto1);
			}
			
			/*	
			 * d) stampare i conti del cliente per cui si sono aggiunto i due conti
			 * 
			 * => loadContiCliente(), metodo loadContiByCodiceFiscaleCliente()/loadContiByNominativoCliente()=> ContoDao		
			*/
			List<Conto> elencoContiCliente = testJdbcBanca.contoDao.loadContoByCodiceFiscale(cliente.getCodiceFiscale());
			
			System.out.println("\nElenco conti aggiunti al cliente: ");
			System.out.println(elencoContiCliente);
			
			
		} catch (BancaModelException bancaModelException) {
			System.out.println(bancaModelException.getMessage());
		} 
	}

	/*
	 * GETTERS/SETTERS (INCAPSULAMENTO)
	 * 
	 * 
	 */
	
	public ClienteDao getClienteDao() {
		return clienteDao;
	}

	public ContoDao getContoDao() {
		return contoDao;
	}

	public MovimentoDao getMovimentoDao() {
		return movimentoDao;
	}
	
}
