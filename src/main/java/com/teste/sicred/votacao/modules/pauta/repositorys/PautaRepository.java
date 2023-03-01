package com.teste.sicred.votacao.modules.pauta.repositorys;

import com.teste.sicred.votacao.modules.associados.model.Associados;
import com.teste.sicred.votacao.modules.pauta.model.Pauta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PautaRepository extends JpaRepository<Pauta, UUID> {
    Optional<Pauta> findByAssociados(Associados associados);
}
