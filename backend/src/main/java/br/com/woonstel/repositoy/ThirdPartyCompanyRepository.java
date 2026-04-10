package br.com.woonstel.repositoy;

import br.com.woonstel.model.ThirdPartyCompany;
import br.com.woonstel.model.enums.ServiceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ThirdPartyCompanyRepository extends JpaRepository<ThirdPartyCompany, UUID> {

    /**
     * Buscar todas as empresas de um condomínio
     */
    List<ThirdPartyCompany> findByCondominium_Id(UUID condominiumId);

    /**
     * Buscar empresas por tipo de serviço em um condomínio
     */
    List<ThirdPartyCompany> findByCondominium_IdAndServiceType(UUID condominiumId, ServiceType serviceType);

    /**
     * Buscar empresa por CNPJ
     */
    Optional<ThirdPartyCompany> findByCnpj(String cnpj);

    /**
     * Buscar empresas com melhor avaliação
     */
    @Query("SELECT c FROM ThirdPartyCompany c " +
           "WHERE c.condominium.id = :condominiumId " +
           "ORDER BY c.averageRating DESC")
    List<ThirdPartyCompany> findTopRatedCompaniesByCondominium(@Param("condominiumId") UUID condominiumId);

}

