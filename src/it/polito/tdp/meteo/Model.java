package it.polito.tdp.meteo;

import java.util.List;

import it.polito.tdp.meteo.bean.Citta;
import it.polito.tdp.meteo.bean.Rilevamento;
import it.polito.tdp.meteo.bean.SimpleCity;
import it.polito.tdp.meteo.db.MeteoDAO;

public class Model {

	private final static int COST = 100;
	private final static int NUMERO_GIORNI_CITTA_CONSECUTIVI_MIN = 3;
	private final static int NUMERO_GIORNI_CITTA_MAX = 6;
	private final static int NUMERO_GIORNI_TOTALI = 15;

	private List<Rilevamento> rilevamenti;
	private MeteoDAO meteoDAO;
	private List<Citta> citta ;
	private List<SimpleCity> parziale;
	private int livello;
	
	public Model() {
    meteoDAO = new MeteoDAO();
    this.citta=meteoDAO.getAllCitta();
    
	}

	public String getUmiditaMedia(int mese) {
        meteoDAO = new MeteoDAO();
        
        String result ="";
       //Double avgM = meteoDAO.getAvgRilevamentiLocalitaMese(mese, "Milano");
       //Double avgT =meteoDAO.getAvgRilevamentiLocalitaMese(mese, "Torino");
       //Double avgG = meteoDAO.getAvgRilevamentiLocalitaMese(mese, "Genova");
       for(Citta c: this.getCitta())
       {
    	   result +=(" a "+c.getNome()+" umidita media di : "+meteoDAO.getAvgRilevamentiLocalitaMese(mese, c.getNome())+"\n");
       }
		return result;
		
	}

	public String trovaSequenza(int mese) {
      meteoDAO = new MeteoDAO();
      rilevamenti = meteoDAO.(mese);
      
      //recursive(livello,parziale,);
		return rilevamenti.toString();
	}

	private Double punteggioSoluzione(List<SimpleCity> soluzioneCandidata) {

		double score = 0.0;
		return score;
	}

	private boolean controllaParziale(List<SimpleCity> parziale) {

		return true;
	}

	public List<Citta> getCitta() {
		return citta;
	}
	
	

}
