package atv3;

import java.util.Scanner;

public class SistemaEstacionamento {
    public static void main(String[] args) {
        Estacionamento estacionamento = new Estacionamento();
        Scanner scanner = new Scanner(System.in);

        // Cadastro de vagas
        for (int i = 1; i <= 5; i++) {
            estacionamento.cadastrarVaga(i, "pequeno");
            estacionamento.cadastrarVaga(i + 5, "médio");
            estacionamento.cadastrarVaga(i + 10, "grande");
        }

        while (true) {
            System.out.println("Escolha uma opção:");
            System.out.println("1. Cadastrar veículo");
            System.out.println("2. Registrar entrada de veículo");
            System.out.println("3. Registrar saída de veículo");
            System.out.println("4. Gerar relatório de vagas ocupadas");
            System.out.println("5. Mostrar histórico de permanência");
            System.out.println("6. Sair");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha

            switch (opcao) {
                case 1:
                    System.out.println("Digite a placa do veículo:");
                    String placa = scanner.nextLine();
                    System.out.println("Digite o modelo do veículo:");
                    String modelo = scanner.nextLine();
                    System.out.println("Digite o tamanho do veículo (pequeno, médio, grande):");
                    String tamanho = scanner.nextLine();
                    estacionamento.cadastrarVeiculo(placa, modelo, tamanho);
                    break;
                case 2:
                    System.out.println("Digite a placa do veículo:");
                    placa = scanner.nextLine();
                    estacionamento.registrarEntrada(placa);
                    break;
                case 3:
                    System.out.println("Digite a placa do veículo:");
                    placa = scanner.nextLine();
                    estacionamento.registrarSaida(placa);
                    break;
                case 4:
                    estacionamento.gerarRelatorio();
                    break;
                case 5:
                    estacionamento.mostrarHistorico();
                    break;
                case 6:
                    System.out.println("Saindo...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
}
