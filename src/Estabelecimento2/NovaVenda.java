package Estabelecimento2;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.DecimalFormat;
import java.text.ParseException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

@SuppressWarnings("serial")
public class NovaVenda extends JPanel {

	private JLabel jlCodProduto;
	private JTextField jtfCodProduto;
	private JLabel jlCodVendedor;
	private JTextField jtfCodVendedor;
	private JLabel jlCodCliente;
	private JFormattedTextField jtfCodCliente;
	private JLabel jlCodDesconto;
	private JTextField jtfCodDesconto;

	private JButton jbAdicionar;
	private JButton jbConfirmar;
	private JButton jbCancelar;

	private JLabel jlSubTotal;
	private JTextArea jtaSubTotal;
	private JLabel jlDesconto;
	private JTextArea jtaDesconto;
	private JLabel jlTotal;
	private JTextArea jtaTotal;

	double somaTotal = 0;
	double somaSubtotal = 0;
	double desconto = 0;

	DecimalFormat df = new DecimalFormat("0.00");

	private JScrollPane scrollTable;
	private JTable jtableProd;

	public NovaVenda() {
		iniciarComponentes();
		eventosBotoes();
	}

	private void iniciarComponentes() {
		//definicoes da pagina de venda
		setLayout(null);
		setBounds(10, 10, 1300, 495);
		setBackground(new Color(255, 250, 205));

		//definindo os estados do produto
		jlCodProduto = new JLabel("Código produto: ");
		add(jlCodProduto);
		jlCodProduto.setFont(new Font("Segoe UI Light", Font.ITALIC, 20));
		jlCodProduto.setBounds(10, 20, 150, 40);

		jtfCodProduto = new JTextField();
		add(jtfCodProduto);
		jtfCodProduto.setFont(new Font("Segoe UI Light", Font.BOLD, 20));
		jtfCodProduto.setBorder(BorderFactory.createMatteBorder(1, 1, 2, 1, Color.black));
		jtfCodProduto.setBackground(new Color(255, 250, 205));
		jtfCodProduto.setBounds(10, 60, 230, 25);

		//definindo os estados do cliente
		jlCodCliente = new JLabel("CPF do cliente: ");
		add(jlCodCliente);
		jlCodCliente.setFont(new Font("Segoe UI Light", Font.ITALIC, 20));
		jlCodCliente.setBounds(270, 20, 150, 40);

		try {

			MaskFormatter maskCpf = new MaskFormatter("###.###.###-##");
			jtfCodCliente = new JFormattedTextField(maskCpf);
			add(jtfCodCliente);
			jtfCodCliente.setFont(new Font("Segoe UI Light", Font.BOLD, 20));
			jtfCodCliente.setBorder(BorderFactory.createMatteBorder(1, 1, 2, 1, Color.black));
			jtfCodCliente.setBackground(new Color(255, 250, 205));
			jtfCodCliente.setBounds(270, 60, 230, 25);

			//definindo os estados do vendedor
			jlCodVendedor = new JLabel("CPF do vendedor: ");
			add(jlCodVendedor);
			jlCodVendedor.setFont(new Font("Segoe UI Light", Font.ITALIC, 20));
			jlCodVendedor.setBounds(530, 20, 150, 40);

			jtfCodVendedor = new JTextField(TelaInicial.getLembraCpf());
			add(jtfCodVendedor);
			jtfCodVendedor.setFont(new Font("Segoe UI Light", Font.BOLD, 20));
			jtfCodVendedor.setBorder(BorderFactory.createMatteBorder(1, 1, 2, 1, Color.black));
			jtfCodVendedor.setBackground(new Color(255, 250, 205));
			jtfCodVendedor.setBounds(530, 60, 230, 25);
			jtfCodVendedor.setEditable(false);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		//definindo os estados do desconto
		jlCodDesconto = new JLabel("Desconto: ");
		add(jlCodDesconto);
		jlCodDesconto.setFont(new Font("Segoe UI Light", Font.ITALIC, 20));
		jlCodDesconto.setBounds(790, 20, 150, 40);

		jtfCodDesconto = new JTextField("0");
		add(jtfCodDesconto);
		jtfCodDesconto.setFont(new Font("Segoe UI Light", Font.BOLD, 20));
		jtfCodDesconto.setBorder(BorderFactory.createMatteBorder(1, 1, 2, 1, Color.black));
		jtfCodDesconto.setBackground(new Color(255, 250, 205));
		jtfCodDesconto.setBounds(790, 60, 230, 25);

		jtfCodDesconto.addFocusListener(new FocusListener() {
			public void focusLost(FocusEvent arg0) {
				// TODO Auto-generated method stub
				if(jtfCodDesconto.getText().isEmpty()){
					jtfCodDesconto.setText("0");
				}
			}

			@Override
			public void focusGained(FocusEvent arg0) {
				// TODO Auto-generated method stub
				if(jtfCodDesconto.getText().equals("0")){
					jtfCodDesconto.setText("");
				}
			}
		});


		//defindo botao adicionar
		jbAdicionar = new JButton("ADICIONAR");
		add(jbAdicionar);
		jbAdicionar.setFont(new Font("Segoe UI Light", Font.PLAIN, 15));
		jbAdicionar.setBorder((Border) javax.swing.BorderFactory.createLineBorder(Color.white, 0));
		jbAdicionar.setBackground(new Color(54, 54, 54)); 
		jbAdicionar.setForeground(Color.white);
		jbAdicionar.setBounds(1070, 45, 200, 40);

		//definindo os estados do subtotal
		jlSubTotal = new JLabel("Subtotal: ");
		add(jlSubTotal);
		jlSubTotal.setFont(new Font("Segoe UI Light", Font.ITALIC, 20));
		jlSubTotal.setBounds(880, 150, 150, 40);

		jtaSubTotal = new JTextArea();
		add(jtaSubTotal);
		jtaSubTotal.setFont(new Font("Segoe UI Light", Font.BOLD, 20));
		jtaSubTotal.setEditable(false);
		jtaSubTotal.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.black));
		jtaSubTotal.setBackground(new Color(255, 250, 205));
		jtaSubTotal.setBounds(970, 155, 200, 40);

		//definindo os estados do desconto
		jlDesconto = new JLabel("Desconto: ");
		add(jlDesconto);
		jlDesconto.setFont(new Font("Segoe UI Light", Font.ITALIC, 20));
		jlDesconto.setBounds(880, 215, 150, 40);

		jtaDesconto = new JTextArea();
		add(jtaDesconto);
		jtaDesconto.setFont(new Font("Segoe UI Light", Font.BOLD, 20));
		jtaDesconto.setEditable(false);
		jtaDesconto.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.black));
		jtaDesconto.setBackground(new Color(255, 250, 205));
		jtaDesconto.setBounds(970, 220, 100, 25);


		//definindo os estados do total
		jlTotal = new JLabel("TOTAL: ");
		add(jlTotal);
		jlTotal.setForeground(Color.red);
		jlTotal.setFont(new Font("Segoe UI Light", Font.BOLD, 25));
		jlTotal.setBounds(880, 290, 150, 40);

		jtaTotal = new JTextArea();
		add(jtaTotal);
		jtaTotal.setForeground(Color.red);
		jtaTotal.setFont(new Font("Segoe UI Light", Font.BOLD, 25));
		jtaTotal.setEditable(false);
		jtaTotal.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.black));
		jtaTotal.setBackground(new Color(255, 250, 205));
		jtaTotal.setBounds(970, 293, 130, 30);

		//defindo os estados do botao confirmar
		jbConfirmar = new JButton("CONFIRMAR");
		add(jbConfirmar);
		jbConfirmar.setFont(new Font("Segoe UI Light", Font.PLAIN, 15));
		jbConfirmar.setBorder((Border) javax.swing.BorderFactory.createLineBorder(Color.white, 0));
		jbConfirmar.setBackground(new Color(54, 54, 54)); 
		jbConfirmar.setForeground(Color.white);
		jbConfirmar.setBounds(880, 380, 200, 40);
		jbConfirmar.setEnabled(false);

		//defindo os estados do botao cancelar
		jbCancelar = new JButton("CANCELAR");
		add(jbCancelar);
		jbCancelar.setFont(new Font("Segoe UI Light", Font.PLAIN, 15));
		jbCancelar.setBorder((Border) javax.swing.BorderFactory.createLineBorder(Color.white, 0));
		jbCancelar.setBackground(new Color(54, 54, 54)); 
		jbCancelar.setForeground(Color.white);
		jbCancelar.setBounds(1100, 380, 200, 40);

		//tabela com produtos escolhidos para venda
		jtableProd = new JTable();
		jtableProd.setModel(new DefaultTableModel(
				new Object [][] {
				},
				new String [] {"Código","Nome","Tamanho","Valor"}
				));

		DefaultTableModel modelo = (DefaultTableModel) jtableProd.getModel();
		ComandosSql comandos = new ComandosSql();

		for(Produtos p: comandos.procurandoProduto(jtfCodProduto.getText())) {
			modelo.addRow(new Object[] {
					p.getScodigo(),
					p.getSnome(),
					p.getStamanho(),
					p.getSvalor()
			});
		}

		jtableProd.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		jtableProd.getColumnModel().getColumn(0).setPreferredWidth(210);
		jtableProd.getColumnModel().getColumn(1).setPreferredWidth(210);
		jtableProd.getColumnModel().getColumn(2).setPreferredWidth(210);
		jtableProd.getColumnModel().getColumn(3).setPreferredWidth(210);
		jtableProd.setEnabled(false);
		jtableProd.setRowHeight(50);
		jtableProd.setFont(new Font("Segoe UI Light", Font.BOLD, 18));
		scrollTable = new JScrollPane();
		scrollTable.setViewportView(jtableProd);
		add(scrollTable);
		scrollTable.setBounds(10, 100, 850, 390);

		//jlabel da marca
		JLabel lrImports = new JLabel("Copyright © 2018 - LORAN HENRIQUE - Todos os direitos reservados");
		add(lrImports);
		lrImports.setFont(new Font("Segoe UI Light", Font.ITALIC, 13));
		lrImports.setForeground(Color.black);
		lrImports.setBounds(900, 410, 400, 100);

	}

	private void eventosBotoes() {

		jbAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				jbConfirmar.setEnabled(true);
				
				DefaultTableModel modelo = (DefaultTableModel) jtableProd.getModel();
				ComandosSql comandos = new ComandosSql();

				for(Produtos p: comandos.procurandoProduto(jtfCodProduto.getText().toUpperCase())) {
					modelo.addRow(new Object[] {
							p.getScodigo(),
							p.getSnome(),
							p.getStamanho(),
							p.getSvalor()
					});
					somaSubtotal = somaSubtotal + p.getSvalor();

				}
				desconto = Double.parseDouble(jtfCodDesconto.getText());
				somaTotal = somaSubtotal - desconto;
				jtfCodProduto.setText("");
				jtfCodDesconto.setText("0");
				jtfCodCliente.setEditable(false);
				jtaSubTotal.setText("");
				jtaDesconto.setText("");
				jtaTotal.setText("");

				jtaSubTotal.append("R$ " + df.format(somaSubtotal));
				jtaDesconto.append("R$ "+ desconto);
				jtaTotal.append("R$ "+ df.format(somaTotal));

			}
		});

		jbConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CalculaTroco(somaTotal).frameTroco.setVisible(true);
				//tabela com produtos escolhidos para venda

				jtfCodProduto.setText("");
				jtfCodDesconto.setText("0");
				jtfCodCliente.setText("");
				jtfCodCliente.setEditable(true);
				jtaSubTotal.setText("");
				jtaDesconto.setText("");
				jtaTotal.setText("");
				somaSubtotal = 0;
				desconto = 0;
				somaTotal = 0;

				DefaultTableModel modelo = (DefaultTableModel) jtableProd.getModel();

				int x = modelo.getRowCount();
				for(int a = 0; a < x; a++){
					modelo.removeRow(0);
				}

				jbConfirmar.setEnabled(false);
				
			}
		});
		
		jbCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				JOptionPane.showMessageDialog(null, "Compra cancelada com sucesso!");
				//tabela com produtos escolhidos para venda

				jtfCodProduto.setText("");
				jtfCodDesconto.setText("0");
				jtfCodCliente.setText("");
				jtfCodCliente.setEditable(true);
				jtaSubTotal.setText("");
				jtaDesconto.setText("");
				jtaTotal.setText("");
				somaSubtotal = 0;
				desconto = 0;
				somaTotal = 0;

				DefaultTableModel modelo = (DefaultTableModel) jtableProd.getModel();

				int x = modelo.getRowCount();
				for(int a = 0; a < x; a++){
					modelo.removeRow(0);
				}

				jbConfirmar.setEnabled(false);
			}
		});
	}
}
