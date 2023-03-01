package com.teste.sicred.votacao.modules.SessaoVotos.http.controller;

import com.teste.sicred.votacao.modules.SessaoVotos.http.dto.VotoDtoRequest;
import com.teste.sicred.votacao.modules.SessaoVotos.model.SessaoVotos;
import com.teste.sicred.votacao.modules.SessaoVotos.model.Voto;
import com.teste.sicred.votacao.modules.SessaoVotos.services.VotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/v1/voto")
public class votoController {

    @Autowired
    private VotoService service;

    @PostMapping
    public ResponseEntity<Voto> votar(VotoDtoRequest dto){
        return ResponseEntity.ok(service.votar(dto));
    }

    @PostMapping
    public ResponseEntity<String> contabilizarVotacao(@RequestBody SessaoVotos sessaoVotos){
        return ResponseEntity.ok(service.contabilizaVotacao(sessaoVotos));
    }
}
