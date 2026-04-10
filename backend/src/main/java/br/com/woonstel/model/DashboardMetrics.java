package br.com.woonstel.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "dashboard_metrics_tb")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DashboardMetrics {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    /**
     * Número de problemas (solicitations) em aberto
     */
    private Long openProblems;

    /**
     * Número de tarefas em andamento
     */
    private Long tasksInProgress;

    /**
     * Número de tarefas concluídas no período
     */
    private Long completedTasks;

    /**
     * Número de tarefas com prioridade alta
     */
    private Long highPriorityTasks;

    /**
     * Custo total estimado de todas as tarefas abertas
     */
    private Double totalEstimatedCost;

    /**
     * Custo total gasto (tarefas concluídas)
     */
    private Double totalActualCost;

    /**
     * Número de serviços agendados para a semana
     */
    private Long scheduledServicesThisWeek;

    /**
     * Número de reservas de áreas comuns confirmadas
     */
    private Long confirmedReservations;

    /**
     * Taxa de conclusão de tarefas (percentual)
     */
    private Double completionRate;

    /**
     * Data do último cálculo das métricas
     */
    private LocalDateTime lastUpdated;

    @ManyToOne
    @JoinColumn(name = "condominium_id", nullable = false)
    private Condominium condominium;

}

