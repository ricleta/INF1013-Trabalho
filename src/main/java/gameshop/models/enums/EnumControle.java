package gameshop.models.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum EnumControle {
    PS5("PS5"), XBOX("Xbox");

    private String nome;

    EnumControle(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    // Static factory method for Jackson deserialization from string name
    @JsonCreator
    public static EnumControle fromNome(String nome) {
        for (EnumControle controle : EnumControle.values()) {
            if (controle.nome.equalsIgnoreCase(nome)) {
                return controle;
            }
        }
        throw new IllegalArgumentException("Controle '" + nome + "' não reconhecido.");
    }
}