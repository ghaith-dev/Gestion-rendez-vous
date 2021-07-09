package Test;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ModifierSession extends JFrame {

	private JPanel contentPane;
	static SessionFrame sess;
	private JTextField mtrtxt;
	private JTextField mdptxt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModifierSession frame = new ModifierSession(sess);
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
	public ModifierSession(SessionFrame sess) {
		this.sess=sess;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 339, 253);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		mtrtxt = new JTextField();
	
		mtrtxt.setColumns(10);
		mtrtxt.setBounds(84, 32, 213, 20);
		contentPane.add(mtrtxt);
		
		mdptxt = new JTextField();
		mdptxt.setColumns(10);
		mdptxt.setBounds(84, 80, 213, 20);
		contentPane.add(mdptxt);
		
		JLabel lblNewLabel = new JLabel("Matricule");
		lblNewLabel.setBounds(10, 35, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblMotDePasse = new JLabel("Mot De Passe");
		lblMotDePasse.setBounds(10, 83, 75, 14);
		contentPane.add(lblMotDePasse);
		
		JLabel lblNewLabel_1_1 = new JLabel("Privilege");
		lblNewLabel_1_1.setBounds(10, 126, 46, 14);
		contentPane.add(lblNewLabel_1_1);
		
		JRadioButton admin = new JRadioButton("Admin");
		admin.setActionCommand("Admin");
		admin.setBounds(84, 122, 75, 23);
		admin.setActionCommand("Admin");
		contentPane.add(admin);
		
		JRadioButton sec = new JRadioButton("Secretaire ");
		sec.setActionCommand("Admin");
		sec.setBounds(168, 122, 109, 23);
		sec.setActionCommand("Secretaire ");
		contentPane.add(sec);
		ButtonGroup grp = new ButtonGroup();
		grp.add(admin);
		grp.add(sec);
		
		JButton btn = new JButton("Valider");
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Sessions se = new Sessions(Integer.parseInt(mtrtxt.getText().toString()),mdptxt.getText().toString(),grp.getSelection().getActionCommand().toString());
				se.id=sess.id;
				se.Modifier();
				JOptionPane.showMessageDialog(null, "Session Ajouter Avec Succés");
				sess.UpdateSession();
				dispose();
			}
		});
		btn.setBounds(110, 177, 89, 23);
		contentPane.add(btn);
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
		mtrtxt.setText(String.valueOf(sess.mtr));
		mdptxt.setText(sess.mdp);
		if(sess.prv.equals("Admin") ){
			admin.setSelected(true);
		}else {
			
			sec.setSelected(true);
			}
		}
}
