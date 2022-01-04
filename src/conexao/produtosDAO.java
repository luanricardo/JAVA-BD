package conexao;

import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import programa.ProdutosModelo;

//classe para realizar o CRUD com o banco de dados
public class produtosDAO 
{
	//variavel de conex�o
    private Connection con;
    
    //metodo para cria um novo produto
    public void AdicionarProduto(ProdutosModelo produto)
    {
    	//tudo dentro de um try em caso de erro
        try{
        	// se n�o houver conex�o, geramos um
            if(con == null)
                con = conexao.getConexao();
            
            String sql = "INSERT INTO PRODUTO(nome,preco) values(?,?)";
            
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, produto.getNome());
            statement.setDouble(2, produto.getPreco());
            int RowsInserted = statement.executeUpdate();
            
            if(RowsInserted > 0)
            {
                JOptionPane.showMessageDialog(null, "Insert com sucesso \nProduto: " + produto.getNome()+"\nPreco: " + 
                        produto.getPreco() + "$","Sucesso",JOptionPane.INFORMATION_MESSAGE);
                System.out.println("Cadastro com sucesso!");
                System.out.println("--------------------------------------");
            }
            else
            {
                System.out.println("Erro com cadastro!");
                System.out.println("--------------------------------------");
            }
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
    }
    
    //metodo para realiza��o de update(atualiza��o) em algum produto
    public void AtualizarProduto(ProdutosModelo produto)
    {
        try{
            if(con == null)
                con = conexao.getConexao();
            
            String sql = "UPDATE produto SET preco = ? WHERE idproduto=?";
            
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setDouble(1, produto.getPreco());
            statement.setInt(2, produto.getId());
            
            int rowsUpdated = statement.executeUpdate();
            if(rowsUpdated > 0)
            {
                JOptionPane.showMessageDialog(null, "Atualizado com sucesso! \nProduto: " + produto.getNome()+"\nNovo Precoo: " + 
                        produto.getPreco() + "$","Sucesso",JOptionPane.INFORMATION_MESSAGE);
                
                System.out.println("Altera��o feita com sucesso!");
                System.out.println("--------------------------------------");
            }
            else
            {
                System.out.println("Erro com atualiza��o!");
                System.out.println("--------------------------------------");
            }
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
    }

 // Criamos o m�todo para recuperar as informa��es de um determinado produto
    public ProdutosModelo ObterProduto(int id)
    {

/* criamos uma inst�ncia da classe de objeto que ser� onde armazenaremos as informa��es
        do produto que queremos analisar */
        ProdutosModelo produto = new ProdutosModelo();    
        try
        {
            if(con == null)
                con = conexao.getConexao();
            
            String sql = "SELECT * FROM produto WHERE idproduto = ?";    
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            
            result.next();
            produto = new ProdutosModelo(result.getInt(1),result.getString(2),result.getInt(3));          
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
        
        return produto;
    }
 // Criamos o m�todo para recuperar todas as informa��es de todos os produtos no banco de dados
    public ArrayList<ProdutosModelo> ObterProdutos()
    {       

/* criamos um array onde iremos armazenar todas as inst�ncias dos objetos que ir�o armazenar todos
        as informa��es de todos os produtos */
        ArrayList<ProdutosModelo> produtos = new ArrayList<ProdutosModelo>();
        try
        {
            if(con == null)
                con = conexao.getConexao();
            
            String sql = "SELECT * FROM produto";
            Statement statement = con.createStatement();
            ResultSet result = statement.executeQuery(sql);
            
            while(result.next())
            {
                ProdutosModelo produto = new ProdutosModelo(result.getInt(1),result.getString(2),result.getInt(3));
                produtos.add(produto);
            }  
        }catch(SQLException ex)
        {
            ex.printStackTrace();
        }
        return produtos;
    }
    //metodo para excluir um produto do banco de dados
    public void EliminarProduto(int id)
    {
        try{
            if(con == null)
                con = conexao.getConexao();
        
            String sql = "DELETE FROM produto WHERE idproduto=?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, id);
            
            int RowsDeleted = statement.executeUpdate();
            if(RowsDeleted > 0)
            {
                JOptionPane.showMessageDialog(null, "Produto excluido","Advertencia",JOptionPane.WARNING_MESSAGE);
                System.out.println("Produto excluido!");
                System.out.println("--------------------------------------");
            }
            else
            {
                System.out.println("N�o � poss�vel eliminar o produto.");
                System.out.println("--------------------------------------");
            }
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
    }
}
