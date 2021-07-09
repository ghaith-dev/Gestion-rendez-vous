package Test;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JRadioButton;
import javax.security.auth.kerberos.KeyTab;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.security.KeyStore;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuItem;

public class SessionFrame extends JFrame {

	private JPanel contentPane;
	private JTextField mtrtxt;
	private JTextField mdptxt;
	private JTable SessionTab;
static int mtr,id;
static String mdp,prv;
 Sessions sess;

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
					SessionFrame frame = new SessionFrame();
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
	public SessionFrame() {
		setTitle("Sessions");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 775, 276);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		mtrtxt = new JTextField();
	
		mtrtxt.setBounds(84, 31, 213, 20);
		contentPane.add(mtrtxt);
		mtrtxt.setColumns(10);
		
		mdptxt = new JTextField();
		mdptxt.setColumns(10);
		mdptxt.setBounds(84, 79, 213, 20);
		contentPane.add(mdptxt);
		
		JRadioButton admin = new JRadioButton("Admin");
		admin.setBounds(84, 121, 75, 23);
		admin.setActionCommand("Admin");
		
		contentPane.add(admin);
		
		JRadioButton sec = new JRadioButton("Secretaire ");
		sec.setBounds(168, 121, 109, 23);
		sec.setActionCommand("Admin");
		contentPane.add(sec);
		
		ButtonGroup group =new ButtonGroup();
		group.add(admin);
		group.add(sec);
		JButton btn = new JButton("Valider");
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
if(mtrtxt.getText().equals("") || mdptxt.getText().equals("") || group.isSelected(group.getSelection())==false) {
	JOptionPane.showMessageDialog(null, "Erreur De Saisie");
}else {
					

			
				Sessions ses = new Sessions(Integer.parseInt(mtrtxt.getText()),mdptxt.getText(),group.getSelection().getActionCommand()); 
			
				try {
					ses.Ajouter();
					JOptionPane.showMessageDialog(null, "Session Modifier Avec Succés");
					UpdateSession();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
}
			}		
		});
		btn.setBounds(101, 180, 89, 23);
		contentPane.add(btn);
		
		JLabel lblNewLabel = new JLabel("Matricule");
		lblNewLabel.setBounds(10, 34, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblMotDePasse = new JLabel("Mot De Passe");
		lblMotDePasse.setBounds(10, 82, 75, 14);
		contentPane.add(lblMotDePasse);
		
		JLabel lblNewLabel_1_1 = new JLabel("Privilege");
		lblNewLabel_1_1.setBounds(10, 125, 46, 14);
		contentPane.add(lblNewLabel_1_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(335, 0, 424, 237);
		contentPane.add(scrollPane);
		
		SessionTab = new JTable();
		scrollPane.setViewportView(SessionTab);
		
		JPopupMenu popupMenu = new JPopupMenu();
		addPopup(SessionTab, popupMenu);
		SessionFrame fr = this;
		JMenuItem Modifiersess = new JMenuItem("Modifier");
		Modifiersess.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				id=Integer.parseInt(SessionTab.getValueAt(SessionTab.getSelectedRow(), 0).toString());
			 mtr=Integer.parseInt(SessionTab.getValueAt(SessionTab.getSelectedRow(), 1).toString());
			mdp= SessionTab.getValueAt(SessionTab.getSelectedRow(), 2).toString();
			prv= SessionTab.getValueAt(SessionTab.getSelectedRow(), 3).toString();
			ModifierSession md = new ModifierSession(fr);
			 md.setVisible(true);
			}
		});
		popupMenu.add(Modifiersess);
		
		JMenuItem SupprimerSess = new JMenuItem("Supprimer");
		SupprimerSess.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int x = JOptionPane.showConfirmDialog(null, "voulez vous vraiment supprimer ?");
				if(x==0) {
				try {
				Sessions sess =new Sessions(Integer.parseInt(SessionTab.getValueAt(SessionTab.getSelectedRow(), 1).toString()),SessionTab.getValueAt(SessionTab.getSelectedRow(), 2).toString(),SessionTab.getValueAt(SessionTab.getSelectedRow(), 3).toString());
				sess.id=Integer.parseInt(SessionTab.getValueAt(SessionTab.getSelectedRow(), 0).toString());
				sess.Supprimer();
				JOptionPane.showMessageDialog(null,"Session Supprimer avec Succés");
				UpdateSession();
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null,"Erreur!!");
				}
			}
			}
		});
		popupMenu.add(SupprimerSess);
		UpdateSession();
		mtrtxt.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyChar()>='0' && e.getKeyChar()<='9' || e.getKeyCode()==KeyEvent.VK_BACK_SPACE) {
					btn.setEnabled(true);
				}else {
					
					JOptionPane.showMessageDialog(null, "Erreur De Saisie,la Matricule doit etre Numérique");
					 btn.setEnabled(false);
				}
					
			}
		});
	}
	public void UpdateSession() {
		DataBaseConnection con =  new DataBaseConnection("SELECT * FROM login");
		
		SessionTab.setModel(DbUtils.resultSetToTableModel(con.rs));
		
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
}
