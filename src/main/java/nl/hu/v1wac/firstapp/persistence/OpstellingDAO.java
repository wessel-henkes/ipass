package nl.hu.v1wac.firstapp.persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import nl.hu.v1wac.firstapp.model.Opstelling;

public class OpstellingDAO extends BaseDAO {
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
		
		String query = "SELECT slagvolgorde, speler_id, team_id FROM Opstelling WHERE wedstrijd_id="+wedstrijd_id+"AND team_id="+team_id+" ORDER BY slagvolgorde ASC;";
		
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
		
		String query = "SELECT veldpositie, speler_id, team_id FROM Opstelling WHERE wedstrijd_id="+wedstrijd_id+"AND team_id="+team_id+" ORDER BY veldpositie ASC;";
		
		List<Opstelling> results = new ArrayList<Opstelling>();
		
		try (Connection con = super.getConnection()) {
			Statement stmt = con.createStatement();
			ResultSet dbResultSet = stmt.executeQuery(query);
			
			while (dbResultSet.next()) {
				int r1 = dbResultSet.getInt("veldpositie");
				int r2 = dbResultSet.getInt("speler_id");
				results.add(new Opstelling(r1,r2,team_id,wedstrijd_id));
			}
			con.close();
		} catch (SQLException sqle) {
			//sqle.printStackT
		}
		return results;	
	
	}
	
	public boolean insertAanwezigen(int wedstrijd_id,List<Integer> speler_id_arr,int team_id){
		
		
		boolean out = false;
		System.out.println(speler_id_arr);
		try (Connection con = super.getConnection()) {
			Statement stmt = con.createStatement();
			 String id = "";
			 int a = 1;
			 for (int i : speler_id_arr){
				 id = id+ String.valueOf(i);
				 
				 if (speler_id_arr.size()> a){
					 id += ",";
					 a += 1;	 
				 }
			 }
			String deletequery = "DELETE count FROM opstelling WHERE wedstrijd_id="+wedstrijd_id+" AND team_id="+team_id+" AND speler_id NOT IN ("+id+") ;";
			System.out.println(deletequery);
			int dbResultSetDelete = stmt.executeUpdate(deletequery);
			System.out.println("rows deleted="+dbResultSetDelete);
			
			for (int speler_id : speler_id_arr){
				String bestaat ="SELECT id FROM opstelling WHERE wedstrijd_id="+wedstrijd_id+" AND team_id="+team_id+" AND speler_id="+speler_id+";";
				ResultSet dbResultSet = stmt.executeQuery(bestaat);
				if (dbResultSet.next()){}
				else {
					String query = "INSERT INTO opstelling (speler_id, team_id, wedstrijd_id)  "+ " VALUES ("+speler_id+","+team_id+","+wedstrijd_id+");";
					System.out.println(query);
					stmt.executeUpdate(query);
				}
				
			}
			con.close();
			out = true;
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return out;
		
	}
	
	
	public boolean setSlagvolgorde(int wedstrijd_id,List<Integer> speler_id_arr,int team_id){
		
		boolean out = false;
		try (Connection con = super.getConnection()) {
			Statement stmt = con.createStatement();
			int index = 1;
			for (int speler_id : speler_id_arr){
				
				Opstelling o = new Opstelling(index,speler_id,team_id,wedstrijd_id);
				String query = "UPDATE opstelling SET slagvolgorde="+o.getSlagvolgorde()+" WHERE  wedstrijd_id="+o.getWedstrijd_id()+" AND team_id="+o.getTeam_id()+" AND speler_id="+o.getSpeler_id()+";";
				System.out.println(query);
				stmt.executeUpdate(query);
				index++;
			}			
			con.close();
			out = true;
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return out;
	}
	
	public boolean setVeldpositie(int wedstrijd_id,List<Integer> speler_id_arr,int team_id){
		
		boolean out = false;
		try (Connection con = super.getConnection()) {
			Statement stmt = con.createStatement();
			int index = 1;
			for (int speler_id : speler_id_arr){
				
				Opstelling o = new Opstelling(index,speler_id,team_id,wedstrijd_id);
				String query = "UPDATE opstelling SET veldpositie="+o.getSlagvolgorde()+" WHERE  wedstrijd_id="+o.getWedstrijd_id()+" AND team_id="+o.getTeam_id()+" AND speler_id="+o.getSpeler_id()+";";
				System.out.println(query);
				int dbResultSet = stmt.executeUpdate(query);
				System.out.println("rows deleted="+dbResultSet);
				index++;
			}			
			con.close();
			out = true;
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return out;
	}

	
	public boolean deleteOpstellingByWedstrijd(int wedstrijd_id){
		String query = "DELETE count FROM opstelling WHERE wedstrijd_id="+wedstrijd_id+";";
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
