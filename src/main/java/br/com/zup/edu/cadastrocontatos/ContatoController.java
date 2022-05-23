package br.com.zup.edu.cadastrocontatos;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.Map;

@RestController
public class ContatoController {

    private final ContatoRepository contatoRepository;

    public ContatoController(ContatoRepository contatoRepository) {
        this.contatoRepository = contatoRepository;
    }

    @PostMapping("/contato")
    public ResponseEntity<?> cadastra(@RequestBody @Valid ContatoRequest contatoRequest,
                                      UriComponentsBuilder uriComponentsBuilder){
        if(contatoRepository.existsByTelefoneAndDepartamento_Sigla(contatoRequest.getTelefone(),
                contatoRequest.getDepartamento().getSigla())){
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Telefone e silga já existentes");
        }

        Contato contato = contatoRequest.toModel();

        contatoRepository.save(contato);

        URI location = uriComponentsBuilder.path("contato/{id}").buildAndExpand(contato.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> errorUniqueException(ConstraintViolationException e, WebRequest request){
        Map<String, Object> body = Map.of(
               "code", 422,
                "status", "UNPROCESSABLE_ENTITY",
                "timestamp", LocalDateTime.now(),
                "path", request.getDescription(false).replace("uri=",""),
                "message", "Telefone e silga já existentes"

        );
        return ResponseEntity.unprocessableEntity().body(body);
    }
}
