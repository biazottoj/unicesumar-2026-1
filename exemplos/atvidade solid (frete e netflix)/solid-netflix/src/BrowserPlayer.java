public class BrowserPlayer implements IPlayer,ICookies{
    @Override
    public void reproduzir() {
        System.out.println("Reproduzindo via Browser.");
    }

    @Override
    public void pausar() {
        System.out.println("Pausando via Browser.");
    }

    @Override
    public void parar() {
        System.out.println("Parando via Browser.");
    }

    @Override
    public Boolean cookiesHabilitados() {
        return true;
    }
}
