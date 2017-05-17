package pdvConfig;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import pdv.excluirSaida;
import pdv.mandaComandos;
import pdv.recebeSaida;

import javax.swing.JTabbedPane;
import javax.swing.Box;
import javax.swing.JButton;
import java.awt.Component;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;

public class telaConfig extends JFrame implements ActionListener{

	private JPanel contentPane;
	JTextPane textPane;
	JButton btnConsultarStatus;

	public telaConfig() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 35, 764, 516);
		contentPane.add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("New tab", null, panel, null);
		
		JPanel pnSat = new JPanel();
		tabbedPane.addTab("SAT", null, pnSat, null);
		pnSat.setLayout(null);
		
		Box verticalBox = Box.createVerticalBox();
		verticalBox.setBounds(10, 270, 160, 207);
		pnSat.add(verticalBox);
		
		btnConsultarStatus = new JButton("Consultar Status");
		btnConsultarStatus.addActionListener(this);
		verticalBox.add(btnConsultarStatus);
		
		textPane = new JTextPane();
		
		JScrollPane scrollPane = new JScrollPane(textPane);
		scrollPane.setBounds(206, 270, 211, 207);
		pnSat.add(scrollPane);
		this.setVisible(true);
		this.setTitle("Configurações de caixa");
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnConsultarStatus){
			new mandaComandos("SAT.Inicializar");
			new mandaComandos("SAT.ConsultarSAT");
			recebeSaida saida = new recebeSaida();
			textPane.setText(saida.recebeSaida());
			
		}
	}
}
