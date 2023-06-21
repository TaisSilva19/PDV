package pvd;

import java.util.Map;
import java.util.Scanner;

public class Produto {

	private static Scanner scanner = new Scanner(System.in);
	private static Produto produto;
	private String codigo;
	private String nome;
	private int estoque;
	private double preco;
	private String codigosBarras;

	public Produto(String codigo, String nome, int estoque, double preco, String codigosBarras) {
		this.codigo = codigo;
		this.nome = nome;
		this.estoque = estoque;
		this.preco = preco;
		this.codigosBarras = codigosBarras;
	}

	public Produto() {
	}

		public static void CadastroDeProduto(Map<String, Produto> listaProducts) {
		String opcao;
		do {
		System.out.println("\n===== Cadastro de Produtos =====");
		System.out.print("Digite o código do produto: ");
		String codigo = scanner.nextLine();
		System.out.print("Digite o nome do produto: ");
		String nome = scanner.nextLine();
		System.out.print("Digite a quantidade em estoque do produto: ");
		int estoque = scanner.nextInt();
		System.out.print("Digite o preço do produto: ");
		double preco = scanner.nextDouble();
		scanner.nextLine();
		System.out.print("Digite o código de barras: ");
	     String codigosBarras = scanner.nextLine();
			
		Produto produto = new Produto(codigo, nome, estoque, preco, codigosBarras);
		listaProducts.put(codigo, produto);
		Produto.produto = produto;

		System.out.println("\nConfirme os dados do produto:");
        System.out.println("Código: " + produto.getCodigo());
        System.out.println("Nome: " + produto.getNome());
        System.out.println("Estoque: " + produto.getEstoque());
        System.out.printf("Preço: R$" + "%.2f",produto.getPreco());
        System.out.println("\nCódigo de Barras: " + produto.getCodigosBarras());
	        
        System.out.print("\nOs dados estão corretos? (S/N): ");
        opcao = scanner.nextLine();

        if (opcao.equalsIgnoreCase("S")) {
            listaProducts.put(codigo, produto);
            Produto.produto = produto;
            System.out.println("Produto cadastrado com sucesso!");
        } else {
            System.out.println("Cadastro cancelado. Tente novamente.");
        }

        System.out.println("------------------------------------------------------\n");

        System.out.print("Deseja cadastrar outro produto? (S/N): ");
        opcao = scanner.nextLine();
    } while (opcao.equalsIgnoreCase("S"));
	
	}	

	public static void listaProdutos(Map<String, Produto> listaProducts) {
		System.out.println("\n===== Estoque de Produtos =====");
		for (Produto product : listaProducts.values()) {
			System.out.println("Código: " + product.getCodigo());
			System.out.println("Nome: " + product.getNome());
			System.out.println("Estoque: " + product.getEstoque());
			System.out.printf("Preço: R$" + "%.2f",product.getPreco());
			System.out.println("\nCodigo de Barras: " + product.getCodigosBarras());
			System.out.println("------------------------------------------------------\n");
		}
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getEstoque() {
		return estoque;
	}

	public void setEstoque(int estoque) {
		this.estoque = estoque;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public String getCodigosBarras() {
		return codigosBarras;
	}

	public void setCodigosBarras(String codigosBarras) {
		this.codigosBarras = codigosBarras;

	}

	public Scanner getScanner() {
		return scanner;
	}

	public void setScanner(Scanner scanner) {
		Produto.scanner = scanner;
	}

	public static Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		Produto.produto = produto;

	}
}
