package pdv;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import pdvBD.ConexaoBDSqlite;
import pdvConfig.telaConfig;
import pdvParametros.teclasPermitidas;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.color.ColorSpace;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyVetoException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.Scanner;
import java.awt.SystemColor;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JDesktopPane;
import javax.swing.border.EtchedBorder;

public class telaPdv extends JFrame implements KeyListener {

	private JPanel contentPane, panelProd, panel;
	private JLabel lblCdigoDoProduto, lblQuantidade, lblValorUnitario, lblValorTotal, lblSubTotal, lblProdutoAtual;
	private JTextField txtQuantidade, txtCodProduto, txtVlrUni, txtVlrTotal, txtSubTotal;
	private JTextPane txtPainelProdutos;
	StringBuilder cupom = new StringBuilder();
	StringBuilder cupomImprime = new StringBuilder();
	StringBuilder CFe = new StringBuilder();
	double vlrSub = 0;
	double vlrEntrada = 0;
	double vlrCupomAPagar;
	int idProd = 1;
	private JTextField txtVlrEntrada;
	private JLabel lblAviso;
	private JTextField txtVlrTroco;
	private JLabel lblTroco;

	public telaPdv() {
		desenhaTela();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_C) {
			if (txtCodProduto.hasFocus()) {
				lblAviso.setText("Tela config...");
				telaConfig conf = new telaConfig();
				this.dispose();
			}
		} else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if (txtCodProduto.hasFocus()) {
				adicionarProduto();
				txtQuantidade.setText("1");
				lblAviso.setText("Em venda...");
				
				txtCodProduto.setText("");
				
				
			} else if (txtVlrEntrada.hasFocus()) {
				txtVlrEntrada.setEditable(false);
				vlrEntrada = Double.parseDouble(txtVlrEntrada.getText());
				vlrCupomAPagar = vlrEntrada - vlrSub;
				txtVlrTroco.setText("" + vlrCupomAPagar);
				
				imprimeCupom();
			}

		} else if (e.getKeyCode() == KeyEvent.VK_MULTIPLY) {
			if (txtCodProduto.hasFocus()) {
				String qnt = txtCodProduto.getText();
				txtQuantidade.setText(qnt);
				txtCodProduto.setText("");
			}
		} else if (e.getKeyCode() == KeyEvent.VK_F) {
			if (txtCodProduto.hasFocus()) {
				finalizaCupom();
			}
		}
	}

	private void imprimeCupom() {
		CFe.append("ESCPOS.imprimirlinha(\"</fn></ce><n><e>CUPOM</e></n>\") \n");
		CFe.append("ESCPOS.imprimirlinha(\"</zera>\")\n");
		CFe.append("ESCPOS.imprimirlinha(\"<ce>Nome do estabelecimento</ce></e>\") \n");
		CFe.append("ESCPOS.imprimirlinha(\"</fn><ce>Endereço teste, 101 - Bairro italost</ce>\")\n");
		CFe.append("ESCPOS.imprimirlinha(\"</fn><ce>Neverland terra do nunca, CEP 13.015-000</ce>\")\n");
		CFe.append("ESCPOS.imprimirlinha(\"CNPJ: 00.000.000/0000-00\")\n");
		CFe.append("ESCPOS.imprimirlinha(\"</fn></n>IE: 00.000.000.000\")\n");
		CFe.append("ESCPOS.imprimirlinha(\"</fn></n>IM 0.000.000-0\")\n");
		CFe.append("ESCPOS.imprimirlinha(\"</linha_dupla>\")\n");
		CFe.append("ESCPOS.imprimirlinha(\"</zera>\")\n");
		CFe.append("ESCPOS.imprimirlinha(\"ITEM CÓDIGO      DESCRIÇÃO     QTD VL.UN ST VL.ITEM\")\n");
		CFe.append("ESCPOS.imprimirlinha(\"</linha_simples>\")\n");
		CFe.append(cupomImprime.toString());
		new mandaComandos(CFe.toString());
		//new mandaComandos(cupomImprime.toString());
			
	}

	public void criaCupom() {
		FileWriter arquivo;
		try {
			arquivo = new FileWriter(new File("C:/cupom.ini"));
			arquivo.write(CFe.toString());
			arquivo.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void finalizaCupom() {
		lblAviso.setText("Digite o valor: ");
		txtVlrEntrada.grabFocus();
		txtVlrEntrada.setEditable(true);

		/**
		 * PARTE PARA EMISSÃO DE CFE AINDA NÃO IMPLEMENTADA
		 */

		/*
		 * CFe.append("[infCFe]\n\nversao=0.06\n\n"); CFe.append(
		 * "[Identificacao]\n\nCNPJ=11111111111111\n\nsignAC=11111111111111\n\nnumeroCaixa=1\n\n"
		 * ); CFe.append(
		 * "[Emitente]\n\nCNPJ=11111111111111\n\nIE=111111111111\n\nIM=\n\nindRatISSQN=S\n\ncRegTrib=3\n\n"
		 * ); CFe.append(
		 * "[Destinatario]\n\nCNPJCPF=11111111111111\n\nxNome=Joao\n\n[Entrega]\n\nxLgr=Rua Cel. Aureliano de Camargo\n\nnro=973\n\nxCpl=\n\nxBairro=Centro\n\nxMun=Tatui\n\nUF=SP\n\n"
		 * ); CFe.append(
		 * "[Produto001]\n\ncProd=1189\n\ninfAdProd=Teste de Produto\n\ncEAN=\n\nxProd=OVO VERMELHO\n\nNCM=04072100\n\nCFOP=5102\n\nuCom=DZ\n\nCombustivel=0\n\nqCom=510\n\nvUnCom=2,70\n\nindRegra=A\n\nvDesc=0\n\nvOutro=0\n\nvItem12741=137,00\n\n"
		 * ); CFe.
		 * append("[ObsFiscoDet001001]\n\nxCampoDet=Teste\n\nxTextoDet=Texto Teste\n\n"
		 * ); CFe.append(
		 * "[ICMS001]\n\nOrig=0\n\nCST=40\n\n[PIS001]\n\nCST=01\n\n[COFINS001]\n\nCST=01\n\n"
		 * ); CFe.append(
		 * "[Total]\n\nvCFeLei12741=137,00\n\n[DescAcrEntr]\n\nvDescSubtot=7,00\n\n[Pagto001]\n\ncMP=01\n\nvMP=1400\n\n"
		 * ); CFe.
		 * append("[DadosAdicionais]\n\ninfCpl=Teste emissao CFe/SAT\n\n[ObsFisco001]\n\n"
		 * ); CFe.append("xCampo=ObsFisco 1\n\nxTexto=Teste ObsFisco 1\")");
		 * 
		 * System.out.println(CFe);
		 * 
		 * criaCupom(); mandaComandos envia = new
		 * mandaComandos("SAT.CriarEnviarCFe(C:/cupom.ini)"); // new //
		 * mandaComandos(
		 * "SAT.ImprimirExtratoVenda(\"C:\\ACBrMonitorPLUS\\Enviado\\11111111111111\\201705\\001-000000-satcfe.xml\")"
		 * ); // new // mandaComandos(
		 * "SAT.ImprimirExtratoVenda(\"C:\\ACBrMonitorPLUS\\Enviado\\11111111111111\\201705\\AD20170518175720-725331-env.xml\")"
		 * ); // new // mandaComandos(
		 * "SAT.ImprimirExtratoVenda(c:/AD20170518175720725331env.xml)");
		 */
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	private void adicionarProduto() {
		ConexaoBDSqlite con = new ConexaoBDSqlite();
		try {
			con.conecta();
			// CONSULTA PRODUTO NO BANCO
			con.exec("select * from produtos where codigo_prod=" + txtCodProduto.getText());
			String nomeProduto = con.resultset.getString("NOME_PROD");
			String valorProduto = con.resultset.getString("VALOR_PROD");
			lblProdutoAtual.setText(nomeProduto);
			txtVlrUni.setText(valorProduto);
			cupomImprime.append("ESCPOS.imprimirlinha(\"</fn></n>"+idProd+" "+txtCodProduto.getText().toString()+" "+nomeProduto+"  "+txtQuantidade.getText().toString()+"    "+txtVlrUni.getText().toString()+"  F1    "+txtVlrTotal.getText().toString()+"\")\n");
			
			cupom.append(idProd);
			cupom.append("  ");
			cupom.append(txtCodProduto.getText().toString());
			cupom.append("  ");
			cupom.append(nomeProduto);
			cupom.append("                ");
			cupom.append(valorProduto);
			cupom.append("           ");
			cupom.append(txtQuantidade.getText().toString());
			cupom.append("  ");
			cupom.append("\n");
			Double vlrTotal = Double.parseDouble(txtVlrUni.getText());
			Double vlrQnt = Double.parseDouble(txtQuantidade.getText());
			Double vlrTotalReal = vlrQnt * vlrTotal;
			txtVlrTotal.setText(vlrTotalReal.toString());
			txtPainelProdutos.setText(cupom.toString());

			vlrSub += vlrTotalReal;

			txtSubTotal.setText("" + vlrSub);

			idProd = idProd + 1;
			con.desconecta();
		} catch (Exception e) {
			lblProdutoAtual.setText("Produto n\u00E3o cadastrado !");
		}

	}

	public void desenhaTela() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		setBounds(0, 0, 1024, 768);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		contentPane.setLayout(null);
		// PANEL PRODUTOS
		panelProd = new JPanel();
		panelProd.setBackground(new Color(46, 139, 87));
		panelProd.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
		panelProd.setBounds(267, 27, 705, 105);

		contentPane.add(panelProd);

		lblProdutoAtual = new JLabel("");
		lblProdutoAtual.setFont(new Font("KodchiangUPC", Font.BOLD, 66));
		panelProd.add(lblProdutoAtual);

		txtPainelProdutos = new JTextPane();
		JScrollPane scrl = new JScrollPane(txtPainelProdutos);
		scrl.setBounds(71, 160, 450, 391);
		contentPane.add(scrl);

		txtCodProduto = new JTextField();
		txtCodProduto.setHorizontalAlignment(SwingConstants.CENTER);
		txtCodProduto.setFont(new Font("Narkisim", Font.PLAIN, 40));
		txtCodProduto.setBounds(580, 175, 392, 47);
		contentPane.add(txtCodProduto);
		txtCodProduto.setColumns(10);

		lblCdigoDoProduto = new JLabel("C\u00F3digo do produto");
		lblCdigoDoProduto.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 15));
		lblCdigoDoProduto.setBounds(580, 160, 169, 14);
		contentPane.add(lblCdigoDoProduto);

		txtQuantidade = new JTextField();
		txtQuantidade.setText("1");
		txtQuantidade.setHorizontalAlignment(SwingConstants.CENTER);
		txtQuantidade.setFont(new Font("Narkisim", Font.PLAIN, 40));
		txtQuantidade.setColumns(10);
		txtQuantidade.setBounds(580, 253, 392, 47);
		contentPane.add(txtQuantidade);

		lblQuantidade = new JLabel("Quantidade");
		lblQuantidade.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 15));
		lblQuantidade.setBounds(580, 233, 169, 14);
		contentPane.add(lblQuantidade);

		txtVlrUni = new JTextField();
		txtVlrUni.setHorizontalAlignment(SwingConstants.CENTER);
		txtVlrUni.setFont(new Font("Narkisim", Font.PLAIN, 40));
		txtVlrUni.setColumns(10);
		txtVlrUni.setBounds(580, 326, 392, 47);
		contentPane.add(txtVlrUni);

		lblValorUnitario = new JLabel("Valor unitario");
		lblValorUnitario.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 15));
		lblValorUnitario.setBounds(580, 311, 169, 14);
		contentPane.add(lblValorUnitario);

		lblValorTotal = new JLabel("Valor total");
		lblValorTotal.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 15));
		lblValorTotal.setBounds(580, 384, 169, 14);
		contentPane.add(lblValorTotal);

		txtVlrTotal = new JTextField();
		txtVlrTotal.setHorizontalAlignment(SwingConstants.CENTER);
		txtVlrTotal.setFont(new Font("Narkisim", Font.PLAIN, 40));
		txtVlrTotal.setColumns(10);
		txtVlrTotal.setBounds(580, 399, 392, 47);
		contentPane.add(txtVlrTotal);

		lblSubTotal = new JLabel("Sub total");
		lblSubTotal.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 15));
		lblSubTotal.setBounds(580, 577, 169, 14);
		contentPane.add(lblSubTotal);

		txtSubTotal = new JTextField();
		txtSubTotal.setForeground(new Color(0, 204, 153));
		txtSubTotal.setHorizontalAlignment(SwingConstants.CENTER);
		txtSubTotal.setFont(new Font("Narkisim", Font.PLAIN, 40));
		txtSubTotal.setColumns(10);
		txtSubTotal.setBounds(580, 592, 392, 67);
		contentPane.add(txtSubTotal);

		panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBackground(SystemColor.textHighlight);
		panel.setBounds(71, 577, 463, 82);
		contentPane.add(panel);

		lblAviso = new JLabel("");
		lblAviso.setFont(new Font("Narkisim", Font.PLAIN, 41));
		panel.add(lblAviso);

		txtVlrEntrada = new JTextField();
		txtVlrEntrada.setDocument(new teclasPermitidas());
		txtVlrEntrada.setHorizontalAlignment(SwingConstants.CENTER);
		txtVlrEntrada.setForeground(Color.BLACK);
		txtVlrEntrada.setFont(new Font("Narkisim", Font.PLAIN, 41));
		txtVlrEntrada.setEditable(false);
		txtVlrEntrada.setColumns(5);
		txtVlrEntrada.setBorder(null);
		txtVlrEntrada.addKeyListener(this);
		txtVlrEntrada.setBackground(SystemColor.textHighlight);
		panel.add(txtVlrEntrada);

		txtVlrUni.setEditable(false);
		txtPainelProdutos.setEditable(false);
		txtVlrTotal.setEditable(false);
		txtQuantidade.setEditable(false);
		txtSubTotal.setEditable(false);

		txtCodProduto.setDocument(new teclasPermitidas());

		txtVlrTroco = new JTextField();
		txtVlrTroco.setHorizontalAlignment(SwingConstants.CENTER);
		txtVlrTroco.setFont(new Font("Narkisim", Font.PLAIN, 40));
		txtVlrTroco.setEditable(false);
		txtVlrTroco.setColumns(10);
		txtVlrTroco.setBounds(580, 492, 392, 47);
		contentPane.add(txtVlrTroco);

		lblTroco = new JLabel("Valor troco");
		lblTroco.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 15));
		lblTroco.setBounds(580, 477, 169, 14);
		contentPane.add(lblTroco);

		txtCodProduto.addKeyListener(this);
		cupom.append("          CUPOM          \n ID COD BARRAS      NOME PRODUTO     VALOR PRODUTO     QNT \n");

	}
}
