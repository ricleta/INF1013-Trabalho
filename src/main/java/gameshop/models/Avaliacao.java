package gameshop.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Avaliacao {
    @JsonProperty("id_jogo") // Maps JSON "id_jogo" to idJogo
    private int idJogo;

    @JsonProperty("id_usuario") // Maps JSON "id_usuario" to idUsuario
    private int idUsuario;

    @JsonProperty("pontuacao") // Maps JSON "pontuacao" to nota
    private int nota;

    @JsonProperty("texto") // Maps JSON "texto" to comentario
    private String comentario;

    // Default constructor for Jackson deserialization
    public Avaliacao() {}

    public Avaliacao(int idJogo, int idUsuario, int nota, String comentario) {
        this.idJogo = idJogo;
        this.idUsuario = idUsuario;
        this.nota = nota;
        this.comentario = comentario;
    }

    public int getIdJogo() {
        return idJogo;
    }

    public void setIdJogo(int idJogo) {
        this.idJogo = idJogo;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        if (nota < 0 || nota > 5) {
            throw new IllegalArgumentException("A nota deve estar entre 0 e 5.");
        }
        this.nota = nota;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        if (comentario == null || comentario.trim().isEmpty()) {
            throw new IllegalArgumentException("O comentário não pode ser vazio.");
        }
        this.comentario = comentario;
    }

    @Override
    public String toString() {
        return "Avaliacao{" +
               "idJogo=" + idJogo +
               ", idUsuario=" + idUsuario +
               ", nota=" + nota +
               ", comentario='" + comentario + '\'' +
               '}';
    }
}