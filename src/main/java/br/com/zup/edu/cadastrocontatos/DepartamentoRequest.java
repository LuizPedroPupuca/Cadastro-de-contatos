package br.com.zup.edu.cadastrocontatos;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class DepartamentoRequest {

    @NotBlank @Size(max = 120)
    private String nome;

    @NotBlank @Pattern(regexp = "[A-Z]") @Size(max = 3)
    private String sigla;

    public DepartamentoRequest(String nome, String sigla) {
        this.nome = nome;
        this.sigla = sigla;
    }

    public DepartamentoRequest() {
    }

    public String getNome() {
        return nome;
    }

    public String getSigla() {
        return sigla;
    }

    public Departamento toModel(){
        return new Departamento(nome, sigla);
    }
}
