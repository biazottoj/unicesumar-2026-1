public class CalculadoraFrete {
    public static Double calculaFrete(Double pesoKg, Integer distanciaKm, ITipoFrete tipoFrete){
        return tipoFrete.calcularFrete(pesoKg, distanciaKm);
    }
}
