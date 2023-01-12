package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroMedico dados){
        repository.save(new Medico(dados));
        System.out.println(dados);
    }

    //pageableDefault configura o padrao para paginacao
    @GetMapping
    public Page<DadosListagemMedico> listar(@PageableDefault(size = 10, page = 0, sort = {"nome"}) Pageable paginacao){
        return repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new);
    }
    @Transactional
    @PutMapping
    public void atualizar(@RequestBody @Valid DadosAtualizaoMedico dados){
        var medico = repository.getReferenceById(dados.id());
        medico.atualizarInformacoes(dados);
    }
    @Transactional
    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id){
        //Na funcionalidade de Delete o registro nao sera apagado (definido pelo cliente). Setaremos o valor de "ativo" para false.
        var medico = repository.getReferenceById(id);
        medico.excluir();
    }


}
