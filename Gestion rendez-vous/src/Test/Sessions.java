package Test;

import java.sql.SQLException;

public class Sessions {
public static int id;
	private int mtr ;
	private String mdp;
	private String prv;
	public Sessions( int mtr, String mdp, String prv) {
		this.id = id;
		this.mtr = mtr;
		this.mdp = mdp;
		this.prv = prv;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getMtr() {
		return mtr;
	}
	public void setMtr(int mtr) {
		this.mtr = mtr;
	}
	public String getMdp() {
		return mdp;
	}
	public void setMdp(String mdp) {
		this.mdp = mdp;
	}
	public String getPrv() {
		return prv;
	}
	public void setPrv(String prv) {
		this.prv = prv;
	}
	public void Ajouter() throws SQLException {
		DataBaseConnection cnx = new DataBaseConnection("SELECT `Id` FROM `login` ");
		while(cnx.rs.next()) {
		id=	cnx.rs.getInt("Id")+1;
		
			
		}
		cnx.Insert("INSERT INTO `login`(`Id`, `Mtr`, `Passwd`, `Priv`) VALUES ('"+id+"','"+mtr+"','"+mdp+"','"+prv+"')");
	}
	public void Modifier() {
		DataBaseConnection cnx = new DataBaseConnection("SELECT * FROM `login` ");
		cnx.Update("UPDATE `login` SET `Id`='"+id+"',`Mtr`='"+mtr+"',`Passwd`='"+mdp+"',`Priv`='"+prv+"' WHERE `Id`='"+id+"'");
	}
	public void Supprimer() {
		
		DataBaseConnection cnx = new DataBaseConnection("SELECT * FROM `login` ");
		cnx.Update("DELETE FROM `login` WHERE `Id`='"+id+"'");
		
	}
	

}
