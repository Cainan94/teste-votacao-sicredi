package com.teste.sicred.votacao.modules.associados.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
@Table(name = "associados")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Associados {

    @Id
    private UUID id;

    @Column
    @NotNull
    private String name;

}
