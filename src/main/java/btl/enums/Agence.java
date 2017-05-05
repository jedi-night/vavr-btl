package btl.enums;

public enum Agence {

    NETAPSYS_BRETAGNE(1,"Netapsys Bretagne"),
    NETAPSYS_PARIS(2,"Netapsys Conseil"),
    SODIFRANCE_RENNES(3,"Sodifrance Rennes");

    private int code;
    private String libelle;

    Agence(int code, String libelle) {
        this.code = code;
        this.libelle = libelle;
    }

    public int getCode() {
        return code;
    }

    public String getLibelle() {
        return libelle;
    }
}
