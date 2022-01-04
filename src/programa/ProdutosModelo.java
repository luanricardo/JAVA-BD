package programa;

//classe na qual faremos um modelo da tabela de banco de dados para ter 
//melhor manuseio de todas as informações e aplicação da visão pelo usuário

/* criamos os valores que as respectivas colunas da tabela assumirão
do banco de dados */
public class ProdutosModelo 
{
	
    private int id = 0;
    private String nome;
    private double preco;
    
//sobrecarregamos o construtor
    
    /* criamos um construtor sem parâmetros para facilitar a operação de recuperação
    de um determinado produto em operações crud */
    public ProdutosModelo()
    {
        this.id = 0;
        this.nome = null;
        this.preco = 0;
    }
    
    /*criamos um construtor com 2 parâmetros porque a variável id
    é autoincremental no banco de dados, então não devemos inseri-lo
    manualmente */
    public ProdutosModelo(String nome, double preco)
    {
        this.nome = nome;
        this.preco = preco;
    }

/* criamos um método com 3 parâmetros para poder inserir todos os
    respectivas informações nas instâncias dos produtos */
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
