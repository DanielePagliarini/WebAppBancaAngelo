
package org.generation.italy.applicationserver.banca.model.dao;



import java.util.List;

import org.generation.italy.applicationserver.banca.model.BancaModelCostants;
import org.generation.italy.applicationserver.banca.model.BancaModelException;
import org.generation.italy.applicationserver.banca.model.entity.Conto;
import org.generation.italy.applicationserver.banca.model.entity.Movimento;


/**
 * 
 * Classe di verifica dei vincoli applicativi sulle operazioni di scrittura dei dati (TRIGGERS) nelle varie tabelle
 * 
 */
public class Trigger {
	
	public static ClienteDao clienteDao;
	public static ContoDao contoDao;
	public static MovimentoDao movimentoDao;
	
    /**
     * Metodo trigger per la verifica dei vincoli:
     * 1) un movimento di prelievo pu� essere effettuato su un conto solo se, sommato al saldo corrente, non supera lo scoperto
   	 * 2) Un movimento pu� essere inserito solo se non sono stati gi� effettuati pi� di x movimenti sul conto 
     * 
     * @param movimento oggetto contenente i dettagli del movimento 
     * @throws BancaModelException 
     */
    public static void checkBeforeInsertMovimento(Movimento movimento) 
    														throws BancaModelException {
        
        //VERIFICA DEL VINCOLO: 
    	//1) un movimento di prelievo pu� essere effettuato su un conto solo se il suo importo, sottratto al saldo corrente, non supera lo scoperto
    	if (movimento.getTipoOperazione().toUpperCase().equals("P")) {
    		
        	//legge i dati del conto per ottenerne il saldo corrente
        	Conto conto = contoDao.loadContoByPrimaryKey(movimento.getIban());

        	if (conto.getSaldo() - movimento.getImporto() < (0 - conto.getScoperto())) {
        		throw new BancaModelException ("Trigger -> checkBeforeInsertMovimento -> un movimento di prelievo pu� essere effettuato su un conto solo se, sommato al saldo corrente, non supera lo scoperto!");
        	}
    	}
    	
    	
        //VERIFICA DEL VINCOLO: 
    	// 2) Un movimento pu� essere inserito solo se non sono stati gi� effettuati pi� di maxMovimentiSuConto (movimenti sul conto) 
//    	List<Movimento> elencoMovimenti = movimentoDao.loadMovimentoByIban(movimento.getIban());
//    	
//    	if (elencoMovimenti.size() >= BancaModelCostants.maxMovimentiSuConto)
//    	{
//    		throw new BancaModelException ("Trigger -> checkBeforeInsertMovimento -> sono qi� stati effettuati due monimenti sul conto!");
//    	}
    	
    } 
    
    
    
}
