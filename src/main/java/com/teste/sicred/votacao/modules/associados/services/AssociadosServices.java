package com.teste.sicred.votacao.modules.associados.services;

import com.teste.sicred.votacao.modules.associados.http.dto.AssociadosDtoRequest;
import com.teste.sicred.votacao.modules.associados.model.Associados;
import com.teste.sicred.votacao.modules.associados.repositorys.AssociadosRepository;
import com.teste.sicred.votacao.modules.pauta.model.Pauta;
import com.teste.sicred.votacao.modules.pauta.services.PautaService;
import com.teste.sicred.votacao.utils.IdGenerate;
import com.teste.sicred.votacao.utils.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AssociadosServices {

    @Autowired
    private AssociadosRepository repository;


    public Associados cadastrar(AssociadosDtoRequest dto) {
        if (Strings.isNullOrEmpty(dto.getName()))
            throw new RuntimeException("Nome de Usuário não pode estar vazio");
        return repository.save(new Associados(IdGenerate.getNew(), dto.getName()));
    }

    public Associados atualizar(AssociadosDtoRequest dto, String id) {
        if (Strings.isNullOrEmpty(dto.getName()))
            throw new RuntimeException("Nome de Usuário não pode estar vazio");
        Optional<Associados> entity = repository.findById(IdGenerate.getFromString(id));
        if (entity.isEmpty())
            throw new RuntimeException("Associado não encontrado");
        entity.get().setName(dto.getName());
        return repository.save(entity.get());
    }

    public Associados deletar(String id) {
        Optional<Associados> entity = repository.findById(IdGenerate.getFromString(id));
        if (entity.isEmpty())
            throw new RuntimeException("Associado não encontrado");
        repository.delete(entity.get());
        return entity.get();
    }

    public Associados findById(Associados associados) {
        if(associados.getId() == null && Strings.isNullOrEmpty(associados.getId().toString()))
            throw new RuntimeException("Informe o Id do associado");
        Optional<Associados> associadosOptional = repository.findById(associados.getId());
        if(associadosOptional.isEmpty())
            throw new RuntimeException("Associado não encontrado");
      return associadosOptional.get();
    }
}
