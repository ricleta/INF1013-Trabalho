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

    // Static factory method for Jackson deserialization from string name
    @JsonCreator
    public static EnumPlataforma fromNome(String nome) {
        for (EnumPlataforma plataforma : EnumPlataforma.values()) {
            if (plataforma.nome.equalsIgnoreCase(nome)) {
                return plataforma;
            }
        }
        throw new IllegalArgumentException("Plataforma '" + nome + "' não reconhecida.");
    }
}