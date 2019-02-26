package Estabelecimento2;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.text.MaskFormatter;

@SuppressWarnings("serial")
public class TelaCadastro extends JFrame{

	//variaveis para o panel de cadastro
	private JFormattedTextField jtfTelefone;
	private JFormattedTextField jtfCpf;
	private JTextField jtfEmail;
	private JTextField jtfNome;
	private JPasswordField pfSenha;
	private JRadioButton cbFeminino;
	private JRadioButton cbMasculino;
	private ButtonGroup grupoSexo;
	private JLabel jlCadastro;
	private JLabel jlCadastro1;
	private JLabel jlLinha;
	private JLabel autoria;
	private JButton btCadastrar;
	private JButton btCancelar;
	private JPanel painelCadastro;

	JFrame telaCadastro;

	public TelaCadastro(){
		iniciarComponentes();
		iniciarEventos();
	}

	private void iniciarComponentes(){

		telaCadastro = new JFrame("Área de cadastro");
		telaCadastro.setLayout(null);
		telaCadastro.setSize(1350, 700);
		telaCadastro.setResizable(false);
		telaCadastro.setLocationRelativeTo(null);

		//definindo propriedades do meu jpanel
		painelCadastro = new JPanel();
		painelCadastro.setBackground(new Color(255, 250, 205));
		painelCadastro.setLayout(null);
		telaCadastro.add(painelCadastro);
		painelCadastro.setBounds(270, 40, 800, 600);

		//adicionando os objetos no meu panel
		//imagem do cabeçalho
		JLabel lb1 = new JLabel(new ImageIcon(getClass().getResource("/img/logoLoja.png"))); 
		painelCadastro.add(lb1);
		lb1.setBounds(4, 3, 250, 250);

		//JLabel : cadastro
		jlCadastro = new JLabel("CADASTRO DE NOVOS FUNCIONÁRIOS");
		painelCadastro.add(jlCadastro);
		jlCadastro.setFont(new Font("Segoe UI Light", Font.BOLD, 20));
		jlCadastro.setBounds(300, 80, 400, 40);

		//JLabel : cadastro
		jlCadastro1 = new JLabel("DA EMPRESA LR IMPORTS Inc.");
		painelCadastro.add(jlCadastro1);
		jlCadastro1.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		jlCadastro1.setBounds(350, 110, 400, 40);

		//JLabel: linha em baixo da escrita
		jlLinha = new JLabel("_____________________________________________________________");
		painelCadastro.add(jlLinha);
		jlLinha.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		jlLinha.setBounds(270, 120, 500, 40);

		//JTextField: Nome
		jtfNome = new JTextField("Digite o nome do funcionário");
		painelCadastro.add(jtfNome);
		JLabel lb = new JLabel(new ImageIcon(getClass().getResource("/img/user.png"))); 
		painelCadastro.add(lb);
		lb.setBounds(20, 282, 40, 40);
		jtfNome.setFont(new Font("Segoe UI Light", Font.ITALIC, 15));
		jtfNome.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.black));
		jtfNome.setBackground(new Color(255, 250, 205));
		jtfNome.setBounds(70, 280, 640, 40);

		jtfNome.addFocusListener(new FocusListener() {
			public void focusLost(FocusEvent arg0) {
				// TODO Auto-generated method stub
				if(jtfNome.getText().isEmpty()){
					jtfNome.setText("Digite o nome do funcionário");
				}
			}

			@Override
			public void focusGained(FocusEvent arg0) {
				// TODO Auto-generated method stub
				if(jtfNome.getText().equals("Digite o nome do funcionário")){
					jtfNome.setText("");
				}
			}
		});

		try {

			//JFormattedTextField : cpf
			MaskFormatter maskCpf = new MaskFormatter("###.###.###-##");
			jtfCpf = new JFormattedTextField(maskCpf);
			painelCadastro.add(jtfCpf);
			JLabel lb2 = new JLabel(new ImageIcon(getClass().getResource("/img/cpf.png"))); 
			painelCadastro.add(lb2);
			lb2.setBounds(20, 332, 40, 40);
			jtfCpf.setFont(new Font("Segoe UI Light", Font.ITALIC, 15));
			jtfCpf.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.black));
			jtfCpf.setBackground(new Color(255, 250, 205));
			jtfCpf.setBounds(70, 330, 640, 40);

			//JFormattedTextField : email
			jtfEmail = new JTextField("exemplo@exemplo.com");
			painelCadastro.add(jtfEmail);
			JLabel lb3 = new JLabel(new ImageIcon(getClass().getResource("/img/email.png"))); 
			painelCadastro.add(lb3);
			lb3.setBounds(20, 382, 40, 40);
			jtfEmail.setFont(new Font("Segoe UI Light", Font.ITALIC, 15));
			jtfEmail.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.black));
			jtfEmail.setBackground(new Color(255, 250, 205));
			jtfEmail.setBounds(70, 380, 640, 40);
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
			painelCadastro.add(jtfTelefone);
			JLabel lb4 = new JLabel(new ImageIcon(getClass().getResource("/img/telefone.png"))); 
			painelCadastro.add(lb4);
			lb4.setBounds(20, 432, 40, 40);
			jtfTelefone.setFont(new Font("Segoe UI Light", Font.ITALIC, 15));
			jtfTelefone.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.black));
			jtfTelefone.setBackground(new Color(255, 250, 205));
			jtfTelefone.setBounds(70, 430, 290, 40);

			//JPassword : senha
			pfSenha = new JPasswordField("Password");
			painelCadastro.add(pfSenha);
			JLabel lbSenha = new JLabel(new ImageIcon(getClass().getResource("/img/closed.png"))); 
			painelCadastro.add(lbSenha);
			lbSenha.setBounds(373, 432, 40, 40);
			pfSenha.setFont(new Font("Segoe UI Light", Font.ITALIC, 15));
			pfSenha.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.black));
			pfSenha.setBackground(new Color(255, 250, 205));
			pfSenha.setBounds(420, 430, 290, 40);

			pfSenha.addFocusListener(new FocusListener() {
				@SuppressWarnings("deprecation")
				public void focusLost(FocusEvent arg0) {
					// TODO Auto-generated method stub
					if(pfSenha.getText().isEmpty()){
						pfSenha.setText("Password");
					}
				}

				@SuppressWarnings("deprecation")
				@Override
				public void focusGained(FocusEvent arg0) {
					// TODO Auto-generated method stub
					if(pfSenha.getText().equals("Password")){
						pfSenha.setText("");
					}
				}
			});

			//CheckBok : masc
			cbMasculino = new JRadioButton("Masculino", true);
			painelCadastro.add(cbMasculino);
			JLabel lb5 = new JLabel(new ImageIcon(getClass().getResource("/img/sexo.png"))); 
			painelCadastro.add(lb5);
			lb5.setBounds(20, 482, 40, 40);
			cbMasculino.setFont(new Font("Segoe UI Light", Font.ITALIC, 15));
			cbMasculino.setBounds(70, 490, 90, 20);
			cbMasculino.setForeground(new Color(0, 0, 0));
			cbMasculino.setBackground(new Color(255, 250, 205));

			//CheckBok : masc
			cbFeminino = new JRadioButton("Feminino");
			painelCadastro.add(cbFeminino);
			cbFeminino.setFont(new Font("Segoe UI Light", Font.ITALIC, 15));
			cbFeminino.setBounds(180, 490, 190, 20);
			cbFeminino.setForeground(new Color(0, 0, 0));
			cbFeminino.setBackground(new Color(255, 250, 205));

			//adicionando o radiobox ao grupo sexo
			grupoSexo = new ButtonGroup();
			grupoSexo.add(cbFeminino);
			grupoSexo.add(cbMasculino);

			//adicionar botao registrar
			btCadastrar = new JButton("INSCREVER");
			painelCadastro.add(btCadastrar);
			btCadastrar.setFont(new Font("Segoe UI Light", Font.PLAIN, 15));
			btCadastrar.setBorder((Border) javax.swing.BorderFactory.createLineBorder(Color.white, 0));
			btCadastrar.setBackground(new Color(54, 54, 54)); 
			btCadastrar.setForeground(Color.white);
			btCadastrar.setBounds(280, 520, 200, 40);
			btCadastrar.getRootPane().setDefaultButton(btCadastrar);


			//botao cancelar
			btCancelar = new JButton("CANCELAR");
			painelCadastro.add(btCancelar);
			btCancelar.setFont(new Font("Segoe UI Light", Font.PLAIN, 15));
			btCancelar.setBorder((Border) javax.swing.BorderFactory.createLineBorder(Color.white, 0));
			btCancelar.setBackground(new Color(54, 54, 54)); 
			btCancelar.setForeground(Color.white);
			btCancelar.setBounds(510, 520, 200, 40);

			//JLabel: autoria
			autoria = new JLabel("Copyright © 2018 - LORAN HENRIQUE - Todos os direitos reservados");
			painelCadastro.add(autoria);
			autoria.setFont(new Font("Segoe UI Light", Font.ITALIC, 12));
			autoria.setBounds(70,570,400,40);

		} catch (Exception e) {
			// TODO: handle exception
		}

		//tem que vir por ultimo para não sobrepor o resto
		Gradient degrade = new Gradient();
		telaCadastro.add(degrade);

	}

	private void iniciarEventos(){

		btCadastrar.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {

				try {
					String cpf = "", nome = "", email = "", telefone = "", senha = "", sexo = "";
					boolean validaNome = false, validaEmail = false, validaSenha =  false, validaCpf = false, validaTelefone = false;

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

					if(pfSenha.getText().equals("") || pfSenha.getText().length() < 6 || pfSenha.getText().length() > 30){
						JOptionPane.showMessageDialog(null, "Informe uma senha mais segura");
						pfSenha.requestFocus();
					}else {
						validaSenha = true;
						senha = pfSenha.getText();
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

					if(cbMasculino.isSelected()) {
						sexo = "Masculino";
					}

					if(cbFeminino.isSelected()) {
						sexo = "Feminino";
					}

					if(validaNome == true && validaEmail == true && validaSenha == true && validaCpf == true && validaTelefone == true){

						JLabel label = new JLabel("Digite a senha do administrador para efetuar o cadastro:");
						JPasswordField jpf = new JPasswordField();
						JOptionPane.showConfirmDialog(null, new Object[]{label, jpf}, "Senha:", JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE);
						
						String senhaDigitada = new String(jpf.getPassword());
						if(senhaDigitada.equals("320teixeira")) {
							ComandosSql comandos = new ComandosSql();
							boolean retorno = comandos.cadastrarResposta(cpf, nome, email, telefone, senha, sexo);
							comandos.buscaRespostas();
							if(retorno) {
								JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso");
								telaCadastro.setVisible(false);
								new TelaInicial().tela.setVisible(true);
								
							}
						}else {
							JOptionPane.showMessageDialog(null, "Tente novamente, usuário/senha incorretos.");
						}


					}


				} catch (Exception e1) {
					// TODO: handle exception
					new RuntimeException("Dados inválidos");
				}

			}
		});

		btCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Cadastro não foi efetuado");
				telaCadastro.setVisible(false);
				new TelaInicial().tela.setVisible(true);
			}
		});
	}
}
