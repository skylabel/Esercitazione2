package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import com.intecs.mab.Player;
import com.intecs.mab.UpperConfidenceBound;

public class ArchiveDB {
	
	
	   static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
	   static final String DB_URL = "jdbc:mysql://localhost/multiarmbanditdb?autoReconnect=true&useSSL=false&serverTimezone=UTC";
	   
	   static final String USER = "root";
	   static final String PASS = "a.12345678";
	   
	   
	   
	   Connection conn = null;
	   Statement stmt = null;
	   
	 
	   public ArchiveDB() {
		 			
		     }
	   
	  
	   
	   public void register(Player player) throws PlayerIsAllReadyPresentException, NullPointerException {
			if(player.equals(null)) throw new NullPointerException();
			
			String query= "SELECT * FROM player WHERE  name="+"'"+player.getName()+"';";
			if(executeQuery(query).equals(null)) {
				throw new PlayerIsAllReadyPresentException();
			}else {
				query="INSERT INTO player (name) VALUES ('"+player.getName()+"')";
				insertquery(query);
			}
			
		}
	   
	   
	   
	   public void delete(Player player) {
		   if(player.equals(null)) throw new NullPointerException();
		   
		   String query = "DELETE FROM player WHERE name = "+"'"+player.getName()+"';";
	   
		   deleteQuery(query);
		   
	        
	   }	   
	   
	   
	  private void insertquery(String query) {
		
		  try {
		  Class.forName(JDBC_DRIVER);
	      System.out.println("Connecting to database...");
	      conn = DriverManager.getConnection(DB_URL,USER,PASS);
		  PreparedStatement preparedStmt = conn.prepareStatement(query);
		
			
		    preparedStmt.execute();
			preparedStmt.close();
			conn.close();
		    
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		  
	  }
	   
	  
	  
	  private void deleteQuery(String query)
	  {
	     try{  
	    	 Class.forName(JDBC_DRIVER);
		      System.out.println("Connecting to database...");
		      conn = DriverManager.getConnection(DB_URL,USER,PASS);
			  PreparedStatement preparedStmt = conn.prepareStatement(query);

			preparedStmt.executeUpdate();
			 
	     }catch(Exception e) {
	     }

	  }
	   
	   
	   
	   private ResultSet executeQuery(String query) {
		   ResultSet rs = null;
		   try{
			   Class.forName(JDBC_DRIVER);
			   conn = DriverManager.getConnection(DB_URL,USER,PASS);
			      
			   stmt = conn.createStatement();
			   rs = stmt.executeQuery(query);
			   rs.close();
			   stmt.close();
			   conn.close();
		   }catch(SQLException se){
			   se.printStackTrace();
		   }catch(Exception e){
			   e.printStackTrace();
		   }
		   return rs;
		   
		   
	   }
	 
	   public static void main(String args[]) throws NullPointerException, PlayerIsAllReadyPresentException {
	
		   ArchiveDB a=new ArchiveDB();
		   Player player=new UpperConfidenceBound("Andrea",new GregorianCalendar(2018,12,5)) ;
		  // a.register(player);
		   
		   a.delete(player);
	   }
	   
	   
}
