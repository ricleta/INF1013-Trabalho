package gameshop.models;

import java.time.YearMonth;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Cartao {
    
    @JsonProperty("numero")
    private String numero;

    @JsonProperty("titular")
    private String titular;

    @JsonProperty("data_expiracao")
    private YearMonth validade;

    @JsonProperty("cvv")
    private String cvv;

    public Cartao() {
    }

    public Cartao(String numero, YearMonth validade, String cvv) {
        this.numero = numero;
        this.validade = validade;
        this.cvv = cvv;
    }

    public String getTitular() {
        return titular;
    }

    public String getNumero() {
        return numero;
    }

    // A biblioteca do Json automaticamente tenta pegar as variaveis de metodos get publicos
    // O JsonIgnore faz com que ele ignore o metodo e nao assuma que existe uma var SeguroNumero
    @JsonIgnore
    public String getSeguroNumero()
    {
        return "**** **** **** " + numero.substring(numero.length() - 4);
    }

    public YearMonth getValidade() {
        return validade;
    }

    public String getCvv() {
        return cvv;
    }
}
