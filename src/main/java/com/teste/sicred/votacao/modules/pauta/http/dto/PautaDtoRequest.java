package com.teste.sicred.votacao.modules.pauta.http.dto;

import com.teste.sicred.votacao.modules.associados.model.Associados;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
public class PautaDtoRequest {
    private String name;
    private String descricao;

    private List<Associados> associados;
}
