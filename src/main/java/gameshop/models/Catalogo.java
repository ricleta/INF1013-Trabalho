package gameshop.models;

import java.lang.reflect.Array;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Catalogo {
    @JsonProperty("id_evento")
    private int id;

    @JsonProperty("nome_evento")
    private String nomeEvento;

    @JsonProperty("jogos_oferecidos")
    private JogoCatalogo[] jogosOferecidos;

    public Catalogo() {
        // Construtor padrão
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getNomeEvento() {
        return nomeEvento;
    }

    public JogoCatalogo[] getJogosOferecidos() {
        ArrayList<JogoCatalogo> jogosVigentes = new ArrayList<>();

        for (JogoCatalogo jogo : jogosOferecidos) {
            if (jogo != null && jogo.isVigente()) {
                jogosVigentes.add(jogo);
            }
        }

        return jogosVigentes.toArray(new JogoCatalogo[0]);
    }

    public double getPrecoJogo(int idJogo) {
        for (JogoCatalogo jogo : jogosOferecidos) {
            if (jogo.getIdJogo() == idJogo && jogo.isVigente()) {
                return jogo.getPreco();
            }
        }
        
        return -1; // Retorna -1 se o jogo não for encontrado
    }
}
