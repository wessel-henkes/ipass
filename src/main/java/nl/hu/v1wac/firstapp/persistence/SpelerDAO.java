package nl.hu.v1wac.firstapp.persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import nl.hu.v1wac.firstapp.model.Speler;

public class SpelerDAO extends BaseDAO {
	public List<Speler> getSpelersByTeam(int team_id){
		return selectSpelers("SELECT  CONCAT(CONCAT(voornaam,' '),achternaam) AS naam, team_id, id FROM spelers WHERE team_id="+team_id+";");
	}
	
	private List<Speler> selectSpelers(String query) {
		List<Speler> results = new ArrayList<Speler>();
		
		try (Connection con = super.getConnection()) {
			Statement stmt = con.createStatement();
			ResultSet dbResultSet = stmt.executeQuery(query);
			
			while (dbResultSet.next()) {
				int r0 = dbResultSet.getInt("id");
				String r1 = dbResultSet.getString("naam");
//				String r2 = dbResultSet.getString("achternaam");
				int r3 = dbResultSet.getInt("team_id");

				results.add(new Speler(r0,r1,r3));
			}
			con.close();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return results;	
	}

	public String getSpelerNaam(int speler_id) {
		String result = null;
		String query ="SELECT CONCAT(CONCAT(voornaam,' '),achternaam) AS naam FROM spelers WHERE id="+speler_id+";";
		try (Connection con = super.getConnection()) {
			Statement stmt = con.createStatement();
			ResultSet dbResultSet = stmt.executeQuery(query);
			while (dbResultSet.next()) {
			String r1 = dbResultSet.getString("naam");
			result = r1;
			break;
			}
			con.close();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return result;	
	}

}




































