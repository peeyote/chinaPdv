package pdv;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import pdv.telaPdvMenus.menuSat;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.color.ColorSpace;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyVetoException;
import java.awt.SystemColor;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JDesktopPane;

public class telaPdv extends JFrame implements KeyListener {

	private JPanel contentPane;
	private JPanel panelProd;
	private JTextPane txtPainelProdutos;
	public JTextField txtCodProduto;
	private JLabel lblCdigoDoProduto;
	private JTextField txtQuantidade;
	private JLabel lblQuantidade;
	private JTextField txtVlrUni;
	private JLabel lblValorUnitario;
	private JLabel lblValorTotal;
	private JTextField txtVlrTotal;
	private JLabel lblSubTotal;
	private JTextField txtSubTotal;
	private JPanel panel;
	private JLabel lblAviso;
	public JDesktopPane desktopPane;
	menuSat sat;

	public telaPdv() {
		desenhaTela();

	}

	public void desenhaTela() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1024, 768);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		contentPane.setLayout(null);
		desktopPane = new JDesktopPane();
		desktopPane.setBackground(new Color(0, 0, 0, 0));
		desktopPane.setBounds(231, 97, 554, 550);
		desktopPane.setVisible(false);
		contentPane.add(desktopPane);
		// PANEL PRODUTOS
		panelProd = new JPanel();
		panelProd.setBackground(new Color(46, 139, 87));
		panelProd.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
		panelProd.setBounds(267, 30, 705, 105);

		contentPane.add(panelProd);

		JLabel lblProdutoAtual = new JLabel("New label");
		lblProdutoAtual.setFont(new Font("Narkisim", Font.PLAIN, 66));
		panelProd.add(lblProdutoAtual);

		txtPainelProdutos = new JTextPane();
		txtPainelProdutos.setBounds(71, 160, 450, 391);
		contentPane.add(txtPainelProdutos);

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
		lblValorTotal.setBounds(580, 404, 169, 14);
		contentPane.add(lblValorTotal);

		txtVlrTotal = new JTextField();
		txtVlrTotal.setHorizontalAlignment(SwingConstants.CENTER);
		txtVlrTotal.setFont(new Font("Narkisim", Font.PLAIN, 40));
		txtVlrTotal.setColumns(10);
		txtVlrTotal.setBounds(580, 419, 392, 47);
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
		panel.setBounds(71, 577, 463, 82);
		contentPane.add(panel);

		lblAviso = new JLabel("New label");
		panel.add(lblAviso);

		txtCodProduto.addKeyListener(this);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_C) {
			desktopPane.setVisible(true);
			sat = new menuSat();
			sat.setVisible(true);
			desktopPane.add(sat);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}
}
