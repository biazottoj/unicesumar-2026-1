package br.edu.refactoring.estacionamento;

import java.time.LocalDateTime;

public class Veiculo {

    private String placa;
    private String modelo;
    private String cor;

    private LocalDateTime dataHoraEntrada;
    private LocalDateTime dataHoraSaidaPrevista;

    private String seguradora;
    private String numeroApolice;
    private String telefoneAssistencia;

    public Veiculo(String placa, String modelo, String cor,
                   LocalDateTime dataHoraEntrada, LocalDateTime dataHoraSaidaPrevista,
                   String seguradora, String numeroApolice, String telefoneAssistencia) {
        this.placa = placa;
        this.modelo = modelo;
        this.cor = cor;
        this.dataHoraEntrada = dataHoraEntrada;
        this.dataHoraSaidaPrevista = dataHoraSaidaPrevista;
        this.seguradora = seguradora;
        this.numeroApolice = numeroApolice;
        this.telefoneAssistencia = telefoneAssistencia;
    }

    public void imprimirDados() {
        System.out.println("Veículo: " + modelo);
        System.out.println("Placa: " + placa);
        System.out.println("Cor: " + cor);
        System.out.println("Entrada registrada: " + dataHoraEntrada);
        System.out.println("Saída prevista: " + dataHoraSaidaPrevista);
        System.out.println("Seguro: " + getDadosSeguroFormatados());
    }

    public String getDadosSeguroFormatados() {
        return seguradora + " | Apólice " + numeroApolice + " | Assistência " + telefoneAssistencia;
    }

    public String getPlaca() {
        return placa;
    }

    public String getModelo() {
        return modelo;
    }

    public String getCor() {
        return cor;
    }

    public LocalDateTime getDataHoraEntrada() {
        return dataHoraEntrada;
    }

    public LocalDateTime getDataHoraSaidaPrevista() {
        return dataHoraSaidaPrevista;
    }

    public String getSeguradora() {
        return seguradora;
    }

    public String getNumeroApolice() {
        return numeroApolice;
    }

    public String getTelefoneAssistencia() {
        return telefoneAssistencia;
    }
}
