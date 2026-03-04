public class SessaoStreaming {
    public SessaoStreaming(IPlayer player) {
        player.reproduzir();
        player.pausar();
        player.parar();

        if(player instanceof IBaixavel){
            ((IBaixavel) player).baixar();
        }

        if(player instanceof ICookies){
            System.out.println(((ICookies) player).cookiesHabilitados());
        }
    }
}
