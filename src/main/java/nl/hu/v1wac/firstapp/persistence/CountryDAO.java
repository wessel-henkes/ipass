package nl.hu.v1wac.firstapp.persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import nl.hu.v1wac.firstapp.model.Country;

public class CountryDAO extends BaseDAO{

	public boolean save(){
		String query = "COMMIT";
		boolean out = false;
		try (Connection con = super.getConnection()) {
			Statement stmt = con.createStatement();
			stmt.executeUpdate(query);
			out = true;
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return out;
	}
		
	public List<Country> findAll(){
		return selectCountries("SELECT * FROM Country order by code");
	}
	
	public Country findCountryByCode(String code){
		Country out = null;
		if (code.length() == 3){
			try {
				out = selectCountries("SELECT * FROM Country WHERE code ='"+code+"'").get(0);
			} catch( IndexOutOfBoundsException e){ System.out.println(e);}
		}
		else {
			try{
				out = selectCountries("SELECT * FROM Country WHERE code2 ='"+code+"'").get(0);
			}catch( IndexOutOfBoundsException e){ System.out.println(e);}
		}
		return out;
	}
	
	private List<Country> selectCountries(String query) {
		List<Country> results = new ArrayList<Country>();
		
		try (Connection con = super.getConnection()) {
			Statement stmt = con.createStatement();
			ResultSet dbResultSet = stmt.executeQuery(query);
			
			while (dbResultSet.next()) {
				String r0 = dbResultSet.getString("code");
				String r1 = dbResultSet.getString("name");
				String r2 = dbResultSet.getString("continent");
				String r3 = dbResultSet.getString("region");
				int r4 = dbResultSet.getInt("surfacearea");
				int r5 = dbResultSet.getInt("indepyear");
				int r6 = dbResultSet.getInt("population");
				int r7 = dbResultSet.getInt("lifeexpectancy");
				int r8 = dbResultSet.getInt("gnp");
				int r9 = dbResultSet.getInt("gnpold");
				String r10 = dbResultSet.getString("localname");
				String r11 = dbResultSet.getString("governmentForm");
				String r12 = dbResultSet.getString("headofstate");
				String r13 = dbResultSet.getString("code2");
				double r14 = dbResultSet.getDouble("latitude");
				double r15 = dbResultSet.getDouble("longitude");
				String r16 = dbResultSet.getString("capital");
					
				results.add(new Country(r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16));
			}
			con.close();
		} catch (SQLException sqle) {
			//sqle.printStackTrace();
		}
		
		return results;
	}
	
	public List<Country> find10LargestPopulations(){
		return selectCountries("SELECT * FROM Country ORDER BY population LIMIT 10");
	}
	
	public List<Country> find10LargestSurfeces(){
		return selectCountries("SELECT * FROM Country ORDER BY surfacearea LIMIT 10");
	}
	
	public Country update(Country c){
		System.out.println(c);
		String query = "UPDATE country SET name='"+c.getName()+"',continent='"+c.getContinent()+"',region='"+c.getRegion()+"',surfacearea="+c.getSurfacearea()+",indepyear="+c.getIndepyear()+",population="+c.getPopulation()+",lifeexpectancy="+c.getLifeexpectancy()+",gnp="+c.getGnp()+",gnpold="+c.getGnpold()+",localname='"+c.getLocalname()+"',governmentForm='"+c.getGovernmentform()+"',headofstate='"+c.getHeadofstate()+"',code2='"+c.getCode2()+"',latitude="+c.getLatitude()+",longitude="+c.getLongitude()+", capital='"+c.getCapital()+"' WHERE code='"+c.getCode()+"'";
		System.out.println(query);
		try (Connection con = super.getConnection()) {
			Statement stmt = con.createStatement();
			stmt.executeUpdate(query);
			con.close();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return c;
		//return findCountryByCode(c.getCode());		
	}
	
	public boolean delete(Country country){
		String query = "DELETE FROM country WHERE code ='"+country.getCode()+"'";
		boolean out = false;
		System.out.println(query);
		try (Connection con = super.getConnection()) {
			Statement stmt = con.createStatement();
			stmt.executeUpdate(query);
			out = true;
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		System.out.println(out);
		
		return out;
		
	}
	
	public Country insert(Country c){
		String query = "INSERT INTO country (code,name,continent,region,surfacearea,indepyear,population,lifeexpectancy,gnp,gnpold,localname,governmentform,headofstate,code2,latitude,longitude,capital) "
				+ " VALUES ('"+c.getCode()+"','"+c.getName()+"','"+c.getContinent()+"','"+c.getRegion()+"',"+c.getSurfacearea()+","+c.getIndepyear()+","+c.getPopulation()+","+c.getLifeexpectancy()+","+c.getGnp()+","+c.getGnpold()+",'"+c.getLocalname()+"','"+c.getGovernmentform()+"','"+c.getHeadofstate()+"','"+c.getCode2()+"',"+c.getLatitude()+","+c.getLongitude()+",'"+c.getCapital()+"')";
		System.out.println(query);
		try (Connection con = super.getConnection()) {
			Statement stmt = con.createStatement();
			stmt.executeUpdate(query);
			System.out.println(query);
			con.close();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return c;
		//return findCountryByCode(c.getCode());		
	}
	
}
