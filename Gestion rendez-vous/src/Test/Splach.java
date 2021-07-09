
package Test;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JFormattedTextField;
import javax.swing.JProgressBar;
import java.awt.Label;
import java.awt.SplashScreen;
import java.awt.Color;
import java.awt.Font;

public class Splach extends JFrame {

	private JPanel contentPane;
	public JLabel Loading;
	public JProgressBar Loadbar;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public Splach() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 716, 388);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(135, 206, 235));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel Image = new JLabel("");
		Image.setBounds(266, 108, 183, 172);
		Image.setIcon(new ImageIcon(Splach.class.getResource("/com/Test/Images/d.png")));
		contentPane.add(Image);
		
 Loadbar = new JProgressBar();
		Loadbar.setBounds(0, 374, 716, 14);
		contentPane.add(Loadbar);

		
	
	 Loading = new JLabel("Veuillez Patienter...");
		Loading.setFont(new Font("Tahoma", Font.BOLD, 23));
		Loading.setBounds(228, 321, 353, 28);
		contentPane.add(Loading);

		
	
		setUndecorated (true);
	}
	public static void main(String[] args) {
		
				
				
				Splach Splash =new Splach();
				Splash.setVisible(true);
				Splash.setLocationRelativeTo(null);
				LoginPage log = new LoginPage();
				try {
					for(int i=0;i<100;i++) {
						
						Thread.sleep(40);
						
					Splash.Loading.setText( "Veuillez Patienter..."+ " " + i+"%" );
				Splash.Loadbar.setValue(i);
				
						
				
					}
					
				
				
				} catch (Exception e) {
					e.printStackTrace();
				}
				new Splach().setVisible(false);
				log.frmLoginPage.setVisible(true);
			Splash.dispose();
				
				
			}
		
	}
	

