package nl.hu.v1wac.firstapp.persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import nl.hu.v1wac.firstapp.model.Opstelling;

public class OpstellingDAO extends BaseDAO {
//	public List<Opstelling> getOpstellingByWedstrijdByTeam(int wedstrijd_id, int team_id){
//		return selectOpstellings("SELECT * FROM Opstelling WHERE wedstrijd_id="+wedstrijd_id+"AND team_id="+team_id+";");
//	}
//	private List<Opstelling> selectOpstellings(String query) {
//	List<Opstelling> results = new ArrayList<Opstelling>();
//	
//	try (Connection con = super.getConnection()) {
//		Statement stmt = con.createStatement();
//		ResultSet dbResultSet = stmt.executeQuery(query);
//		
//		while (dbResultSet.next()) {
//			String r0= dbResultSet.getString("veldpositie");
//			int r1 = dbResultSet.getInt("slagvolgorde");
//			int r2 = dbResultSet.getInt("speler_id");
//			int r3 = dbResultSet.getInt("team_id");
//			int r4 = dbResultSet.getInt("wedstrijd_id");
//			int r5 = dbResultSet.getInt("id");
//
//			results.add(new Opstelling(r0,r1,r2,r3,r4,r5));
//		}
//		con.close();
//	} catch (SQLException sqle) {
//		//sqle.printStackT
//	}
//	return results;	
//}
//
public List<Opstelling> getAanwezigByWedstrijdByTeam(int wedstrijd_id, int team_id){
		
		String query = "SELECT speler_id, team_id FROM Opstelling WHERE wedstrijd_id="+wedstrijd_id+"AND team_id="+team_id+";";
		System.out.println(query);
		List<Opstelling> results = new ArrayList<Opstelling>();
		
		try (Connection con = super.getConnection()) {
			Statement stmt = con.createStatement();
			ResultSet dbResultSet = stmt.executeQuery(query);
			
			while (dbResultSet.next()) {
				
				int r2 = dbResultSet.getInt("speler_id");


				results.add(new Opstelling(r2,team_id, wedstrijd_id));
			}
			con.close();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return results;	
	
	}
	
	public List<Opstelling> getSlagvolgordeByWedstrijdByTeam(int wedstrijd_id, int team_id){
		
		String query = "SELECT slagvolgorde, speler_id, team_id FROM Opstelling WHERE wedstrijd_id="+wedstrijd_id+"AND team_id="+team_id+";";
		
		List<Opstelling> results = new ArrayList<Opstelling>();
		
		try (Connection con = super.getConnection()) {
			Statement stmt = con.createStatement();
			ResultSet dbResultSet = stmt.executeQuery(query);
			
			while (dbResultSet.next()) {
				int r1 = dbResultSet.getInt("slagvolgorde");
				int r2 = dbResultSet.getInt("speler_id");


				results.add(new Opstelling(r1,r2,team_id,wedstrijd_id));
			}
			con.close();
		} catch (SQLException sqle) {
			//sqle.printStackT
		}
		return results;	
	
	}
	
	public List<Opstelling> getVeldpositieByWedstrijdByTeam(int wedstrijd_id, int team_id){
		
		String query = "SELECT veldpositie, speler_id, team_id FROM Opstelling WHERE wedstrijd_id="+wedstrijd_id+"AND team_id="+team_id+";";
		
		List<Opstelling> results = new ArrayList<Opstelling>();
		
		try (Connection con = super.getConnection()) {
			Statement stmt = con.createStatement();
			ResultSet dbResultSet = stmt.executeQuery(query);
			
			while (dbResultSet.next()) {
				String r1 = dbResultSet.getString("veldpositie");
				int r2 = dbResultSet.getInt("speler_id");
				results.add(new Opstelling(r1,r2,team_id,wedstrijd_id));
			}
			con.close();
		} catch (SQLException sqle) {
			//sqle.printStackT
		}
		return results;	
	
	}
	

	
	public boolean delete(int wedstrijd_id, int team_id){
		String query = "DELETE FROM opstelling WHERE wedstrijd_id="+wedstrijd_id+"AND team_id="+team_id+";";
		boolean out = false;
		System.out.println(query);
		try (Connection con = super.getConnection()) {
			Statement stmt = con.createStatement();
			stmt.executeUpdate(query);
			out = true;
			con.close();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		System.out.println(out);
		
		return out;
		
	}
	
	public boolean insertAanwezigen(Opstelling o){
		String query = "INSERT INTO opstelling (speler_id, team_id, wedstrijd_id)  "
				+ " VALUES ('"+o.getSpeler_id()+"','"+o.getTeam_id()+"',"+o.getWedstrijd_id()+"');";
		System.out.println(query);
		boolean out = false;
		try (Connection con = super.getConnection()) {
			Statement stmt = con.createStatement();
			stmt.executeUpdate(query);
			System.out.println(query);
			con.close();
			out = true;
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return out;
		//return findOpstellingByCode(o.getCode());		
	}
	
	
	public boolean setSlagvolgorde(Opstelling o){
		String query = "UPDATE opstelling SET slagvolgorde="+o.getSlagvolgorde()+" WHERE  wedstrijd_id="+o.getWedstrijd_id()+"AND team_id="+o.getTeam_id()+"AND speler_id="+o.getSpeler_id()+";";
		System.out.println(query);
		boolean out = false;
		try (Connection con = super.getConnection()) {
			Statement stmt = con.createStatement();
			stmt.executeUpdate(query);
			con.close();
			out = true;
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return out;
	}
	
	public boolean setVeldpositie(Opstelling o){
		String query = "UPDATE opstelling SET veldpositie="+o.getVeldpositie()+" WHERE  wedstrijd_id="+o.getWedstrijd_id()+"AND team_id="+o.getTeam_id()+"AND speler_id="+o.getSpeler_id()+";";
		System.out.println(query);
		boolean out = false;
		try (Connection con = super.getConnection()) {
			Statement stmt = con.createStatement();
			stmt.executeUpdate(query);
			con.close();
			out = true;
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return out;
	}
	
	public boolean deleteOpstellingByWedstrijd(int wedstrijd_id){
		String query = "DELETE FROM opstelling WHERE wedstrijd_id="+wedstrijd_id+";";
		boolean out = false;
		System.out.println(query);
		try (Connection con = super.getConnection()) {
			Statement stmt = con.createStatement();
			stmt.executeUpdate(query);
			out = true;
			con.close();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		System.out.println(out);
		
		return out;
		
	}
	
}
