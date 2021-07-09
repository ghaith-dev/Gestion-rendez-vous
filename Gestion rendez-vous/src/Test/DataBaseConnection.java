package Test;
import java.sql.*;

public class DataBaseConnection {
	
	public Connection con ;
	public Statement set;
	public ResultSet rs,rs2; 
	public String sql,qu;
	int in;
	public DataBaseConnection(String sql) {
		try {
			this.sql=sql;
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Cabinet?serverTimezone=UTC","root","");
			set = con.createStatement();
			rs = set.executeQuery(sql);
			
			
		}catch(Exception e) {
		
			System.out.println("Erreur"+e);
		}
		
	}
	public void Insert(String qu) {
		try {
			in=set.executeUpdate(qu);
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void Update(String qu) {
		try {
			in=set.executeUpdate(qu);
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
		}
		
		
	


