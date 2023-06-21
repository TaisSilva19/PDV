package pvd;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Caixa {

	private static double saldo;
	private static Scanner scanner = new Scanner(System.in);
	private static List<Pagamento> payments = new ArrayList<>();

	public static void SaldoInicial() {
		System.out.println("\n===== Saldo do Caixa =====");
		System.out.println("Saldo Inicial: R$ " + saldo );

		int[] valoresCedulas = { 100, 50, 20, 10 };
		double[] valoresMoedas = { 1, 0.25, 0.50 };

		double saldoRestante = saldo;
		int[] quantidadeCedulas = new int[valoresCedulas.length];
		int[] quantidadeMoedas = new int[valoresMoedas.length];

		for (int i = 0; i < valoresCedulas.length; i++) {
			quantidadeCedulas[i] = (int) (saldoRestante / valoresCedulas[i]);
			saldoRestante %= valoresCedulas[i];
		}

		for (int i = 0; i < valoresMoedas.length; i++) {
			quantidadeMoedas[i] = (int) (saldoRestante / valoresMoedas[i]);
			saldoRestante %= valoresMoedas[i];
		}

		System.out.println("\nQuantidade de cédulas e moedas: ");

		System.out.println("Cedulas: ");
		for (int i = 0; i < valoresCedulas.length; i++) {
			System.out.println("R$" + valoresCedulas[i] + ": " + quantidadeCedulas[i]);
		}

		System.out.println("\nMoedas: ");
		for (int i = 0; i < valoresMoedas.length; i++) {
			System.out.println("R$" + valoresMoedas[i] + ": " + quantidadeMoedas[i]);
		}
		System.out.println("------------------------------------------------------\n");
	}

	public void ajustaSaldo() {
		System.out.println("\n===== Ajuste de Saldo do Caixa =====");
		System.out.print("Digite o valor do ajuste: ");
		double valorAjuste = scanner.nextDouble();
		scanner.nextLine();

		System.out.println("Digite 'P' para adicionar ou 'N' para remover:");
		String descricao = scanner.nextLine();
		double saldoAntesAjuste = saldo;
		double saldoAjustado = 0;
		
		if (descricao.equalsIgnoreCase("P")) {
		Pagamento payment = new Pagamento(valorAjuste, descricao);
		payments.add(payment);

		saldoAjustado = (saldo + valorAjuste);
		saldo = saldoAjustado;
		}
		
		if (descricao.equalsIgnoreCase("N")) {
			Pagamento payment = new Pagamento(valorAjuste, descricao);
			payments.add(payment);

			saldoAjustado = (saldo - valorAjuste);
			saldo = saldoAjustado;
			}
		
		System.out.println("\n\nSaldo do caixa ajustado com sucesso! \n");
		System.out.printf("\nSaldo antes do ajuste: R$" + "%.2f",saldoAntesAjuste);
		System.out.printf("\nSaldo pré-definido: R$" + "%.2f",valorAjuste);
		System.out.printf("\nSaldo ajustado: R$" + "%.2f",saldoAjustado);
		System.out.println("\n------------------------------------------------------\n\n");
	}

	public static void adicionarAoCaixa(double valor) {
		saldo += valor;
		System.out.println("Valor de R$ " + valor + " adicionado ao caixa \n");

	}
	public static double getSaldo() {
		return saldo;
	}

	public static void setSaldo(double saldo) {
		Caixa.saldo = saldo;
	}
}
