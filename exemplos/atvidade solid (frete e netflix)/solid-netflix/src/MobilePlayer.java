public class MobilePlayer implements IPlayer, IBaixavel{
    @Override
    public void reproduzir() {
        System.out.println("Reproduzindo via Mobile.");
    }

    @Override
    public void pausar() {
        System.out.println("Pausando via Mobile.");
    }

    @Override
    public void parar() {
        System.out.println("Parando via Mobile.");
    }

    @Override
    public void baixar() {
        System.out.println("Baixando via Mobile.");
    }
}
