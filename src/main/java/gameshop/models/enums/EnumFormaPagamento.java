package gameshop.models.enums;

public enum EnumFormaPagamento {
    CARTAO_CREDITO("Cartão de Crédito"), CARTAO_DEBITO("Cartão de Débito"), PIX("PIX");

    private String forma;

    EnumFormaPagamento(String forma) {
        this.forma = forma;
    }

    public String getNome() {
        return forma;
    }

    @Override
    public String toString() {
        return forma;
    }
}
