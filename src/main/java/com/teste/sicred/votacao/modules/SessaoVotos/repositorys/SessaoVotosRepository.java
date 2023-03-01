package com.teste.sicred.votacao.modules.SessaoVotos.repositorys;

import com.teste.sicred.votacao.modules.SessaoVotos.model.SessaoVotos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SessaoVotosRepository extends JpaRepository<SessaoVotos, UUID> {
}
