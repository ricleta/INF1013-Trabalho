package gameshop.models.enums;

public enum EnumPlataforma {
    NINTENDO_SWITCH("Nintendo Switch"), WII_U("Wii U"), PS5("PS5"), XBOX("Xbox");

    private String nome;
    EnumPlataforma(String nome) {
        this.nome = nome;
    }
    public String getNome() {
        return nome;
    }
}