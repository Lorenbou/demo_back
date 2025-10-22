package br.com.satc.demo.domain.enums;

public enum Status {
    INATIVO(0, "Inativo"),
    ATIVO(1, "Ativo");

    private final Integer codigo;
    private final String descricao;

    Status(Integer codigo, String descricao) { this.codigo = codigo; this.descricao = descricao; }
    public Integer getCodigo() { return codigo; }
    public String getDescricao() { return descricao; }

    public static Status toEnum(Integer cod) {
        if (cod == null) return null;
        for (Status s : values()) if (cod.equals(s.getCodigo())) return s;
        throw new IllegalArgumentException("Status inválido: " + cod);
    }
    public static String getDescricaoPorCodigo(Integer cod) {
        Status s = toEnum(cod);
        return (s != null) ? s.getDescricao() : "Código inválido";
    }
}
