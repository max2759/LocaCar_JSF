package be.atc.LocacarJSF.enums;

public enum EnumTypeAds {
    Sale("Vente"),
    Leasing("Leasing"),
    ;

    private final String label;

    EnumTypeAds(String label) {
        this.label = label;
    }

    public String getLabel() {
        return this.label;
    }
}

