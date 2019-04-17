package br.edu.utfpr.dto;

public class PaisDTO {
    private int id;
    private String nome;
    private String sigla;
    private int codigoTelefone;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public int getCodigoTelefone() {
        return codigoTelefone;
    }

    public void setCodigoTelefone(int codigoTelefone) {
        this.codigoTelefone = codigoTelefone;
    }
}