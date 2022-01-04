package programa;

//classe na qual faremos um modelo da tabela de banco de dados para ter 
//melhor manuseio de todas as informa��es e aplica��o da vis�o pelo usu�rio

/* criamos os valores que as respectivas colunas da tabela assumir�o
do banco de dados */
public class ProdutosModelo 
{
	
    private int id = 0;
    private String nome;
    private double preco;
    
//sobrecarregamos o construtor
    
    /* criamos um construtor sem par�metros para facilitar a opera��o de recupera��o
    de um determinado produto em opera��es crud */
    public ProdutosModelo()
    {
        this.id = 0;
        this.nome = null;
        this.preco = 0;
    }
    
    /*criamos um construtor com 2 par�metros porque a vari�vel id
    � autoincremental no banco de dados, ent�o n�o devemos inseri-lo
    manualmente */
    public ProdutosModelo(String nome, double preco)
    {
        this.nome = nome;
        this.preco = preco;
    }

/* criamos um m�todo com 3 par�metros para poder inserir todos os
    respectivas informa��es nas inst�ncias dos produtos */
    public ProdutosModelo(int id,String nome,double preco)
    {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
    }
    
 // criamos o respectivo setter e getter
    
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @return the preco
     */
    public double getPreco() {
        return preco;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }
    
    /**
     * @param preco the preco to set
     */
    public void setPreco(double preco) {
        this.preco = preco;
    }
}
