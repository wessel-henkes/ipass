package nl.hu.v1wac.firstapp.persistence;
//
//import java.sql.Connection;
//
//import javax.naming.InitialContext;
//import javax.sql.DataSource;
//
//public class BaseDAO {
//	/**
//	 * Om dit werkend te krijgen moet je in bestand
//	 * 
//	 * 		src/main/webapp/META-INF/context.xml
//	 * 
//	 * De juiste instellingen voor de database opgeven en
//	 * zorgen dat in deze database de queries uit het 
//	 * 
//	 * CompanyExampleDump.sql zijn uitgevoerd. Je kunt dit 
//	 * SQL-bestand vinden in de root van dit project.
//	 * 
//	 * @return Connection
//	 */
//	protected final Connection getConnection() {
//		Connection result = null;
//
//		try {
//			InitialContext ic = new InitialContext();
//			DataSource datasource = (DataSource) ic.lookup("java:comp/env/jdbc/PostgresDS");
//			
//			result = datasource.getConnection();
//		} catch (Exception ex) {
//			throw new RuntimeException(ex);
//		}
//		
//		return result;
//	}
//	
//	
//}


import java.net.URI;
import java.sql.Connection;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
public class BaseDAO {
	 private DataSource connectionPool;
	 
	 public BaseDAO() {
		 try {
			 final String DATABASE_URL_PROP = System.getenv("DATABASE_URL");
			 if (DATABASE_URL_PROP != null) {
				 URI dbUri = new URI(DATABASE_URL_PROP);
				 String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + dbUri.getPath();
				 BasicDataSource pool = new BasicDataSource();
			 if (dbUri.getUserInfo() != null) {
				 pool.setUsername(dbUri.getUserInfo().split(":")[0]);
				 pool.setPassword(dbUri.getUserInfo().split(":")[1]);
			 }
			 pool.setDriverClassName("org.postgresql.Driver");
			 pool.setUrl(dbUrl);
			 pool.setInitialSize(0);
			 pool.setMaxOpenPreparedStatements(1);
			 pool.setMinIdle(0);
			 pool.setMaxIdle(0);
			 
			 
			
			 connectionPool = pool;
			 } else {
				 InitialContext ic = new InitialContext();
				 connectionPool = (DataSource) ic.lookup("java:comp/env/jdbc/PostgresDS");
			 }
		 } catch (Exception e) {
			 throw new RuntimeException(e);
		 }
	}
	
	 protected final Connection getConnection() {
		 try {
			 return connectionPool.getConnection();
		 } catch (Exception ex) {
			 throw new RuntimeException(ex);
		 }
	 }
}


