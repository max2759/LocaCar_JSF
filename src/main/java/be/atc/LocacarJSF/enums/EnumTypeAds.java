package be.atc.LocacarJSF.enums;

public enum EnumTypeAds {
    Sale("vente"),
    Leasing("leasing"),
    ;

    private final String label;

    private EnumTypeAds(String label) {
        this.label = label;
    }

    public String getLabel() {
        return this.label;
    }
}

