package br.edu.utfpr.dto;

import br.edu.utfpr.excecao.NomeClienteJaExisteException;
import lombok.Builder;
import lombok.Data;
import br.edu.utfpr.excecao.NomeClienteMenor5CaracteresException;

@Data
@Builder
public class ClienteDTO {
    private int id;
    private String nome;
    private int idade;
    private String telefone;
    private double limiteCredito;
    private PaisDTO pais;

    public void setNome(String nome) throws NomeClienteMenor5CaracteresException, NomeClienteJaExisteException{
        if (nome.length() < 5)
            throw new NomeClienteMenor5CaracteresException(nome);

        if (nome.equals(getNome()))
            throw new NomeClienteJaExisteException(nome);

        this.nome = nome;
    }



    public String getNome() {
        return nome;
    }

    public int getId() {
        return id;
    }

    public int getIdade() {
        return idade;
    }

    public String getTelefone() {
        return telefone;
    }

    public double getLimiteCredito() {
        return limiteCredito;
    }

    public PaisDTO getPais() {
        return pais;
    }
}