package modelo;

public enum TipoTerreno {
    UMIDO("Trecho úmido — crescimento acelerado"),
    SECO("Trecho seco — crescimento lento");

    private final String descricao;

    TipoTerreno(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
