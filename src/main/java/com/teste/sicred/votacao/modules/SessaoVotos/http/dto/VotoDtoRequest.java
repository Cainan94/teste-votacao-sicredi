package com.teste.sicred.votacao.modules.SessaoVotos.http.dto;

import com.teste.sicred.votacao.modules.SessaoVotos.model.SessaoVotos;
import com.teste.sicred.votacao.modules.associados.model.Associados;
import lombok.Getter;

@Getter
public class VotoDtoRequest {
    private boolean isAccept;
    private Associados associados = new Associados();
    private SessaoVotos sessaoVotos = new SessaoVotos();
}
