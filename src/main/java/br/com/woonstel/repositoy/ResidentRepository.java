package br.com.woonstel.repositoy;

import br.com.woonstel.model.Resident;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ResidentRepository extends JpaRepository<Resident, UUID> {
}
