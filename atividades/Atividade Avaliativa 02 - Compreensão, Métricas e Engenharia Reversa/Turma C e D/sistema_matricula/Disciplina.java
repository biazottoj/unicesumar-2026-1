public class Disciplina {
    private String codigo;
    private String nome;
    private int vagas;
    private double valor;

    public Disciplina(String codigo, String nome, int vagas, double valor) {
        this.codigo = codigo;
        this.nome = nome;
        this.vagas = vagas;
        this.valor = valor;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public int getVagas() {
        return vagas;
    }

    public double getValor() {
        return valor;
    }

    public boolean temVagas() {
        return vagas > 0;
    }

    public void reduzirVaga() {
        vagas--;
    }
}
