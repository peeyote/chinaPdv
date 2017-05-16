package pdvBD;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import pdv.telaPdv;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;

public class telaConfigBD extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField txtCaminho;
	JButton btnTesta,btnOk;
	public Connection con = null;
	public Statement stm = null;

	public telaConfigBD() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 360, 190);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtCaminho = new JTextField();
		txtCaminho.setBounds(92, 38, 220, 20);
		contentPane.add(txtCaminho);
		txtCaminho.setColumns(10);
		
		JLabel lblCaminho = new JLabel("Caminho");
		lblCaminho.setBounds(36, 41, 46, 14);
		contentPane.add(lblCaminho);
		
		btnTesta = new JButton("Testa");
		btnTesta.addActionListener(this);
		btnTesta.setBounds(52, 94, 89, 23);
		contentPane.add(btnTesta);
		
		btnOk = new JButton("OK");
		btnOk.addActionListener(this);
		btnOk.setBounds(168, 94, 89, 23);
		contentPane.add(btnOk);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnTesta){
			try {

				Class.forName("org.firebirdsql.jdbc.FBDriver");
				con = DriverManager.getConnection("jdbc:firebirdsql:localhost/3050:"+txtCaminho.getText()+"", "SYSDBA",
						"masterkey");
				stm = con.createStatement();
				//se conectado com sucesso
				
			} catch (Exception er) {
				System.out.println("Não foi possível conectar ao banco !" + er.getMessage());
				
			}
			
		}else if(e.getSource()==btnOk){
			
		}
	}
}
