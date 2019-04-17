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

    public void setIdade(int idade) {

        if(idade <= 18){
            limiteCredito = 100;
        }

        if(idade > 18 && idade > 35){
            limiteCredito = 300;
        }

        if(idade >= 35){
            limiteCredito = 500;
        }
        this.idade = idade;
    }

    public void setPais(PaisDTO pais) {
        if(pais.equals("Brasil")){
            limiteCredito = 100;
        }
        this.pais = pais;
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