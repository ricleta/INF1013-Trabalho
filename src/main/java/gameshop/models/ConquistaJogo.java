package gameshop.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ConquistaJogo {
    @JsonProperty("id_conquista") // Maps JSON field name to Java field
    private int idConquista;

    private String conquista; // From JSON "conquista"
    private String icone;     // From JSON "icone"
    private String dificuldade; // From JSON "dificuldade"

    public ConquistaJogo() {
    }

    public int getIdConquista() {
        return idConquista;
    }

    public void setIdConquista(int idConquista) {
        this.idConquista = idConquista;
    }

    public String getConquista() {
        return conquista;
    }

    public void setConquista(String conquista) {
        this.conquista = conquista;
    }

    public String getIcone() {
        return icone;
    }

    public void setIcone(String icone) {
        this.icone = icone;
    }

    public String getDificuldade() {
        return dificuldade;
    }

    public void setDificuldade(String dificuldade) {
        this.dificuldade = dificuldade;
    }

    @Override
    public String toString() {
        return "ConquistaJogo{" +
               "idConquista=" + idConquista +
               ", conquista='" + conquista + '\'' +
               ", icone='" + icone + '\'' +
               ", dificuldade='" + dificuldade + '\'' +
               '}';
    }
}