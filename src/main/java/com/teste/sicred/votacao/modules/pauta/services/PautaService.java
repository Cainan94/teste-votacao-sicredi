package com.teste.sicred.votacao.modules.pauta.services;

import com.teste.sicred.votacao.modules.associados.model.Associados;
import com.teste.sicred.votacao.modules.associados.services.AssociadosServices;
import com.teste.sicred.votacao.modules.pauta.http.dto.PautaDtoRequest;
import com.teste.sicred.votacao.modules.pauta.model.Pauta;
import com.teste.sicred.votacao.modules.pauta.repositorys.PautaRepository;
import com.teste.sicred.votacao.utils.IdGenerate;
import com.teste.sicred.votacao.utils.Strings;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PautaService {

    @Autowired
    private PautaRepository repository;

    @Autowired
    private AssociadosServices associadosServices;

    public Pauta cadastrarPauta(PautaDtoRequest dto) {
        if (Strings.isNullOrEmpty(dto.getName()))
            throw new RuntimeException("Nome da Pauta é obrigatório");
        Pauta entity = Pauta.toModel(dto);
        entity.setId(IdGenerate.getNew());

        return repository.save(entity);
    }

    public Pauta atualizarPauta(PautaDtoRequest dto, String id) {
        if (Strings.isNullOrEmpty(dto.getName()))
            throw new RuntimeException("Nome da Pauta é obrigatório");
        Optional<Pauta> entity = repository.findById(IdGenerate.getFromString(id));
        if (entity.isEmpty())
            throw new RuntimeException("Pauta não encontrado");
        entity.get().setName(dto.getName());
        entity.get().setDescricao(dto.getDescricao());
        return repository.save(entity.get());
    }

    public Pauta deletarPauta(PautaDtoRequest dto, String id) {
        if (Strings.isNullOrEmpty(dto.getName()))
            throw new RuntimeException("Nome da Pauta é obrigatório");
        Optional<Pauta> entity = repository.findById(IdGenerate.getFromString(id));
        if (entity.isEmpty())
            throw new RuntimeException("Pauta não encontrado");

        repository.delete(entity.get());
        return entity.get();
    }

    public Pauta findByAssociado(Associados associados) {
        Associados ass = associadosServices.findById(associados);
        Optional<Pauta> optionalPauta = repository.findByAssociados(ass);
        if (optionalPauta.isEmpty())
            throw new RuntimeException("Usuário não pertence a Pauta");

        Pauta result = optionalPauta.get();
        Set<Associados> associadosSet = new HashSet<>();
        associadosSet.add(ass);
        result.setAssociados(associadosSet);
        return result;
    }
}
