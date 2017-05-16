package pdv.telaPdvMenus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

import pdv.mandaComandos;
import pdv.recebeSaida;

import javax.swing.JButton;

public class menuSat extends JInternalFrame implements ActionListener {
	private JTextPane txtRetorno;
	public JButton btnConsultarStatus; 
	JScrollPane scrl;
	public mandaComandos envia;
	public recebeSaida saida;
	private JButton btnConsultarSat;
	public menuSat() {
		setResizable(true);
		this.setTitle("Menu sat");
		this.setClosable(true);
		setBounds(0, 0, 554, 550);
		getContentPane().setLayout(null);
		
		
		txtRetorno = new JTextPane();
		scrl = new JScrollPane(txtRetorno,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrl.setBounds(0,288,528,222);
		getContentPane().add(scrl);
		btnConsultarStatus = new JButton("Consultar status");
		btnConsultarStatus.setBounds(197, 24, 157, 23);
		btnConsultarStatus.addActionListener(this);
		getContentPane().add(btnConsultarStatus);
		
		btnConsultarSat = new JButton("Consultar Sat");
		btnConsultarSat.setBounds(197, 58, 157, 23);
		getContentPane().add(btnConsultarSat);
		btnConsultarSat.addActionListener(this);
		btnConsultarSat.grabFocus();
		try {
			this.setSelected(true);
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnConsultarStatus){
		String consultarStatus= "SAT.ConsultarStatusOperacional";
		System.out.println(consultarStatus);
		new mandaComandos(consultarStatus);
		saida = new recebeSaida();
		txtRetorno.setText(saida.saida1);
		}else
		if(e.getSource()==btnConsultarSat){
			String consultarSat= "SAT.ConsultarSAT";
			System.out.println(consultarSat);
			new mandaComandos(consultarSat);
			saida = new recebeSaida();
			txtRetorno.setText(saida.saida1);	
		}
	}
}		
