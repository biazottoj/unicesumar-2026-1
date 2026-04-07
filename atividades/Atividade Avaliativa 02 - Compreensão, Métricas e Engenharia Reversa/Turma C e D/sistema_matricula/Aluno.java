public class Aluno {
    private String ra;
    private String nome;
    private String email;
    private boolean adimplente;

    public Aluno(String ra, String nome, String email, boolean adimplente) {
        this.ra = ra;
        this.nome = nome;
        this.email = email;
        this.adimplente = adimplente;
    }

    public String getRa() {
        return ra;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public boolean isAdimplente() {
        return adimplente;
    }
}
