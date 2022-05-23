package br.com.zup.edu.cadastrocontatos;

import javax.persistence.*;


@Entity
public class Departamento {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(unique = true)
    private String sigla;

    public Departamento(String nome, String sigla) {
        this.nome = nome;
        this.sigla = sigla;
    }

    @Deprecated
    public Departamento() {
    }
}
