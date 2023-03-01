package com.teste.sicred.votacao.modules.pauta.model;

import com.teste.sicred.votacao.modules.associados.model.Associados;
import com.teste.sicred.votacao.modules.pauta.http.dto.PautaDtoRequest;
import com.teste.sicred.votacao.modules.SessaoVotos.model.SessaoVotos;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "pauta")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Pauta {

    @Id
    private UUID id;

    @Column
    @NotNull
    private String name;

    @Column
    private String descricao;

    @OneToOne
    private SessaoVotos sessaoVotos = new SessaoVotos();

    @OneToMany
    @JoinColumn
    private Set<Associados> associados = new HashSet<>();

    public static Pauta toModel(PautaDtoRequest dto) {
        return Pauta.builder()
                .descricao(dto.getDescricao())
                .name(dto.getName())
                .build();
    }

}
