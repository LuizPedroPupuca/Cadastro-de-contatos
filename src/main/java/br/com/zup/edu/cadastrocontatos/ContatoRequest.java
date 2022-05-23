package br.com.zup.edu.cadastrocontatos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

public class ContatoRequest {

    @NotBlank @Pattern(regexp = "^\\+[1-9][0-9]\\d{1,14}")
    private String telefone;

    @NotBlank
    private String funcionario;

    @NotNull @Past
    private LocalDate dataCadastro;

    @NotNull
    private DepartamentoRequest departamento;

    public ContatoRequest(String telefone, String funcionario, LocalDate dataCadastro, DepartamentoRequest departamento) {
        this.telefone = telefone;
        this.funcionario = funcionario;
        this.dataCadastro = dataCadastro;
        this.departamento = departamento;
    }

    public ContatoRequest() {
    }

    public String getTelefone() {
        return telefone;
    }

    public String getFuncionario() {
        return funcionario;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public DepartamentoRequest getDepartamento() {
        return departamento;
    }

    public Contato toModel(){
        return new Contato(telefone, funcionario, dataCadastro, departamento.toModel());
    }

}
