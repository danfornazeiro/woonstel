package br.com.woonstel.model.enums;

public enum ScheduledServiceStatus {
    SCHEDULED("Agendado"),
    IN_PROGRESS("Em Andamento"),
    COMPLETED("Concluído"),
    CANCELLED("Cancelado");

    private final String displayName;

    ScheduledServiceStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}

