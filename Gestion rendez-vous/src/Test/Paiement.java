package Test;

import java.sql.ResultSet;

public class Paiement {
	private static int Id;
	private String Date;
	private double Prix;
	private int PatientId;
	public Paiement(String date, double prix, int patientId) {
	
		Date = date;
		Prix = prix;
		PatientId = patientId;
	}
	public void payer() {
		DataBaseConnection con= new  DataBaseConnection("SELECT * FROM paiement");
		con.Insert("INSERT INTO `paiement`(`Date`, `Prix`, `IdPatient`) VALUES ('"+Date+"','"+Prix+"','"+PatientId+"')");
		
	}
	
}

