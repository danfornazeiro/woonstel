package br.com.woonstel.model.enums;

public enum ServiceType {
    ELECTRICAL("Elétrica"),
    HYDRAULIC("Hidráulica"),
    ELEVATOR("Elevador"),
    AUTOMATIC_GATE("Portão Automático"),
    CLEANING("Limpeza"),
    GARDENING("Jardinagem"),
    SECURITY("Segurança"),
    STRUCTURAL("Manutenção Estrutural"),
    PAINTING("Pintura"),
    PLUMBING("Encanamento"),
    HVAC("Ar Condicionado"),
    OTHER("Outro");

    private final String displayName;

    ServiceType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}

