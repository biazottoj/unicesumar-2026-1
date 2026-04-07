public class Matricula {
    private Aluno aluno;
    private Disciplina disciplina;
    private double valorPago;
    private boolean ativa;

    public Matricula(Aluno aluno, Disciplina disciplina, double valorPago, boolean ativa) {
        this.aluno = aluno;
        this.disciplina = disciplina;
        this.valorPago = valorPago;
        this.ativa = ativa;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public double getValorPago() {
        return valorPago;
    }

    public boolean isAtiva() {
        return ativa;
    }
}
