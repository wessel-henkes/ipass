package nl.hu.v1wac.firstapp.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO extends BaseDAO {
	 public String findRoleForUsernameAndPassword(String username, String password) {
	 String role = null;
	 String query = "SELECT role FROM useraccount WHERE username = ? AND password = ?";
	 System.out.println(query);
	 try (Connection con = super.getConnection()) {

	 PreparedStatement pstmt = con.prepareStatement(query);
	 pstmt.setString(1, username);
	 pstmt.setString(2, password);
	 System.out.println(pstmt);
	 ResultSet rs = pstmt.executeQuery();
	 if (rs.next())
	 role = rs.getString("role");

	 } catch (SQLException sqle) {
	 sqle.printStackTrace();
	 }
	 System.out.println("Rol="+role);
	 return role;
	 }
	 
	 public String findTeamForUsernameAndPassword(String username, String password) {
			System.out.println("getting team from db");
			String team = null;
			String query = "SELECT team_id FROM useraccount WHERE username = ? AND password = ?";
			System.out.println(query);
			try (Connection con = super.getConnection()) {
				PreparedStatement pstmt = con.prepareStatement(query);
				pstmt.setString(1, username);
				pstmt.setString(2, password);
				System.out.println(pstmt);
				ResultSet rs = pstmt.executeQuery();
				if (rs.next()){
					 team = rs.getString("team_id");
				}
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
			System.out.println("team_id="+team);
			return team;
		}	 
	 
}