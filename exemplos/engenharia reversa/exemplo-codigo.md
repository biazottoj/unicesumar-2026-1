```
class Pedido {
    public String clienteEmail;
    public double valorItens;
    public boolean clientePremium;
    public boolean entregaExpressa;
    public boolean internacional;
}

class ClienteRepository {
    public boolean existe(String email) {
        return email != null && email.contains("@");
    }
}

class EmailService {
    public void enviarConfirmacao(String email, String mensagem) {
        System.out.println("Enviando email para " + email + ": " + mensagem);
    }
}

class FreteService {
    public double calcular(Pedido pedido) {
        double frete = 20;

        if (pedido.entregaExpressa) {
            frete += 15;
        }

        if (pedido.internacional) {
            frete += 50;
        }

        return frete;
    }
}

class PedidoService {
    private ClienteRepository clienteRepository = new ClienteRepository();
    private EmailService emailService = new EmailService();
    private FreteService freteService = new FreteService();

    public double fecharPedido(Pedido pedido) {
        if (pedido == null) return -1;
        if (pedido.clienteEmail == null || pedido.clienteEmail.isBlank()) return -2;
        if (!clienteRepository.existe(pedido.clienteEmail)) return -3;

        double total = pedido.valorItens;

        if (pedido.clientePremium) {
            if (pedido.valorItens > 500) {
                total = total * 0.85;
            } else {
                total = total * 0.95;
            }
        } else {
            if (pedido.valorItens > 800) {
                total = total * 0.90;
            }
        }

        double frete = freteService.calcular(pedido);
        total = total + frete + 7.5; // taxa fixa sem nome

        if (total > 1000) {
            emailService.enviarConfirmacao(pedido.clienteEmail, "Pedido VIP aprovado");
        } else {
            emailService.enviarConfirmacao(pedido.clienteEmail, "Pedido aprovado");
        }

        return total;
    }

    public void gerarLogAntigo(Pedido pedido) {
        System.out.println("LOG LEGADO");
    }
}
```