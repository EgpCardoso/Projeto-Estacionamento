package atv3;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Estacionamento {
    private List<Vaga> vagas = new ArrayList<>();
    private List<Veiculo> veiculos = new ArrayList<>();

    public void cadastrarVaga(int numero, String tamanho) {
        vagas.add(new Vaga(numero, tamanho));
    }

    public void cadastrarVeiculo(String placa, String modelo, String tamanho) {
        veiculos.add(new Veiculo(placa, modelo, tamanho));
    }

    public void registrarEntrada(String placa) {
        Veiculo veiculo = veiculos.stream().filter(v -> v.getPlaca().equals(placa)).findFirst().orElse(null);
        if (veiculo != null) {
            Vaga vaga = vagas.stream().filter(v -> v.isDisponivel() && v.getTamanho().equals(veiculo.getTamanho())).findFirst().orElse(null);
            if (vaga != null) {
                veiculo.setHoraEntrada(LocalDateTime.now());
                vaga.setDisponivel(false);
                System.out.println("Veículo " + placa + " estacionado na vaga " + vaga.getNumero());
            } else {
                System.out.println("Não há vagas disponíveis para o tamanho do veículo.");
            }
        } else {
            System.out.println("Veículo não cadastrado.");
        }
    }

    public void registrarSaida(String placa) {
        Veiculo veiculo = veiculos.stream().filter(v -> v.getPlaca().equals(placa)).findFirst().orElse(null);
        if (veiculo != null && veiculo.getHoraEntrada() != null) {
            veiculo.setHoraSaida(LocalDateTime.now());
            Duration duracao = Duration.between(veiculo.getHoraEntrada(), veiculo.getHoraSaida());
            long horas = duracao.toHours();
            double valor = calcularValor(horas);
            Vaga vaga = vagas.stream().filter(v -> !v.isDisponivel() && v.getTamanho().equals(veiculo.getTamanho())).findFirst().orElse(null);
            if (vaga != null) {
                vaga.setDisponivel(true);
            }
            System.out.println("Veículo " + placa + " saiu. Tempo de permanência: " + horas + " horas. Valor a pagar: R$ " + valor);
        } else {
            System.out.println("Veículo não encontrado ou não está estacionado.");
        }
    }

    private double calcularValor(long horas) {
        if (horas <= 1) {
            return 5.0;
        } else if (horas <= 3) {
            return 10.0;
        } else {
            return 15.0;
        }
    }

    public void gerarRelatorio() {
        System.out.println("Vagas ocupadas:");
        for (Vaga vaga : vagas) {
            if (!vaga.isDisponivel()) {
                Veiculo veiculo = veiculos.stream().filter(v -> v.getTamanho().equals(vaga.getTamanho()) && v.getHoraEntrada() != null && v.getHoraSaida() == null).findFirst().orElse(null);
                if (veiculo != null) {
                    System.out.println("Vaga " + vaga.getNumero() + " - Tamanho: " + vaga.getTamanho() + " - Placa: " + veiculo.getPlaca());
                }
            }
        }
    }

    public void mostrarHistorico() {
        System.out.println("Histórico de permanência:");
        for (Veiculo veiculo : veiculos) {
            if (veiculo.getHoraEntrada() != null && veiculo.getHoraSaida() != null) {
                Duration duracao = Duration.between(veiculo.getHoraEntrada(), veiculo.getHoraSaida());
                long horas = duracao.toHours();
                double valor = calcularValor(horas);
                System.out.println("Placa: " + veiculo.getPlaca() + " - Tempo de permanência: " + horas + " horas - Valor pago: R$ " + valor);
            }
        }
    }
}
