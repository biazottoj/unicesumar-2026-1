package br.edu.refactoring.estacionamento;

import java.time.Duration;

public class OperacaoEstacionamento {

    public long calcularMinutosPermanencia(TicketEstacionamento ticket) {
        return Duration.between(
                ticket.getDataHoraEntrada(),
                ticket.getDataHoraSaidaPrevista()).toMinutes();
    }
}
