package com.teste.sicred.votacao.modules.SessaoVotos.model;

import com.teste.sicred.votacao.modules.pauta.model.Pauta;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "SessaoVotos")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class SessaoVotos {

    @Id
    private UUID id;

    @Column
    private boolean isOpen;
    @Column
    private LocalDateTime dateInicio;

    @Column
    private LocalDateTime dateFim;

    @OneToOne
    @JoinColumn(name = "pauta_id")
    private Pauta pauta;

    @OneToMany
    private Set<Voto> votos = new HashSet<>();
}
