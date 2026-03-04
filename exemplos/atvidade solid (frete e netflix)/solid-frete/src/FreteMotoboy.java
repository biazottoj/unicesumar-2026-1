public class FreteMotoboy implements ITipoFrete{
    @Override
    public Double calcularFrete(Double pesoKg, Integer distanciaKm) {

        if(distanciaKm > 15){
            System.out.println("Tipo de frete inválida para entregas a mais de 15KM!");
            return -1.0;
        }

        if(pesoKg > 10){
            System.out.println("Entrega inválida para encomendas com mais de 10Kg!");
            return -1.0;
        }

        return 8.00 + (2.00 * distanciaKm);
    }
}
