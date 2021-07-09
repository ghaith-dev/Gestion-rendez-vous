package Test;
import java.sql.SQLException;

public class RendezVous {
	private String sql;
	int i=0;
	private String Date;
	private String Heure;
	private String Sujet;
	private String Patient;
	
	public RendezVous(String Date,String Heure,String Sujet,String Patient) {
		this.Date=Date;
		this.Heure=Heure;
		this.Sujet=Sujet;
		this.Patient=Patient;
	}
	
	public void AjouterRendezVous() {
		DataBaseConnection con = new DataBaseConnection("SELECT Id FROM `rendez-vous` ") ;
		try {
			while(con.rs.next()) {
				i=con.rs.getInt(1)+1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		con.Insert("INSERT INTO `rendez-vous`(`Id`,`Date`, `Heure`, `Sujet`, `PatientId`) VALUES('"+i+"','"+this.Date+"','"+this.Heure+"','"+this.Sujet+"','"+this.Patient+"')");
		
	}
	public void ModifierRendezVous() {
		DataBaseConnection con = new DataBaseConnection("SELECT Id FROM `rendez-vous` ") ;
		
		
		
		con.Update("UPDATE `rendez-vous` SET `Date`='"+this.Date+"',`Heure`='"+this.Heure+"' ,`Sujet`='"+this.Sujet+"' WHERE `Id`='"+this.Patient+"'");
		
	}

	public String getSujet() {
		return Sujet;
	}
	
}
