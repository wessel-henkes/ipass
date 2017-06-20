package nl.hu.v1wac.firstapp.persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import nl.hu.v1wac.firstapp.model.Team;

public class TeamDAO extends BaseDAO {
	public String getTeamName(int team_id){
		return selectTeamName("SELECT naam FROM teams WHERE id="+team_id+";");
	}
	
	private String selectTeamName(String query) {
		String r0 = null ;
		
		try (Connection con = super.getConnection()) {
			Statement stmt = con.createStatement();
			ResultSet dbResultSet = stmt.executeQuery(query);
			while (dbResultSet.next()) {
				r0 = dbResultSet.getString("naam");
				break;
			}
			
			con.close();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return r0;	
	}
	
	@SuppressWarnings("unused")
	private List<Team> selectTeams(String query) {
		List<Team> results = new ArrayList<Team>();
		
		try (Connection con = super.getConnection()) {
			Statement stmt = con.createStatement();
			ResultSet dbResultSet = stmt.executeQuery(query);
			
			while (dbResultSet.next()) {
				String r0 = dbResultSet.getString("naam");
				int r1 = dbResultSet.getInt("id");
				int r2 = dbResultSet.getInt("competitie_id");


				results.add(new Team(r0,r1,r2));
			}
			con.close();
		} catch (SQLException sqle) {
			//sqle.printStackT
		}
		return results;	
	}
}