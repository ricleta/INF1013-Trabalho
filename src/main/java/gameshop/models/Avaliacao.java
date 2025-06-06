package gameshop.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Avaliacao {

    @JsonProperty("id_jogo")
    private int idJogo;

    @JsonProperty("id_usuario")
    private int idUsuario;

    @JsonProperty("nota")
    private int nota;

    @JsonProperty("texto")
    private String comentario;

    public Avaliacao() {
        // Construtor padrão necessário para a serialização/deserialização
    }

    public Avaliacao(int idJogo, int idUsuario, int nota, String comentario) {
        this.idJogo = idJogo;
        this.idUsuario = idUsuario;
        this.nota = nota;
        this.comentario = comentario;
    }

    public int getIdJogo() {
        return idJogo;
    }

    public int getIdUsuario() {
        return idUsuario;
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
}