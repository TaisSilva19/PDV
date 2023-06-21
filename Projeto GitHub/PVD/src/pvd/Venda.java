package pvd;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Venda {
	private Produto produto;
	private int quantidade;
	private Date data;
	private String hora;
	private String sistema;
	private String metodoDePagamento;
	private double precoVenda;
	private String nome;
	private boolean venderMais = true;
	private String nomeVendedor;
	private static List<Venda> sales = new ArrayList<>();
	private Scanner scanner = new Scanner(System.in);

	public Venda(Produto produto, int quantidade, double valor, String metodoDePagamento) {
		this.produto = produto;
		this.quantidade = quantidade;
		this.precoVenda = valor;
		this.metodoDePagamento = metodoDePagamento;
	}

	public void vendeProduto(Map<String, Produto> venda) {
		System.out.println("\n===== Venda de Produtos =====");
		System.out.println("Vendedor:");
		String nomeVendedor = scanner.nextLine();
		System.out.println("\nEscolha entre os produtos cadastrados:\n");

		for (Produto produto : venda.values()) {
			System.out.println("Nome: " + produto.getNome());
			System.out.println("Código de barras: " + produto.getCodigosBarras());
		}

		System.out.println("\nDigite o código de barras desejado:");
		String codigo = scanner.nextLine();
		
		
		
		Produto produto = venda.get(codigo);

		if (produto != null) {
			System.out.println("Produto selecionado: " + produto.getNome());
			setProduto(produto);

			System.out.print("Digite a quantidade vendida: ");
			int quantidadeVendida = scanner.nextInt();
			scanner.nextLine();
			setQuantidade(quantidadeVendida);

			boolean metodoValido = false;
			do {
				System.out.print("Digite o método de pagamento:\n");
				System.out.println("1 - Cartão de Débito");
				System.out.println("2 - Cartão de Crédito");
				System.out.println("3 - Pix");
				System.out.println("4 - Dinheiro");
				int metodoDePagamento = scanner.nextInt();
				scanner.nextLine();

				switch (metodoDePagamento) {
				case 1:
					setMetodoDePagamento("Cartão de Débito");
					metodoValido = true;
					break;

				case 2:
					setMetodoDePagamento("Cartão de Crédito");
					metodoValido = true;
					break;

				case 3:
					setMetodoDePagamento("Pix");
					metodoValido = true;
					break;

				case 4:
					setMetodoDePagamento("Dinheiro");
					metodoValido = true;
					break;

				default:
					System.out.println("Método de pagamento inválido. Tente novamente.");
					break;
				}
			} while (!metodoValido);

			if (getQuantidade() <= produto.getEstoque()) {
				setPrecoVenda(produto.getPreco());
				produto.setEstoque(produto.getEstoque() - getQuantidade());

				System.out.println("\nProdutos no carrinho:");
				System.out.println("Produto: " + produto.getNome());
				System.out.println("Quantidade Vendida: " + getQuantidade());
				System.out.printf("Valor Total: R$%.2f", getSaldoTotal());

				System.out.println("\nConfirmar venda? (S/N):");
				String opcao = scanner.nextLine();

				if (opcao.equalsIgnoreCase("S")) {
					produto.setEstoque(produto.getEstoque() - getQuantidade());

					Venda vendas = new Venda(produto, getQuantidade(), getSaldoTotal(), getMetodoDePagamento());
					vendas.setNomeVendedor(nomeVendedor);
					sales.add(vendas);
					Caixa.adicionarAoCaixa(getSaldoTotal());

					System.out.println("\nDeseja realizar uma nova venda? (S/N):");
					String respostaCompra = scanner.nextLine();

					if (respostaCompra.equalsIgnoreCase("S")) {
						vendeProduto(venda);
					} else {
						System.out.println("Obrigado por comprar no MakeUP Plaza, volte sempre!");
						System.out.println("------------------------------------------------------\n");

						System.out.print("Deseja imprimir o cupom fiscal? (S/N):");
						String respostaCupom = scanner.nextLine();

						if (respostaCupom.equalsIgnoreCase("S")) {
							emitirCupomFiscal();
							imprimirCupomFiscal(vendas);
						}
					}
				} else {
					System.out.println("Venda cancelada.");
				}
			} else {
				System.out.println("Quantidade insuficiente em estoque.");
			}
		} else {
			System.out.println("Código de produto inválido.");
		}
	}

	public void emitirCupomFiscal() {
		LocalDateTime dataAtual = LocalDateTime.now();
		DateTimeFormatter formatoDataHora = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

		System.out.println("\n===== Cupom Fiscal =====");
		System.out.println("Empresa: MakeUP Plaza");
		System.out.println("Data: " + dataAtual.format(formatoDataHora));
		for (Venda sale : sales) {
			System.out.println("\nVendedor: " + sale.getNomeVendedor());
			System.out.println("Produto: " + sale.getProduto().getNome());
			System.out.println("Quantidade Vendida: " + sale.getQuantidade());
			System.out.printf("Valor total da venda: R$" + "%.2f", sale.getSaldoTotal());
			System.out.println();
			System.out.println("Método de Pagamento: " + sale.getMetodoDePagamento());
			System.out.println("------------------------------------------------------");
		}
	}

	public void historico() {
		System.out.println("\n===== Histórico de Vendas =====");
		System.out.println("1 - Resumido");
		System.out.println("2 - Detalhado");
		System.out.print("Escolha uma opção: ");
		int opçao = scanner.nextInt();
		scanner.nextLine();
		if (opçao == 1) {
			System.out.println("\n===== Histórico de Vendas Resumido =====");
			for (Venda sale : sales) {
				System.out.println("Produto: " + sale.getProduto().getNome());
				System.out.println("Quantidade vendida: " + sale.getQuantidade());
				System.out.printf("Valor vendido: R$" + "%.2f", sale.precoVenda);
				System.out.println("\n------------------------------------------------------\n");
			}
		} else if (opçao == 2) {
			System.out.println("\n===== Histórico de Vendas Detalhado =====");
			for (Venda sale : sales) {
				System.out.println("Venda #" + (sales.indexOf(sale) + 1));
				System.out.println("Produto: " + sale.getProduto().getNome());
				System.out.println("Quantidade vendida: " + sale.getQuantidade());
				System.out.printf("Valor unitário: R$" + "%.2f", sale.precoVenda);
				System.out.printf("\nValor total da compra: R$" + "%.2f", sale.getSaldoTotal());
				System.out.println("\nMétodo de Pagamento: " + sale.getMetodoDePagamento());
				System.out.println("------------------------------------------------------\n");
			}
		} else {
			System.out.println("Opção inválida.");
			System.out.println("------------------------------------------------------\n");
		}
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public double getSaldoTotal() {
		return getPrecoVenda() * getQuantidade();
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public String getSistema() {
		return sistema;
	}

	public void setSistema(String sistema) {
		this.sistema = sistema;
	}

	public String getMetodoDePagamento() {
		return metodoDePagamento;
	}

	public void setMetodoDePagamento(String metodoDePagamento) {
		this.metodoDePagamento = metodoDePagamento;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public boolean isVenderMais() {
		return venderMais;
	}

	public void setVenderMais(boolean venderMais) {
		this.venderMais = venderMais;
	}

	public String getNomeVendedor() {
		return nomeVendedor;
	}

	public void setNomeVendedor(String nomeVendedor) {
		this.nomeVendedor = nomeVendedor;
	}

	private void imprimirCupomFiscal(Venda vendas) {
	}

	public double getPrecoVenda() {
		return precoVenda;
	}

	public void setPrecoVenda(double precoVenda) {
		this.precoVenda = precoVenda;
	}
}
