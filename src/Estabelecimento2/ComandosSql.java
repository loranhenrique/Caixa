package Estabelecimento2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;

public class ComandosSql {

	//Método para fazer um INSERT na tabela de Respostas da somativa
	public boolean cadastrarResposta(String cpf, String nome, String email, String telefone, String senha, String sexo){
		try {
			//Criação de um objeto da classe Connection
			Connection conexao = new ConectaMySql().iniciarConexao();

			//Criação do comando Sql a ser executado no banco de dados 
			String sql = "INSERT INTO cadastrofuncionario VALUES(?,?,?,?,?,?);";

			//preparar a conexão para executar o comando SQL
			// o que o comando abaixo faz é : "Preparar a conexão para executa tal comando SQL"
			PreparedStatement ps = conexao.prepareStatement(sql);

			//passagem de valores para cada "?"
			ps.setString(1, cpf);
			ps.setString(2, nome);
			ps.setString(3, email);
			ps.setString(4, telefone);
			ps.setString(5, senha);
			ps.setString(6, sexo);

			//Para executar o comando no banco (INSERT, DELETE OU UPDATE), usamos o método
			//executeUpdate(). Este método retorna a quantidade de linhas afetadas na tabela ou então zero se não afetou nada
			if (ps.executeUpdate()!=0) {
				conexao.close();//fecha a conexão e libera os recursos
				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public void buscaRespostas(){
		try {
			Connection conexao = new ConectaMySql().iniciarConexao();
			String sql = "SELECT * FROM cadastrofuncionario;";
			PreparedStatement ps = conexao.prepareStatement(sql);

			//Para executar o comando SELECT é necessário armazenar o 
			//seu retorno em um objeto do tipo ResultSet

			ResultSet resultado = ps.executeQuery();//Executa e armazena

			//Para percorrer cada linha que o SELECT deixou armazenado no 
			//ResultSet "Resultado" podemos usar o while 

			while (resultado.next()) {//enquanto houver uma linha na sequencia
				System.out.println("CPF: "+resultado.getString(1));
				System.out.println("Nome: "+resultado.getString(2));
				System.out.println("Email: "+resultado.getString(3));
				System.out.println("Telefone: "+resultado.getString(4));
				System.out.println("Senha: "+resultado.getString(5));
				System.out.println("Sexo: "+resultado.getString(6));
				System.out.println();
			}
			conexao.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean validaLogin(String email, String senha) {
		
		boolean tipoRetorno = false;
		
		try {
			
			Connection conexao = new ConectaMySql().iniciarConexao();
			String sql = "SELECT email,senha,cpf FROM cadastrofuncionario;";
			PreparedStatement ps = conexao.prepareStatement(sql);
			
			ResultSet resultado = ps.executeQuery();
			
			while(resultado.next()) {
				if(email.equals(resultado.getString("email")) && senha.equals(resultado.getString("senha"))) {
					//System.out.println(resultado.getString("cpf"));
					//System.out.println(resultado.getString("senha"));
					TelaInicial.setLembraCpf(resultado.getString("cpf"));
					tipoRetorno = true;
				}
			}
			
			conexao.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tipoRetorno;
	}
	
	public List<Produtos> procurandoProduto(String codigo){
		List<Produtos> produtos1 = new ArrayList<>();
		Connection conexao = new ConectaMySql().iniciarConexao();
		try {
			
			String sql = "SELECT codigo, nome, tamanho, valor FROM produtos;";
			PreparedStatement ps = conexao.prepareStatement(sql);
			
			ResultSet resultado = ps.executeQuery();
			
			while(resultado.next()) {
				if(codigo.equals(resultado.getString("codigo"))){
					Produtos produtos = new Produtos();
					produtos.setScodigo(resultado.getString("codigo").toUpperCase());
					produtos.setSnome(resultado.getString("nome").toUpperCase());
					produtos.setStamanho(resultado.getString("tamanho").toUpperCase());
					produtos.setSvalor(resultado.getFloat("valor"));
					produtos1.add(produtos);
				}
			}
			conexao.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return produtos1;
	}
	
	public boolean cadastrarRoupas(String codigo, String nome, String tamanho, float valor, int quantidade) {
		try {
			//Criação de um objeto da classe Connection
			Connection conexao = new ConectaMySql().iniciarConexao();

			//Criação do comando Sql a ser executado no banco de dados 
			String sql = "INSERT INTO produtos VALUES(?,?,?,?,?);";

			//preparar a conexão para executar o comando SQL
			// o que o comando abaixo faz é : "Preparar a conexão para executa tal comando SQL"
			PreparedStatement ps = conexao.prepareStatement(sql);

			//passagem de valores para cada "?"
			ps.setString(1, codigo);
			ps.setString(2, nome);
			ps.setString(3, tamanho);
			ps.setFloat(4, valor);
			ps.setInt(5, quantidade);

			//Para executar o comando no banco (INSERT, DELETE OU UPDATE), usamos o método
			//executeUpdate(). Este método retorna a quantidade de linhas afetadas na tabela ou então zero se não afetou nada
			if (ps.executeUpdate()!=0) {
				conexao.close();//fecha a conexão e libera os recursos
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean cadastrarClientes(String cpf, String nome, String email, String telefone, String estado) {
		try {
			//Criação de um objeto da classe Connection
			Connection conexao = new ConectaMySql().iniciarConexao();

			//Criação do comando Sql a ser executado no banco de dados 
			String sql = "INSERT INTO cliente VALUES(?,?,?,?,?);";

			//preparar a conexão para executar o comando SQL
			// o que o comando abaixo faz é : "Preparar a conexão para executa tal comando SQL"
			PreparedStatement ps = conexao.prepareStatement(sql);

			//passagem de valores para cada "?"
			ps.setString(1, cpf);
			ps.setString(2, nome);
			ps.setString(3, email);
			ps.setString(4, telefone);
			ps.setString(5, estado);

			//Para executar o comando no banco (INSERT, DELETE OU UPDATE), usamos o método
			//executeUpdate(). Este método retorna a quantidade de linhas afetadas na tabela ou então zero se não afetou nada
			if (ps.executeUpdate()!=0) {
				conexao.close();//fecha a conexão e libera os recursos
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public List<Produtos> read(){
		List<Produtos> produtos1 = new ArrayList<>();
		Connection conexao = new ConectaMySql().iniciarConexao();
		try {
			
			String sql = "SELECT * FROM produtos;";
			PreparedStatement ps = conexao.prepareStatement(sql);
			
			ResultSet resultado = ps.executeQuery();
			
			while(resultado.next()) {
				
				Produtos produtos = new Produtos();
				produtos.setScodigo(resultado.getString("codigo").toUpperCase());
				produtos.setSnome(resultado.getString("nome").toUpperCase());
				produtos.setStamanho(resultado.getString("tamanho").toUpperCase());
				produtos.setSvalor(resultado.getFloat("valor"));
				produtos.setSquantidade(resultado.getInt("quantidade"));
				produtos1.add(produtos);
			}
			conexao.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return produtos1;
	}
	
	public List<Clientes> listaClientes(){
		List<Clientes> clientes1 = new ArrayList<>();
		Connection conexao = new ConectaMySql().iniciarConexao();
		try {
			
			String sql = "SELECT * FROM cliente;";
			PreparedStatement ps = conexao.prepareStatement(sql);
			
			ResultSet resultado = ps.executeQuery();
			
			while(resultado.next()) {
				

				Clientes cliente = new Clientes();
				cliente.setCpf(resultado.getString("cpf"));
				cliente.setNome(resultado.getString("nome").toUpperCase());
				cliente.setEmail(resultado.getString("email").toUpperCase());
				cliente.setTelefone(resultado.getString("telefone"));
				cliente.setUf(resultado.getString("estado"));
				clientes1.add(cliente);
			}
			conexao.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return clientes1;
	}
	
	//Método para buscar no banc de dados e montar um componente JComboBox com os dados da consulta (SELECT)
		//Ao final, será retornado um objeto JComboBox já pronto, isto é preenchido 
		
		public JComboBox<Estados> montaListaPerguntas(){
			JComboBox<Estados> lista = new JComboBox<Estados>();
			try{
				Connection conexao = new ConectaMySql().iniciarConexao();
				String sql = "SELECT sigla FROM estados;";
				PreparedStatement ps = conexao.prepareStatement(sql);
				ResultSet resultado = ps.executeQuery();
				
				while(resultado.next()){
					//Para cada pergunta que encontrar no banco de dados
					//Cria um objeto novo da classe Pergunta
					Estados p = new Estados(resultado.getString("sigla"));
					//Adiciona a pergunta no JComboBox
					lista.addItem(p);
				}
				conexao.close();//Fecha a conexão e libera recurso
				
			}catch(Exception exc){
				exc.printStackTrace();
			}
			
			return lista;
		}
}
