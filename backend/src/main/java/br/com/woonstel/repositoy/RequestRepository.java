package br.com.woonstel.repositoy;

import br.com.woonstel.model.Solicitation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RequestRepository extends JpaRepository<Solicitation, UUID> {
}
