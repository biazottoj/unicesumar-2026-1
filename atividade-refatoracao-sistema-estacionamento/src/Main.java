package br.edu.refactoring.estacionamento;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Main {

    public static void main(String[] args) {
        Estacionamento estacionamento = new Estacionamento(
                "ParkTech Centro",
                "12.345.678/0001-90",
                "Avenida Brasil",
                "1500",
                "Centro",
                "Maringá",
                "PR",
                "87000-000");

        Cliente cliente = new Cliente(
                "Mariana Souza",
                "123.456.789-00",
                "(44) 99999-0000",
                4200,
                18,
                true,
                "Banco Acadêmico",
                "1234",
                "98765-0",
                "Pix");

        Veiculo veiculo = new Veiculo(
                "ABC-1234",
                "Honda Civic",
                "Prata",
                LocalDateTime.of(2026, 6, 20, 9, 30),
                LocalDateTime.of(2026, 6, 20, 12, 15),
                "Seguro Total",
                "AP-889900",
                "0800-123-456");

        Vaga vaga = new Vaga(
                "A12",
                "Setor Azul",
                true,
                true,
                "Mariana Souza",
                LocalDateTime.of(2026, 6, 20, 9, 0));

        TicketEstacionamento ticket = new TicketEstacionamento("TCK-1001", veiculo, vaga, false);
        ReservaVaga reserva = new ReservaVaga(vaga, cliente);
        RegistroPagamento pagamento = new RegistroPagamento(ticket, cliente, new BigDecimal("48.5"));
        OperacaoEstacionamento operacao = new OperacaoEstacionamento();
        CentralClientes centralClientes = new CentralClientes();
        PainelVagas painelVagas = new PainelVagas();

        estacionamento.imprimirDados();
        System.out.println();

        cliente.imprimirResumo();
        System.out.println();

        veiculo.imprimirDados();
        System.out.println();

        ticket.imprimirTicket();
        System.out.println();

        reserva.imprimirReserva();
        System.out.println();

        System.out.println("Minutos de permanência: " + operacao.calcularMinutosPermanencia(ticket));
        System.out.println("Desconto do cliente: " + centralClientes.calcularDescontoCliente(cliente) + "%");
        System.out.println("Status da vaga: " + painelVagas.descreverStatus(vaga));
        System.out.println();

        pagamento.imprimirComprovante();
    }
}
