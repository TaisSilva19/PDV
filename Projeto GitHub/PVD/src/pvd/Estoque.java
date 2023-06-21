package pvd;

import java.util.Map;
import java.util.Scanner;

public class Estoque {

	private Scanner scanner = new Scanner(System.in);
	private Produto produto;

	public void alteraPrecoEstoque(Map<String, Produto> estoque) {
		System.out.println("\n===== Alteração de Preço e Estoque =====");
		System.out.print("Digite o código do produto: ");
		String codigo = scanner.nextLine();
		produto = estoque.get(codigo);

		if (produto != null) {
			System.out.println("Produto: " + produto.getNome());
			System.out.print("Digite o novo preço do produto: ");
			double novoPreco = scanner.nextDouble();
			System.out.print("Digite a nova quantidade em estoque do produto: ");
			int novoEstoque = scanner.nextInt();
			scanner.nextLine();
			produto.setPreco(novoPreco);
			produto.setEstoque(novoEstoque);
			System.out.println("Preço e estoque do produto atualizados com sucesso!");
		} else {
			System.out.println("Produto não encontrado.");
			System.out.println("------------------------------------------------------\n");
		}
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

}
