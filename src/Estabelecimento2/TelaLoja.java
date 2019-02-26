package Estabelecimento2;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

@SuppressWarnings("serial")
public class TelaLoja extends JFrame {

	private JTabbedPane tpAbas;
	private JTextField codigo;
	private JTextField nome;
	private JTextField tamanho;
	private JTextField valor;
	private JTextField quantidade;
	private JLabel autoria;
	private JLabel jlCadastro;
	private JLabel jlCadastro1;
	private JLabel jlLinha;
	private JLabel jlTempo;
	private JScrollPane scrollTable;

	private JTable jtableProd;

	//botoes do cadastro de roupas
	private JButton btoCadastrar;
	private JButton btCancelar;


	//menu de configuração
	private JMenuBar mnBarra;
	private JMenu jmArquivo;
	private JMenuItem miSair;
	private JMenuItem miLogout;

	//variaveis para o panel de cadastro
	private JFormattedTextField jtfTelefone;
	private JFormattedTextField jtfCpf;
	private JTextField jtfEmail;
	private JTextField jtfNome;
	private JComboBox<Estados> listaPerguntas;
	private JLabel jlCadastro2;
	private JLabel jlCadastro3;
	private JLabel jlLinha1;
	private JLabel autoria1;
	private JButton btCadastrar;
	private JButton btCancelar1;
	
	private JScrollPane scrollTable1;
	private JTable jtableProd1;

	JFrame frameLoja;

	public TelaLoja() {
		iniciarComponentes();
		eventosBotoes();
	}

	private void iniciarComponentes() {

		try {

			//informações tela da loja
			frameLoja = new JFrame("LR IMPORTS Inc.");
			frameLoja.setLayout(null); 
			frameLoja.setSize(1350, 700);
			frameLoja.setResizable(false);
			frameLoja.setLocationRelativeTo(null);

			//label para colocar tempo logado
			jlTempo = new JLabel();
			frameLoja.add(jlTempo);
			jlTempo.setForeground(Color.white);
			jlTempo.setFont(new Font("Segoe UI Light", Font.PLAIN, 20));
			jlTempo.setVisible(true);
			jlTempo.setBounds(900, 10, 300, 100);

			//Configuração do Tread
			Thread log = new Thread(new Runnable() {
				@Override
				public void run() {
					int segundo = 0 , minuto = 0, hora = 0;
					while(true){
						jlTempo.setText("Tempo Logado: "+hora+" : "+minuto+" : "+segundo);
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if(segundo<60)
							segundo++;
						else{
							if(minuto<60){
								segundo=0;
								minuto++;
							}else{
								minuto=0;
								hora++;
							}

						}
					}
				}
			});
			log.start();

			//jmenubar
			ImageIcon img_logo = new ImageIcon(getClass().getResource("/img/config.png")); 
			mnBarra = new JMenuBar();
			jmArquivo = new JMenu();
			jmArquivo.setIcon(img_logo);
			jmArquivo.setFont(new Font("Segoe UI Light", Font.PLAIN, 20));
			jmArquivo.setMinimumSize(new Dimension(80, 80));
			jmArquivo.setMaximumSize(new Dimension(80, 80));
			jmArquivo.setBackground(Color.black);
			jmArquivo.setSize(120, 100);

			//menuitem sair
			miLogout = new JMenuItem("Trocar usuário ");
			miLogout.setFont(new Font("Segoe UI Light", Font.PLAIN, 15));

			//adiconando no menu
			jmArquivo.add(miLogout);
			mnBarra.add(jmArquivo);
			frameLoja.add(mnBarra);
			mnBarra.setBackground(Color.black);
			mnBarra.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.black));
			mnBarra.setBounds(1213, 20, 80, 80);

			//menuitem sair
			miSair = new JMenuItem("Finalizar sessão");
			miSair.setFont(new Font("Segoe UI Light", Font.PLAIN, 15));

			//adiconando no menu
			jmArquivo.add(miSair);
			mnBarra.add(jmArquivo);
			frameLoja.add(mnBarra);
			mnBarra.setBackground(Color.black);
			mnBarra.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.black));
			mnBarra.setBounds(1213, 20, 80, 80);


			//Primeira panel
			JPanel panel1 = new JPanel(); 
			panel1.setBackground(new Color(255, 250, 205));
			panel1.setLayout(null);

			//adicionando tabela
			jtableProd = new JTable();
			jtableProd.setModel(new DefaultTableModel(
					new Object [][] {
					},
					new String [] {"Código","Nome","Tamanho","Valor","Quantidade"}
					));

			DefaultTableModel modelo = (DefaultTableModel) jtableProd.getModel();
			ComandosSql comandos = new ComandosSql();

			for(Produtos p: comandos.read()) {
				modelo.addRow(new Object[] {
						p.getScodigo(),
						p.getSnome(),
						p.getStamanho(),
						p.getSvalor(),
						p.getSquantidade()
				});
			}

			jtableProd.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			jtableProd.getColumnModel().getColumn(0).setPreferredWidth(250);
			jtableProd.getColumnModel().getColumn(1).setPreferredWidth(250);
			jtableProd.getColumnModel().getColumn(2).setPreferredWidth(250);
			jtableProd.getColumnModel().getColumn(3).setPreferredWidth(250);
			jtableProd.getColumnModel().getColumn(4).setPreferredWidth(250);
			jtableProd.setEnabled(false);
			jtableProd.setRowHeight(50);
			jtableProd.setFont(new Font("Segoe UI Light", Font.BOLD, 18));
			scrollTable = new JScrollPane();
			scrollTable.setViewportView(jtableProd);
			panel1.add(scrollTable);
			scrollTable.setBounds(25, 20, 1268, 480);

			//Segunda panel
			JPanel panel2 = new JPanel();
			panel2.setBackground(new Color(255, 250, 205));
			panel2.setLayout(null);

			//adicionando imagem da loja
			JLabel l1b = new JLabel(new ImageIcon(getClass().getResource("/img/logoLoja.png"))); 
			panel2.add(l1b);
			l1b.setLayout(null);
			l1b.setBounds(820, 110, 250, 250);

			//JLabel : cadastro
			jlCadastro = new JLabel("CADASTRO DE NOVAS ROUPAS");
			panel2.add(jlCadastro);
			jlCadastro.setFont(new Font("Segoe UI Light", Font.BOLD, 20));
			jlCadastro.setBounds(120, 50, 400, 40);

			//JLabel : cadastro
			jlCadastro1 = new JLabel("DA EMPRESA LR IMPORTS Inc.");
			panel2.add(jlCadastro1);
			jlCadastro1.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
			jlCadastro1.setBounds(150, 80, 400, 40);

			//JLabel: linha em baixo da escrita
			jlLinha = new JLabel("_____________________________________________________________");
			panel2.add(jlLinha);
			jlLinha.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
			jlLinha.setBounds(60, 90, 500, 40);

			//Código da roupa
			JLabel codr = new JLabel(("Código da roupa: "));
			panel2.add(codr);
			codr.setFont(new Font("Segoe UI Light", Font.ITALIC, 20));
			codr.setBounds(50, 160, 150, 40);
			codigo = new JTextField();
			panel2.add(codigo);
			codigo.setFont(new Font("Segoe UI Light", Font.BOLD, 20));
			codigo.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.black));
			codigo.setBackground(new Color(255, 250, 205));
			codigo.setBounds(200, 170, 300, 25);

			//nome da roupa
			JLabel nomer = new JLabel("Nome da roupa: ");
			panel2.add(nomer);
			nomer.setFont(new Font("Segoe UI Light", Font.ITALIC, 20));
			nomer.setBounds(50, 230, 150, 40);
			nome = new JTextField();
			panel2.add(nome);
			nome.setFont(new Font("Segoe UI Light", Font.BOLD, 20));
			nome.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.black));
			nome.setBackground(new Color(255, 250, 205));
			nome.setBounds(190, 240, 310, 25);

			//tamanho da roupa
			JLabel tamr = new JLabel("Tamanho da roupa: ");
			panel2.add(tamr);
			tamr.setFont(new Font("Segoe UI Light", Font.ITALIC, 20));
			tamr.setBounds(50, 300, 170, 40);
			tamanho = new JTextField();
			panel2.add(tamanho);
			tamanho.setFont(new Font("Segoe UI Light", Font.BOLD, 20));
			tamanho.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.black));
			tamanho.setBackground(new Color(255, 250, 205));
			tamanho.setBounds(220, 310, 280, 25);

			//valor da roupa
			JLabel valorr = new JLabel("Valor da roupa: ");
			panel2.add(valorr);
			valorr.setFont(new Font("Segoe UI Light", Font.ITALIC, 20));
			valorr.setBounds(50, 370, 170, 40);
			valor = new JTextField();
			panel2.add(valor);
			valor.setFont(new Font("Segoe UI Light", Font.BOLD, 20));
			valor.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.black));
			valor.setBackground(new Color(255, 250, 205));
			valor.setBounds(190, 380, 310, 25);

			//quantidade da roupa
			JLabel quantidader = new JLabel("Quantidade de peças: ");
			panel2.add(quantidader);
			quantidader.setFont(new Font("Segoe UI Light", Font.ITALIC, 20));
			quantidader.setBounds(50, 440, 200, 40);
			quantidade = new JTextField();
			panel2.add(quantidade);
			quantidade.setFont(new Font("Segoe UI Light", Font.BOLD, 20));
			quantidade.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.black));
			quantidade.setBackground(new Color(255, 250, 205));
			quantidade.setBounds(230, 450, 272, 25);


			//JLabel: autoria
			autoria = new JLabel("Copyright © 2018 - LORAN HENRIQUE - Todos os direitos reservados");
			panel2.add(autoria);
			autoria.setFont(new Font("Segoe UI Light", Font.ITALIC, 12));
			autoria.setBounds(70,480,400,40);

			//adicionar botao registrar
			btoCadastrar = new JButton("CADASTRAR");
			panel2.add(btoCadastrar);
			btoCadastrar.setFont(new Font("Segoe UI Light", Font.PLAIN, 15));
			btoCadastrar.setBorder((Border) javax.swing.BorderFactory.createLineBorder(Color.white, 0));
			btoCadastrar.setBackground(new Color(54, 54, 54)); 
			btoCadastrar.setForeground(Color.white);
			btoCadastrar.setBounds(720, 460, 200, 40);


			//botao cancelar
			btCancelar = new JButton("LIMPAR");
			panel2.add(btCancelar);
			btCancelar.setFont(new Font("Segoe UI Light", Font.PLAIN, 15));
			btCancelar.setBorder((Border) javax.swing.BorderFactory.createLineBorder(Color.white, 0));
			btCancelar.setBackground(new Color(54, 54, 54)); 
			btCancelar.setForeground(Color.white);
			btCancelar.setBounds(960, 460, 200, 40);

			//Terceira panel para nova venda
			JPanel panel3 = new JPanel();
			panel3.setBackground(new Color(255, 250, 205));
			panel3.setLayout(null);

			//chamando o panel de nova venda
			NovaVenda nvVenda = new NovaVenda();
			panel3.add(nvVenda);

			//QUARTA panel para novo cliente
			JPanel panel4 = new JPanel();
			panel4.setBackground(new Color(255, 250, 205));
			panel4.setLayout(null);

			//adicionando os objetos no meu panel
			//imagem do cabeçalho
			JLabel lb1 = new JLabel(new ImageIcon(getClass().getResource("/img/logoLoja.png"))); 
			panel4.add(lb1);
			lb1.setBounds(900, 120, 250, 250);

			//JLabel : cadastro
			jlCadastro2 = new JLabel("CADASTRO DE NOVOS CLIENTES");
			panel4.add(jlCadastro2);
			jlCadastro2.setFont(new Font("Segoe UI Light", Font.BOLD, 20));
			jlCadastro2.setBounds(200, 30, 400, 40);

			//JLabel : cadastro
			jlCadastro3 = new JLabel("DA EMPRESA LR IMPORTS Inc.");
			panel4.add(jlCadastro3);
			jlCadastro3.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
			jlCadastro3.setBounds(250, 60, 400, 40);

			//JLabel: linha em baixo da escrita
			jlLinha1 = new JLabel("_____________________________________________________________");
			panel4.add(jlLinha1);
			jlLinha1.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
			jlLinha1.setBounds(140, 70, 500, 40);

			//JTextField: Nome
			jtfNome = new JTextField("Digite o nome do cliente");
			panel4.add(jtfNome);
			JLabel lb = new JLabel(new ImageIcon(getClass().getResource("/img/user.png"))); 
			panel4.add(lb);
			lb.setBounds(20, 182, 40, 40);
			jtfNome.setFont(new Font("Segoe UI Light", Font.ITALIC, 15));
			jtfNome.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.black));
			jtfNome.setBackground(new Color(255, 250, 205));
			jtfNome.setBounds(70, 180, 640, 40);

			jtfNome.addFocusListener(new FocusListener() {
				public void focusLost(FocusEvent arg0) {
					// TODO Auto-generated method stub
					if(jtfNome.getText().isEmpty()){
						jtfNome.setText("Digite o nome do cliente");
					}
				}

				@Override
				public void focusGained(FocusEvent arg0) {
					// TODO Auto-generated method stub
					if(jtfNome.getText().equals("Digite o nome do cliente")){
						jtfNome.setText("");
					}
				}
			});

			try {

				//JFormattedTextField : cpf
				MaskFormatter maskCpf = new MaskFormatter("###.###.###-##");
				jtfCpf = new JFormattedTextField(maskCpf);
				panel4.add(jtfCpf);
				JLabel lb2 = new JLabel(new ImageIcon(getClass().getResource("/img/cpf.png"))); 
				panel4.add(lb2);
				lb2.setBounds(20, 232, 40, 40);
				jtfCpf.setFont(new Font("Segoe UI Light", Font.ITALIC, 15));
				jtfCpf.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.black));
				jtfCpf.setBackground(new Color(255, 250, 205));
				jtfCpf.setBounds(70, 230, 640, 40);

				//JFormattedTextField : email
				jtfEmail = new JTextField("exemplo@exemplo.com");
				panel4.add(jtfEmail);
				JLabel lb3 = new JLabel(new ImageIcon(getClass().getResource("/img/email.png"))); 
				panel4.add(lb3);
				lb3.setBounds(20, 282, 40, 40);
				jtfEmail.setFont(new Font("Segoe UI Light", Font.ITALIC, 15));
				jtfEmail.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.black));
				jtfEmail.setBackground(new Color(255, 250, 205));
				jtfEmail.setBounds(70, 280, 640, 40);

				jtfEmail.addFocusListener(new FocusListener() {
					public void focusLost(FocusEvent arg0) {
						// TODO Auto-generated method stub
						if(jtfEmail.getText().isEmpty()){
							jtfEmail.setText("exemplo@exemplo.com");
						}
					}

					@Override
					public void focusGained(FocusEvent arg0) {
						// TODO Auto-generated method stub
						if(jtfEmail.getText().equals("exemplo@exemplo.com")){
							jtfEmail.setText("");
						}
					}
				});

				//JFormattedTextField: telefone
				MaskFormatter maskTel = new MaskFormatter("(##)#####-####");
				maskTel.setPlaceholderCharacter('_');
				jtfTelefone = new JFormattedTextField(maskTel);
				panel4.add(jtfTelefone);
				JLabel lb4 = new JLabel(new ImageIcon(getClass().getResource("/img/telefone.png"))); 
				panel4.add(lb4);
				lb4.setBounds(20, 332, 40, 40);
				jtfTelefone.setFont(new Font("Segoe UI Light", Font.ITALIC, 15));
				jtfTelefone.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.black));
				jtfTelefone.setBackground(new Color(255, 250, 205));
				jtfTelefone.setBounds(70, 330, 290, 40);

				//combo box lista de estados
				listaPerguntas = new ComandosSql().montaListaPerguntas();
				listaPerguntas.setBounds(430,340, 200, 30);
				listaPerguntas.setFont(new Font("Segoe UI Light", Font.PLAIN, 20));
				listaPerguntas.setBorder((Border) javax.swing.BorderFactory.createLineBorder(new Color(0, 0, 0, 0), 0));
				listaPerguntas.setBackground(new Color(255, 215, 0)); 
				listaPerguntas.setForeground(Color.black);
				listaPerguntas.addItemListener(new ItemListener(){
					public void itemStateChanged(ItemEvent arg0){
						if(arg0.getStateChange()==ItemEvent.SELECTED){

							Estados p = (Estados) listaPerguntas.getSelectedItem();
							System.out.println("Texto: "+p.getEstadosBr());
							System.out.println();
						}
					}
				});
				panel4.add(listaPerguntas);


				//adicionar botao registrar
				btCadastrar = new JButton("INSCREVER");
				panel4.add(btCadastrar);
				btCadastrar.setFont(new Font("Segoe UI Light", Font.PLAIN, 15));
				btCadastrar.setBorder((Border) javax.swing.BorderFactory.createLineBorder(Color.white, 0));
				btCadastrar.setBackground(new Color(54, 54, 54)); 
				btCadastrar.setForeground(Color.white);
				btCadastrar.setBounds(120, 420, 200, 40);
				//btCadastrar.getRootPane().setDefaultButton(btCadastrar);

				//botao cancelar
				btCancelar1 = new JButton("LIMPAR");
				panel4.add(btCancelar1);
				btCancelar1.setFont(new Font("Segoe UI Light", Font.PLAIN, 15));
				btCancelar1.setBorder((Border) javax.swing.BorderFactory.createLineBorder(Color.white, 0));
				btCancelar1.setBackground(new Color(54, 54, 54)); 
				btCancelar1.setForeground(Color.white);
				btCancelar1.setBounds(370, 420, 200, 40);

				//JLabel: autoria
				autoria1 = new JLabel("Copyright © 2018 - LORAN HENRIQUE - Todos os direitos reservados");
				panel4.add(autoria1);
				autoria1.setFont(new Font("Segoe UI Light", Font.ITALIC, 12));
				autoria1.setBounds(170,460,400,40);

			} catch (Exception e) {
				// TODO: handle exception
			}

			/*//chamando o panel de novo cliente
			NovoCliente nvCliente = new NovoCliente();
			panel4.add(nvCliente);*/

			//QUINTA panel para mostrar os clientes
			JPanel panel5 = new JPanel();
			panel5.setBackground(new Color(255, 250, 205));
			panel5.setLayout(null);

			//adicionando tabela
			jtableProd1 = new JTable();
			jtableProd1.setModel(new DefaultTableModel(
					new Object [][] {
					},
					new String [] {"CPF","Nome","Email","Telefone","Estado UF"}
					));

			DefaultTableModel modelo1 = (DefaultTableModel) jtableProd1.getModel();
			ComandosSql comandos1 = new ComandosSql();

			for(Clientes c: comandos1.listaClientes()) {
				modelo1.addRow(new Object[] {
						c.getCpf(),
						c.getNome(),
						c.getEmail(),
						c.getTelefone(),
						c.getUf()
				});
			}

			jtableProd1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			jtableProd1.getColumnModel().getColumn(0).setPreferredWidth(250);
			jtableProd1.getColumnModel().getColumn(1).setPreferredWidth(250);
			jtableProd1.getColumnModel().getColumn(2).setPreferredWidth(350);
			jtableProd1.getColumnModel().getColumn(3).setPreferredWidth(250);
			jtableProd1.getColumnModel().getColumn(4).setPreferredWidth(150);
			jtableProd1.setEnabled(false);
			jtableProd1.setRowHeight(50);
			jtableProd1.setFont(new Font("Segoe UI Light", Font.BOLD, 18));
			scrollTable1 = new JScrollPane();
			scrollTable1.setViewportView(jtableProd1);
			panel5.add(scrollTable1);
			scrollTable1.setBounds(25, 20, 1268, 480);
			

			//criando as  JTabbedPane e adicionando os panel dentro das abas
			tpAbas = new JTabbedPane(); 
			tpAbas.addTab("Estoque da Loja", panel1); 
			tpAbas.addTab(" Novas roupas ", panel2); 
			tpAbas.addTab("  Nova venda  ", panel3);
			tpAbas.addTab(" Novo cliente ", panel4); 
			tpAbas.addTab("   Clientes   ", panel5);
			tpAbas.setFont(new Font("Segoe UI Light", Font.PLAIN, 15));
			tpAbas.setBackground(new Color(54, 54, 54));
			tpAbas.setForeground(Color.white);
			tpAbas.setBounds(10, 100, 1325, 550);




		} catch (Exception e) {
			// TODO: handle exception
		}


		//adicionando as abas no frame
		frameLoja.add(tpAbas); 

		//tem que vir por ultimo para não sobrepor o resto
		Gradient degrade = new Gradient();
		frameLoja.add(degrade);
	}

	public void eventosBotoes(){
		
		btCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String cpf = "", nome = "", email = "", telefone = "", estado = "";
					boolean validaNome = false, validaEmail = false, validaCpf = false, validaTelefone = false;

					if(jtfNome.getText().equals("") || (jtfNome.getText().length() < 5) || (jtfNome.getText().length() > 40)){
						JOptionPane.showMessageDialog(null, "Informe um nome válido");
						jtfNome.requestFocus();
					}else {
						validaNome = true;
						nome = jtfNome.getText();
					}

					if(jtfEmail.getText().length() > 1 && jtfEmail.getText().indexOf("@") >= 1 && jtfEmail.getText().indexOf(".") >= 1 && jtfEmail.getText().length() < 50){
						validaEmail = true;
						email = jtfEmail.getText();
					}else{
						JOptionPane.showMessageDialog(null, "Informe um email válido");
						jtfEmail.requestFocus();
					}

					if(jtfCpf.getText().length() > 10) {
						validaCpf = true;
						cpf = jtfCpf.getText();
					}else {
						JOptionPane.showMessageDialog(null, "Informe um cpf válido");
					}

					if(jtfTelefone.getText().length() > 9) {
						validaTelefone = true;
						telefone = jtfTelefone.getText();
					}else {
						JOptionPane.showMessageDialog(null, "Informe um telefone válido");
					}
					
					estado = listaPerguntas.getSelectedItem().toString();

					if(validaNome == true && validaEmail == true && validaCpf == true && validaTelefone == true){
						
						
							ComandosSql comandos = new ComandosSql();
							boolean retorno = comandos.cadastrarClientes(cpf, nome, email, telefone, estado);
							if(retorno) {
								JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso");
								
								
								DefaultTableModel lll = (DefaultTableModel) jtableProd1.getModel();
								lll.addRow(new Object[]{
										jtfCpf.getText(),
										jtfNome.getText().toUpperCase(),
										jtfEmail.getText().toUpperCase(),
										jtfTelefone.getText(),
										listaPerguntas.getSelectedItem().toString()
								});

								jtfCpf.setText("");
								jtfNome.setText("");
								jtfEmail.setText("");
								jtfTelefone.setText("");
								listaPerguntas.setSelectedItem("AC");
								
							}
						}
					
				} catch (Exception e1) {
					// TODO: handle exception
					new RuntimeException("Dados inválidos");
				}
			}
		});

	

		miSair.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.exit(0);
			}
		});

		miLogout.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				frameLoja.setVisible(false);
				new TelaInicial().tela.setVisible(true);
			}
		});

		btoCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Produtos produto = new Produtos();
				boolean validaCod = false, validaNome = false, validaTam = false, validaValor = false, validadeQtd = false;
				try {
					if(codigo.getText().equals("") || codigo.getText().length() < 4 || codigo.getText().length() > 10) {
						JOptionPane.showMessageDialog(null, "Informe um código válido");
						codigo.requestFocus();
					}else {
						validaCod = true;
						produto.setScodigo(codigo.getText().toUpperCase());
					}

					if(nome.getText().equals("") || nome.getText().length() <5 || nome.getText().length() > 40) {
						JOptionPane.showMessageDialog(null, "Informe um nome válido");
						nome.requestFocus();
					}else {
						validaNome = true;
						produto.setSnome(nome.getText().toUpperCase());
					}

					if(tamanho.getText().equals("") || tamanho.getText().length() > 3) {
						JOptionPane.showMessageDialog(null, "Informe um tamanho válido");
						tamanho.requestFocus();
					}else {
						validaTam = true;
						produto.setStamanho(tamanho.getText().toUpperCase());
					}

					if(valor.getText().equals("") || valor.getText().length() > 10) {
						JOptionPane.showMessageDialog(null, "Informe um valor válido");
						valor.requestFocus();
					}else {
						validaValor = true;
						produto.setSvalor(Float.parseFloat(valor.getText().replace(',', '.')));
					}

					if(quantidade.getText().equals("") || quantidade.getText().length() > 10) {
						JOptionPane.showMessageDialog(null, "Informe uma quantidade válido");
						quantidade.requestFocus();
					}else {
						validadeQtd = true;
						produto.setSquantidade(Integer.parseInt(quantidade.getText()));
					}

					if(validaCod == true && validaNome == true && validaTam == true && validaValor == true && validadeQtd == true) {
						ComandosSql comandos = new ComandosSql();
						boolean retorno = comandos.cadastrarRoupas(produto.getScodigo(), produto.getSnome(), produto.getStamanho(), produto.getSvalor(), produto.getSquantidade());
						if(retorno) {
							JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso");

							DefaultTableModel dtm = (DefaultTableModel) jtableProd.getModel();
							dtm.addRow(new Object[]{
									codigo.getText().toUpperCase(),
									nome.getText().toUpperCase(),
									tamanho.getText().toUpperCase(),
									valor.getText(),
									quantidade.getText()
							});

							codigo.setText("");
							nome.setText("");
							tamanho.setText("");
							valor.setText("");
							quantidade.setText("");
						}
					}
				} catch (Exception e2) {
					e2.getStackTrace();
				}
			}
		});

		btCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				codigo.setText("");
				nome.setText("");
				tamanho.setText("");
				valor.setText("");
				quantidade.setText("");
			}
		});

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new TelaLoja().frameLoja.setVisible(true);
	}

}
