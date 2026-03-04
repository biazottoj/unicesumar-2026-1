public class FreteDrone implements ITipoFrete{
    @Override
    public Double calcularFrete(Double pesoKg, Integer distanciaKm) {
        if(distanciaKm > 3){
            System.out.println("Tipo de frete inválida para entregas a mais de 3KM!");
            return -1.0;
        }

        if(pesoKg > 4){
            System.out.println("Entrega inválida para encomendas com mais de 4Kg!");
            return -1.0;
        }

        return 20.00 + (3.00 * distanciaKm);
    }
}
