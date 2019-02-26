package Estabelecimento2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class ConectaMySql {

	//Criação de um objeto da classe connection (import java.sql.Connection)
		//Nesse objeto ficarão os dados da conexão (IP,usuário, senha, schema)
		//bem como o seu estado, isto é, conectado ou não
		private Connection conexao;
		
		//Montagem de método para configurar a conexão e retornar o objeto "conexao"
		public Connection iniciarConexao(){
			//especificação de qual driver será usado para a conexão 
			
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				//Class.forName("com.mysql.jdbc.Driver");
				
				conexao = DriverManager.getConnection("jdbc:mysql://ESN509VMYSQL/lojaroupa_loran?"
						+"user=aluno&password=Senai1234&"
						+"useSSL=false&useTimezone=true&serverTimezone=UTC");
				
				/*conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/lojaroupa_loran?"
						+"user=root&password=320teixeira&"
						+"useSSL=false&useTimezone=true&serverTimezone=UTC");*/
				
				JOptionPane.showMessageDialog(null, "Conexão Realizada");
				
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return conexao;
		}
		
		public static void main(String[] args) {
			// TODO Auto-generated method stub
				new ConectaMySql().iniciarConexao();
				
				//ou
				//ConectaMySql conecta = new ConectaMySql();
				//conecta.iniciarConexao();

		}
}
