package Test;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.SystemColor;


import java.awt.Dimension;
import java.awt.Window.Type;
import java.awt.Component;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Frame;
import java.awt.SplashScreen;

public class LoginPage extends  JFrame{

	public JFrame frmLoginPage;
	private JTextField MtrTxt;
	private JPasswordField PasswdTxt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
	
	
	
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
				
					
					LoginPage window = new LoginPage();
					
					window.frmLoginPage.setVisible(true);
					window.frmLoginPage.setExtendedState(JFrame.MAXIMIZED_BOTH);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LoginPage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @param <PlaceholderTextField>
	 */
	private <PlaceholderTextField> void initialize() {
		frmLoginPage = new JFrame();
		frmLoginPage.setName("Login Page");
		frmLoginPage.setMinimumSize(new Dimension(1366, 786));
		frmLoginPage.setSize(new Dimension(1366, 786));
		frmLoginPage.setTitle("Login Page");
		
		frmLoginPage.setBounds(100, 100, 837, 599);
		frmLoginPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLoginPage.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		
		panel.setBackground(new Color(0,0,0,60));
		panel.setBounds(370, 219, 610, 309);
		frmLoginPage.getContentPane().add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 0,0, 128));
		panel_1.setBounds(0, 0, 610, 58);
		panel.add(panel_1);
		panel_1.setLayout(null);
		 
		JLabel lblNewLabel = new JLabel("Page De Connexion");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel.setForeground(SystemColor.text);
		lblNewLabel.setBounds(167, 11, 330, 31);
		panel_1.add(lblNewLabel);
		
		MtrTxt = new JTextField();
		MtrTxt.setFont(new Font("Tahoma", Font.BOLD, 16));
		MtrTxt.setBackground(SystemColor.control);
		MtrTxt.setBounds(122, 100, 375, 31);
		panel.add(MtrTxt);
		MtrTxt.setColumns(10);
		
		JButton LoginButton = new JButton("Connecter");
		LoginButton.addActionListener(new ActionListener() {
		

			
			public void actionPerformed(ActionEvent e) {
				 String qu= "SELECT * FROM Login Where Mtr='"+MtrTxt.getText()+"' AND Passwd='"+PasswdTxt.getText().toString()+"'" ;
				DataBaseConnection con = new DataBaseConnection(qu);
				
			
				try {
					
					if(con.rs.next()) {
						
				
				
					TableauDeBordSec tdbs = new TableauDeBordSec();
					tdbs.setVisible(true);
					frmLoginPage.setVisible(false);
				}
							
							
						
					else {
						javax.swing.JOptionPane.showMessageDialog(null,"Verifier Votre Donné");
					}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					
				}
						
				
				
				
			}
		});
		LoginButton.setForeground(new Color(255, 255, 255));
		LoginButton.setBackground(new Color(0, 0,0, 128));
		LoginButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		LoginButton.setBounds(238, 233, 123, 47);
		panel.add(LoginButton);
		
		PasswdTxt = new JPasswordField();
		PasswdTxt.setFont(new Font("Tahoma", Font.BOLD, 16));
		PasswdTxt.setBackground(SystemColor.control);
		PasswdTxt.setBounds(122, 163, 375, 31);
		panel.add(PasswdTxt);
		
		JLabel lblNewLabel_1 = new JLabel("Matricule");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(27, 110, 85, 14);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Mot De Passe");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2.setBounds(10, 173, 102, 14);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setIcon(new ImageIcon("C:\\Users\\Ghaith\\Desktop\\hush-naidoo-yo01Z-9HQAw-unsplash.jpg"));
		lblNewLabel_3.setBounds(0, 0, 1360, 789);
		frmLoginPage.getContentPane().add(lblNewLabel_3);
	}
}
