package gameshop.models.enums;

public enum EnumOS {
    WINDOWS("Windows"), MAC("Mac"), LINUX("Linux");

    private String nome;

    EnumOS(String nome) {
        this.nome = nome;
    }
    public String getNome() {
        return nome;
    }
}