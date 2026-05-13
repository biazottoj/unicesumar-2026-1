package br.edu.refactoring.estacionamento;

public class Estacionamento {

    private String nome;
    private String cnpj;

    private String rua;
    private String numero;
    private String bairro;
    private String cidade;
    private String estado;
    private String cep;

    public Estacionamento(String nome, String cnpj, String rua, String numero,
                          String bairro, String cidade, String estado, String cep) {
        this.nome = nome;
        this.cnpj = cnpj;
        this.rua = rua;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
    }

    public void imprimirDados() {
        System.out.println("Estacionamento: " + nome);
        System.out.println("CNPJ: " + cnpj);
        System.out.println("Endereço: " + rua + ", " + numero + " - " + bairro);
        System.out.println("Cidade: " + cidade + " - " + estado);
        System.out.println("CEP: " + cep);
    }

    public String getEnderecoCompleto() {
        return rua + ", " + numero + " - " + bairro + ", " + cidade + " - " + estado + ", " + cep;
    }

    public String getNome() {
        return nome;
    }

    public String getCnpj() {
        return cnpj;
    }
}
