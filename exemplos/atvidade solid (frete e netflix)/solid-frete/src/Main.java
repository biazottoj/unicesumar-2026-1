//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        ITipoFrete frete = new FreteDrone();
        System.out.println(CalculadoraFrete.calculaFrete(4.0,
                3, frete));
    }
}