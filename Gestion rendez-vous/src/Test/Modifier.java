package Test;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JProgressBar;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import com.mysql.cj.exceptions.ExceptionFactory;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import com.mysql.cj.jdbc.exceptions.*;
public class Modifier extends JFrame  {

	private JPanel contentPane;
	static TableauDeBordSec sec;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) throws SQLException {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Modifier frame = new Modifier(sec);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the frame.
	 * @wbp.parser.constructor
	 */
	public Modifier(TableauDeBordSec sec)   {
		this.sec=sec;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 446, 226);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JComboBox Jour = new JComboBox();
		Jour.setModel(new DefaultComboBoxModel(new String[] {"", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		Jour.setFont(new Font("Tahoma", Font.BOLD, 14));
		Jour.setBounds(76, 23, 79, 20);
		Jour.setSelectedItem("");
		contentPane.add(Jour);
		
		JComboBox Mois = new JComboBox();
		Mois.setModel(new DefaultComboBoxModel(new String[] {"", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"}));
		Mois.setFont(new Font("Tahoma", Font.BOLD, 14));
		Mois.setBounds(191, 23, 79, 20);
		contentPane.add(Mois);
		
		JComboBox Anne = new JComboBox();
		Anne.setModel(new DefaultComboBoxModel(new String[] {"", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032", "2033", "2034", "2035", "2036", "2037", "2038", "2039", "2040", "2041", "2042", "2043", "2044", "2045", "2046", "2047", "2048", "2049", "2050"}));
		Anne.setFont(new Font("Tahoma", Font.BOLD, 14));
		Anne.setBounds(307, 23, 79, 20);
		contentPane.add(Anne);
		
		JComboBox Heure = new JComboBox();
		Heure.setModel(new DefaultComboBoxModel(new String[] {"", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18"}));
		Heure.setFont(new Font("Tahoma", Font.BOLD, 14));
		Heure.setBounds(76, 72, 79, 20);
		contentPane.add(Heure);
		
		JComboBox Minute = new JComboBox();
		Minute.setModel(new DefaultComboBoxModel(new String[] {"", "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59", "60"}));
		Minute.setFont(new Font("Tahoma", Font.BOLD, 14));
		Minute.setBounds(177, 72, 79, 20);
		contentPane.add(Minute);
		
		JComboBox Sujet = new JComboBox();
		Sujet.setFont(new Font("Tahoma", Font.BOLD, 14));
		Sujet.setModel(new DefaultComboBoxModel(new String[] {"", "Visite", "Controlle"}));
		Sujet.setBounds(76, 114, 187, 20);
		contentPane.add(Sujet);
		
		JLabel lblNewLabel = new JLabel("/");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(167, 26, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("/");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(282, 26, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel(":");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2.setBounds(167, 75, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Date");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_3.setBounds(10, 26, 46, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_3_1 = new JLabel("Heure");
		lblNewLabel_3_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_3_1.setBounds(10, 75, 46, 14);
		contentPane.add(lblNewLabel_3_1);
		
		JLabel lblNewLabel_3_2 = new JLabel("Sujet");
		lblNewLabel_3_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_3_2.setBounds(10, 117, 46, 14);
		contentPane.add(lblNewLabel_3_2);
		
		JButton valider = new JButton("Valider");
		String DateS = Anne.getSelectedItem().toString() + "-"+ Mois.getSelectedItem().toString() + "-"+ Jour.getSelectedItem().toString();
		String HeureS = Heure.getSelectedItem().toString()+":"+Minute.getSelectedItem().toString();
		String SujetS = Sujet.getSelectedItem().toString();
		String id =  String.valueOf(sec.id);
	RendezVous rdv = new RendezVous(Anne.getSelectedItem().toString() + "-"+ Mois.getSelectedItem().toString() + "-"+ Jour.getSelectedItem().toString(),Heure.getSelectedItem().toString()+":"+Minute.getSelectedItem().toString(), Sujet.getSelectedItem().toString(),String.valueOf(sec.id));
		valider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(Anne.getSelectedItem().toString().equals("") || Jour.getSelectedItem().toString().equals("") || Mois.getSelectedItem().toString().equals("") || Heure.getSelectedItem().toString().equals("") || Minute.getSelectedItem().toString().equals("") || Sujet.getSelectedItem().toString().equals("")  ) {
					
					JOptionPane.showMessageDialog(null, "Champ(s) Vide(s) " );
					
					return;}
	DataBaseConnection con = new DataBaseConnection("SELECT Id FROM `rendez-vous` ") ;
	

	
	con.Update("UPDATE `rendez-vous` SET `Date`='"+Anne.getSelectedItem().toString() + "-"+ Mois.getSelectedItem().toString() + "-"+ Jour.getSelectedItem().toString()+"',`Heure`='"+Heure.getSelectedItem().toString()+":"+Minute.getSelectedItem().toString()+"' ,`Sujet`='"+Sujet.getSelectedItem().toString()+"' WHERE `Id`='"+String.valueOf(sec.id)+"'");
			sec.Update();
			
		
	
	JOptionPane.showMessageDialog(null, "Le Rendez-Vous Modifier Avec Succès " );
	Jour.setSelectedItem("");
	Mois.setSelectedItem("");
	Anne.setSelectedItem("");
	Heure.setSelectedItem("");
	Minute.setSelectedItem("");
	Sujet.setSelectedItem("");
	dispose();
			}	
			
		
		});
		valider.setFont(new Font("Tahoma", Font.BOLD, 14));
		valider.setBounds(170, 153, 89, 23);
		contentPane.add(valider);
		
		JLabel image = new JLabel("");
		image.setBounds(0, 0, 430, 187);
		image.setIcon(new ImageIcon(Modifier.class.getResource("/com/Test/Images/back2.jpg")));
		contentPane.add(image);
	}
	public Modifier(Class<? extends Modifier> class1) {
		// TODO Auto-generated constructor stub
	}
}
