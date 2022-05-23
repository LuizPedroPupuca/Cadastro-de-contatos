package br.com.zup.edu.cadastrocontatos;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.time.LocalDate;

@Table(uniqueConstraints = {
        @UniqueConstraint(name = "Unique_ingresso_numero_data"
                , columnNames = {"telefone", "departamento_id"})
})

@Entity
public class Contato {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String telefone;

    @Column(nullable = false)
    private String funcionario;

    @Column(nullable = false)
    private LocalDate dataCadastro;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    private Departamento departamento;

    public Contato(String telefone, String funcionario, LocalDate dataCadastro, Departamento departamento) {
        this.telefone = telefone;
        this.funcionario = funcionario;
        this.dataCadastro = dataCadastro;
        this.departamento = departamento;
    }

    public Contato() {
    }

    public Long getId() {
        return id;
    }
}
