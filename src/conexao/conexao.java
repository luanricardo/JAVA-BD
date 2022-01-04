package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//classe com a qual iremos gerar a conex�o java com o banco de dados
public class conexao 
{
	// cria os par�metros de conex�o *precisa colocar os parametro do seu usu�rio no MySQL, talvez sej� necess�rio instalar o driver JDBC, estou utilizando a IDE eclipse*
    public static final String URL = "jdbc:mysql://localhost:3306/proj";
    public static final String usuario = "root";
    public static final String chave = "123456";    
    public static Connection con= null;
    

 // criamos o m�todo que nos permitir� retornar a conex�o em outras classes
    public static Connection getConexao()
    {
    	//envolvemos tudo na try de lidar com os erros
        try {
            con = DriverManager.getConnection(URL, usuario, chave);
            
            if(con != null)
            {
                System.out.println("--------------------------------------");
                System.out.println("Conexao realizada");
                System.out.println("--------------------------------------");
            }
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
     // retornamos a conex�o com o banco de dados
        return con;
    }
}
