package br.com.woonstel.repositoy;

import br.com.woonstel.model.DashboardMetrics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DashboardMetricsRepository extends JpaRepository<DashboardMetrics, UUID> {

    /**
     * Buscar métricas por condomínio
     */
    Optional<DashboardMetrics> findByCondominium_Id(UUID condominiumId);

    /**
     * Atualizar ou criar métricas do condomínio
     */
    @Query(value = "INSERT INTO dashboard_metrics_tb " +
           "(condominium_id, open_problems, tasks_in_progress, completed_tasks, high_priority_tasks, " +
           "total_estimated_cost, total_actual_cost, scheduled_services_this_week, confirmed_reservations, " +
           "completion_rate, last_updated, id) " +
           "SELECT " +
           ":condominiumId, " +
           "(SELECT COUNT(*) FROM request_tb WHERE condominium_id = :condominiumId AND status = 'OPEN'), " +
           "(SELECT COUNT(*) FROM task_tb WHERE condominium_id = :condominiumId AND status = 'IN_PROGRESS'), " +
           "(SELECT COUNT(*) FROM task_tb WHERE condominium_id = :condominiumId AND status = 'COMPLETED'), " +
           "(SELECT COUNT(*) FROM task_tb WHERE condominium_id = :condominiumId AND priority = 'HIGH'), " +
           "COALESCE((SELECT SUM(budget) FROM task_tb WHERE condominium_id = :condominiumId), 0), " +
           "COALESCE((SELECT SUM(actual_cost) FROM task_tb WHERE condominium_id = :condominiumId AND actual_cost IS NOT NULL), 0), " +
           "(SELECT COUNT(*) FROM scheduled_service_tb WHERE condominium_id = :condominiumId AND scheduled_date BETWEEN CURRENT_TIMESTAMP AND CURRENT_TIMESTAMP + INTERVAL 7 DAY), " +
           "(SELECT COUNT(*) FROM reservation_tb WHERE condominium_id = :condominiumId AND status = 'CONFIRMED'), " +
           "COALESCE(CAST((SELECT COUNT(*) FROM task_tb WHERE condominium_id = :condominiumId AND status = 'COMPLETED') AS DECIMAL) / " +
           "NULLIF((SELECT COUNT(*) FROM task_tb WHERE condominium_id = :condominiumId), 0) * 100, 0), " +
           "CURRENT_TIMESTAMP, " +
           "gen_random_uuid() " +
           "ON CONFLICT (condominium_id) DO UPDATE SET " +
           "open_problems = (SELECT COUNT(*) FROM request_tb WHERE condominium_id = :condominiumId AND status = 'OPEN'), " +
           "tasks_in_progress = (SELECT COUNT(*) FROM task_tb WHERE condominium_id = :condominiumId AND status = 'IN_PROGRESS'), " +
           "completed_tasks = (SELECT COUNT(*) FROM task_tb WHERE condominium_id = :condominiumId AND status = 'COMPLETED'), " +
           "high_priority_tasks = (SELECT COUNT(*) FROM task_tb WHERE condominium_id = :condominiumId AND priority = 'HIGH'), " +
           "total_estimated_cost = COALESCE((SELECT SUM(budget) FROM task_tb WHERE condominium_id = :condominiumId), 0), " +
           "total_actual_cost = COALESCE((SELECT SUM(actual_cost) FROM task_tb WHERE condominium_id = :condominiumId AND actual_cost IS NOT NULL), 0), " +
           "scheduled_services_this_week = (SELECT COUNT(*) FROM scheduled_service_tb WHERE condominium_id = :condominiumId AND scheduled_date BETWEEN CURRENT_TIMESTAMP AND CURRENT_TIMESTAMP + INTERVAL 7 DAY), " +
           "confirmed_reservations = (SELECT COUNT(*) FROM reservation_tb WHERE condominium_id = :condominiumId AND status = 'CONFIRMED'), " +
           "last_updated = CURRENT_TIMESTAMP",
           nativeQuery = true)
    void refreshMetricsForCondominium(@Param("condominiumId") UUID condominiumId);

}

