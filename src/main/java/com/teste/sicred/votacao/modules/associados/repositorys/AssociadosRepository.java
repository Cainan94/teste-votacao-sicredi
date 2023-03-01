package com.teste.sicred.votacao.modules.associados.repositorys;


import com.teste.sicred.votacao.modules.associados.model.Associados;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AssociadosRepository extends JpaRepository<Associados, UUID> {
}
