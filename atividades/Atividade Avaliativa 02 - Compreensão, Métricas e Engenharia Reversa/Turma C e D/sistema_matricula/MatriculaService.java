public class MatriculaService {
    private MatriculaRepository repo;
    private PagamentoService pagamentoService;
    private NotificadorEmail emailService;

    public MatriculaService(MatriculaRepository repo,
                            PagamentoService pagamentoService,
                            NotificadorEmail emailService) {
        this.repo = repo;
        this.pagamentoService = pagamentoService;
        this.emailService = emailService;
    }

    public String processarMatricula(Aluno aluno,
                                     Disciplina disciplina,
                                     boolean bolsa,
                                     boolean periodoAjuste,
                                     boolean documentosOk) {

        if (aluno != null) {
            if (disciplina != null) {
                if (!repo.jaMatriculado(aluno.getRa(), disciplina.getCodigo())) {
                    if (disciplina.temVagas()) {
                        if (documentosOk) {
                            double valor = disciplina.getValor();

                            if (bolsa) {
                                valor = valor * 0.5;
                            }

                            if (periodoAjuste) {
                                valor = valor + 20;
                            }

                            if (aluno.isAdimplente()) {
                                if (pagamentoService.cobrar(aluno, valor)) {
                                    Matricula matricula =
                                            new Matricula(aluno, disciplina, valor, true);

                                    repo.salvar(matricula);
                                    emailService.enviarConfirmacao(aluno, disciplina);
                                    disciplina.reduzirVaga();

                                    return "Matrícula concluída";
                                } else {
                                    return "Pagamento recusado";
                                }
                            } else {
                                return "Aluno inadimplente";
                            }
                        } else {
                            return "Documentação pendente";
                        }
                    } else {
                        return "Sem vagas";
                    }
                } else {
                    return "Aluno já matriculado";
                }
            } else {
                return "Disciplina inválida";
            }
        } else {
            return "Aluno inválido";
        }
    }
}
