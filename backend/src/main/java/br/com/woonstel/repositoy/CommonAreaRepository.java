package br.com.woonstel.repositoy;

import br.com.woonstel.model.CommonArea;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CommonAreaRepository extends JpaRepository<CommonArea, UUID> {
}
