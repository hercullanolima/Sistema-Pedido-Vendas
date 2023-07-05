import db.EstoquesDB;
import db.ProdutosDB;
import db.UsuariosBD;
import models.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    static ProdutosDB produtosDB = new ProdutosDB();
    static UsuariosBD usuariosBD = new UsuariosBD();
    static EstoquesDB estoqueDB = new EstoquesDB();

    public static void main(String[] args) throws Exception {


        int option;

        do {
            System.out.println("-------------------------------------------");
            System.out.println("     *** SISTEMA PEDIDO DE VENDAS  ***     ");
            System.out.println("|1.| - Cadastrar produto                  |");
            System.out.println("|2.| - Listar produtos cadastrados:       |");
            System.out.println("|3.| - Cadastrar usuário ADMINISTRADOR    |");
            System.out.println("|4.| - Cadastrar usuário CLIENTE          |");
            System.out.println("|5.| - Listar todos os usuários           |");
            System.out.println("|6.| - Cadastrar novo estoque de produtos |");
            System.out.println("|7.| - Listar todos os estoques           |");
            System.out.println("|0.| - Sair                               |");
            System.out.println("-------------------------------------------");

            Scanner scanner = new Scanner(System.in);

            System.out.print("Qual operação você deseja realizar: ");
            option = scanner.nextInt();

            process(option);
        } while (option !=0);

    }
    public static void process (int option) throws Exception{
        switch (option){
            case 1:{
                Scanner scanner = new Scanner(System.in);

                System.out.print("Qual a descrição que você deseja dar ao novo produto? ");
                String descricao = scanner.nextLine();

                System.out.print("Qual o ID você deseja dar ao novo produto: ");
                int id = scanner.nextInt();

                System.out.print("Qual o preço: ");
                double preco = scanner.nextDouble();

                System.out.print( "Qual a data de validade: ");
                String dataString = scanner.next();

                Date dataValidade = new SimpleDateFormat("dd/MM/yyyy").parse(dataString);

                Produto novoProduto = new Produto(id, descricao, preco, dataValidade);

                produtosDB.addNovoProduto(novoProduto);
                break;

                //novoProduto.setId(id);
                //novoProduto.setDescricao(descricao);


            }
            //LISTAGEM DE PRODUTOS
            case 2:{
                List<Produto> listaDeProdutos = produtosDB.getProdutoList();

                for (Produto produto : listaDeProdutos){

                    System.out.println("------------------------------------------");
                    System.out.println("| Produto(s) abaixo criado com sucesso!  |");
                    System.out.println("--- ID: " + produto.getId());
                    System.out.println("--- Descrição: " + produto.getDescricao());
                    System.out.println("--- Preço: " + produto.getPreco());
                    System.out.println("--- Data de validade: " + produto.getDataValidade());
                    System.out.println("-------------------------------------------");


                }
                break;
            }
            case 3:{
                Scanner scanner = new Scanner(System.in);

                System.out.println("Qual o nome do usuário ADMINISTRADOR: ");
                String nome = scanner.nextLine();

                Admin novoAdmin = new Admin(nome);
                usuariosBD.AddNovoUsuario(novoAdmin);
                break;
            }
            case 4:{
                Scanner scanner = new Scanner(System.in);

                System.out.println("Qual o nome do usuário CLIENTE: ");
                String nome = scanner.nextLine();

                Cliente novoCliente = new Cliente(nome);
                usuariosBD.AddNovoUsuario(novoCliente);
                break;
            }
            case 5:{
                System.out.println("----------------------------------------------");
                System.out.println("-----*** LISTANDO USUÁRIOS CADASTRADOS ***----");
                System.out.println("----------------------------------------------");
                for (Usuario usuario : usuariosBD.getUsuarioList()){
                    System.out.println("ID: " + usuario.getId());
                    System.out.println("NOME: " + usuario.getNome());
                    System.out.println("TIPO: " + usuario.getTipoUsuario());
                    System.out.println("----------------------------------------------");
                }
                break;
            }
            case 6:{
                Scanner scanner = new Scanner(System.in);
                System.out.println("----------------------------------------------");
                System.out.println("---*** CADASTRANDO ESTOQUE DE PRODUTOS ***----");
                System.out.println("----------------------------------------------");

                System.out.print("Qual o identificador do estoque: ");
                String id =scanner.next();

                System.out.print("Qual o produto que será adicionado ao estoque (informe o ID): ");
                int produtoId= scanner.nextInt();

                Produto produto = produtosDB.getProdutoPorID(produtoId);
                System.out.println("PRODUTO ID: " + produto.getId());
                System.out.println("PRODUTO DESCRIÇÂO: " + produto.getDescricao());
                System.out.println("VALIDADE DO PRODUTO: " + produto.getDataValidade());

                System.out.println("Qual a quantidade de produtos a ser adicionado em estoque? ");
                int quantidade = scanner.nextInt();

                Estoque novoEstoque = new Estoque(id, produto, quantidade);
                estoqueDB.addNovoEstoque(novoEstoque);
                break;
            }
            case 7:{
                System.out.println("----------------------------------------------");
                System.out.println("-----*** LISTANDO ESTOQUES CADASTRADOS ***----");
                System.out.println("----------------------------------------------");
                for (Estoque estoque : estoqueDB.getEstoques()) {
                    System.out.println("ID: " + estoque.getId());
                    System.out.println("PRODUTO: " + estoque.getProduto().getDescricao());
                    System.out.println("PREÇO: " + estoque.getProduto().getPreco());
                    System.out.println("QUANTIDADE: " + estoque.getQuantidade());
                    System.out.println("--------------------------------------------");
                }
                break;
            }
        }
    }
}