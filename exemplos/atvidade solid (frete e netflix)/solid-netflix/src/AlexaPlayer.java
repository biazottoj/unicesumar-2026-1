public class AlexaPlayer implements IPlayer, IBaixavel, ICookies{
    @Override
    public void baixar() {
        System.out.println("Baixar via Alexa.");
    }

    @Override
    public Boolean cookiesHabilitados() {
        return null;
    }

    @Override
    public void reproduzir() {
        System.out.println("Reproduzindo via Alexa.");
    }

    @Override
    public void pausar() {
        System.out.println("Pausando via Alexa.");
    }

    @Override
    public void parar() {
        System.out.println("Parando via Alexa.");
    }
}
