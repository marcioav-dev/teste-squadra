package org.example.controller;

import org.example.entity.SistemaEntity;
import org.example.repository.SistemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.time.LocalDateTime;
import java.util.List;
import javax.validation.Valid;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/api/sistemas")
public class SistemaController {

    private final SistemaRepository repository;

    @Autowired
    public SistemaController(SistemaRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SistemaEntity save(@RequestBody @Valid SistemaEntity sistema){
        if (sistema.getStatus() == null){
            sistema.setStatus("ATIVO");
        }
        if(sistema.getJustificativa_ultima_alteracao() == null){
            sistema.setJustificativa_ultima_alteracao("Sistema criado!");
        }
        if (sistema.getUsuario_ultima_alteracao() == null){
            sistema.setUsuario_ultima_alteracao("usuarioTeste");
        }
        return repository.save(sistema);
    }

    @GetMapping
    public List<SistemaEntity> findAll(){
        return repository.findAll();
    }

    @GetMapping("{id}")
    public SistemaEntity findById(@PathVariable Integer id){
        return repository.findById(id).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @RequestMapping(value = "/pesquisar", method = RequestMethod.GET)
    public Page<SistemaEntity> findCustom(@RequestParam("descricao") String descricao,
                                          @RequestParam("sigla") String sigla,
                                          @RequestParam("email") String email,
                                          @RequestParam(value = "page", defaultValue = "0") Integer pagina,
                                          @RequestParam(value = "size", defaultValue = "10") Integer paginaTamanho){
        System.out.println(pagina.toString());
        PageRequest pageRequest = PageRequest.of(pagina, paginaTamanho);
        SistemaEntity sistemaEntity = new SistemaEntity();
        if (descricao.equals("null")){
            descricao = null;
        }
        if (sigla.equals("null")){
            sigla = null;
        }
        if (email.equals("null")){
            email = null;
        }
        sistemaEntity.setDescricao(descricao);
        sistemaEntity.setSigla(sigla);
        sistemaEntity.setEmail_de_atendimento_sistema(email);
        if((sistemaEntity.getDescricao() != null) && (sistemaEntity.getSigla() != null) && (sistemaEntity.getEmail_de_atendimento_sistema() != null)){
            return repository.findByParams(sistemaEntity.getDescricao(), sistemaEntity.getSigla() , sistemaEntity.getEmail_de_atendimento_sistema(), pageRequest);
        }else if(sistemaEntity.getDescricao() != null && sistemaEntity.getSigla()  == null && sistemaEntity.getEmail_de_atendimento_sistema() == null){
            return repository.findByDescricao(sistemaEntity.getDescricao(), pageRequest);
        }else if(sistemaEntity.getDescricao() == null && sistemaEntity.getSigla()  != null && sistemaEntity.getEmail_de_atendimento_sistema() == null){
            return repository.findBySigla(sistemaEntity.getSigla(), pageRequest);
        }else if(sistemaEntity.getDescricao() == null && sistemaEntity.getSigla()  == null && sistemaEntity.getEmail_de_atendimento_sistema() != null){
            return repository.findByEmailDeAtendimento(sistemaEntity.getEmail_de_atendimento_sistema(), pageRequest);
        }else if(sistemaEntity.getDescricao() != null && sistemaEntity.getSigla()  != null && sistemaEntity.getEmail_de_atendimento_sistema() == null){
            return repository.findByDescricaoSigla(sistemaEntity.getDescricao(), sistemaEntity.getSigla(), pageRequest);
        }else if(sistemaEntity.getDescricao() != null && sistemaEntity.getSigla()  == null && sistemaEntity.getEmail_de_atendimento_sistema() != null){
            return repository.findByDescricaoEmail(sistemaEntity.getDescricao(), sistemaEntity.getEmail_de_atendimento_sistema(), pageRequest);
        }else if(sistemaEntity.getDescricao() == null && sistemaEntity.getSigla()  != null && sistemaEntity.getEmail_de_atendimento_sistema() != null){
            return repository.findBySiglaEmail(sistemaEntity.getSigla() , sistemaEntity.getEmail_de_atendimento_sistema(), pageRequest);
        }else{
            return repository.findAll(pageRequest);
        }

    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id){
        repository.findById(id)
                .map( sistemaEntity -> {
                    repository.delete(sistemaEntity);
                    return Void.TYPE;
                })
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }


    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Integer id, @RequestBody @Valid SistemaEntity sistemaEntityToUpdate){
        repository.findById(id)
                .map( sistemaEntity -> {
                    if (sistemaEntity.getUsuario_ultima_alteracao() == null){
                        sistemaEntity.setUsuario_ultima_alteracao("usuarioTeste");
                    }
                    sistemaEntity.setDescricao(sistemaEntityToUpdate.getDescricao());
                    sistemaEntity.setSigla(sistemaEntityToUpdate.getSigla());
                    sistemaEntity.setEmail_de_atendimento_sistema(sistemaEntityToUpdate.getEmail_de_atendimento_sistema());
                    sistemaEntity.setUrl_sistema(sistemaEntityToUpdate.getUrl_sistema());
                    sistemaEntity.setStatus(sistemaEntityToUpdate.getStatus());
                    sistemaEntity.setUsuario_ultima_alteracao(sistemaEntityToUpdate.getUsuario_ultima_alteracao());
                    sistemaEntity.setJustificativa_ultima_alteracao(sistemaEntityToUpdate.getJustificativa_ultima_alteracao());
                    sistemaEntity.setData_ultima_alteracao(LocalDateTime.now());
                    return repository.save(sistemaEntity);
                })
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
