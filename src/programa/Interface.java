package programa;

//importamos todas as bibliotecas de que precisamos
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import conexao.*;

//estendemos a classe para jframe para ter a interface gráfica
public class Interface extends JFrame
{
	// contador para atualizar a tabela e exibir as informações
	int contador = 0;

	// elementos para conexão e CRUD
	conexao con = new conexao();
    produtosDAO crud = new produtosDAO();
    ArrayList<ProdutosModelo> listarProdutos;

 // elementos da interface gráfica e funcionalidade dos botões etc
     
    String[] titulos = {"id","nome","preco"};
    
    JScrollPane jsp;

    private final JPanel painelPrincipal = new JPanel();
    private final JPanel painelCadastrar = new JPanel(new GridLayout(2,2));
    private final JPanel painelExcluir = new JPanel(new GridLayout(1,2));
    private final JPanel painelAtualizar = new JPanel(new GridLayout(2,2));
    private final JPanel painelTabela = new JPanel(new BorderLayout());
    private static JTable tabela;
    private DefaultTableModel modelo;
    private Button adicionar;
    private Button excluir;
    private Button atualizar;
    private Button consultar;
    private Button sair;
    private JLabel SecaoAdicionar;
    private JLabel SecaoEliminar;
    private JLabel SecaoAtualizar;
    private JLabel nome;
    private JLabel preco;
    private JLabel id;
    private JLabel id2;
    private JTextField campoNome;
    private JTextField campoPreco;
    private JTextField campoPreco2;
    private JTextField campoId;
    private JTextField campoId2;
    
    public Interface()
    {      
        this.setTitle("Produtos em estoque");
        this.setVisible(true);
        this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        
        iniciar_componentes();
        
        this.add(painelPrincipal, BorderLayout.CENTER);
        this.pack();
    }
    
    public void iniciar_componentes()
    {
        painelPrincipal.setLayout(new BoxLayout(painelPrincipal, BoxLayout.Y_AXIS));
        criar_consultas();
        criar_tabela();
    }
    
    public void criar_tabela()
    {
        modelo = new DefaultTableModel();
        modelo.addColumn("Id");
        modelo.addColumn("Nome");
        modelo.addColumn("Preco");

        mostrar_tabela(listarProdutos);
        tabela = new JTable(modelo);

        DefaultTableCellRenderer alinear = new DefaultTableCellRenderer();
        alinear.setHorizontalAlignment(SwingConstants.CENTER);
        
        for(int i = 0; i < tabela.getColumnCount(); i++)
            tabela.getColumnModel().getColumn(i).setCellRenderer(alinear);
        
        jsp = new JScrollPane(tabela);
        
        painelTabela.add(jsp, BorderLayout.CENTER);
        
        consultar = new Button("consultar");
        painelTabela.add(consultar, BorderLayout.AFTER_LINE_ENDS);
        
        ActionListener oyente = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                if(contador == 0)
                {
                    listarProdutos = crud.ObterProdutos();
                    mostrar_tabela(listarProdutos);
                    contador += 1;
                }
                
            }
        };
        consultar.addActionListener(oyente);
        
        sair = new Button("Sair");
        painelTabela.add(sair, BorderLayout.SOUTH);
        
        ActionListener oyente2 = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                System.exit(0);
            }
        };        
        sair.addActionListener(oyente2);
        
        painelPrincipal.add(painelTabela);
    }
    
    public void mostrar_tabela(ArrayList<ProdutosModelo> produtos)
    {
        if(produtos != null)
        {
            String[][] corpo = new String[produtos.size()][3];
            for(int i = 0; i<produtos.size(); i++)
            {
                modelo.addRow(new Object[]{produtos.get(i).getId(),produtos.get(i).getNome(),produtos.get(i).getPreco()});
            }
        }
    }
    
    public void criar_consultas()
    {
        SecaoAdicionar = new JLabel("Selecione para adicionar produtos",SwingConstants.CENTER);
        painelPrincipal.add(SecaoAdicionar);
        
        nome = new JLabel("Nome",SwingConstants.CENTER);
        painelCadastrar.add(nome);
        campoNome = new JTextField();
        painelCadastrar.add(campoNome);
        
        preco = new JLabel("Preco",SwingConstants.CENTER);
        painelCadastrar.add(preco);
        campoPreco = new JTextField();
        painelCadastrar.add(campoPreco);
         
        painelPrincipal.add(painelCadastrar);
        
        adicionar = new Button("Adicionar");
        painelPrincipal.add(adicionar);
        ActionListener oyente = new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                ProdutosModelo produtoNovo = new ProdutosModelo(campoNome.getText(),Double.parseDouble(campoPreco.getText()));
                crud.AdicionarProduto(produtoNovo);
                
                listarProdutos = crud.ObterProdutos();
                modelo.addRow(new Object[]{listarProdutos.get(listarProdutos.size()-1).getId(),listarProdutos.get(listarProdutos.size()-1).getNome(),listarProdutos.get(listarProdutos.size()-1).getPreco()});
                modelo.fireTableDataChanged();
            }
        };
        adicionar.addActionListener(oyente);
        
        SecaoEliminar = new JLabel("Selecione para excluir um produto",SwingConstants.CENTER);
        painelPrincipal.add(SecaoEliminar);
    
        id = new JLabel("Id",SwingConstants.CENTER);
        painelExcluir.add(id);
        campoId = new JTextField();
        painelExcluir.add(campoId);
        
        painelPrincipal.add(painelExcluir);
        
        excluir = new Button("Excluir");
        ActionListener oyente2 = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                crud.EliminarProduto(Integer.parseInt(campoId.getText()));
                
                int fila = 0;
                for(int i = 0; i<listarProdutos.size(); i++)
                {
                    if(listarProdutos.get(i).getId() == Integer.parseInt(campoId.getText()))
                        break;
                    
                    fila += 1;
                }
                listarProdutos = crud.ObterProdutos();
                modelo.removeRow(fila);
                modelo.fireTableDataChanged();
            }
        };
        excluir.addActionListener(oyente2);
        painelPrincipal.add(excluir);
        
        SecaoAtualizar = new JLabel("Selecione para atualizar um produto",SwingConstants.CENTER);
        painelPrincipal.add(SecaoAtualizar);
    
        id2 = new JLabel("Id",SwingConstants.CENTER);
        painelAtualizar.add(id2);
        campoId2 = new JTextField();
        painelAtualizar.add(campoId2);
        preco = new JLabel("Novo Preco",SwingConstants.CENTER);
        painelAtualizar.add(preco);
        campoPreco2 = new JTextField();
        painelAtualizar.add(campoPreco2);
        
        painelPrincipal.add(painelAtualizar);
        
        atualizar = new Button("Atualizar");
        ActionListener oyente3 = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                ProdutosModelo produtoNovo = crud.ObterProduto(Integer.parseInt(campoId2.getText()));
                produtoNovo.setPreco(Integer.parseInt(campoPreco2.getText()));
                crud.AtualizarProduto(produtoNovo);
                
                listarProdutos = crud.ObterProdutos();
                int fila = 0;
                for(int i = 0; i<listarProdutos.size(); i++)
                {
                    if(listarProdutos.get(i).getId() == Integer.parseInt(campoId2.getText()))
                        break;
                    
                    fila += 1;
                }
                modelo.setValueAt(produtoNovo.getPreco(), fila, 2);
                modelo.fireTableDataChanged();
            }
        };

        atualizar.addActionListener(oyente3);
        painelPrincipal.add(atualizar);
    }
}
