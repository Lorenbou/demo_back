package br.com.satc.demo.domain.enums;

public enum Perfil {
    ADMIN(1, "Administrador"),
    GERENTE(2, "Gerente"),
    USUARIO(3, "Usuário");

    private final Integer codigo;
    private final String descricao;

    Perfil(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }
    public Integer getCodigo() { return codigo; }
    public String getDescricao() { return descricao; }

    public static Perfil toEnum(Integer cod) {
        if (cod == null) return null;
        for (Perfil p : values()) if (cod.equals(p.getCodigo())) return p;
        throw new IllegalArgumentException("Perfil inválido: " + cod);
    }
    public static String getDescricaoPorCodigo(Integer cod) {
        Perfil p = toEnum(cod);
        return (p != null) ? p.getDescricao() : "Código inválido";
    }
}
