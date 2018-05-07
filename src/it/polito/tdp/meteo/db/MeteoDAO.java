package it.polito.tdp.meteo.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.meteo.bean.Citta;
import it.polito.tdp.meteo.bean.Rilevamento;

public class MeteoDAO {

	
	public List<Citta> getAllCitta() {
		String sql = "SELECT DISTINCT localita FROM situazione ORDER BY localita";

		List<Citta> result = new ArrayList<>();

		try {
			Connection conn = DBConnect.getInstance().getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				result.add(new Citta(res.getString("localita")));
			}

			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}
	
	
	
	
	public List<Rilevamento> getAllRilevamenti() {

		final String sql = "SELECT Localita, Data, Umidita FROM situazione ORDER BY data ASC";

		List<Rilevamento> rilevamenti = new ArrayList<Rilevamento>();

		try {
			Connection conn = DBConnect.getInstance().getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				Rilevamento r = new Rilevamento(rs.getString("Localita"), rs.getDate("Data"), rs.getInt("Umidita"));
				rilevamenti.add(r);
			}

			conn.close();
			return rilevamenti;

		} catch (SQLException e) {

			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public List<Rilevamento> getAllRilevamentiLocalitaMese(int mese, String localita) {
        
		return null;
	}

	public Double getAvgRilevamentiLocalitaMese(int mese, String localita) {
       
		Double avg;

		final String sql = "SELECT AVG(Umidita) AS U \n" + 
				"FROM situazione \n" + 
				"WHERE Localita = ? AND MONTH(Data) = ? ";

		try {
			Connection conn = DBConnect.getInstance().getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			
			st.setString(1,localita);
			st.setInt(2, mese);
			
			ResultSet rs = st.executeQuery();

			rs.next();
            avg=rs.getDouble("U");
			conn.close();

		} catch (SQLException e) {

			e.printStackTrace();
			throw new RuntimeException(e);
		}
	
		return avg ;
	}
	
	public List<Rilevamento> getAllRilevamentiDiUnPeriodo(int mese) {

		final String sql = "SELECT Localita, Data, Umidita FROM situazione WHERE MONTH(Data) = '01' AND DAY(Data)<=15 ORDER BY data ASC";

		List<Rilevamento> rilevamenti = new ArrayList<Rilevamento>();

		try {
			Connection conn = DBConnect.getInstance().getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				Rilevamento r = new Rilevamento(rs.getString("Localita"), rs.getDate("Data"), rs.getInt("Umidita"));
				rilevamenti.add(r);
			}

			conn.close();
			System.out.println(rilevamenti.toString());
			return rilevamenti;

		} catch (SQLException e) {

			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
