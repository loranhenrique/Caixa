package Estabelecimento2;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.DecimalFormat;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

@SuppressWarnings("serial")
public class CalculaTroco extends JFrame {

	private JLabel jlValorRecebido;
	private JTextField jtfValorRecebido;
	private JLabel jlTroco;
	private JTextArea jtaTroco;
	private JLabel jlTotal;
	private JTextArea jtaTotal;
	private JButton jbConfirmar;
	private JButton jbAdicionar;
	
	NovaVenda nvVenda = new NovaVenda();
	double valorRecebido = 0;
	double troco = 0;
	double valorTotal = 0;
	
	DecimalFormat df = new DecimalFormat("0.00");
	
	JFrame frameTroco;
	
	public CalculaTroco(double valorTotal) {
		this.valorTotal = valorTotal;
		iniciarComponentes();
		eventoBotao();
	}

	private void iniciarComponentes() {
		try {
			
			//informações tela da loja
			frameTroco = new JFrame("Calculando troco");
			frameTroco.setLayout(null); 
			frameTroco.setSize(500, 400);
			frameTroco.setResizable(false);
			frameTroco.setLocationRelativeTo(null);
			
			//Primeira panel
			JPanel panelTroco = new JPanel(); 
			panelTroco.setBackground(new Color(255, 250, 205));
			panelTroco.setLayout(null);
			frameTroco.add(panelTroco);
			panelTroco.setBounds(45, 40, 400, 300);
			
			//definindo os estados do valor recebido
			jlValorRecebido = new JLabel("Valor recebido: ");
			panelTroco.add(jlValorRecebido);
			jlValorRecebido.setFont(new Font("Segoe UI Light", Font.ITALIC, 20));
			jlValorRecebido.setBounds(10, 20, 150, 40);

			jtfValorRecebido = new JTextField("0");
			panelTroco.add(jtfValorRecebido);
			jtfValorRecebido.setFont(new Font("Segoe UI Light", Font.BOLD, 20));
			jtfValorRecebido.setBorder(BorderFactory.createMatteBorder(1, 1, 2, 1, Color.black));
			jtfValorRecebido.setBackground(new Color(255, 250, 205));
			jtfValorRecebido.setBounds(10, 60, 230, 25);
			
			jtfValorRecebido.addFocusListener(new FocusListener() {
				public void focusLost(FocusEvent arg0) {
					// TODO Auto-generated method stub
					if(jtfValorRecebido.getText().isEmpty()){
						jtfValorRecebido.setText("0");
					}
				}

				@Override
				public void focusGained(FocusEvent arg0) {
					// TODO Auto-generated method stub
					if(jtfValorRecebido.getText().equals("0")){
						jtfValorRecebido.setText("");
					}
				}
			});
			
			//defindo botao adicionar
			jbAdicionar = new JButton("ADICIONAR");
			panelTroco.add(jbAdicionar);
			jbAdicionar.setFont(new Font("Segoe UI Light", Font.PLAIN, 15));
			jbAdicionar.setBorder((Border) javax.swing.BorderFactory.createLineBorder(Color.white, 0));
			jbAdicionar.setBackground(new Color(54, 54, 54)); 
			jbAdicionar.setForeground(Color.white);
			jbAdicionar.setBounds(260, 45, 120, 40);
			
			//definindo os estados do total
			jlTotal = new JLabel("Total: ");
			panelTroco.add(jlTotal);
			jlTotal.setForeground(Color.red);
			jlTotal.setFont(new Font("Segoe UI Light", Font.BOLD, 20));
			jlTotal.setBounds(10, 100, 150, 40);

			
			
			jtaTotal = new JTextArea("R$ "+ df.format(valorTotal));
			panelTroco.add(jtaTotal);
			jtaTotal.setForeground(Color.red);
			jtaTotal.setFont(new Font("Segoe UI Light", Font.BOLD, 20));
			jtaTotal.setEditable(false);
			jtaTotal.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.black));
			jtaTotal.setBackground(new Color(255, 250, 205));
			jtaTotal.setBounds(10, 140, 130, 30);
			
			//definindo os estados do valor recebido
			jlTroco = new JLabel("Troco: ");
			panelTroco.add(jlTroco);
			jlTroco.setFont(new Font("Segoe UI Light", Font.ITALIC, 20));
			jlTroco.setBounds(10, 180, 150, 40);

			jtaTroco = new JTextArea();
			panelTroco.add(jtaTroco);
			jtaTroco.setFont(new Font("Segoe UI Light", Font.BOLD, 20));
			jtaTroco.setEditable(false);
			jtaTroco.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.black));
			jtaTroco.setBackground(new Color(255, 250, 205));
			jtaTroco.setBounds(10, 220, 230, 25);
			
			//defindo os estados do botao confirmar
			jbConfirmar = new JButton("CONFIRMAR");
			panelTroco.add(jbConfirmar);
			jbConfirmar.setFont(new Font("Segoe UI Light", Font.PLAIN, 15));
			jbConfirmar.setBorder((Border) javax.swing.BorderFactory.createLineBorder(Color.white, 0));
			jbConfirmar.setBackground(new Color(54, 54, 54)); 
			jbConfirmar.setForeground(Color.white);
			jbConfirmar.setBounds(100, 250, 200, 40);
			jbConfirmar.setEnabled(false);
			
			//tem que vir por ultimo para não sobrepor o resto
			Gradient degrade = new Gradient();
			frameTroco.add(degrade);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void eventoBotao() {
		jbAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				jbConfirmar.setEnabled(true);
				
				valorRecebido = Double.parseDouble(jtfValorRecebido.getText());
				troco = valorRecebido - valorTotal;
				
				jtaTroco.append("R$ "+df.format(troco));
			}
		});
		
		jbConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameTroco.setVisible(false);
			}
		});
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//new CalculaTroco().frameTroco.setVisible(true);
	}
}
