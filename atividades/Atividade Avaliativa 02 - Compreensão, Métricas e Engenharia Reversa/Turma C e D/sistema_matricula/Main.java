public class Main {
    public static void main(String[] args) {
        Aluno aluno = new Aluno("2024001", "Maria", "maria@email.com", true);
        Disciplina disciplina = new Disciplina("ES01", "Engenharia de Software", 10, 500.0);

        MatriculaRepository repo = new MatriculaRepository();
        PagamentoService pagamentoService = new PagamentoService();
        NotificadorEmail emailService = new NotificadorEmail();

        MatriculaService matriculaService =
                new MatriculaService(repo, pagamentoService, emailService);

        String resultado = matriculaService.processarMatricula(
                aluno,
                disciplina,
                true,
                false,
                true
        );

        System.out.println(resultado);
    }
}
