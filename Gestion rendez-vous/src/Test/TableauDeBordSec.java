package Test;
import java.awt.BorderLayout;
import com.Test.Images.*;

import java.awt.EventQueue;

import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.prefs.BackingStoreException;

import net.proteanit.*;
import net.proteanit.sql.DbUtils;

import javax.swing.*;
import org.jdesktop.swingx.*;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import org.jdesktop.swingx.calendar.CalendarUtils;
import org.jdesktop.swingx.calendar.DatePickerFormatter;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*
;

import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyAdapter;public class TableauDeBordSec extends JFrame {

	private JPanel contentPane;
	public JTable table;
	public static int id;
	private JTable patientTable;
	private JTextField Nom;
	private JTextField Prénom;
	private JTextField CIN;
	private JTextField Ntel;
	private JTextField Ncnam;
	private JTextField recherche;

public static int passid,Id,cin,cnam,tel;
public static String nom,prenom,sex;
public static Date dateN;
public static TableModel passModel;
public static int passrow;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			  UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch(Exception e) {
			  System.out.println("Error setting native LAF: " + e);
			}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TableauDeBordSec frame = new TableauDeBordSec();
					frame.setVisible(true);
					frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Object[] getdata = new Object[8]; 
	private JTable PaiementTab;
	public TableauDeBordSec() {
	
		
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1409, 788);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu_1 = new JMenu("Sessions");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Gestion Des Sessions");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SessionFrame fr = new SessionFrame();
				fr.setVisible(true);
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_1);
		
		JMenu mnNewMenu_2 = new JMenu("Quitter");
		mnNewMenu_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int x = 	JOptionPane.showConfirmDialog(null,"voulez-Vous Quitter ?" );
				if(x==0) {
					System.exit(x=0);
				}
			}
		});
		
		menuBar.add(mnNewMenu_2);
		
				
		
		
	
		contentPane = new JPanel();
		
		
	
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 1370, 749);
		
		contentPane.add(tabbedPane);
		
		JPanel panel = new JPanel();
		
		
		panel.setBorder(null);
		panel.setBackground(UIManager.getColor("EditorPane.selectionForeground"));
		tabbedPane.addTab("Rendez-Vous", null, panel, null);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 219, 1365, 502);
		panel.add(scrollPane);
		 
		 JPopupMenu popupMenu = new JPopupMenu();
		 addPopup(scrollPane, popupMenu);
		Modifier md = new Modifier(this);
		 JMenuItem ModifierMenuItem = new JMenuItem("Modifier");
		 ModifierMenuItem.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		boolean test= true;
		 		try {
		 		 id =  (int) table.getValueAt(table.getSelectedRow(), 0);}catch(Exception ex) {
		 			JOptionPane.showMessageDialog(null, "Aucune Ligne Sélectionnée");
		 			test=false;
		 		}
		 		
		 		if(test)
		 		md.setVisible(true);else {return;}
		 	}
		 });
		
		
		 
	
		 ModifierMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, InputEvent.CTRL_MASK));
		 popupMenu.add(ModifierMenuItem);
		 
		 JMenuItem Supprimer = new JMenuItem("Supprimer");
		 Supprimer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		 Supprimer.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		DataBaseConnection con = new DataBaseConnection("SELECT Id FROM `rendez-vous` ") ;
		 		int x = JOptionPane.showConfirmDialog(null, "voulez vous vraiment supprimer ?");
				if(x==0) {
		 		try {
		 			id =  (int) table.getValueAt(table.getSelectedRow(), 0);
			 		 }catch(Exception ex) {
			 			JOptionPane.showMessageDialog(null, "Aucune Ligne Sélectionnée");
			 		
			 		}
				}
		 		
		 		con.Update("DELETE FROM `rendez-vous` WHERE `Id`='"+id+"'");
		 				Update();
		 	}
		 });
		 popupMenu.add(Supprimer);
		 
		 JMenuItem mntmNewMenuItem_2 = new JMenuItem("Payer");
		 mntmNewMenuItem_2.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		final   double prix=  35;
		 		if(table.getSelectedRow()==-1) {
		 			JOptionPane.showMessageDialog(null, "Acune Ligne Sélectionner");
		 		}else {
		 		try {
		 		Paiement pay = new Paiement(table.getValueAt(table.getSelectedRow(), 2).toString(),prix,Integer.parseInt( table.getValueAt(table.getSelectedRow(), 0).toString()));
		 		pay.payer();
		 		JOptionPane.showMessageDialog(null, "Le Paiement Effectué Avec Succès");
		 		Afficher();
		 		
		 		}catch(Exception exp ) {
		 			//JOptionPane.showMessageDialog(null, "Erreur !!"+exp);
		 		}
		 	}}
		 });
		 mntmNewMenuItem_2.setMnemonic(KeyEvent.VK_P);
		 popupMenu.add(mntmNewMenuItem_2);
		
		
		
		 table = new JTable();
		table.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		scrollPane.setViewportView(table);
		
		JComboBox PatientN = new JComboBox();
		PatientN.setBounds(115, 29, 460, 36);
		 PatientN.setEnabled(true);
		PatientN.setModel(new DefaultComboBoxModel(new String[] {""}));
		PatientN.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel.add(PatientN);
		 AutoCompleteDecorator.decorate(PatientN);
		
		JComboBox Jour = new JComboBox();
		Jour.setBounds(115, 76, 106, 36);
		Jour.setFont(new Font("Tahoma", Font.BOLD, 14));
		Jour.setEditable(true);
		 AutoCompleteDecorator.decorate(Jour);
		Jour.setModel(new DefaultComboBoxModel(new String[] {"", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		panel.add(Jour);
		
		JComboBox Mois = new JComboBox();
		Mois.setBounds(247, 76, 106, 36);
		Mois.setFont(new Font("Tahoma", Font.BOLD, 14));
		Mois.setEditable(true);
		 AutoCompleteDecorator.decorate(Mois);
		Mois.setModel(new DefaultComboBoxModel(new String[] {"", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"}));
		panel.add(Mois);
		
		JComboBox Anné = new JComboBox();
		Anné.setBounds(385, 76, 106, 36);
		Anné.setFont(new Font("Tahoma", Font.BOLD, 14));
		Anné.setEditable(true);
		 AutoCompleteDecorator.decorate(Anné);
		Anné.setModel(new DefaultComboBoxModel(new String[] {"", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032", "2033", "2034", "2035", "2036", "2037", "2038", "2039", "2040", "2041", "2042", "2043", "2044", "2045", "2046", "2047", "2048", "2049", "2050"}));
		panel.add(Anné);
		
		JComboBox Heure = new JComboBox();
		Heure.setBounds(115, 123, 106, 36);
		Heure.setFont(new Font("Tahoma", Font.BOLD, 14));
		Heure.setEditable(true);
		 AutoCompleteDecorator.decorate(Heure);
		Heure.setModel(new DefaultComboBoxModel(new String[] {"", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18"}));
		panel.add(Heure);
		
		JComboBox Minut = new JComboBox();
		Minut.setBounds(247, 123, 106, 36);
		Minut.setFont(new Font("Tahoma", Font.BOLD, 14));
		Minut.setEditable(true);
		 AutoCompleteDecorator.decorate(Minut);
		Minut.setModel(new DefaultComboBoxModel(new String[] {"", "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59", "60"}));
		panel.add(Minut);
		
		JComboBox sujet = new JComboBox();
		sujet.setBounds(115, 170, 238, 36);
		sujet.setFont(new Font("Tahoma", Font.BOLD, 14));
		sujet.setModel(new DefaultComboBoxModel(new String[] {"", "Visite", "Controle"}));
		panel.add(sujet);
		
		JLabel lblNewLabel = new JLabel("Nom Patient");
		lblNewLabel.setBounds(10, 40, 408, 14);
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		panel.add(lblNewLabel);
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setBounds(10, 87, 106, 14);
		lblDate.setForeground(Color.BLACK);
		lblDate.setFont(new Font("Tahoma", Font.BOLD, 16));
		panel.add(lblDate);
		
		JLabel lblHeure = new JLabel("Heure");
		lblHeure.setBounds(10, 134, 95, 14);
		lblHeure.setForeground(Color.BLACK);
		lblHeure.setFont(new Font("Tahoma", Font.BOLD, 16));
		panel.add(lblHeure);
		
		JLabel lblSujet = new JLabel("Sujet");
		lblSujet.setBounds(10, 181, 408, 20);
		lblSujet.setForeground(Color.BLACK);
		lblSujet.setFont(new Font("Tahoma", Font.BOLD, 16));
		panel.add(lblSujet);
		
		JLabel lblNewLabel_4 = new JLabel("/");
		lblNewLabel_4.setBounds(231, 85, 13, 14);
		lblNewLabel_4.setForeground(Color.BLACK);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 16));
		panel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("/");
		lblNewLabel_5.setBounds(363, 85, 26, 14);
		lblNewLabel_5.setForeground(Color.BLACK);
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 16));
		panel.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel(":");
		lblNewLabel_6.setBounds(231, 132, 13, 14);
		lblNewLabel_6.setForeground(Color.BLACK);
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 16));
		panel.add(lblNewLabel_6);
		
		JButton btnNewButton = new JButton("Enregistrer Randez-Vous");
		btnNewButton.setBounds(587, 29, 230, 32);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Date= Anné.getSelectedItem().toString()+"-"+Mois.getSelectedItem().toString()+"-"+Jour.getSelectedItem().toString();
				
				String Heure1 = Heure.getSelectedItem().toString()+":"+ Minut.getSelectedItem().toString();
				RendezVous rdv = new RendezVous(Date,Heure1,sujet.getSelectedItem().toString(),PatientN.getSelectedItem().toString());
				
				if(Date.equals("") || Heure1.equals("")||PatientN.getSelectedItem().toString().equals("")||sujet.getSelectedItem().toString().equals("")) {
					JOptionPane.showMessageDialog(null, "Champ vide");
				}else {
				rdv.AjouterRendezVous();
				JOptionPane.showMessageDialog(null, "La Rendez-vous ajouté avec Succès");
				
				Update();
				PatientN.setSelectedItem("");
				Jour.setSelectedItem("");
				Mois.setSelectedItem("");
				Anné.setSelectedItem("");
				Heure.setSelectedItem("");
				Minut.setSelectedItem("");
				sujet.setSelectedItem("");
				
				
				}
			}
		});
		btnNewButton.setBackground(SystemColor.control);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel.add(btnNewButton);
		
		JLabel image = new JLabel("New label");
		image.setBounds(0, 0, 1365, 227);
	image.setIcon(new ImageIcon(TableauDeBordSec.class.getResource("/com/Test/Images/back.jpg")));
		panel.add(image);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Les Patients", null, panel_1, null);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(521, 230, 844, 491);
		scrollPane_1.setBackground(Color.WHITE);
		panel_1.add(scrollPane_1);
		
		patientTable = new JTable();
		patientTable.setFont(new Font("Tahoma", Font.BOLD, 14));
		scrollPane_1.setViewportView(patientTable);
		
		JPopupMenu popupMenu_1 = new JPopupMenu();
		addPopup(patientTable, popupMenu_1);
		
		JMenuItem Item1 = new JMenuItem("Modifier ");
		TableauDeBordSec o =this;
		

		Item1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				getdata[0]=patientTable.getValueAt(patientTable.getSelectedRow(),0);
				//passid=(int) patientTable.getValueAt(patientTable.getSelectedRow(),0);
				//Id = passid;
				getdata[1]=patientTable.getValueAt(patientTable.getSelectedRow(),1);
				getdata[2]=patientTable.getValueAt(patientTable.getSelectedRow(),2);
				getdata[3]=patientTable.getValueAt(patientTable.getSelectedRow(),3);
				getdata[4]=patientTable.getValueAt(patientTable.getSelectedRow(),4);
				getdata[5]=patientTable.getValueAt(patientTable.getSelectedRow(),5);
				getdata[6]=patientTable.getValueAt(patientTable.getSelectedRow(),6);
				getdata[7]=patientTable.getValueAt(patientTable.getSelectedRow(),7);
				ModifierPatient mp = new ModifierPatient(o);
				
				//nom= (String) String.valueOf(patientTable.getValueAt(patientTable.getSelectedRow(),1));
			//	prenom=(String) patientTable.getValueAt(patientTable.getSelectedRow(),2);
			//dateN=(Date) patientTable.getValueAt(patientTable.getSelectedRow(),3);
				 //sex=(String) patientTable.getValueAt(patientTable.getSelectedRow(),4);
				 //cin=(int) patientTable.getValueAt(patientTable.getSelectedRow(),5);
				// tel=(int) patientTable.getValueAt(patientTable.getSelectedRow(),6);
				 // cnam=(int) patientTable.getValueAt(patientTable.getSelectedRow(),7);
				  
              			
		   mp.setVisible(true);
			}
		});
		popupMenu_1.add(Item1);
		
		JMenuItem Item2 = new JMenuItem("Supprimer");
		Item2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
		 			int id1 =  (int) patientTable.getValueAt(patientTable.getSelectedRow(), 0);
			 		 }catch(Exception ex) {
			 			JOptionPane.showMessageDialog(null, "Aucune Ligne Sélectionnée");
			 		
			 		}
				int x = JOptionPane.showConfirmDialog(null, "voulez vous vraiment supprimer ?");
				if(x==0) {
				int id1 =  (int) patientTable.getValueAt(patientTable.getSelectedRow(), 0);
				DataBaseConnection con = new DataBaseConnection("SELECT * FROM patients");
		 		con.Update("DELETE FROM `patients` WHERE `Id`='"+id1+"'");
		 				UpdatePatient();}
		 	}
			
		});
		popupMenu_1.add(Item2);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(0, 0, 1365, 231);
		panel_1.add(panel_2);
		panel_2.setLayout(null);

		JRadioButton radrecherche = new JRadioButton("Recherche Par N\u00B0 CIN");
		
		radrecherche.setBounds(1075, 112, 150, 23);
	
		panel_2.add(radrecherche);
		recherche = new JTextField();
		panel_2.add(recherche);
		recherche.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(radrecherche.isSelected()) {
					DataBaseConnection con = new DataBaseConnection("SELECT * FROM patients WHERE `CIN` LIKE '%"+recherche.getText()+"%'");
		patientTable.setModel(DbUtils.resultSetToTableModel(con.rs));
				}else {
					DataBaseConnection con = new DataBaseConnection("SELECT * FROM patients WHERE `Nom` LIKE '%"+recherche.getText()+"%'");
					patientTable.setModel(DbUtils.resultSetToTableModel(con.rs));
				}
		
			}});
		
		
		
	
			
		
		
		
		
			
		recherche.setBounds(1075,77,280,28);
		panel_2.add(recherche);
		JLabel lblNewLabel_2 = new JLabel("Recherche Patient");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel_2.setBounds(904, 78, 161, 21);
		panel_2.add(lblNewLabel_2);
		
		recherche.setColumns(10);
		
		
		
		
	
		
		
		JLabel Image2 = new JLabel("");
		Image2.setFont(new Font("Tahoma", Font.BOLD, 17));
		Image2.setBounds(0, 0, 1365, 231);
		panel_2.add(Image2);
		Image2.setIcon(new ImageIcon(TableauDeBordSec.class.getResource("/com/Test/Images/back.jpg")));
		
		Nom = new JTextField();
		Nom.setBounds(124, 242, 308, 29);
		panel_1.add(Nom);
		Nom.setColumns(10);
		
		Prénom = new JTextField();
		Prénom.setBounds(124, 293, 308, 29);
		Prénom.setColumns(10);
		panel_1.add(Prénom);
		
		CIN = new JTextField(8);
		CIN.setBounds(124, 439, 308, 29);
		CIN.setColumns(10);
		panel_1.add(CIN);
		
		JComboBox day = new JComboBox();
		day.setBounds(124, 345, 77, 29);
		day.setEditable(true);
		day.setModel(new DefaultComboBoxModel(new String[] {"", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		AutoCompleteDecorator.decorate(day);
		panel_1.add(day);
		
		JComboBox month = new JComboBox();
		month.setBounds(237, 345, 77, 29);
		month.setEditable(true);
		month.setModel(new DefaultComboBoxModel(new String[] {"", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"}));
		AutoCompleteDecorator.decorate(month);
		panel_1.add(month);
		
		JComboBox year = new JComboBox();
		year.setBounds(355, 345, 77, 29);
		year.setEditable(true);
		year.setModel(new DefaultComboBoxModel(new String[] {"", "1910", "1911", "1912", "1913", "1914", "1915", "1916", "1917", "1918", "1919", "1920", "1921", "1922", "1923", "1924", "1925", "1926", "1927", "1928", "1929", "1930", "1931", "1932", "1933", "1934", "1935", "1936", "1937", "1938", "1939", "1940", "1941", "1942", "1943", "1944", "1945", "1946", "1947", "1948", "1949", "1950", "1951", "1952", "1953", "1954", "1955", "1956", "1957", "1958", "1959", "1960", "1961", "1962", "1963", "1964", "1965", "1966", "1967", "1968", "1969", "1970", "1971", "1972", "1973", "1974", "1975", "1976", "1977", "1978", "1979", "1980", "1981", "1982", "1983", "1984", "1985", "1986", "1987", "1988", "1989", "1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020"}));
		AutoCompleteDecorator.decorate(year);
		panel_1.add(year);
		
		Ntel = new JTextField(8);
		Ntel.setBounds(124, 493, 308, 29);
		Ntel.setColumns(10);
		panel_1.add(Ntel);
		
		Ncnam = new JTextField(8);
		Ncnam.setBounds(124, 573, 308, 29);
		Ncnam.setColumns(10);
		panel_1.add(Ncnam);
		Ncnam.setEnabled(true);
		ButtonGroup group2 = new ButtonGroup();
		JRadioButton Homme = new JRadioButton("Homme");
		Homme.setActionCommand("Homme");
		Homme.setBounds(124, 397, 77, 23);
		panel_1.add(Homme);
		group2.add(Homme);
		
		JRadioButton Femme = new JRadioButton("Femme");
		Femme.setBounds(220, 397, 109, 23);
		Femme.setActionCommand("Femme");
		panel_1.add(Femme);
		group2.add(Femme);
		JButton btvalider = new JButton("Valider");
		btvalider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 String dateN= year.getSelectedItem().toString()+"-"+month.getSelectedItem().toString()+"-"+day.getSelectedItem().toString();
				DataBaseConnection con = new DataBaseConnection("SELECT * FROM patients");
				int	i=0;;
				try {
					while(con.rs.next()) {
						i=con.rs.getInt(1)+1;
					}
				} catch (SQLException exp) {
					// TODO Auto-generated catch block
					exp.printStackTrace();
				}
				try {
				con.Update("INSERT INTO `patients`(`Id`, `Nom`, `Prénom`, `Date_De_Naissence`, `Sex` , `CIN`, `Ntel`, `CNAM`) VALUES ('"+i+"','"+Nom.getText().toString()+"','"+Prénom.getText().toString()+"','"+dateN+"','"+group2.getSelection().getActionCommand()+"','"+CIN.getText()+"','"+Ntel.getText()+"','"+Ncnam.getText()+"')");
				JOptionPane.showMessageDialog(null, "Patient Enregistrer avec succès");
				Nom.setText("");
				Prénom.setText("");
				day.setSelectedItem("");
				month.setSelectedItem("");
				year.setSelectedItem("");
				Homme.setSelected(false);
				Femme.setSelected(false);
				CIN.setText("");
				Ntel.setText("");
				Ncnam.setText("");
				
				
				
				UpdatePatient();}catch(Exception exp3){
					JOptionPane.showMessageDialog(null, "Erreur!!");
				}
			}
		});
		
		btvalider.setMnemonic(KeyEvent.VK_ENTER);
		btvalider.setBounds(227, 626, 102, 29);
		panel_1.add(btvalider);
		
		JLabel lblNewLabel_1 = new JLabel("Nom");
		lblNewLabel_1.setBounds(29, 249, 46, 14);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Pr\u00E9nom");
		lblNewLabel_1_1.setBounds(29, 300, 46, 14);
		panel_1.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Date De Naissance");
		lblNewLabel_1_2.setBounds(10, 352, 117, 14);
		panel_1.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("N\u00B0 CIN");
		lblNewLabel_1_3.setBounds(29, 446, 46, 14);
		panel_1.add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_4 = new JLabel("N\u00B0 t\u00E9l\u00E9phone");
		lblNewLabel_1_4.setBounds(10, 500, 104, 14);
		panel_1.add(lblNewLabel_1_4);
		
		JLabel lblNewLabel_1_5 = new JLabel("N\u00B0 CNAM");
		lblNewLabel_1_5.setBounds(29, 580, 65, 14);
		panel_1.add(lblNewLabel_1_5);
		
		
		JLabel lblNewLabel_1_3_1 = new JLabel("Sex");
		lblNewLabel_1_3_1.setBounds(29, 401, 46, 14);
		panel_1.add(lblNewLabel_1_3_1);
		
		
		JLabel lblNewLabel_1_5_1 = new JLabel("CNAM");
		lblNewLabel_1_5_1.setBounds(29, 547, 46, 14);
		panel_1.add(lblNewLabel_1_5_1);
		 ButtonGroup group = new ButtonGroup();
		
		JRadioButton oui = new JRadioButton("Oui");
		oui.setBounds(124, 543, 59, 23);
		
		oui.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Ncnam.setEnabled(true);
			}
		});
		panel_1.add(oui);
		oui.setSelected(true);
		
		
		
		
		 group.add(oui);
		JRadioButton non = new JRadioButton("Non");
		non.setBounds(220, 543, 109, 23);
		panel_1.add(non);
		group.add(non);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		tabbedPane.addTab("Paiement", null, panel_3, null);
		panel_3.setLayout(null);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(0, 0, 1365, 710);
		scrollPane_2.setBackground(Color.WHITE);
		panel_3.add(scrollPane_2);
		
		PaiementTab = new JTable();
		PaiementTab.setFont(new Font("Tahoma", Font.BOLD, 14));
		PaiementTab.setBackground(Color.WHITE);
		scrollPane_2.setViewportView(PaiementTab);
		Afficher();
		
		
		
		non.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Ncnam.setEnabled(false);
				Ncnam.setText("0");
			}
		});
		
		DataBaseConnection cnx = new DataBaseConnection("SELECT * FROM patients " );
		try {
		while(cnx.rs.next()) {
				PatientN.addItem(cnx.rs.getString("Nom").toString()+" "+cnx.rs.getString("Prénom").toString());
			}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	
	
				   
	       Update();
	      UpdatePatient();
	
	}


	public void Update() {
		DefaultTableModel model = new DefaultTableModel();
		  
		model.addColumn("Id");
	     model.addColumn("Nom Patient");
	     model.addColumn("Date");
	     model.addColumn("Heure");
	     model.addColumn("Sujet");


		  DataBaseConnection cnx2 = new DataBaseConnection("SELECT * FROM `rendez-vous` WHERE Date=CURDATE() " );
	       
			try {
				while(cnx2.rs.next()) {
					model.addRow(new Object[]{
							cnx2.rs.getInt("Id"),
							cnx2.rs.getString("PatientId").toString(),
							cnx2.rs.getDate("Date").toString(),
							cnx2.rs.getString("Heure").toString(),
							cnx2.rs.getString("Sujet").toString(),
							
							
					});
					
				}	
				
					   
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			table.setModel(model);
	}
	
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
	public void UpdatePatient() {
		DataBaseConnection con =  new DataBaseConnection("SELECT * FROM patients");
		
		patientTable.setModel(DbUtils.resultSetToTableModel(con.rs));
		
	}
	public void Afficher() {
		DataBaseConnection con= new  DataBaseConnection("SELECT pai.`Id`, rdv.`Date`, rdv.`Sujet`, pai.`Prix` ,rdv.`PatientId`AS 'Patient' FROM `rendez-vous` rdv JOIN `paiement` pai ON rdv.Id = pai.IdPatient ");
		
		PaiementTab.setModel(DbUtils.resultSetToTableModel(con.rs));
	}   
	
	}

