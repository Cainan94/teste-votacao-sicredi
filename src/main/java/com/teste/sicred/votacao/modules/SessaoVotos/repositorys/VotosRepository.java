package com.teste.sicred.votacao.modules.SessaoVotos.repositorys;

import com.teste.sicred.votacao.modules.SessaoVotos.model.SessaoVotos;
import com.teste.sicred.votacao.modules.SessaoVotos.model.Voto;
import com.teste.sicred.votacao.modules.associados.model.Associados;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface VotosRepository extends JpaRepository<Voto, UUID> {
    Optional<Voto> findByAssociados(Associados associados);
    Set<Voto> findAllBySessaoVotos(SessaoVotos sessaoVotos);
}
