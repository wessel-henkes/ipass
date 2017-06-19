package nl.hu.v1wac.firstapp.persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import nl.hu.v1wac.firstapp.model.Wedstrijd;

public class WedstrijdDAO extends BaseDAO{
	public List<Wedstrijd> getAllWedstrijden(int team_id){
		System.out.println("getting wedstrijden from database");
		return selectWedstrijden("SELECT * FROM wedstrijden WHERE team_uit="+team_id+" OR team_thuis="+team_id+" ORDER BY datum ASC;");
	}
	
	private List<Wedstrijd> selectWedstrijden(String query) {
		List<Wedstrijd> results = new ArrayList<Wedstrijd>();
		
		try (Connection con = super.getConnection()) {
			Statement stmt = con.createStatement();
			ResultSet dbResultSet = stmt.executeQuery(query);
			System.out.println("connected to database");
			while (dbResultSet.next()) {
				int r0 = dbResultSet.getInt("id");
				int r1 = dbResultSet.getInt("competitie_id");
				int r2 = dbResultSet.getInt("team_uit");
				int r3 = dbResultSet.getInt("team_thuis");
				String r4 = dbResultSet.getString("datum");
				String r5 = dbResultSet.getString("tijd");	
				Wedstrijd w = new Wedstrijd(r0,r1,r2,r3,r4,r5);
				System.out.println(w);
				results.add(w);
			}
			con.close();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return results;
	}
	
}
