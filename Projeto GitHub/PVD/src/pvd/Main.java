package pvd;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

	private static Map<String, Produto> products = new HashMap<>();
	private static Produto produto = new Produto();
	private static Estoque estoque = new Estoque();
	private static Venda venda = new Venda(produto, 0, 0, "");
	private static Caixa caixa = new Caixa();

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
	    System.out.println("===== Bem Vindo ao sistema da MakeUP Plaza =====");
	    System.out.println("           Tecle ENTER para abrir");
	    scanner.nextLine();
	    System.out.println("Digite o saldo inicial do caixa para abrir o sistema: ");
	    double saldoInicial = scanner.nextDouble();
	    Caixa.setSaldo(saldoInicial);
	    Caixa.SaldoInicial();
		
		int choice;
		do {
			System.out.println("===== SELECIONE A OPÇÃO DESEJADA =====");
			System.out.println("1 - Saldo do caixa");
			System.out.println("2 - Cadastrar produtos");
			System.out.println("3 - Estoque de Produtos");
			System.out.println("4 - Alterar preço e estoque");
			System.out.println("5 - Ajustar saldo do caixa");
			System.out.println("6 - Venda de produtos");
			System.out.println("7 - Histórico de vendas");
			System.out.println("8 - Consulta cupom fiscal");
			System.out.println("0 - Sair");
			System.out.print("\nEscolha uma opção: ");
			choice = scanner.nextInt();
			scanner.nextLine();

			switch (choice) {
			case 1:
				Caixa.SaldoInicial();
				break;

			case 2:
				Produto.CadastroDeProduto(products);
				break;

			case 3:
				Produto.listaProdutos(products);
				break;

			case 4:
				estoque.alteraPrecoEstoque(products);
				break;

			case 5:
				caixa.ajustaSaldo();
				break;

			case 6:
				venda.vendeProduto(products);
				break;

			case 7:
				venda.historico();
				break;

			case 8:
				venda.emitirCupomFiscal();
				break;

			case 0:
				System.out.println("Saindo do sistema ...");
				scanner.close();
				return;
			default:
				System.out.println("Opção inválida. Tente novamente.");
			}

			System.out.println();
		} while (choice != 0);

		scanner.close();
	}

}
