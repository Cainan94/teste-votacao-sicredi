package com.teste.sicred.votacao.modules.SessaoVotos.http.dto;

import com.teste.sicred.votacao.modules.pauta.model.Pauta;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
public class SessaoVotosDtoRquest {
    private LocalDateTime dateInicio;

    private int minutosSessao;
    private Pauta pauta;
}
