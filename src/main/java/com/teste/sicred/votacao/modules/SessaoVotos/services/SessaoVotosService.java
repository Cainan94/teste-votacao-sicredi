package com.teste.sicred.votacao.modules.SessaoVotos.services;

import com.teste.sicred.votacao.modules.SessaoVotos.http.dto.SessaoVotosDtoRquest;
import com.teste.sicred.votacao.modules.SessaoVotos.http.dto.VotoDtoRequest;
import com.teste.sicred.votacao.modules.SessaoVotos.model.SessaoVotos;
import com.teste.sicred.votacao.modules.SessaoVotos.model.Voto;
import com.teste.sicred.votacao.modules.SessaoVotos.repositorys.SessaoVotosRepository;
import com.teste.sicred.votacao.modules.SessaoVotos.repositorys.VotosRepository;
import com.teste.sicred.votacao.modules.associados.model.Associados;
import com.teste.sicred.votacao.modules.pauta.model.Pauta;
import com.teste.sicred.votacao.modules.pauta.services.PautaService;
import com.teste.sicred.votacao.utils.IdGenerate;
import com.teste.sicred.votacao.utils.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.TimeZone;

@Service
public class SessaoVotosService {

    @Autowired
    private SessaoVotosRepository sessaoVotosRepository;

    public SessaoVotos iniciarSessaoVotos(SessaoVotosDtoRquest dto) {

        SessaoVotos entity = criarSessaoVotos(dto);
        return sessaoVotosRepository.save(entity);
    }

    private SessaoVotos criarSessaoVotos(SessaoVotosDtoRquest dto) {
        return SessaoVotos.builder()
                .id(IdGenerate.getNew())
                .dateFim(this.calculaTempoSessao(dto.getMinutosSessao()))
                .pauta(dto.getPauta())
                .dateInicio(LocalDateTime.now())
                .isOpen(true)
                .build();
    }

    private SessaoVotos encerrarSessaoVotos(SessaoVotos entity) {
        entity.setOpen(false);
        return sessaoVotosRepository.save(entity);
    }

    private LocalDateTime calculaTempoSessao(Integer minutosSessao) {
        if (minutosSessao == null || minutosSessao == 0)
            minutosSessao = 1;
        Calendar agora = Calendar.getInstance();
        agora.add(Calendar.MINUTE, minutosSessao);

        TimeZone tz = agora.getTimeZone();
        ZoneId zoneId = tz.toZoneId();
        return LocalDateTime.ofInstant(agora.toInstant(), zoneId);
    }

    public SessaoVotos  buscarSessaoVotos(SessaoVotos sessaoVotos) {
        if (sessaoVotos.getId() == null && Strings.isNullOrEmpty(sessaoVotos.getId().toString()))
            throw new RuntimeException("Informe id da sessão");
        Optional<SessaoVotos> optionalSessaoVotos = sessaoVotosRepository.findById(sessaoVotos.getId());
        if(optionalSessaoVotos.isEmpty())
            throw new RuntimeException("Sessão não encontrada.");
        return this.validaDataSessao(optionalSessaoVotos.get());
    }

    private SessaoVotos validaDataSessao(SessaoVotos sessaoVotos) {
        if(sessaoVotos.isOpen() && sessaoVotos.getDateFim().compareTo(LocalDateTime.now())<0){
            return encerrarSessaoVotos(sessaoVotos);
        }
        return sessaoVotos;
    }

    public SessaoVotos findById(SessaoVotos sessaoVotos){
        if(sessaoVotos == null && Strings.isNullOrEmpty(sessaoVotos.getId().toString()))
            throw new RuntimeException("Informe o id da sessão");
        Optional<SessaoVotos> optionalSessaoVotos = sessaoVotosRepository.findById(sessaoVotos.getId());
        if(optionalSessaoVotos.isEmpty())
            throw new RuntimeException("Sessão não encontrada");
        return optionalSessaoVotos.get();

    }

}
