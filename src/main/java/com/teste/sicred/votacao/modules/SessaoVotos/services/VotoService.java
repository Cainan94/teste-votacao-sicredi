package com.teste.sicred.votacao.modules.SessaoVotos.services;

import com.teste.sicred.votacao.modules.SessaoVotos.http.dto.VotoDtoRequest;
import com.teste.sicred.votacao.modules.SessaoVotos.model.SessaoVotos;
import com.teste.sicred.votacao.modules.SessaoVotos.model.Voto;
import com.teste.sicred.votacao.modules.SessaoVotos.repositorys.VotosRepository;
import com.teste.sicred.votacao.modules.associados.model.Associados;
import com.teste.sicred.votacao.modules.pauta.model.Pauta;
import com.teste.sicred.votacao.modules.pauta.services.PautaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class VotoService {


    @Autowired
    private VotosRepository votosRepository;

    @Autowired
    private SessaoVotosService sessaoVotosService;

    @Autowired
    private PautaService pautaService;

    public Voto votar(VotoDtoRequest dto) {
        SessaoVotos sessaoVotos = sessaoVotosService.buscarSessaoVotos(dto.getSessaoVotos());
        if (!sessaoVotos.isOpen())
            throw new RuntimeException("Não pode votar em sessão fechada");
        Pauta pauta = pautaService.findByAssociado(dto.getAssociados());
        if (associadoJaVotou(pauta.getAssociados().stream().findFirst().get()) == false)
            throw new RuntimeException("Associado já votou");
        return votosRepository.save(Voto.builder()
                .sessaoVotos(sessaoVotos)
                .isAccept(dto.isAccept())
                .associados(pauta.getAssociados().stream().findFirst().get())
                .build());
    }

    private Voto validaVoto(VotoDtoRequest dto) {
        SessaoVotos sessaoVotos = sessaoVotosService.buscarSessaoVotos(dto.getSessaoVotos());
        if (!sessaoVotos.isOpen())
            throw new RuntimeException("Não pode votar em sessão fechada");
        Pauta pauta = pautaService.findByAssociado(dto.getAssociados());
        if (associadoJaVotou(pauta.getAssociados().stream().findFirst().get()) == false)
            throw new RuntimeException("Associado já votou");

        return Voto.builder()
                .sessaoVotos(sessaoVotos)
                .isAccept(dto.isAccept())
                .associados(pauta.getAssociados().stream().findFirst().get())
                .build();


    }

    private boolean associadoJaVotou(Associados associados) {
        Optional<Voto> votoOptional = votosRepository.findByAssociados(associados);
        if (votoOptional.isEmpty())
            return true;
        return false;
    }


    public String contabilizaVotacao(SessaoVotos sessaoVotos) {
        sessaoVotos = sessaoVotosService.findById(sessaoVotos);

        Set<Voto> votoSet = votosRepository.findAllBySessaoVotos(sessaoVotos);
        int sim = 0;
        int nao = 0;
        for(Voto v : votoSet){
            if(v.isAccept())
                sim++;
            else nao++;
        }

        return "A seção de votos da Pauta: "+sessaoVotos.getPauta().getName()+" Teve o total de "+ sim+nao+" votos sendo eles "+sim+" Sim, e "+nao+" Não";
    }

}
