package Test;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.HeadlessException;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import com.mysql.cj.jdbc.exceptions.SQLExceptionsMapping;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ModifierPatient extends JFrame {

	private JPanel contentPane;
	private JTextField Nom;
	private JTextField prenom;
	private JTextField Ncin;
	private JTextField Ntel;
	private JTextField Ncnam;
	public static TableauDeBordSec f;
	private JTextField dateden;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModifierPatient frame = new ModifierPatient(f);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ModifierPatient(TableauDeBordSec f) {
		this.f=f;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 560, 525);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		Nom = new JTextField();
		Nom.setColumns(10);
		Nom.setBounds(155, 48, 308, 29);
		Nom.setText(f.getdata[1].toString());
		contentPane.add(Nom);
		
		JLabel lblNewLabel_1 = new JLabel("Nom");
		lblNewLabel_1.setBounds(60, 55, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Pr\u00E9nom");
		lblNewLabel_1_1.setBounds(60, 106, 46, 14);
		contentPane.add(lblNewLabel_1_1);
		
		prenom = new JTextField();
		prenom.setColumns(10);
		prenom.setBounds(155, 99, 308, 29);
		prenom.setText(f.getdata[2].toString());
		contentPane.add(prenom);
		
		JLabel lblNewLabel_1_2 = new JLabel("Date De Naissance");
		lblNewLabel_1_2.setBounds(41, 158, 117, 14);
		contentPane.add(lblNewLabel_1_2);
		
		JRadioButton Homme = new JRadioButton("Homme");
		Homme.setActionCommand("Homme");
		Homme.setBounds(155, 203, 77, 23);
		contentPane.add(Homme);
		
		JRadioButton Femme = new JRadioButton("Femme");
		Femme.setBounds(251, 203, 109, 23);
		Femme.setActionCommand("Femme");
		contentPane.add(Femme);
		if(f.getdata[4].toString().equals("Homme")) {
			Homme.setSelected(true);
		}else {
			
			Femme.setSelected(true);
		}
		JLabel lblNewLabel_1_3_1 = new JLabel("Sexe");
		lblNewLabel_1_3_1.setBounds(60, 207, 46, 14);
		contentPane.add(lblNewLabel_1_3_1);
		
		JLabel lblNewLabel_1_3 = new JLabel("N\u00B0 CIN");
		lblNewLabel_1_3.setBounds(60, 252, 46, 14);
		contentPane.add(lblNewLabel_1_3);
		
		Ncin = new JTextField(10);
		Ncin.setBounds(155, 245, 308, 29);
		Ncin.setText(f.getdata[5].toString());
		contentPane.add(Ncin);
		
		JLabel lblNewLabel_1_4 = new JLabel("N\u00B0 t\u00E9l\u00E9phone");
		lblNewLabel_1_4.setBounds(41, 306, 104, 14);
		contentPane.add(lblNewLabel_1_4);
		
		Ntel = new JTextField(10);
		Ntel.setBounds(155, 299, 308, 29);
		Ntel.setText(f.getdata[6].toString());
		contentPane.add(Ntel);
		
		Ncnam = new JTextField(10);
		Ncnam.setEnabled(true);
		Ncnam.setBounds(155, 351, 308, 29);
		Ncnam.setText(f.getdata[7].toString());
		contentPane.add(Ncnam);
		JButton btvalider = new JButton("Valider");
		ButtonGroup group = new ButtonGroup();
		ButtonGroup group2 = new ButtonGroup();
		group.add(Homme);
		group.add(Femme);
		dateden = new JTextField();
		dateden.setBounds(155, 146, 308, 29);
		contentPane.add(dateden);
		dateden.setText(f.getdata[3].toString());
		dateden.setColumns(10);
	
		btvalider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String daten = dateden.getText();
				int id=f.passid;
				DataBaseConnection con = new DataBaseConnection("SELECT * FROM `patients`");
				con.Update("UPDATE `patients` SET `Nom`='"+Nom.getText().toString()+"',`Prénom`='"+prenom.getText().toString()+"',`Date_De_Naissence`='"+daten+"',`Sex`='"+group.getSelection().getActionCommand()+"',`CIN`='"+Ncin.getText()+"',`Ntel`='"+Ntel.getText()+"',`CNAM`='"+Ncnam.getText()+"' WHERE `Id`='"+f.getdata[0].toString()+"'");
				JOptionPane.showMessageDialog(null, "Patient Modifié avec Succès");
				f.UpdatePatient();
				dispose();
			}
		});
		btvalider.setBounds(227, 432, 102, 29);
		contentPane.add(btvalider);
		
		JLabel lblNewLabel_1_5 = new JLabel("N\u00B0 CNAM");
		lblNewLabel_1_5.setBounds(41, 358, 65, 14);
		contentPane.add(lblNewLabel_1_5);
		
		
		

		
		
		

			
		}
	}


