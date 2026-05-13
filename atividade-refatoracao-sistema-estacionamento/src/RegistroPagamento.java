package br.edu.refactoring.estacionamento;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class RegistroPagamento {

    private TicketEstacionamento ticket;
    private Cliente cliente;
    private BigDecimal valor;

    public RegistroPagamento(TicketEstacionamento ticket, Cliente cliente, BigDecimal valor) {
        this.ticket = ticket;
        this.cliente = cliente;
        this.valor = valor;
    }

    public void imprimirComprovante() {
        String valorFormatado = "R$ "
                + valor.setScale(2, RoundingMode.HALF_UP)
                       .toString()
                       .replace(".", ",");

        System.out.println("Pagamento do ticket: " + ticket.getCodigo());
        System.out.println("Cliente: " + cliente.getNome());
        System.out.println("Valor: " + valorFormatado);

        if (valor.compareTo(new BigDecimal("50.00")) >= 0) {
            System.out.println("Classificação: pagamento de alto valor");
        } else {
            System.out.println("Classificação: pagamento comum");
        }
    }

    public TicketEstacionamento getTicket() {
        return ticket;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public BigDecimal getValor() {
        return valor;
    }
}
