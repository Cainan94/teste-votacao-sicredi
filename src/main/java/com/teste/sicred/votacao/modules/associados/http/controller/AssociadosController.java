package com.teste.sicred.votacao.modules.associados.http.controller;

import com.teste.sicred.votacao.modules.associados.http.dto.AssociadosDtoRequest;
import com.teste.sicred.votacao.modules.associados.model.Associados;
import com.teste.sicred.votacao.modules.associados.services.AssociadosServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/associados")
public class AssociadosController {

    @Autowired
    private AssociadosServices service;

    @PostMapping()
    public ResponseEntity<Associados> cadastrarAssociado(@Valid @RequestBody AssociadosDtoRequest dto){
        return new ResponseEntity<>(service.cadastrar(dto), HttpStatus.OK);
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<Associados> atualizarAssociado(@Valid @RequestBody AssociadosDtoRequest dto, @PathVariable String id){
        return new ResponseEntity<>(service.atualizar(dto, id), HttpStatus.OK);
    }

    @PutMapping(value = "/delete/{id}")
    public ResponseEntity<Associados> deletarAssociado(@Valid @RequestBody AssociadosDtoRequest dto, @PathVariable String id){
        return new ResponseEntity<>(service.deletar(id), HttpStatus.OK);
    }
}
