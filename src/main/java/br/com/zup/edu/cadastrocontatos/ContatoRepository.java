package br.com.zup.edu.cadastrocontatos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ContatoRepository extends JpaRepository<Contato, Long> {
    boolean existsByTelefoneAndDepartamento_Sigla(String telefone, String sigla);
}
