package br.com.woonstel.model.enums;

public enum SolicitationStatus {
    OPEN("Aberto"),
    IN_ANALYSIS("Em Análise"),
    IN_PROGRESS("Em Andamento"),
    WAITING_EXTERNAL("Aguardando Serviço Externo"),
    COMPLETED("Concluído"),
    CANCELLED("Cancelado");

    private final String displayName;

    SolicitationStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}

