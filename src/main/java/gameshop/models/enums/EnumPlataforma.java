package gameshop.models.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum EnumPlataforma {
    NINTENDO_SWITCH("Nintendo Switch"), WII_U("Wii U"), PS5("PS5"), XBOX("Xbox");

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