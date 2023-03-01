package com.teste.sicred.votacao.modules.SessaoVotos.http.controller;

import com.teste.sicred.votacao.modules.SessaoVotos.http.dto.SessaoVotosDtoRquest;
import com.teste.sicred.votacao.modules.SessaoVotos.http.dto.VotoDtoRequest;
import com.teste.sicred.votacao.modules.SessaoVotos.model.SessaoVotos;
import com.teste.sicred.votacao.modules.SessaoVotos.model.Voto;
import com.teste.sicred.votacao.modules.SessaoVotos.services.SessaoVotosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/sessaoVotacao")
public class SessaoVotosController {

    @Autowired
    private SessaoVotosService sessaoVotosService;

    @PostMapping
    public ResponseEntity<SessaoVotos> criarSessaoVotacao(@RequestBody SessaoVotosDtoRquest dto){
        return ResponseEntity.ok(sessaoVotosService.iniciarSessaoVotos(dto));
    }

    @GetMapping
    public ResponseEntity<SessaoVotos> consultaSessao(@RequestBody SessaoVotos sessaoVotos){
        return ResponseEntity.ok(sessaoVotosService.buscarSessaoVotos(sessaoVotos));
    }
}
