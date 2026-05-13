package br.edu.refactoring.estacionamento;

public class ReservaVaga {

    private Vaga vaga;
    private Cliente cliente;

    public ReservaVaga(Vaga vaga, Cliente cliente) {
        this.vaga = vaga;
        this.cliente = cliente;
    }

    public void imprimirReserva() {
        System.out.println("Reserva da vaga: " + vaga.getCodigo());
        System.out.println("Setor: " + vaga.getSetor());
        System.out.println("Cliente da reserva: " + vaga.getNomeClienteReserva());
        System.out.println("Cliente vinculado: " + cliente.getNome());
        System.out.println("Horário da reserva: " + vaga.getHorarioReserva());
    }

    public Vaga getVaga() {
        return vaga;
    }

    public Cliente getCliente() {
        return cliente;
    }
}
