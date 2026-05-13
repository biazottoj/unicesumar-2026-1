package br.edu.refactoring.estacionamento;

public class CentralClientes {

    public int calcularDescontoCliente(Cliente cliente) {
        if (cliente.isMensalista() && cliente.getPontosFidelidade() >= 4000) {
            return 20;
        }

        if (cliente.getMesesComoCliente() >= 12 && cliente.getPontosFidelidade() >= 2000) {
            return 10;
        }

        if (cliente.getPontosFidelidade() >= 1000) {
            return 5;
        }

        return 0;
    }
}
