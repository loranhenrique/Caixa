package Estabelecimento2;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;

@SuppressWarnings("serial")
public class TelaInicial extends JFrame {

	private JTextField jtfEmail;
	private JPasswordField pfSenha;
	private JButton btLogar;
	private JButton btCadastrar;
	private JLabel login;
	private JLabel autoria;
	private JPanel painel;

	//descrição da frame inicial: legenda logoLoja
	private JLabel descricao;
	private JLabel descricao1;
	private JLabel descricao2;
	private JLabel descricao3;
	private JLabel descricao4;
	private JLabel descricao5;

	private static String lembraCpf;
	
	JFrame tela;

	public TelaInicial(){
		iniciarComponentes();
		eventoBotao();
	}

	private void iniciarComponentes(){

		tela = new JFrame("Área de login");
		tela.setLayout(null);
		tela.setSize(1350, 700);
		tela.setResizable(false);
		tela.setLocationRelativeTo(null);
		tela.setDefaultCloseOperation(EXIT_ON_CLOSE);

		//adicionando imagem da loja
		JLabel l1b = new JLabel(new ImageIcon(getClass().getResource("/img/logoLoja.png"))); 
		tela.add(l1b);
		l1b.setLayout(null);
		l1b.setBounds(170, 20, 300, 400);

		//adicionando os JLabel: descrição
		descricao = new JLabel("A Lr Imports Inc., com proprietário Leandro Roger, é uma empresa nacional do Brasil");
		tela.add(descricao);
		descricao.setFont(new Font("Segoe UI Light", Font.ITALIC, 15));
		descricao.setForeground(new Color(255, 255, 255));
		descricao.setBounds(70, 200, 600, 350);

		descricao1 = new JLabel("de venda de roupas e acessórios de várias marcas como Oakley, Lacoste, Hurley");
		tela.add(descricao1);
		descricao1.setFont(new Font("Segoe UI Light", Font.ITALIC, 15));
		descricao1.setForeground(new Color(255, 255, 255));
		descricao1.setBounds(70, 225, 600, 350);

		descricao2 = new JLabel("Respect e outros, camisetas de times, tênis nike, puma, adidas, mizuno e outros, focada");
		tela.add(descricao2);
		descricao2.setFont(new Font("Segoe UI Light", Font.ITALIC, 15));
		descricao2.setForeground(new Color(255, 255, 255));
		descricao2.setBounds(60, 250, 600, 350);

		descricao3 = new JLabel("no público de meninos e meninas, adolescentes e adultos.");
		tela.add(descricao3);
		descricao3.setFont(new Font("Segoe UI Light", Font.ITALIC, 15));
		descricao3.setForeground(new Color(255, 255, 255));
		descricao3.setBounds(130, 275, 600, 350);

		descricao4 = new JLabel("Possui uma loja fisica própria concentrada no centro de Campinas, no estado de São Paulo.");
		tela.add(descricao4);
		descricao4.setFont(new Font("Segoe UI Light", Font.ITALIC, 18));
		descricao4.setForeground(new Color(255, 255, 255));
		descricao4.setBounds(20, 350, 700, 350);

		descricao5 = new JLabel("Na Avenida Doutor Moraes Salles, 393 – Dentro do FREE SHOPPING, no 2º PISO.");
		tela.add(descricao5);
		descricao5.setFont(new Font("Segoe UI Light", Font.ITALIC, 18));
		descricao5.setForeground(new Color(255, 255, 255));
		descricao5.setBounds(30, 380, 700, 350);

		//adicionando o painel para area de login
		painel = new JPanel();
		painel.setBackground(new Color(255, 250, 205));
		painel.setLayout(null);
		tela.add(painel);
		painel.setBounds(750, 65, 500, 550);

		//adicionando as informações do jpanel para login
		//imagem cadeado inicial
		JLabel cadeadoInicial = new JLabel(new ImageIcon(getClass().getResource("/img/padlock.png"))); 
		painel.add(cadeadoInicial);
		cadeadoInicial.setLayout(null);
		cadeadoInicial.setBounds(150, 25, 200, 150);

		//JLabel : para entrar com informações
		login = new JLabel("Entre com seus dados para efetuar o login:");
		painel.add(login);
		login.setFont(new Font("Segoe UI Light", Font.ITALIC, 20));
		login.setBounds(70,200,400,40);

		//JTextField: Login
		jtfEmail = new JTextField("Email ID");
		painel.add(jtfEmail);
		JLabel lb = new JLabel(new ImageIcon(getClass().getResource("/img/user.png"))); 
		painel.add(lb);
		lb.setBounds(10, 252, 40, 40);
		jtfEmail.setFont(new Font("Segoe UI Light", Font.ITALIC, 15));
		jtfEmail.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.black));
		jtfEmail.setBackground(new Color(255, 250, 205));
		jtfEmail.setBounds(60, 250, 400, 40);
		
		jtfEmail.addFocusListener(new FocusListener() {
			public void focusLost(FocusEvent arg0) {
				// TODO Auto-generated method stub
				if(jtfEmail.getText().isEmpty()){
					jtfEmail.setText("Email ID");
				}
			}
			
			@Override
			public void focusGained(FocusEvent arg0) {
				// TODO Auto-generated method stub
				if(jtfEmail.getText().equals("Email ID")){
					jtfEmail.setText("");
				}
			}
		});

		//JPasswordField: senha
		pfSenha = new JPasswordField("Digite sua senha");
		painel.add(pfSenha);
		JLabel lb1 = new JLabel(new ImageIcon(getClass().getResource("/img/closed.png"))); 
		painel.add(lb1);
		lb1.setBounds(10, 335, 40, 40);
		pfSenha.setFont(new Font("Segoe UI Light", Font.ITALIC, 15));
		pfSenha.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.black));
		pfSenha.setBackground(new Color(255, 250, 205));
		pfSenha.getSelectedText();
		pfSenha.setBounds(60, 330, 400, 40);

		pfSenha.addFocusListener(new FocusListener() {
			@SuppressWarnings("deprecation")
			public void focusLost(FocusEvent arg0) {
				// TODO Auto-generated method stub
				if(pfSenha.getText().isEmpty()){
					pfSenha.setText("Email ID");
				}
			}
		
			@Override
			public void focusGained(FocusEvent arg0) {
				// TODO Auto-generated method stub
					pfSenha.setText("");
				
			}
		});

		//JButton: logar
		btLogar = new JButton("EFETUAR LOGIN");
		painel.add(btLogar);
		btLogar.setFont(new Font("Segoe UI Light", Font.PLAIN, 15));
		btLogar.setBorder((Border) javax.swing.BorderFactory.createLineBorder(Color.white, 0));
		btLogar.setBackground(new Color(54, 54, 54)); 
		btLogar.setForeground(Color.white);
		btLogar.setBounds(40, 420, 200, 40);
		btLogar.getRootPane().setDefaultButton(btLogar);
		
		//JButton: cadastrar
		btCadastrar = new JButton("INSCREVER-SE");
		painel.add(btCadastrar);
		btCadastrar.setFont(new Font("Segoe UI Light", Font.PLAIN, 15));
		btCadastrar.setBorder((Border) javax.swing.BorderFactory.createLineBorder(Color.white, 0));
		btCadastrar.setBackground(new Color(54, 54, 54)); 
		btCadastrar.setForeground(Color.white);
		btCadastrar.setBounds(260, 420, 200, 40);



		//JLabel: autoria
		autoria = new JLabel("Copyright © 2018 - LORAN HENRIQUE - Todos os direitos reservados");
		painel.add(autoria);
		autoria.setFont(new Font("Segoe UI Light", Font.ITALIC, 12));
		autoria.setBounds(70,500,400,40);

		//tem que vir por ultimo para não sobrepor o resto
		Gradient degrade = new Gradient();
		tela.add(degrade);
	}

	public void eventoBotao() {

		btCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tela.setVisible(false);
				new TelaCadastro().telaCadastro.setVisible(true);
			}
		});

		btLogar.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				try {
					//cria o objeto para poder acessar o método que contém na classe 
					ComandosSql comandos = new ComandosSql();

					//verifica se retorna verdadeiro, se retorna acessa a tela da loja
					if(comandos.validaLogin(jtfEmail.getText(), pfSenha.getText())) {
						tela.setVisible(false);
						new TelaLoja().frameLoja.setVisible(true);
					}else {
						JOptionPane.showMessageDialog(null, "Tente novamente, usuário/senha incorretos.");
					}

				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
	}

	public static String getLembraCpf() {
		return lembraCpf;
	}

	public static void setLembraCpf(String lembraCpf) {
		TelaInicial.lembraCpf = lembraCpf;
	}
	
	
}
