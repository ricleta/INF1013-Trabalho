package gameshop.models.enums;

public enum EnumControle {
    PS5("PS5"), XBOX("Xbox");

    private String nome;

    EnumControle(String nome) {
        this.nome = nome;
    }
    public String getNome() {
        return nome;
    }
}
