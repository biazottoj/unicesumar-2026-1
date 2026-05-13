package br.edu.refactoring.estacionamento;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

public class TicketEstacionamento {

    private String codigo;
    private Veiculo veiculo;
    private Vaga vaga;
    private boolean perdido;

    public TicketEstacionamento(String codigo, Veiculo veiculo, Vaga vaga, boolean perdido) {
        this.codigo = codigo;
        this.veiculo = veiculo;
        this.vaga = vaga;
        this.perdido = perdido;
    }

    public void imprimirTicket() {
        LocalDateTime entrada = veiculo.getDataHoraEntrada();

        String entradaFormatada = entrada.getDayOfMonth() + "/"
                + entrada.getMonthValue() + "/"
                + entrada.getYear() + " "
                + entrada.getHour() + ":"
                + String.format("%02d", entrada.getMinute());

        System.out.println("Ticket: " + codigo);
        System.out.println("Veículo: " + veiculo.getPlaca());
        System.out.println("Vaga: " + vaga.getCodigo());
        System.out.println("Entrada: " + entradaFormatada);

        if (entrada.getDayOfWeek() == DayOfWeek.SATURDAY
                || entrada.getDayOfWeek() == DayOfWeek.SUNDAY
                || entrada.getHour() < 7
                || entrada.getHour() >= 22) {
            System.out.println("Período: horário especial");
        } else {
            System.out.println("Período: horário regular");
        }

        if (perdido) {
            System.out.println("Situação: ticket perdido");
        } else {
            System.out.println("Situação: ticket regular");
        }
    }

    public String getCodigo() {
        return codigo;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public Vaga getVaga() {
        return vaga;
    }

    public boolean isPerdido() {
        return perdido;
    }

    public LocalDateTime getDataHoraEntrada() {
        return veiculo.getDataHoraEntrada();
    }

    public LocalDateTime getDataHoraSaidaPrevista() {
        return veiculo.getDataHoraSaidaPrevista();
    }
}
