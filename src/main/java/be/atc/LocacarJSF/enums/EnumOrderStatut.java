package be.atc.LocacarJSF.enums;

public enum EnumOrderStatut {
    Validate("Validé"),
    Canceled("Annulé"),
    Pending("En attente"),
    ;

    private final String label;

    EnumOrderStatut(String label) {
        this.label = label;
    }

    public String getLabel() {
        return this.label;
    }
}
