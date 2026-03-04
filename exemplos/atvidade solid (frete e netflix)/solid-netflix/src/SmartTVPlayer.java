public class SmartTVPlayer implements IPlayer{
    @Override
    public void reproduzir() {
        System.out.println("Reproduzindo via SmartTV.");
    }

    @Override
    public void pausar() {
        System.out.println("Pausando via SmartTV.");
    }

    @Override
    public void parar() {
        System.out.println("Parando via SmartTV.");
    }
}
