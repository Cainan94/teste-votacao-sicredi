package com.teste.sicred.votacao.modules.SessaoVotos.model;

import com.teste.sicred.votacao.modules.associados.model.Associados;
import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Voto {

    @Id
    private UUID id;

    @Column
    private boolean isAccept;

    @OneToOne
    @JoinColumn(name = "associado_id")
    private Associados associados = new Associados();

    @ManyToOne
    @JoinColumn(name = "sessao_votos_id")
    private SessaoVotos sessaoVotos = new SessaoVotos();
}
