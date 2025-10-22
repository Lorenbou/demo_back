package br.com.satc.demo.domain.enums;

public enum Prioridade {
    BAIXA(1, "Baixa"),
    MEDIA(2, "Média"),
    ALTA(3, "Alta");

    private final Integer codigo;
    private final String descricao;

    Prioridade(Integer codigo, String descricao) { this.codigo = codigo; this.descricao = descricao; }
    public Integer getCodigo() { return codigo; }
    public String getDescricao() { return descricao; }

    public static Prioridade toEnum(Integer cod) {
        if (cod == null) return null;
        for (Prioridade p : values()) if (cod.equals(p.getCodigo())) return p;
        throw new IllegalArgumentException("Prioridade inválida: " + cod);
    }
    public static String getDescricaoPorCodigo(Integer cod) {
        Prioridade p = toEnum(cod);
        return (p != null) ? p.getDescricao() : "Código inválido";
    }
}
