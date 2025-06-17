package gameshop.models.enums;

public enum EnumPlataforma {
    NINTENDO_SWITCH("Nintendo Switch"), WII_U("Wii U"), PS5("PS5"), XBOX("Xbox"), PC("PC"), PS4("PS4"), XBOX_ONE("Xbox One"), PS3("PS3"), XBOX_360("Xbox 360");

    private String nome;

    EnumPlataforma(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return nome;

    }
}