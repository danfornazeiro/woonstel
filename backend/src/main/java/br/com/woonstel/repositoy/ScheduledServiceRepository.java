package br.com.woonstel.repositoy;

import br.com.woonstel.model.ScheduledService;
import br.com.woonstel.model.enums.ScheduledServiceStatus;
import br.com.woonstel.model.enums.ServiceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface ScheduledServiceRepository extends JpaRepository<ScheduledService, UUID> {

    /**
     * Buscar todos os serviços agendados de um condomínio
     */
    List<ScheduledService> findByCondominium_Id(UUID condominiumId);

    /**
     * Buscar serviços agendados por status
     */
    List<ScheduledService> findByCondominium_IdAndStatus(UUID condominiumId, ScheduledServiceStatus status);

    /**
     * Buscar serviços agendados por tipo de serviço
     */
    List<ScheduledService> findByCondominium_IdAndServiceType(UUID condominiumId, ServiceType serviceType);

    /**
     * Buscar serviços agendados para a semana
     */
    @Query("""
    SELECT s FROM ScheduledService s
    WHERE s.condominium.id = :condominiumId
    AND s.scheduledDate BETWEEN :now AND :futureDate
    ORDER BY s.scheduledDate ASC
""")
    List<ScheduledService> findServicesScheduledForThisWeek(
            @Param("condominiumId") UUID condominiumId,
            @Param("now") LocalDateTime now,
            @Param("futureDate") LocalDateTime futureDate
    );

    /**
     * Buscar serviços que estão vencidos ou em andamento
     */
    @Query("SELECT s FROM ScheduledService s " +
           "WHERE s.condominium.id = :condominiumId " +
           "AND (s.status = 'IN_PROGRESS' OR " +
           "(s.scheduledDate < CURRENT_TIMESTAMP AND s.status != 'COMPLETED'))")
    List<ScheduledService> findOverdueOrInProgressServices(@Param("condominiumId") UUID condominiumId);

    /**
     * Buscar serviços por empresa terceirizada
     */
    List<ScheduledService> findByThirdPartyCompany_Id(UUID companyId);

    /**
     * Calcular custo total gasto com serviços (apenas concluídos)
     */
    @Query("SELECT SUM(s.actualCost) FROM ScheduledService s " +
           "WHERE s.condominium.id = :condominiumId " +
           "AND s.status = 'COMPLETED'")
    Double getTotalActualCostByCondominium(@Param("condominiumId") UUID condominiumId);

}

